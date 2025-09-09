package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.AlquilerAltaProductoControlador;
import java.sql.Date;
import java.time.LocalDate;

public class AlquilerAltaProducto extends JPanel {
    private JTextField nombreField, tallaField, precioVentaField, diasMaximosAlquilerField, telefonoField; 
    private AlquilerAltaProductoControlador controlador;

    public AlquilerAltaProducto() {
        setLayout(null);
        JLabel tituloLabel = new JLabel("Alta Producto de Alquiler");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        tituloLabel.setBounds(50, 20, 250, 25);
        add(tituloLabel);

        // Campos de entrada
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(30, 70, 80, 25);
        add(nombreLabel);

        nombreField = new JTextField();
        nombreField.setBounds(120, 70, 150, 25);
        add(nombreField);

        JLabel tallaLabel = new JLabel("Talla:");
        tallaLabel.setBounds(30, 110, 80, 25);
        add(tallaLabel);

        tallaField = new JTextField();
        tallaField.setBounds(120, 110, 150, 25);
        add(tallaField);

        JLabel precioVentaLabel = new JLabel("Precio Venta:");
        precioVentaLabel.setBounds(30, 150, 100, 25);
        add(precioVentaLabel);

        precioVentaField = new JTextField();
        precioVentaField.setBounds(120, 150, 150, 25);
        add(precioVentaField);

        JLabel diasMaximosLabel = new JLabel("Días Máximos:");
        diasMaximosLabel.setBounds(30, 190, 100, 25);
        add(diasMaximosLabel);

        diasMaximosAlquilerField = new JTextField();
        diasMaximosAlquilerField.setBounds(120, 190, 150, 25);
        add(diasMaximosAlquilerField);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(30, 230, 100, 25);
        add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(120, 230, 150, 25);
        add(telefonoField);

        JButton altaButton = new JButton("Dar de Alta");
        altaButton.setBounds(120, 280, 120, 30);
        add(altaButton);

        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreField.getText();
                    String talla = tallaField.getText();
                    double precioVenta = Double.parseDouble(precioVentaField.getText());
                    int diasMaximos = Integer.parseInt(diasMaximosAlquilerField.getText());
                    int telefono = Integer.parseInt(telefonoField.getText()); // Cambiado a String para no perder ceros
                    boolean disponibilidad = true; // Suponiendo que el producto está disponible
                    LocalDate fechaAlquiler = LocalDate.now(); // Asignar fecha actual como fecha de alquiler

                    if (controlador != null) {
                    	controlador.AltaProductoAlquilado(nombre, talla, precioVenta, disponibilidad, fechaAlquiler, telefono, diasMaximos);
                        JOptionPane.showMessageDialog(AlquilerAltaProducto.this, "Producto de alquiler añadido con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(AlquilerAltaProducto.this, "Controlador no configurado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AlquilerAltaProducto.this, "Verifique los datos ingresados.");
                }
            }
        });
    }

    public void setControlador(AlquilerAltaProductoControlador controlador) {
        this.controlador = controlador;
    }
}

