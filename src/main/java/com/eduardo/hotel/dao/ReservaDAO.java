package com.eduardo.hotel.dao;

import com.eduardo.hotel.model.Reserva;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Reserva> getById(String id) {
        List<Reserva> reservas = new ArrayList<>();
        var sql = "SELECT * FROM RESERVAS WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var reservaId = resultSet.getBigDecimal(1);
                var dataEntrada = resultSet.getDate(2);
                var dataSaida = resultSet.getDate(3);
                var valor = resultSet.getDouble(4);
                var formaPagemento = resultSet.getString(5);
                Reserva reserva = new Reserva(reservaId.toBigInteger(), dataEntrada.toLocalDate(), dataSaida.toLocalDate(), valor,
                        formaPagemento);
                reservas.add(reserva);
            }

            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Date dataEntrada, Date dataSaida, Double valor, String formaPagamento, BigDecimal id) {
        var sql = "UPDATE RESERVAS SET DATA_ENTRADA = ?, DATA_SAIDA = ?, VALOR = ?, FORMA_PAGAMENTO = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, dataEntrada);
            statement.setDate(2, dataSaida);
            statement.setDouble(3, valor);
            statement.setString(4, formaPagamento);
            statement.setBigDecimal(5, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(BigInteger id) {
        var sql = "DELETE FROM RESERVAS WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBigDecimal(1, new BigDecimal(id));
            statement.executeUpdate();
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
