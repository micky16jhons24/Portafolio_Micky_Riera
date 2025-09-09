package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.VentaProductoControlador;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class VentaProductoVista extends JFrame {
    private JTextField nombreTextField;
    private JTextField tallaTextField;
    private JTextField precioTextField;
    private JTextField cantidadTextField;
    private JComboBox<ClienteUsuario> clienteComboBox; // ComboBox para seleccionar cliente
    private JButton registrarButton;
    private JButton modificarButton;

    private VentaProductoControlador controlador;

    public VentaProductoVista(VentaProductoControlador controlador, ClienteUsuario[] clientes) {
        this.controlador = controlador;
        initialize(clientes);
    }

    private void initialize(ClienteUsuario[] clientes) {
        setTitle("Registro de Ventas de Productos");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre del Producto:");
        nombreLabel.setBounds(10, 20, 150, 25);
        add(nombreLabel);

        nombreTextField = new JTextField();
        nombreTextField.setBounds(180, 20, 200, 25);
        add(nombreTextField);

        JLabel tallaLabel = new JLabel("Talla del Producto:");
        tallaLabel.setBounds(10, 60, 150, 25);
        add(tallaLabel);

        tallaTextField = new JTextField();
        tallaTextField.setBounds(180, 60, 200, 25);
        add(tallaTextField);

        JLabel precioLabel = new JLabel("Precio de Venta:");
        precioLabel.setBounds(10, 100, 150, 25);
        add(precioLabel);

        precioTextField = new JTextField();
        precioTextField.setBounds(180, 100, 200, 25);
        add(precioTextField);

        JLabel cantidadLabel = new JLabel("Cantidad Vendida:");
        cantidadLabel.setBounds(10, 140, 150, 25);
        add(cantidadLabel);

        cantidadTextField = new JTextField();
        cantidadTextField.setBounds(180, 140, 200, 25);
        add(cantidadTextField);

        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setBounds(10, 180, 150, 25);
        add(clienteLabel);

        clienteComboBox = new JComboBox<>(clientes);
        clienteComboBox.setBounds(180, 180, 200, 25);
        add(clienteComboBox);

        registrarButton = new JButton("Registrar Venta");
        registrarButton.setBounds(10, 220, 150, 25);
        add(registrarButton);

        modificarButton = new JButton("Modificar Venta");
        modificarButton.setBounds(180, 220, 150, 25);
        add(modificarButton);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarVenta();
            }
        });
    }

    private void registrarVenta() {
        String nombre = nombreTextField.getText().trim();
        String talla = tallaTextField.getText().trim();
        Double precio;
        int cantidad;

        try {
            precio = Double.parseDouble(precioTextField.getText().trim());
            cantidad = Integer.parseInt(cantidadTextField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio y cantidad deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ClienteUsuario clienteSeleccionado = (ClienteUsuario) clienteComboBox.getSelectedItem(); // Obtener el cliente seleccionado
        controlador.realizarVentaProducto(nombre, talla, precio, cantidad, clienteSeleccionado);
        JOptionPane.showMessageDialog(this, "Venta registrada correctamente.");
        clearFields();
    }

    private void modificarVenta() {
        String nombre = nombreTextField.getText().trim();
        String talla = tallaTextField.getText().trim();
        Double precio;
        int cantidad;

        try {
            precio = Double.parseDouble(precioTextField.getText().trim());
            cantidad = Integer.parseInt(cantidadTextField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio y cantidad deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = 1; // Cambia esto por la lógica real para obtener el ID del producto.

        if (controlador.modificarVenta(id, nombre, talla, precio, new Date(System.currentTimeMillis()), cantidad)) {
            JOptionPane.showMessageDialog(this, "Venta modificada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar la venta.");
        }
        clearFields();
    }

    private void clearFields() {
        nombreTextField.setText("");
        tallaTextField.setText("");
        precioTextField.setText("");
        cantidadTextField.setText("");
    }

	public void setControlador(VentaProductoControlador ventaProductoControlador) {
		// TODO Auto-generated method stub
		
	}
}
