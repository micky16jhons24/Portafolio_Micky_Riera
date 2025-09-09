package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import javax.swing.*;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ProductoAltaControlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoAlta extends JPanel {
    private JTextField nombreTextField;
    private JTextField tallaTextField;
    private JTextField precioVentaTextField;
    private JCheckBox disponibilidadCheckBox;
    private ProductoAltaControlador controlador;

    public ProductoAlta() {
        setLayout(null);

        JLabel tituloLabel = new JLabel("Alta Producto");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(132, 12, 164, 17);
        add(tituloLabel);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(40, 50, 60, 17);
        add(nombreLabel);

        nombreTextField = new JTextField();
        nombreTextField.setBounds(140, 50, 114, 21);
        add(nombreTextField);

        JLabel tallaLabel = new JLabel("Talla:");
        tallaLabel.setBounds(40, 90, 60, 17);
        add(tallaLabel);

        tallaTextField = new JTextField();
        tallaTextField.setBounds(140, 90, 114, 21);
        add(tallaTextField);

        JLabel precioVentaLabel = new JLabel("Precio Venta:");
        precioVentaLabel.setBounds(40, 130, 80, 17);
        add(precioVentaLabel);

        precioVentaTextField = new JTextField();
        precioVentaTextField.setBounds(140, 130, 114, 21);
        add(precioVentaTextField);

        JLabel disponibilidadLabel = new JLabel("Disponible:");
        disponibilidadLabel.setBounds(40, 170, 80, 17);
        add(disponibilidadLabel);

        disponibilidadCheckBox = new JCheckBox();
        disponibilidadCheckBox.setBounds(140, 170, 114, 21);
        add(disponibilidadCheckBox);

        JButton altaButton = new JButton("Dar de alta");
        altaButton.setBounds(140, 210, 120, 27);
        add(altaButton);

        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreTextField.getText();
                String talla = tallaTextField.getText();
                Double precioVenta;
                
                try {
                    precioVenta = Double.parseDouble(precioVentaTextField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ProductoAlta.this, "Precio debe ser un número válido.");
                    return;
                }
                
                boolean disponibilidad = disponibilidadCheckBox.isSelected();

                if (controlador != null) {
                    controlador.altaProducto(nombre, talla, precioVenta, disponibilidad);
                    JOptionPane.showMessageDialog(ProductoAlta.this, "Producto dado de alta correctamente");
                } else {
                    JOptionPane.showMessageDialog(ProductoAlta.this, "Controlador no configurado.");
                }
                clear();
            }
        });
    }

    // Método para asignar el controlador
    public void setControlador(ProductoAltaControlador controlador) {
        this.controlador = controlador;
    }

    // Método para limpiar los campos del formulario
    private void clear() {
        nombreTextField.setText("");
        tallaTextField.setText("");
        precioVentaTextField.setText("");
        disponibilidadCheckBox.setSelected(false);
    }
}
