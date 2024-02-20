package com.eduardo.hotel.dao;

import com.eduardo.hotel.model.Hospede;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedDAO {
    private Connection connection;
    public HospedDAO(Connection connection) {
        this.connection = connection;
    }
    public void register(Hospede hospede) {
        var sql = "INSERT INTO HOSPEDES(NOME, SOBRENOME, DATA_NASCIMENTO, NACIONALIDADE, TELEFONE, RESERVA_ID, " +
                "USUARIO_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hospede.getNome());
            statement.setString(2, hospede.getSobrenome());
            statement.setDate(3, Date.valueOf(hospede.getDataNascimento()));
            statement.setString(4, hospede.getNacionalidade());
            statement.setString(5, hospede.getTelefone());
            statement.setBigDecimal(6, new BigDecimal(hospede.getReservaId()));
            statement.setBigDecimal(7, new BigDecimal(hospede.getUsuarioId()));
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Hospede> getAllBySobrenome(String sobrenome) {
        List<Hospede> hospedes = new ArrayList<>();
        var sql = "SELECT * FROM HOSPEDES WHERE SOBRENOME = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sobrenome);
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    var id = resultSet.getBigDecimal(1);
                    var nome = resultSet.getString(2);
                    var sobreN = resultSet.getString(3);
                    var nascimento = resultSet.getDate(4);
                    var nacionalidade = resultSet.getString(5);
                    var telefone = resultSet.getString(6);
                    var reservaId = resultSet.getBigDecimal(7);
                    var usuarioId = resultSet.getBigDecimal(8);
                    Hospede hospede = new Hospede(id.toBigInteger(), nome, sobreN, nascimento.toLocalDate(),
                            nacionalidade, telefone,
                            reservaId.toBigInteger()
                            , usuarioId.toBigInteger());

                    hospedes.add(hospede);
                }

            return hospedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
