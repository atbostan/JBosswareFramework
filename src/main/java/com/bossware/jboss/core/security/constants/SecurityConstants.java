package com.bossware.jboss.core.security.constants;

public class SecurityConstants {
    public static final String[] PUBLIC_URLS = {"/roles"};
    public static final String SIGN_UP_URLS = "/users";
    public static final String[] SWAGGER_URLS = {"/v2/api-docs","/configuration/**","/swagger*/**","/webjars/**"};

    public static final String ISSUER = "JBoss Framework";

    public static final String AUDIENCE = "User Management Portal";

    public static final String AUTHORITIES = "authorities";

    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token Can Not Be Verified";

    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final long TOKEN_EXPIRE_TIME = 84000000;



}
