package com.eduardo.hotel.view;

import com.eduardo.hotel.controller.HospedeController;
import com.eduardo.hotel.controller.ReservaController;
import com.eduardo.hotel.model.Hospede;
import com.eduardo.hotel.model.HotelUtil;
import com.eduardo.hotel.model.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BuscasFrame extends JFrame {
    private JButton buscarButton, editarButton, deletarButton;
    private JTextField buscarField;
    private JLabel tituloLabel;
    private JTable hospedesTable, reservasTable;
    private DefaultTableModel hospedesModel, reservasModel;
    private JTabbedPane pane;
    private JScrollPane hospedesScrollPane, reservasScrollPane;
    private JPanel contentPanel;
    private HospedeController hospedeController;
    private ReservaController reservaController;
    public BuscasFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(null);
        setContentPane(contentPanel);
        contentPanel.setVisible(true);

        tituloLabel = new JLabel("SISTEMA DE BUSCA");
        tituloLabel.setBounds(300, 50, 200, 30);
        add(tituloLabel);

        buscarField = new JTextField();
        buscarField.setBounds(400, 100, 150, 30);
        add(buscarField);

        buscarButton = new JButton("BUSCAR");
        buscarButton.setBounds(600, 100, 150, 30);
        add(buscarButton);

        pane = new JTabbedPane(JTabbedPane.TOP);
        pane.setBackground(new Color(12, 190, 199, 255));
        pane.setBounds(10, 150, 760, 350);
        contentPanel.add(pane);

        hospedesTable = new JTable();
        hospedesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospedesModel = (DefaultTableModel) hospedesTable.getModel();
        hospedesModel.addColumn("Número do hospede");
        hospedesModel.addColumn("Nome");
        hospedesModel.addColumn("Sobrenome");
        hospedesModel.addColumn("Data de nascimento");
        hospedesModel.addColumn("Nacionalidade");
        hospedesModel.addColumn("Telefone");
        hospedesModel.addColumn("Número da reserva");
        hospedesScrollPane = new JScrollPane(hospedesTable);
        pane.addTab("Hospedes", null, hospedesScrollPane, null);
        hospedesScrollPane.setVisible(true);

        reservasTable = new JTable();
        reservasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reservasModel = (DefaultTableModel) reservasTable.getModel();
        reservasModel.addColumn("Número da reserva");
        reservasModel.addColumn("Data check in");
        reservasModel.addColumn("Data chek out");
        reservasModel.addColumn("Valor");
        reservasModel.addColumn("Forma de pagamento");
        reservasScrollPane = new JScrollPane(reservasTable);
        pane.addTab("Reservas", null, reservasScrollPane, null);
        reservasScrollPane.setVisible(true);

        editarButton = new JButton("EDITAR");
        editarButton.setBounds(500, 520, 100, 30);
        contentPanel.add(editarButton);

        deletarButton = new JButton("DELETAR");
        deletarButton.setBounds(640, 520, 100, 30);
        contentPanel.add(deletarButton);

        buscarField.setToolTipText("Pesquise usando sobrenome ou número da reserva");

        buscarButton.addActionListener(e -> {
            clearHospedesTable();
            fillHospedesTable();
        });

        buscarButton.addActionListener(e -> {
            clearReservasTable();
            fillReservasTable();
        });

        editarButton.addActionListener(e -> {
            try {
                updateTableReservas();
            } catch (ArrayIndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "Pesquise primeiro");
            }
        });
    }

    private void updateTableReservas() {
        var itemSelected = reservasModel.getValueAt(reservasTable.getSelectedRow(), reservasTable.getSelectedColumn());
        if (itemSelected instanceof BigInteger) {
            var id = new BigDecimal(String.valueOf(itemSelected));
            var dataEntrada = Date.valueOf(String.valueOf(reservasModel.getValueAt(reservasTable.getSelectedRow(), 1)));
            var dataSaida = Date.valueOf(String.valueOf(reservasModel.getValueAt(reservasTable.getSelectedRow(), 2)));
            var val = Double.valueOf(ChronoUnit.DAYS.between(dataEntrada.toLocalDate(), dataSaida.toLocalDate()) * HotelUtil.VALOR.getValorDia());
            var formaPagamento = (String) reservasModel.getValueAt(reservasTable.getSelectedRow(), 4);
            reservaController.update(dataEntrada, dataSaida, val, formaPagamento, id);
            JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o id");
        }

    }

    private void fillReservasTable() {
        var id = buscarField.getText();
        var reservas = getReservasById(id);
        for (Reserva reserva : reservas) {
            reservasModel.addRow(new Object[]{
                    reserva.getId(),
                    reserva.getDataEntrada(),
                    reserva.getDataSaida(),
                    reserva.getValor(),
                    reserva.getFormaPagamento()
            });
        }
    }

    private List<Reserva> getReservasById(String id) {
        reservaController = new ReservaController();
        return reservaController.getById(id);
    }

    private void clearReservasTable() {
        reservasModel.getDataVector().clear();
    }

    private void fillHospedesTable() {
        var hospedes = getHospedesBySobrenome();
        for (Hospede hospede : hospedes) {
            hospedesModel.addRow(new Object[] {
                    hospede.getId(),
                    hospede.getNome(),
                    hospede.getSobrenome(),
                    hospede.getDataNascimento(),
                    hospede.getNacionalidade(),
                    hospede.getTelefone(),
                    hospede.getReservaId(),
                    hospede.getUsuarioId()
            });
        }

    }

    private List<Hospede> getHospedesBySobrenome() {
        hospedeController = new HospedeController();
        return hospedeController.getAllBySobrenome(buscarField.getText());
    }

    private void clearHospedesTable() {
        hospedesModel.getDataVector().clear();
    }

    public static void main(String[] args) {
        BuscasFrame buscasFrame = new BuscasFrame();
        buscasFrame.setVisible(true);
    }
}
