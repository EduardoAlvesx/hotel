package com.eduardo.hotel.dao;

import com.eduardo.hotel.model.Reserva;

import java.sql.*;

public class ReservaDAO {
    private Connection connection;
    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }
    public void register(Reserva reserva) {
        var sql = "INSERT INTO RESERVAS (DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(reserva.getDataEntrada()));
            statement.setDate(2, Date.valueOf(reserva.getDataSaida()));
            statement.setDouble(3, reserva.getValor());
            statement.setString(4, reserva.getFormaPagamento());

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {

                while (resultSet.next()) {
                    reserva.setId(resultSet.getBigDecimal(1).toBigInteger());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
