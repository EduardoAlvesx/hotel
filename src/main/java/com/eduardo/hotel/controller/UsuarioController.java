package com.eduardo.hotel.controller;

import com.eduardo.hotel.dao.UsuarioDAO;
import com.eduardo.hotel.factory.ConnectionFactory;
import com.eduardo.hotel.model.UserDetails;
import com.eduardo.hotel.model.Usuario;

import java.sql.Connection;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    public UsuarioController() {
        Connection connection = new ConnectionFactory().getConnection();
        this.usuarioDAO = new UsuarioDAO(connection);
    }
    public void register(Usuario usuario) {
        this.usuarioDAO.register(usuario);
    }

    public UserDetails getUserByuUsername(String username) {
        return this.usuarioDAO.getUserByUsername(username);
    }
}
