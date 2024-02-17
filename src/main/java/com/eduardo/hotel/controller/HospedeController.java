package com.eduardo.hotel.controller;

import com.eduardo.hotel.dao.HospedDAO;
import com.eduardo.hotel.factory.ConnectionFactory;
import com.eduardo.hotel.model.Hospede;

import java.sql.Connection;
import java.util.List;

public class HospedeController {
    private HospedDAO hospedDAO;
    public HospedeController() {
        Connection connection = new ConnectionFactory().getConnection();
        this.hospedDAO = new HospedDAO(connection);
    }
    public void register(Hospede hospede) {
        hospedDAO.register(hospede);
    }

    public List<Hospede> getAllBySobrenome(String sobrenome) {
        return hospedDAO.getAllBySobrenome(sobrenome);
    }
}
