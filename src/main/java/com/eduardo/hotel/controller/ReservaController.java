package com.eduardo.hotel.controller;

import com.eduardo.hotel.dao.ReservaDAO;
import com.eduardo.hotel.factory.ConnectionFactory;
import com.eduardo.hotel.model.Reserva;

import java.sql.Connection;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;
    public ReservaController() {
        Connection connection = new ConnectionFactory().getConnection();
        reservaDAO = new ReservaDAO(connection);
    }
    public void register(Reserva reserva) {
        reservaDAO.register(reserva);
    }
    public List<Reserva> getById(String id) {
        return reservaDAO.getById(id);
    }
}
