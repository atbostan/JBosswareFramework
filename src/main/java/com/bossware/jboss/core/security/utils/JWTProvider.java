package com.bossware.jboss.core.security.utils;

import com.bossware.jboss.application.features.user.security.UserPrincipal;
import com.bossware.jboss.persistance.repositories.AuthRepository;
import com.bossware.jboss.persistance.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class JWTProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthRepository authRepository;

//    private String generateJWTToken(UserPrincipal userPrincipal){
//        String[] claims =get
//    }


}
