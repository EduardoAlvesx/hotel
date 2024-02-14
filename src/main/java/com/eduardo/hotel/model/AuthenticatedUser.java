package com.eduardo.hotel.model;

public class AuthenticatedUser {
    private static Long id;
    private static String username;
    public static void saveUserSession(Usuario usuario) {
        id = usuario.getId();
        username = usuario.getUserName();
    }

    public static Long getId() {
        return id;
    }

    public static String getUsername() {
        return username;
    }
}
