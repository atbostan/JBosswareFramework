package com.bossware.jboss.application.features.user.exceptions;

import com.bossware.jboss.core.exceptions.LocalizedExceptions;

public class UserNameExistException extends LocalizedExceptions {
    public UserNameExistException(String message) {
        super(message);
    }
}
