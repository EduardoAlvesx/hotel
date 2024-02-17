package com.eduardo.hotel.model;

import java.math.BigInteger;
import java.time.LocalDate;

public class Hospede {
    private BigInteger id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String nacionalidade;
    private String telefone;
    private BigInteger reservaId;
    private BigInteger usuarioId;

    public Hospede(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone,
                   BigInteger reservaId, BigInteger usuarioId) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.reservaId = reservaId;
        this.usuarioId = usuarioId;
    }

    public Hospede(BigInteger id, String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone, BigInteger reservaId, BigInteger usuarioId) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.reservaId = reservaId;
        this.usuarioId = usuarioId;
    }

    public Hospede() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setReservaId(BigInteger reservaId) {
        this.reservaId = reservaId;
    }

    public void setUsuarioId(BigInteger usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public BigInteger getReservaId() {
        return reservaId;
    }

    public BigInteger getUsuarioId() {
        return usuarioId;
    }

    public BigInteger getId() {
        return id;
    }
}
