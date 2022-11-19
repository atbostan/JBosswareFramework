package com.bossware.jboss.application.features.role.services;

import com.bossware.jboss.application.base.ServiceGenericBase;
import com.bossware.jboss.application.features.role.dtos.RoleRequestDto;
import com.bossware.jboss.application.features.role.dtos.RoleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService extends ServiceGenericBase<RoleRequestDto, RoleResponseDto> {
    ResponseEntity<List<RoleResponseDto>> findRolesByUserName(String userName);
    ResponseEntity<List<RoleResponseDto>> findRolesByEmail(String userEmail);
}
