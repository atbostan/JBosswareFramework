package com.bossware.jboss.core.security.utils;

import com.bossware.jboss.application.features.user.constants.UserMessages;
import com.bossware.jboss.core.http.models.HttpResponseModel;
import com.bossware.jboss.core.security.constants.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JWTAuthEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
            throws IOException {
        HttpResponseModel httResponse = new HttpResponseModel(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN,HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(), UserMessages.FORBIDDEN_MESSAGE);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        OutputStream stream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(stream,httResponse);
        stream.flush();

    }
}
