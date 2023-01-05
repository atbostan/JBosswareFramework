package com.bossware.jboss.application.features.user.impl;

import com.bossware.jboss.application.base.ServiceGenericBase;
import com.bossware.jboss.application.features.user.constants.UserMessages;
import com.bossware.jboss.application.features.user.dtos.UserRequestDto;
import com.bossware.jboss.application.features.user.dtos.UserResponseDto;
import com.bossware.jboss.application.features.user.exceptions.EmailExistException;
import com.bossware.jboss.application.features.user.exceptions.UserNameExistException;
import com.bossware.jboss.application.features.user.security.UserPrincipal;
import com.bossware.jboss.application.features.user.services.UserLoginAttemptService;
import com.bossware.jboss.application.features.user.services.UserService;
import com.bossware.jboss.core.mappers.UserSourceDestinationMapper;
import com.bossware.jboss.domain.entities.User;
import com.bossware.jboss.persistance.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    UserSourceDestinationMapper mapper = Mappers.getMapper(UserSourceDestinationMapper.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    UserLoginAttemptService userLoginAttemptService;
    @Override
    public ResponseEntity<UserResponseDto> create(UserRequestDto userRequestDto) throws Exception {
        User userExistRecord = userRepository.findUserByEmail(userRequestDto.getEmail());
        if(userExistRecord!=null){
            throw new EmailExistException(UserMessages.USER_ALREADY_EXIST);
        }
        String pass = userRequestDto.getPassword();
        userRequestDto.setPassword(encoder.encode(pass));
        User user = mapper.reqToEntity(userRequestDto);
        user.setCreationTime();
        user.setActive(true);
        user.setNotLocked(true);
        User createdUser = userRepository.save(user);
        UserResponseDto res = mapper.entityToResp(createdUser);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<UserResponseDto> getEntityById(long id) {
        User User = userRepository.findById(id).get();
        UserResponseDto res = mapper.entityToResp(User);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> update(long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).get();
        if(userRequestDto.getUserName()!=null) {
            user.setUserName(userRequestDto.getUserName());
        }
        if(userRequestDto.getEmail()!=null) {
            user.setEmail(userRequestDto.getEmail());
        }
        if(userRequestDto.getName()!=null) {
            user.setName(userRequestDto.getName());
        }
        if(userRequestDto.getLastName()!=null) {
            user.setLastName(userRequestDto.getLastName());
        }
        if(userRequestDto.getPassword()!=null) {
            user.setPassword(userRequestDto.getPassword());
        }
        user.setModificationTime();
        User updatedUser = userRepository.save(user);
        UserResponseDto res = mapper.entityToResp(updatedUser);
        return  new ResponseEntity<>(res,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> delete(long id) {
        User User = userRepository.findById(id).get();
        userRepository.delete(User);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<List<UserResponseDto>> getAll(int page, int limit) {
        page = page == 0 ? 0 : page;
        limit = limit == 0 ? 15 : limit;
        Pageable pageReq =  PageRequest.of(page,limit);
        List<User> UserList = userRepository.findAll(pageReq).toList();
        List<UserResponseDto>  res = mapper.entityToRespList(UserList);
        return  new ResponseEntity<>(res,HttpStatus.OK);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User User = userRepository.findUserByUserName(username);
        if(User ==null){
            throw new UsernameNotFoundException(UserMessages.USER_NOT_FOUND);
        }else{
            try {
                validateLoggingAttempt(User);
            }catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

            User.setLastLoginDateDisplay(User.getLastLoginDate());
            User.setLastLoginDate(new Date());
            userRepository.save(User);
            UserPrincipal userPrincipal = new UserPrincipal(User);
            return  userPrincipal;

        }

    }


    private void validateLoggingAttempt(User User) throws ExecutionException {
        if(User.isNotLocked()){
            if(userLoginAttemptService.checkIfMaxAttempt(User.getUserName())){
                User.setNotLocked(false);
            }else{
                User.setNotLocked(true);
            }
        }else {
            userLoginAttemptService.evict(User.getUserName());
        }
    }


    @Override
    public ResponseEntity<UserResponseDto> findUserByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        UserResponseDto res = mapper.entityToResp(user);
        return  new ResponseEntity<>(res,HttpStatus.OK);    }

    @Override
    public ResponseEntity<UserResponseDto> findUserByUserEmail(String email) {
        User user = userRepository.findUserByUserName(email);
        UserResponseDto res = mapper.entityToResp(user);
        return  new ResponseEntity<>(res,HttpStatus.OK);       }
}
