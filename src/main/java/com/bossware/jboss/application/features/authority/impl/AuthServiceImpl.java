package com.bossware.jboss.application.features.authority.impl;

import com.bossware.jboss.application.base.ServiceGenericBase;
import com.bossware.jboss.application.features.authority.dtos.AuthRequestDto;
import com.bossware.jboss.application.features.authority.dtos.AuthResponseDto;
import com.bossware.jboss.core.mappers.AuthSourceDestinationMapper;
import com.bossware.jboss.domain.entities.Auth;
import com.bossware.jboss.domain.entities.Role;
import com.bossware.jboss.persistance.repositories.AuthRepository;
import com.bossware.jboss.persistance.repositories.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements ServiceGenericBase<AuthRequestDto, AuthResponseDto> {

    AuthSourceDestinationMapper mapper = Mappers.getMapper(AuthSourceDestinationMapper.class);

    @Autowired
    AuthRepository authRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<AuthResponseDto> create(AuthRequestDto authRequestDto) {
        Auth auth = mapper.reqToEntity(authRequestDto);
        Auth createdAuth = authRepository.save(auth);
        AuthResponseDto res = mapper.entityToResp(createdAuth);
        return new ResponseEntity<>(res, HttpStatus.CREATED);    }

    @Override
    public ResponseEntity<AuthResponseDto> getEntityById(long id) {
        Auth auth = authRepository.findById(id).get();
        AuthResponseDto res = mapper.entityToResp(auth);
        return new ResponseEntity<>(res,HttpStatus.OK);    }

    @Override
    public ResponseEntity<AuthResponseDto> update(long id, AuthRequestDto authRequestDto) {
        Auth auth= authRepository.findById(id).get();
        Role role = roleRepository.findById(authRequestDto.getRoleId()).get();
        if(authRequestDto.getAuthName()!=null) {
            auth.setAuthName(authRequestDto.getAuthName());
        }

        if(authRequestDto.getRoleId()!=0 &&role!=null) {
            auth.setRole(role);
        }

        Auth updatedAuth = authRepository.save(auth);
        AuthResponseDto res = mapper.entityToResp(updatedAuth);
        return  new ResponseEntity<>(res,HttpStatus.OK);        }

    @Override
    public ResponseEntity<AuthResponseDto> delete(long id) {
        Auth auth = authRepository.findById(id).get();
        authRepository.delete(auth);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<AuthResponseDto>> getAll(int page, int limit) {
        page = page == 0 ? 0 : page;
        limit = limit == 0 ? 15 : limit;
        Pageable pageReq =  PageRequest.of(page,limit);
        List<Auth> authList = authRepository.findAll(pageReq).toList();
        List<AuthResponseDto>  res = mapper.entityToRespList(authList);
        return  new ResponseEntity<>(res,HttpStatus.OK);
    }
}
