package com.eduardo.hotel.controller;

import com.eduardo.hotel.dao.HospedDAO;
import com.eduardo.hotel.factory.ConnectionFactory;
import com.eduardo.hotel.model.Hospede;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
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

    public void update(BigDecimal id, String nome, String sobrenome, Date dataNascimentom, String nacionalidade, String telefore) {
        this.hospedDAO.update(id, nome, sobrenome, dataNascimentom, nacionalidade, telefore);
    }

    public void delete(BigInteger id) {
        this.hospedDAO.delete(id);
    }
}
