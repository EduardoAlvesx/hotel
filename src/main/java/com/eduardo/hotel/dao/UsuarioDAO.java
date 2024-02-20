package com.eduardo.hotel.dao;

import com.eduardo.hotel.model.UserDetails;
import com.eduardo.hotel.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public UserDetails getUserByUsername(String name) {
        var sql = "select * from usuario where username = ?";
        UserDetails userDetails = new UserDetails();
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var userName = resultSet.getString("username");
                var password = resultSet.getString("pass_word");
                userDetails.setId(id);
                userDetails.setUsername(userName);
                userDetails.setPassword(password);
            }

            return userDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
