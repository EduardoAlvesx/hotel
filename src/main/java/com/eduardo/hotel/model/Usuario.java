package com.eduardo.hotel.model;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Usuario {
    private Long id;
    private String userName;
    private String password;
    public Usuario(String userName, String password) {
        this.userName = userName;
        this.password = hashPassword(password);
    }
    public Usuario(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
    public Usuario() {

    }
    public String hashPassword(String password) {
         return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
