package com.bossware.jboss.application.features.user.impl;

import com.bossware.jboss.application.base.ServiceGenericBase;
import com.bossware.jboss.application.features.user.dtos.UserRequestDto;
import com.bossware.jboss.application.features.user.dtos.UserResponseDto;
import com.bossware.jboss.core.mappers.UserSourceDestinationMapper;
import com.bossware.jboss.domain.entities.User;
import com.bossware.jboss.persistance.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements ServiceGenericBase<UserRequestDto , UserResponseDto> {

    UserSourceDestinationMapper mapper = Mappers.getMapper(UserSourceDestinationMapper.class);

    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity<UserResponseDto> create(UserRequestDto userRequestDto) {
        User user = mapper.reqToEntity(userRequestDto);
        User createdUser = userRepository.save(user);
        UserResponseDto res = mapper.entityToResp(createdUser);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponseDto> getEntityById(long id) {
        User user = userRepository.findById(id).get();
        UserResponseDto res = mapper.entityToResp(user);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> update(long id, UserRequestDto userRequestDto) {
        User user= userRepository.findById(id).get();
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
        User updatedUser = userRepository.save(user);
        UserResponseDto res = mapper.entityToResp(updatedUser);
        return  new ResponseEntity<>(res,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> delete(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<List<UserResponseDto>> getAll(int page, int limit) {
        page = page == 0 ? 0 : page;
        limit = limit == 0 ? 15 : limit;
        Pageable pageReq =  PageRequest.of(page,limit);
        List<User> userList = userRepository.findAll(pageReq).toList();
        List<UserResponseDto>  res = mapper.entityToRespList(userList);
        return  new ResponseEntity<>(res,HttpStatus.OK);

    }
}
