package com.bossware.jboss.application.features.user.constants;

public class UserMessages {

    //region User Security Constants
    public static final String ACCOUNT_LOCKED = "Your account has been locked." +
            "Please contact your administrator.";

    public static final String ACCOUNT_DISABLED = "Your account has been disabled" +
            "Please contact your administrator.";

    public static final String INCORRECT_CREDENTIALS = "User name or password incorrect" +
            "Please try again.";

    public static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint." +
            "Please send a '%s' request.";

    public static final String NOT_ENOUGH_PERMISSION = "You don't have enough permission.";

    public static final String FORBIDDEN_MESSAGE = "You need to login to access this page";

    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";


    //endregion

    //region User Data Exceptions

    public static final String USER_NOT_FOUND = "User not found by specified information by given you";
    //endregion


}
