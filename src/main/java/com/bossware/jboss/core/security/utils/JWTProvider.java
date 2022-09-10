package com.bossware.jboss.core.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bossware.jboss.application.features.user.security.UserPrincipal;
import com.bossware.jboss.core.security.constants.SecurityConstants;
import com.bossware.jboss.persistance.repositories.AuthRepository;
import com.bossware.jboss.persistance.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private String expirationTime;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthRepository authRepository;


    public List<GrantedAuthority> getAuthorities (String token){
        String[] claimsOfUser = getClaimsFromToken(token);
        return Arrays.stream(claimsOfUser).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication(String userName , List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userName,null,authorities);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }

    private String generateJWTToken(UserPrincipal userPrincipal){
        String[] claims =getClaimsFromUser(userPrincipal);
        return JWT.create()
                .withIssuer(SecurityConstants.ISSUER)
                .withAudience(SecurityConstants.AUDIENCE)
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(SecurityConstants.AUTHORITIES,claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+expirationTime))
                .sign(Algorithm.HMAC512(secret));
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        List<String> authList =new ArrayList<>();
        for(GrantedAuthority grantedAuthority : userPrincipal.getAuthorities()){
            authList.add(grantedAuthority.getAuthority());
        }
        return authList.toArray(new String[0]);
    }

    private String[] getClaimsFromToken(String token){
        JWTVerifier verifier = getJWTVerifier();
        return  verifier.verify(token).getClaim(SecurityConstants.AUTHORITIES).asArray(String.class);

    }

    public String getSubject(String token){
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    public boolean isTokenValid(String userName,String token){
        JWTVerifier verifier = getJWTVerifier();
        return  StringUtils.hasText(userName) && isTokenVerified(verifier,token);
    }
    private boolean isTokenVerified(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm =Algorithm.HMAC512(secret);
            verifier =JWT.require(algorithm).withIssuer(SecurityConstants.ISSUER).build();
        }catch (JWTVerificationException ex){
            throw  new JWTVerificationException(SecurityConstants.TOKEN_CANNOT_BE_VERIFIED);
        }
        return  verifier;

    }


}
