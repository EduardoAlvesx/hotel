package com.eduardo.hotel.view;

import com.eduardo.hotel.model.Reserva;
import com.eduardo.hotel.model.ReservaSession;

import javax.swing.*;

public class HospedesFrame extends JFrame {
    private JTextField numeroReservaField;
    private Reserva reserva;
    public HospedesFrame() {
        setSize(640, 440);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        reserva = new Reserva();
        numeroReservaField = new JTextField();
        numeroReservaField.setBounds(50, 50, 100, 25);
        numeroReservaField.setEditable(false);
        add(numeroReservaField);
        numeroReservaField.setText(ReservaSession.getId().toString());
    }

}
