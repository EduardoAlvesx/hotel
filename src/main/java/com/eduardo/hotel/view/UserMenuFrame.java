package com.eduardo.hotel.view;

import javax.swing.*;
import java.awt.*;

public class UserMenuFrame extends JFrame {
    private JButton reservasButton, buscarButton;
    public UserMenuFrame() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        reservasButton = new JButton("Registros de reservas");
        reservasButton.setBounds(50, 100, 200, 40);
        add(reservasButton);

        buscarButton = new JButton("Buscar");
        buscarButton.setBounds(50, 150, 200, 40);
        add(buscarButton);

        reservasButton.addActionListener(e -> {
            EventQueue.invokeLater(() -> {
                this.dispose();
                ReservasFrame reservasFrame = new ReservasFrame();
                reservasFrame.setVisible(true);
            });
        });

        buscarButton.addActionListener(e -> {
            EventQueue.invokeLater(() -> {
                this.dispose();
                BuscasFrame buscasFrame = new BuscasFrame();
                buscasFrame.setVisible(true);
            });
        });
    }
}
