package com.eduardo.hotel;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.eduardo.hotel.view.Login;
import com.eduardo.hotel.view.MainMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        new Login();


    }
}
