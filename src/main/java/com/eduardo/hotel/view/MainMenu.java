package com.eduardo.hotel.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {
    private JButton loginButton;
    private Login login;
    public MainMenu() {
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500, 400);
        setLocationRelativeTo(null);

        this.loginButton = new JButton("Login");
        this.loginButton.setBounds(250, 150, 100, 30);
        add(this.loginButton);

        loginFrame();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                var result = JOptionPane.showConfirmDialog(
                        frame,
                        "Você tem certeza que quer sair?",
                        "sair",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                   setDefaultCloseOperation(EXIT_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });

    }
    private void loginFrame() {
        this.loginButton.addActionListener(e -> {
//            this.dispose();
//            new Login();
        });
    }
}
