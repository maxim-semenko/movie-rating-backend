package com.max.movierating.constant;

/**
 * The type API constant.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
public final class APIConstant {

    /**
     * Private constructor.
     */
    private APIConstant() {
    }

    /**
     * Request mapping for Authentication API.
     */
    public static final String AUTHENTICATION_API = "/api/v1/auth";
    /**
     * Request mapping for Baskets API.
     */
    public static final String BASKETS_API = "/api/v1/baskets";
    /**
     * Request mapping for Countries API {@link com.max.movierating.controller.CountryController}.
     */
    public static final String COUNTRIES_API = "/api/v1/countries";
    /**
     * Request mapping for Films API {@link com.max.movierating.controller.FilmController}.
     */
    public static final String FILMS_API = "/api/v1/films";
    /**
     * Request mapping for Countries API {@link com.max.movierating.controller.GenreController}.
     */
    public static final String GENRES_API = "/api/v1/genres";
    /**
     * Request mapping for Payment API {@link com.max.movierating.controller.PaymentController}.
     */
    public static final String PAYMENT_API = "/api/v1/payment";
    /**
     * Request mapping for Roles API {@link com.max.movierating.controller.RoleController}.
     */
    public static final String ROLES_API = "/api/v1/roles";
    /**
     * Request mapping for Users API.
     */
    public static final String USERS_API = "/api/v1/users";
    /**
     * Request mapping for Marks API.
     */
    public static final String MARKS_API = "/api/v1/marks";
    /**
     * Request mapping for Admin API.
     */
    public static final String ADMIN_API = "/api/v1/administration";
    /**
     * Request mapping for Mail API.
     */
    public static final String MAIL_API = "/api/v1/mail";
    /**
     * Request mapping for Purchase storage API.
     */
    public static final String PURCHASE_API = "/api/v1/purchases";
}
