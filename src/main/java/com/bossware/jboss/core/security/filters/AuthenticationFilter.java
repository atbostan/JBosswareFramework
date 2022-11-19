package com.bossware.jboss.core.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bossware.jboss.application.features.role.services.RoleService;
import com.bossware.jboss.application.features.user.security.UserPrincipal;
import com.bossware.jboss.application.features.user.services.UserService;
import com.bossware.jboss.core.security.constants.SecurityConstants;
import com.bossware.jboss.core.security.models.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        try {


            UserLoginRequest creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserLoginRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        UserPrincipal principal =(UserPrincipal) auth.getPrincipal();
        String userName = principal.getUsername();

//        String token = generateJWTToken(principal);
//        UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl");
//        UserDto userDto = userService.getUser(userName);
//
//        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
//        res.addHeader("UserID", userDto.getUserId());

    }



    private String generateJWTToken(UserPrincipal userPrincipal){
        String[] claims =getClaimsFromUser(userPrincipal);
        return JWT.create()
                .withIssuer(SecurityConstants.ISSUER)
                .withAudience(SecurityConstants.AUDIENCE)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(SecurityConstants.AUTHORITIES,claims)
                //.withExpiresAt(new Date(System.currentTimeMillis()+expirationTime))
                .sign(Algorithm.HMAC512("secret"));
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        List<String> authList =new ArrayList<>();
        for(GrantedAuthority grantedAuthority : userPrincipal.getAuthorities()){
            authList.add(grantedAuthority.getAuthority());
        }
        return authList.toArray(new String[0]);
    }
}
