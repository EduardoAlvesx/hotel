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
        buscarField.setToolTipText("Pesquise usando sobrenome ou número da reserva");
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

        buscarButton.addActionListener(e -> {
            if (isSelectedModel(0)) {
                clearHospedesTable();
                fillHospedesTable();
            } else {
                clearReservasTable();
                fillReservasTable();
            }
        });

        editarButton.addActionListener(e -> {
            try {
                if (isSelectedModel(0)) {
                    updateTableHospedes();
                } else {
                    updateTableReservas();
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "Pesquise pelo sobrenome ou id primeiro");
            }
        });

        deletarButton.addActionListener(e -> {
            try {
                if (isSelectedModel(0)) {
                    deleteHospedes();
                } else {
                    deleteReservas();
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                JOptionPane.showMessageDialog(null, "Pesquise primeiro para deletar");
            }
        });
    }

    private boolean isSelectedModel(int index) {
        return pane.getSelectedIndex() == index;
    }


    private void deleteHospedes() {
        var id = getHospedesValueAt();
        if (hospedesTable.getSelectedColumn() == 0) {
            hospedeController.delete((BigInteger) id);
            JOptionPane.showMessageDialog(null, "Item deletado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Delete clicando no número do hóspede");
        }
    }

    private void deleteReservas() {
        var id = getReservasValueAt();
        if (id instanceof BigInteger) {
            reservaController.delete((BigInteger) id);
            JOptionPane.showMessageDialog(null, "Sua reserva número: %d foi deletado com sucesso".formatted(id));
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o número da reserva para deletar a reserva");
        }
    }
    private Object getHospedesValueAt() {
        return hospedesModel.getValueAt(hospedesTable.getSelectedRow(), hospedesTable.getSelectedColumn());
    }

    private Object getReservasValueAt() {
        return reservasModel.getValueAt(reservasTable.getSelectedRow(), reservasTable.getSelectedColumn());
    }

    private void updateTableHospedes() {
        var selectedItem = getHospedesValueAt();
        if (selectedItem instanceof BigInteger) {
            var id = new BigDecimal(String.valueOf(selectedItem));
            var nome = String.valueOf(hospedesModel.getValueAt(hospedesTable.getSelectedRow(), 1));
            var sobrenome = String.valueOf(hospedesModel.getValueAt(hospedesTable.getSelectedRow(), 2));
            var dataNascimento = Date.valueOf(String.valueOf(hospedesModel.getValueAt(hospedesTable.getSelectedRow(), 3)));
            var nacionalidade = String.valueOf(hospedesModel.getValueAt(hospedesTable.getSelectedRow(), 4));
            var telefone = String.valueOf(hospedesModel.getValueAt(hospedesTable.getSelectedRow(), 5));

            hospedeController.update(id, nome, sobrenome, dataNascimento, nacionalidade, telefone);
            JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o id e editar para concluir");
        }
    }

    private void updateTableReservas() {
        var itemSelected = getReservasValueAt();
        if (itemSelected instanceof BigInteger) {
            var id = new BigDecimal(String.valueOf(itemSelected));
            var dataEntrada = Date.valueOf(String.valueOf(reservasModel.getValueAt(reservasTable.getSelectedRow(), 1)));
            var dataSaida = Date.valueOf(String.valueOf(reservasModel.getValueAt(reservasTable.getSelectedRow(), 2)));
            var val = Double.valueOf(ChronoUnit.DAYS.between(dataEntrada.toLocalDate(), dataSaida.toLocalDate()) * HotelUtil.VALOR.getValorDia());
            var formaPagamento = (String) reservasModel.getValueAt(reservasTable.getSelectedRow(), 4);
            reservaController.update(dataEntrada, dataSaida, val, formaPagamento, id);
            JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o id e editar para concluir");
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
