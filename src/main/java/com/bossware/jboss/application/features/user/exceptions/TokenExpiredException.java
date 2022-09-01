package com.bossware.jboss.application.features.user.exceptions;

import com.bossware.jboss.core.exceptions.LocalizedExceptions;

public class TokenExpiredException extends LocalizedExceptions {
    public TokenExpiredException(String message) {
        super(message);
    }
}
