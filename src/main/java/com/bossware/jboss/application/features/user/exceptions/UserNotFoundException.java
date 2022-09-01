package com.bossware.jboss.application.features.user.exceptions;

import com.bossware.jboss.core.exceptions.LocalizedExceptions;

public class UserNotFoundException extends LocalizedExceptions {
    public UserNotFoundException(String messageKey) {
        super(messageKey);
    }
}
