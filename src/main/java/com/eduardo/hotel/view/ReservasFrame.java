package com.eduardo.hotel.view;

import com.eduardo.hotel.controller.ReservaController;
import com.eduardo.hotel.model.Reserva;
import com.eduardo.hotel.model.ReservaSession;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class ReservasFrame extends JFrame {
    private JLabel dateInLabel, dateOutLabel, valorReservaLabel;
    private DatePicker dateInPicker;
    private DatePicker dateOutPicker;
    private JTextField valorField;
    private JComboBox<String> pagamentosComboBox;
    private JLabel formasPagamentoLabel;
    private JButton confirmacaoButton;
    private ReservaController reservaController;
    private Reserva reserva;
    private String valorReserva;
    public ReservasFrame() {
        setSize(640, 440);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        dateInLabel = new JLabel("DATA DE CHECK IN");
        dateInLabel.setBounds(50, 60, 160, 30);
        add(dateInLabel);

        dateOutLabel = new JLabel("DATA DE CHECK OUT");
        dateOutLabel.setBounds(50, 135, 160, 20);
        add(dateOutLabel);

        dateInPicker = new DatePicker();
        dateInPicker.setBounds(50, 90, 200, 25);
        add(dateInPicker);

        dateOutPicker = new DatePicker();
        dateOutPicker.setBounds(50, 160, 200, 25);
        add(dateOutPicker);

        valorReservaLabel = new JLabel("VALOR DA RESERVA");
        valorReservaLabel.setBounds(50, 190, 200, 25);
        add(valorReservaLabel);

        valorField = new JTextField();
        valorField.setBounds(50, 220, 200, 25);
        valorField.setEditable(false);
        add(valorField);

        formasPagamentoLabel = new JLabel("FORMA DE PAGAMENTO");
        formasPagamentoLabel.setBounds(50, 260, 200, 25);
        add(formasPagamentoLabel);

        String[] formasPagamento = {"Cartão de crédito", "Cartão de débito", "Boleto"};
        pagamentosComboBox = new JComboBox<>(formasPagamento);
        pagamentosComboBox.setBounds(50, 290, 200, 25);
        add(pagamentosComboBox);

        confirmacaoButton = new JButton("PROXIMO");
        confirmacaoButton.setBounds(50, 350, 200, 40);
        add(confirmacaoButton);

        dateInPicker.addDateChangeListener(e -> {
            if (dateOutPicker.getDate() != null) {
                valorField.setText(String.valueOf(getValorReserva()));
            }
        });


        dateOutPicker.addDateChangeListener(e -> {
            if (dateInPicker.getDate() != null) {
                valorField.setText(String.valueOf(getValorReserva()));
            }


        });

        confirmacaoButton.addActionListener(e -> {
            if (register()) {
                EventQueue.invokeLater(() -> {
                    this.dispose();
                    HospedesFrame hospedesFrame = new HospedesFrame();
                    hospedesFrame.setVisible(true);
                });
            }
        });
    }

    private boolean register() {
        reservaController = new ReservaController();
        var dataEntrada = dateInPicker.getDate();
        var dataSaida = dateOutPicker.getDate();
        var valor = getValorReserva();
        var formaPagamento = Objects.requireNonNull(pagamentosComboBox.getSelectedItem()).toString();
        if (dataEntrada == null || dataSaida == null || String.valueOf(valor).isBlank()) {
            JOptionPane.showMessageDialog(null, "Campos vazios ");
            return false;
        } else {
            reserva = new Reserva(dataEntrada, dataSaida, Double.parseDouble(valor), formaPagamento);
            reservaController.register(reserva);
            ReservaSession.setId(reserva.getId());
            JOptionPane.showMessageDialog(null, "Seu número de reserva: " + reserva.getId());
            return true;
        }
    }

    public static void main(String[] args) {
        ReservasFrame reservasFrame = new ReservasFrame();
        reservasFrame.setVisible(true);
    }

    public String getValorReserva() {
        if (dateInPicker.getDate() == null || dateOutPicker.getDate() == null) {
            return "";
        }
        valorReserva = String.valueOf(ChronoUnit.DAYS.between(dateInPicker.getDate(), dateOutPicker.getDate()) * 1000);
        return valorReserva;
    }
}
