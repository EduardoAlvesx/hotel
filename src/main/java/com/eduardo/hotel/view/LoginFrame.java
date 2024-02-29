package com.eduardo.hotel.view;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.eduardo.hotel.controller.UsuarioController;
import com.eduardo.hotel.model.AuthenticatedUser;
import com.eduardo.hotel.model.UserDetails;
import com.eduardo.hotel.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField passwordField, userField;
    private JButton loginButton, registerButton;
    private JLabel passwordLabel, userLabel;
    private UsuarioController usuarioController;
    private Usuario usuario;
    public LoginFrame() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        passwordField = new JTextField();
        passwordField.setBounds(280, 180, 140, 25);
        add(passwordField);

        userField = new JTextField();
        userField.setBounds(280, 150, 140, 25);
        userField.setFont(new Font("Roboto", Font.PLAIN, 16));
        add(userField);

        userLabel = new JLabel("USUÁRIO");
        userLabel.setBounds(440, 150, 80, 20);
        userLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        add(userLabel);

        passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(440, 180, 120, 20);
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        add(passwordLabel);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(280, 220, 120, 25);
        loginButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        add(loginButton);

        registerButton = new JButton("REGISTER");
        registerButton.setBounds(420, 220, 120, 25);
        registerButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        add(registerButton);

        registerButton.addActionListener(e -> register());
        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        usuarioController = new UsuarioController();
        var usuario = usuarioController.getUserByuUsername(userField.getText());
        var userFieldIsblank = userField.getText().isBlank();
        var passwordFieldIsBlank = passwordField.getText().isBlank();

        if (userFieldIsblank || passwordFieldIsBlank) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar vazios");
        } else {
            if (usuario.getUsername() == null || !isVerified(passwordField.getText(), usuario)) {
                JOptionPane.showMessageDialog(null, "Acesso negado");
            } else {
                JOptionPane.showMessageDialog(null, "Usuário %s logado com sucesso".formatted(userField.getText()));
                AuthenticatedUser.saveUserSession(usuario);
                EventQueue.invokeLater(() -> {
                    dispose();
                    UserMenuFrame userMenuFrame = new UserMenuFrame();
                    userMenuFrame.setVisible(true);
                });
            }
        }
    }

    private boolean isVerified(String password, UserDetails usuario) {
        return BCrypt.verifyer().verify(password.toCharArray(), usuario.getPassword()).verified;
    }


    private void register() {
        if (userField.getText().isBlank() || passwordField.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Os campos não podem estar vazios");

        } else if (usernameExists()) {
            JOptionPane.showMessageDialog(null, "Nome de usuário já existe escolha um novo");
        } else {
            usuario = new Usuario(userField.getText(), passwordField.getText());
            usuarioController = new UsuarioController();
            usuarioController.register(usuario);
            JOptionPane.showMessageDialog(null, "Usuario %s cadastrado com sucesso".formatted(usuario.getUserName()));
        }
    }

    private boolean usernameExists() {
        usuarioController = new UsuarioController();
        var usuario = usuarioController.getUserByuUsername(userField.getText());
        return usuario.getUsername() != null;
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
