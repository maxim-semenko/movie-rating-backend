package com.max.movierating.constant;


/**
 * The type Errors constant.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
public class ErrorConstant {

    /**
     * Private constructor.
     */
    private ErrorConstant() {
    }

    // User's errors
    public static final String ERROR_INVALID_OLD_PASSWORD = "Invalid old password!";
    public static final String ERROR_INVALID_PASSWORD = "Invalid password!";
    public static final String USER_NOT_FOUND = "User was not found: ";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exist: ";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exist: ";

    // Film's errors

    // Genre's errors
    public static final String GENRE_NOT_FOUND = "Genre was not found!";
    public static final String ERROR_CANT_DELETE_GENRE = "Can't delete genre, because it used in another entity!";

    // Country's errors
    public static final String ERROR_CANT_DELETE_COUNTRY = "Can't delete country, because it used in another entity!";


    // Mark's errors
    public static final String MARK_NOT_FOUND = "Mark was not found: ";

}
