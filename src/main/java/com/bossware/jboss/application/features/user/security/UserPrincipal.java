package com.bossware.jboss.application.features.user.security;

import com.bossware.jboss.domain.entities.Auth;
import com.bossware.jboss.domain.entities.Role;
import com.bossware.jboss.domain.entities.User;
import com.bossware.jboss.persistance.repositories.AuthRepository;
import com.bossware.jboss.persistance.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPrincipal implements UserDetails {

    private User user;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthRepository authRepository;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
        Role[] roles = roleRepository.findAllByUserId(user.getId());
        List<String> authNames = new ArrayList<>();

        if ( roles !=null ){
            for (Role role: roles) {
                Auth[] authsByRole = authRepository.findAllByRoleId(role.getId());
                authNames.add(Arrays.stream(authsByRole).map(a->a.getAuthName()).toString());
            }
        }

        if (authNames!=null){
            return authNames.stream().map(SimpleGrantedAuthority::new ).collect(Collectors.toList());

        }else {
            return  null;
        }

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }
}
