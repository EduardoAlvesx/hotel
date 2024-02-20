package com.eduardo.hotel.model;

public enum HotelUtil {
    VALOR(1000.00);
    private Double valorDia;
    HotelUtil(Double valorDia) {
        this.valorDia = valorDia;
    }

    public Double getValorDia() {
        return valorDia;
    }
}
