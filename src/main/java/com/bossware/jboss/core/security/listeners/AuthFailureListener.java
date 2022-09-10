package com.bossware.jboss.core.security.listeners;

import com.bossware.jboss.application.features.user.services.UserLoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class AuthFailureListener {

    @Autowired
    private UserLoginAttemptService loggingAttemptService;

    @EventListener
    public  void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) throws ExecutionException {
        Object principal =event.getAuthentication().getPrincipal();
        if(principal instanceof  String){
            String userName= (String) principal;
            loggingAttemptService.add(userName);
        }
    }
}
