package com.eduardo.hotel.model;

import java.math.BigInteger;
import java.time.LocalDate;

public class Reserva {
    private BigInteger id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private Double valor;
    private String formaPagamento;

    public Reserva(LocalDate dataEntrada, LocalDate dataSaida, Double valor, String formaPagamento) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    public Reserva(BigInteger id, LocalDate dataEntrada, LocalDate dataSaida, Double valor, String formaPagamento) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }
    public Reserva(LocalDate dataEntrada) {
        if (dataEntrada != null) {
            this.dataEntrada = dataEntrada;
        }

    }

    public BigInteger getId() {
        return id;
    }

    public Reserva() {
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public Double getValor() {
        return valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
}
