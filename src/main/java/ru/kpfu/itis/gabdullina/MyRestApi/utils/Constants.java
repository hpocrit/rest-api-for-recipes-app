package ru.kpfu.itis.gabdullina.MyRestApi.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String USERNAME_NOT_FOUND = "User not found";
    public static final String ENTER_USERNAME = "Enter username!!";
    public static final String ENTER_PASSWORD = "Enter password!!";
    public static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,30}";
    public static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{4,30}";
    public static final String IMAGE_PATTERN = "/^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$/\n";
    public static final String USER = "User";
    public static final String RECIPE = "Recipe";
    public static final String PASSWORDS_NOT_MATCH = "Passwords are not matching";
    public static final String USER_EXIST = "User with this username is already exist";
    public static final String INCORRECT_USERNAME = "Incorrect username";
    public static final String INCORRECT_PASSWORD = "Incorrect password";
    public static final String SECURITY_SWAGGER = "bearerAuth";
}
