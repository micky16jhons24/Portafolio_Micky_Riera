package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.AlquilerBajaProductoControlador;

public class AlquilerBajaProducto extends JPanel {
    private JTextField idField;
    private AlquilerBajaProductoControlador controlador;

    public AlquilerBajaProducto() {
        setLayout(null);

        JLabel tituloLabel = new JLabel("Baja Producto de Alquiler");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        tituloLabel.setBounds(50, 20, 250, 25);
        add(tituloLabel);

        JLabel idLabel = new JLabel("ID Producto:");
        idLabel.setBounds(30, 70, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(120, 70, 150, 25);
        add(idField);

        JButton bajaButton = new JButton("Eliminar");
        bajaButton.setBounds(120, 110, 100, 30);
        add(bajaButton);

        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());
                    if (controlador != null) {
                        boolean resultado = controlador.bajaProductoAlquiler(id);
                        if (resultado) {
                            JOptionPane.showMessageDialog(AlquilerBajaProducto.this, "Producto eliminado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(AlquilerBajaProducto.this, "Producto no encontrado.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(AlquilerBajaProducto.this, "Controlador no inicializado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AlquilerBajaProducto.this, "Por favor, ingrese un ID válido.");
                }
            }
        });
    }

    public void setControlador(AlquilerBajaProductoControlador controlador) {
        this.controlador = controlador;
    }
}

