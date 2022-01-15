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
    public static final String USER_NOT_FOUND = "User was not found!";

    // Film's errors

    // Genre's errors
    public static final String GENRE_NOT_FOUND = "Genre was not found!";
    public static final String ERROR_CANT_DELETE_GENRE = "Can't delete genre, because it used in another entity!";

    // Country's errors

}
