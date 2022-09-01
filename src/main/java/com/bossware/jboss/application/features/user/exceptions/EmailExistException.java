package com.bossware.jboss.application.features.user.exceptions;

import com.bossware.jboss.core.exceptions.LocalizedExceptions;

public class EmailExistException extends LocalizedExceptions {
    public EmailExistException(String message) {
        super(message);
    }
}