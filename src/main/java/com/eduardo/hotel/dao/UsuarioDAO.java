package com.eduardo.hotel.dao;

import com.eduardo.hotel.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    public void register(Usuario usuario) {
        var sql = "insert into usuario (username, pass_word) values (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getUserName());
            statement.setString(2, usuario.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {
        var sql = "select * from usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var username = resultSet.getString("username");
                var password = resultSet.getString("pass_word");
                Usuario usuario = new Usuario(id, username, password);
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
