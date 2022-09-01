package com.bossware.jboss.core.exceptions.localizations;

import java.util.Locale;
import java.util.ResourceBundle;

public class ExceptionMessageBundler {
    public static String getMessageForLocale(String messageKey, Locale locale) {
        return ResourceBundle.getBundle("exceptionMessages", locale)
                .getString(messageKey);
    }
}
