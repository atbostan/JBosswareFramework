package com.bossware.jboss.application.features.user.services;

import com.bossware.jboss.application.base.ServiceGenericBase;
import com.bossware.jboss.application.features.user.dtos.UserRequestDto;
import com.bossware.jboss.application.features.user.dtos.UserResponseDto;
import org.springframework.http.ResponseEntity;


public interface UserService extends ServiceGenericBase<UserRequestDto, UserResponseDto> {

    ResponseEntity<UserResponseDto> findUserByUserName(String userName);
    ResponseEntity<UserResponseDto> findUserByUserEmail(String email);
}
