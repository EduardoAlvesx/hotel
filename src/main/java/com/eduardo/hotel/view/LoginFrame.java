package com.eduardo.hotel.view;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.eduardo.hotel.controller.UsuarioController;
import com.eduardo.hotel.model.AuthenticatedUser;
import com.eduardo.hotel.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField passwordField;
    private JTextField userField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel passwordLabel;
    private JLabel userLabel;
    private UsuarioController usuarioController;
    private Usuario usuario;
    public LoginFrame() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        passwordField = new JTextField();
        passwordField.setBounds(140, 80, 140, 20);
        add(passwordField);

        userField = new JTextField();
        userField.setBounds(140, 50, 140, 20);
        add(userField);

        userLabel = new JLabel("USUÁRIO");
        userLabel.setBounds(300, 50, 80, 20);
        add(userLabel);

        passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(300, 80, 80, 20);
        add(passwordLabel);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(140, 120, 80, 20);
        add(loginButton);

        registerButton = new JButton("REGISTER");
        registerButton.setBounds(240, 120, 120, 20);
        add(registerButton);



        registerButton.addActionListener(e -> register());
        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        usuarioController = new UsuarioController();
        var usuarios = usuarioController.getAll();
        var username = "";
        var password = "";

        for (Usuario usuario : usuarios) {
            var isUserName = usuario.getUserName().equals(userField.getText());
            var isPassword = usuario.getPassword().equals(passwordField.getText());
            if (isUserName && isPassword) {
                AuthenticatedUser.saveUserSession(usuario);
                username = usuario.getUserName();
                password = usuario.getPassword();
            }
        }

        if (!(username.isEmpty() && password.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Usuário %s entrou no sistema".formatted(userField.getText()));
            this.dispose();
            EventQueue.invokeLater(() -> {
                UserMenuFrame userMenuFrame = new UserMenuFrame();
                userMenuFrame.setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(null, "Acesso negado");
        }

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
            var resu = BCrypt.verifyer().verify(passwordField.getText().toCharArray(),
                    usuario.getPassword().toCharArray());
            System.out.println(resu);
        }
    }

    private boolean usernameExists() {
        usuarioController = new UsuarioController();
        var all = usuarioController.getAll();
        List<String> list = new ArrayList<>();

        for (Usuario usuario : all) {
            list.add(usuario.getUserName());
        }

        return list.contains(userField.getText());
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
