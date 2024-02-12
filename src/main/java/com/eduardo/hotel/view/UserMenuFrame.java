package com.eduardo.hotel.view;

import com.eduardo.hotel.model.AuthenticatedUser;
import com.eduardo.hotel.model.Usuario;

import javax.swing.*;

public class UserMenuFrame extends JFrame {
    private JLabel userLabel;
    private AuthenticatedUser authenticatedUser;
    private Usuario usuario;
    public UserMenuFrame() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        userLabel = new JLabel();
        userLabel.setText("Bem vindo %s id: %d".formatted(AuthenticatedUser.getUsername(), AuthenticatedUser.getId()));
        userLabel.setBounds(100, 50, 140, 30);
        add(userLabel);
    }
}
