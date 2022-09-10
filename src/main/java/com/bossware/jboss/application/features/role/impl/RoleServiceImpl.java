package com.bossware.jboss.application.features.role.impl;

import com.bossware.jboss.application.base.ServiceGenericBase;
import com.bossware.jboss.application.features.role.dtos.RoleRequestDto;
import com.bossware.jboss.application.features.role.dtos.RoleResponseDto;
import com.bossware.jboss.core.mappers.RoleSourceDestinationMapper;
import com.bossware.jboss.domain.entities.Role;
import com.bossware.jboss.domain.entities.User;
import com.bossware.jboss.persistance.repositories.RoleRepository;
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
public class RoleServiceImpl implements ServiceGenericBase<RoleRequestDto , RoleResponseDto> {

    RoleSourceDestinationMapper mapper = Mappers.getMapper(RoleSourceDestinationMapper.class);

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<RoleResponseDto> create(RoleRequestDto roleRequestDto) {
        Role role = mapper.reqToEntity(roleRequestDto);
        Role createdRole = roleRepository.save(role);
        RoleResponseDto res = mapper.entityToResp(createdRole);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RoleResponseDto> getEntityById(long id) {
        Role role = roleRepository.findById(id).get();
        RoleResponseDto res = mapper.entityToResp(role);
        return new ResponseEntity<>(res,HttpStatus.OK);    }

    @Override
    public ResponseEntity<RoleResponseDto> update(long id, RoleRequestDto roleRequestDto) {
        Role role= roleRepository.findById(id).get();
        User User = userRepository.findById(roleRequestDto.getUserId()).get();
        if(roleRequestDto.getRoleName()!=null) {
            role.setRoleName(roleRequestDto.getRoleName());
        }

        if(roleRequestDto.getUserId()!=0 && User !=null) {
            role.setUser(User);
        }

        Role updatedRole = roleRepository.save(role);
        RoleResponseDto res = mapper.entityToResp(updatedRole);
        return  new ResponseEntity<>(res,HttpStatus.OK);    }

    @Override
    public ResponseEntity delete(long id) {
        Role role = roleRepository.findById(id).get();
        roleRepository.delete(role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);    }

    @Override
    public ResponseEntity<List<RoleResponseDto>> getAll(int page, int limit) {
        page = page == 0 ? 0 : page;
        limit = limit == 0 ? 15 : limit;
        Pageable pageReq =  PageRequest.of(page,limit);
        List<Role> roleList = roleRepository.findAll(pageReq).toList();
        List<RoleResponseDto>  res = mapper.entityToRespList(roleList);
        return  new ResponseEntity<>(res,HttpStatus.OK);
    }
}
