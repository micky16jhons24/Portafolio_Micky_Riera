package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.VentaProductoControlador;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class VentaProductoVista extends JPanel {
    private JTextField productoIdTextField;
    private JTextField cantidadTextField;
    private JTextField fechaVentaTextField;
    private JComboBox<ClienteUsuario> clienteComboBox;
    private JButton registrarVentaButton;

    private VentaProductoControlador controlador;

    public VentaProductoVista(VentaProductoControlador controlador, List<ClienteUsuario> clientes) {
        this.controlador = controlador;
        initialize(clientes);
    }

    private void initialize(List<ClienteUsuario> clientes) {
        setLayout(null);

        JLabel productoIdLabel = new JLabel("ID del Producto:");
        productoIdLabel.setBounds(10, 20, 150, 25);
        add(productoIdLabel);

        productoIdTextField = new JTextField();
        productoIdTextField.setBounds(180, 20, 200, 25);
        add(productoIdTextField);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setBounds(10, 60, 150, 25);
        add(cantidadLabel);

        cantidadTextField = new JTextField();
        cantidadTextField.setBounds(180, 60, 200, 25);
        add(cantidadTextField);

        JLabel fechaVentaLabel = new JLabel("Fecha de Venta (yyyy-MM-dd):");
        fechaVentaLabel.setBounds(10, 100, 200, 25);
        add(fechaVentaLabel);

        fechaVentaTextField = new JTextField();
        fechaVentaTextField.setBounds(180, 100, 200, 25);
        add(fechaVentaTextField);

        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setBounds(10, 140, 150, 25);
        add(clienteLabel);

        clienteComboBox = new JComboBox<>(clientes.toArray(new ClienteUsuario[0]));
        clienteComboBox.setBounds(180, 140, 200, 25);
        add(clienteComboBox);

        registrarVentaButton = new JButton("Registrar Venta");
        registrarVentaButton.setBounds(110, 200, 150, 30);
        add(registrarVentaButton);

        registrarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes disponibles. Agrega clientes antes de realizar una venta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            registrarVentaButton.setEnabled(false);
        }
    }

    private void registrarVenta() {
        try {
            int productoId = Integer.parseInt(productoIdTextField.getText().trim());
            int cantidad = Integer.parseInt(cantidadTextField.getText().trim());
            Date fechaVenta = Date.valueOf(fechaVentaTextField.getText().trim());
            ClienteUsuario clienteSeleccionado = (ClienteUsuario) clienteComboBox.getSelectedItem();

            if (controlador.realizarVentaProducto(productoId, clienteSeleccionado.getDni(), fechaVenta, cantidad)) {
                JOptionPane.showMessageDialog(this, "Venta registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la venta. Verifica los datos e intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Por favor, verifica los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        productoIdTextField.setText("");
        cantidadTextField.setText("");
        fechaVentaTextField.setText("");
    }
}
