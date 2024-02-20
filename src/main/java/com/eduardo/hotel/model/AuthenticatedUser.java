package com.eduardo.hotel.model;

public class AuthenticatedUser {
    private static Long id;
    private static String username;
    public static void saveUserSession(UserDetails userDetails) {
        id = userDetails.getId();
        username = userDetails.getUsername();
    }

    public static Long getId() {
        return id;
    }

    public static String getUsername() {
        return username;
    }
}
