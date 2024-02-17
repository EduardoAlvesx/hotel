package com.eduardo.hotel.view;

import com.eduardo.hotel.controller.HospedeController;
import com.eduardo.hotel.model.*;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.Objects;

public class HospedesFrame extends JFrame {
    private JTextField nomeField, sobrenomeField, telefoneField, numeroReservaField;
    private JLabel nomeLabel, sobrenomeLabel, dataNascimentoLabel, nacionalidadeLabel, telefoneLabel, numeroReservaLabel;
    private DatePicker dataNascimentoPicker;
    private JComboBox<String> nacionalidadeComboBox;
    private JButton salvarButton;
    private HospedeController hospedeController;
    private Hospede hospede;

    public HospedesFrame() {
        setSize(840, 540);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        nomeLabel = new JLabel("NOME");
        nomeLabel.setBounds(50, 50, 100, 20);
        nomeLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(50, 80, 250, 30);
        nomeField.setBackground(Color.white);
        nomeField.setFont(new Font("Roboto", Font.PLAIN, 14));
        nomeField.setBorder(BorderFactory.createEtchedBorder());
        add(nomeField);

        sobrenomeLabel = new JLabel("SOBRENOME");
        sobrenomeLabel.setBounds(50, 130, 100, 20);
        add(sobrenomeLabel);

        sobrenomeField = new JTextField();
        sobrenomeField.setBounds(50, 160, 250, 30);
        add(sobrenomeField);

        dataNascimentoLabel = new JLabel("DATA DE NASCIMENTO");
        dataNascimentoLabel.setBounds(50, 210, 200, 20);
        add(dataNascimentoLabel);

        dataNascimentoPicker = new DatePicker();
        dataNascimentoPicker.setBounds(50, 240, 180, 25);
        add(dataNascimentoPicker);

        nacionalidadeLabel = new JLabel("NACIONALIDADE");
        nacionalidadeLabel.setBounds(50, 290, 150, 30);
        add(nacionalidadeLabel);

        String[] nacionalidades = {"Brasileiro", "Americano", "Alemão"};
        nacionalidadeComboBox = new JComboBox<>(nacionalidades);
        nacionalidadeComboBox.setBounds(50, 320, 150, 30);
        add(nacionalidadeComboBox);

        telefoneLabel = new JLabel("TELEFONE");
        telefoneLabel.setBounds(50, 360, 150, 30);
        add(telefoneLabel);

        telefoneField = new JTextField();
        telefoneField.setBounds(50, 390, 200, 30);
        add(telefoneField);

        numeroReservaLabel = new JLabel("NÚMERO DA RESERVA");
        numeroReservaLabel.setBounds(50, 420, 200, 30);
        add(numeroReservaLabel);

        numeroReservaField = new JTextField();
        numeroReservaField.setBounds(50, 450, 200, 30);
        numeroReservaField.setEditable(false);
        numeroReservaField.setText(ReservaSession.getId().toString());
        add(numeroReservaField);

        salvarButton = new JButton("SALVAR");
        salvarButton.setBounds(300, 450, 200, 30);
        add(salvarButton);

        salvarButton.addActionListener(e -> register());
    }

    private void register() {
        hospedeController = new HospedeController();
        var nome = nomeField.getText();
        var sobrenome = sobrenomeField.getText();
        var dataNascimento = dataNascimentoPicker.getDate();
        var nacionalidade = Objects.requireNonNull(nacionalidadeComboBox.getSelectedItem()).toString();
        var telefone = telefoneField.getText();
        var reservaId = ReservaSession.getId();
        var usuarioId = AuthenticatedUser.getId();
        hospede = new Hospede(nome, sobrenome, dataNascimento, nacionalidade, telefone, reservaId,
                BigInteger.valueOf(usuarioId));
        hospedeController.register(hospede);
        JOptionPane.showMessageDialog(null, "Suas informações foram salvas " + AuthenticatedUser.getUsername());
        EventQueue.invokeLater(() -> {
            this.dispose();
            UserMenuFrame userMenuFrame = new UserMenuFrame();
            userMenuFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        HospedesFrame hospedesFrame = new HospedesFrame();
        hospedesFrame.setVisible(true);
    }

}
