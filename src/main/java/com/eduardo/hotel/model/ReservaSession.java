package com.eduardo.hotel.model;

import java.math.BigInteger;

public class ReservaSession {
    private static BigInteger id;

    public static void setId(BigInteger id) {
        ReservaSession.id = id;
    }

    public static BigInteger getId() {
        return id;
    }
}
