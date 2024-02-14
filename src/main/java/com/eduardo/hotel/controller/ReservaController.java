package com.eduardo.hotel.controller;

import com.eduardo.hotel.dao.ReservaDAO;
import com.eduardo.hotel.factory.ConnectionFactory;
import com.eduardo.hotel.model.Reserva;

import java.sql.Connection;

public class ReservaController {
    private ReservaDAO reservaDAO;
    public ReservaController() {
        Connection connection = new ConnectionFactory().getConnection();
        reservaDAO = new ReservaDAO(connection);
    }
    public void register(Reserva reserva) {
        reservaDAO.register(reserva);
    }
}
