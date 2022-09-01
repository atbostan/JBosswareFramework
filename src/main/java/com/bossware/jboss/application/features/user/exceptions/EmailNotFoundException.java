package com.bossware.jboss.application.features.user.exceptions;

import com.bossware.jboss.core.exceptions.LocalizedExceptions;

public class EmailNotFoundException extends LocalizedExceptions {
    public EmailNotFoundException(String message) {
        super(message);
    }
}
