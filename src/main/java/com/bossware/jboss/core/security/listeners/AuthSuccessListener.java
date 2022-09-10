package com.bossware.jboss.core.security.listeners;

import com.bossware.jboss.application.features.user.security.UserPrincipal;
import com.bossware.jboss.application.features.user.services.UserLoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessListener {
    @Autowired
    private UserLoginAttemptService loggingAttemptService;

    @EventListener
    public  void onAuthenticationSuccess(AuthenticationSuccessEvent event){
        Object principal = event.getAuthentication().getPrincipal();
        if(principal instanceof UserPrincipal){
            UserPrincipal user = (UserPrincipal)principal;
            loggingAttemptService.evict(user.getUsername());
        }
    }
}
