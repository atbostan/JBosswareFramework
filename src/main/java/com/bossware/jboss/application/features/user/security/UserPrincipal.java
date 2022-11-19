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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {



    private User UserEntity;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthRepository authRepository;

    public UserPrincipal(User UserEntity) {
        this.UserEntity = UserEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
//        List<Role> roles = roleRepository.findROlesByUserEmail(UserEntity.getEmail());
        List<String> authNames = new ArrayList<>();

//        if ( roles !=null ){
//            for (Role role: roles) {
//                List<Auth> authsByRole = authRepository.findAllByRoleId(role.getId());
//                for (Auth auth:authsByRole) {
//                    authNames.add(auth.getAuthName());
//                }
//            }
//        }

        if (authNames!=null){
            return authNames.stream().map(SimpleGrantedAuthority::new ).collect(Collectors.toList());

        }else {
            return  null;
        }

    }

    @Override
    public String getPassword() {
        return UserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return UserEntity.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserEntity.isNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserEntity.isActive();
    }
}
