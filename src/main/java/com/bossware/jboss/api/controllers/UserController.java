package com.bossware.jboss.api.controllers;

import com.bossware.jboss.api.controllers.base.JBossControllerBase;
import com.bossware.jboss.application.features.user.dtos.UserRequestDto;
import com.bossware.jboss.application.features.user.dtos.UserResponseDto;
import com.bossware.jboss.application.features.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends JBossControllerBase {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) throws Exception {
           return userService.create(userRequestDto);
    }

}
