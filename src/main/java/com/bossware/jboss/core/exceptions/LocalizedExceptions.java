package com.bossware.jboss.core.exceptions;

import com.bossware.jboss.core.exceptions.localizations.ExceptionMessageBundler;

import java.util.Locale;

public class LocalizedExceptions extends  Exception{
    private final String messageKey;
    private final Locale locale;

    public  LocalizedExceptions(String messageKey){
        this(messageKey,Locale.getDefault());
    }
    public LocalizedExceptions(String messageKey, Locale locale) {
        this.messageKey = messageKey;
        this.locale = locale;
    }

    public String getLocalizedMessage() {
        return ExceptionMessageBundler.getMessageForLocale(messageKey, locale);
    }
}
