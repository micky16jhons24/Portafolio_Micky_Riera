package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.AlquilerModificacionProductoControlador;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;

public class AlquilerModificacionProducto extends JPanel {
    private AlquilerModificacionProductoControlador controlador;
    private JTextField idTextField, nombreTextField, tallaTextField, precioVentaTextField, precioAlquilerTextField, diasMaximosTextField;
    private JButton modificarButton, cargarButton;

    public AlquilerModificacionProducto() {
        setLayout(new GridLayout(7, 2));
        
        add(new JLabel("ID Producto:"));
        idTextField = new JTextField();
        idTextField.setBounds(150, 50, 100, 21);
        add(idTextField);

        
        add(new JLabel("Nombre:"));
        nombreTextField = new JTextField();
        idTextField.setBounds(150, 50, 100, 21);
        add(nombreTextField);
        
        
        
        add(new JLabel("Talla:"));
        tallaTextField = new JTextField();
        idTextField.setBounds(150, 50, 100, 21);
        add(tallaTextField);

        add(new JLabel("Precio Venta:"));
        precioVentaTextField = new JTextField();
        idTextField.setBounds(150, 50, 100, 21);
        add(precioVentaTextField);

        add(new JLabel("Precio Alquiler:"));
        precioAlquilerTextField = new JTextField();
        idTextField.setBounds(150, 50, 100, 21);
        add(precioAlquilerTextField);

        add(new JLabel("Días Máximos Alquiler:"));
        idTextField.setBounds(150, 50, 100, 21);
        diasMaximosTextField = new JTextField();
        add(diasMaximosTextField);

        cargarButton = new JButton("Cargar Datos");
        modificarButton = new JButton("Modificar");
        add(cargarButton);
        add(modificarButton);

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idTextField.getText().trim();
                try {
                    int id = Integer.parseInt(idText);
                    AlquilerProducto producto = controlador.consultarDatosProductoalquilado(id);
                    if (producto != null) {
                        cargarDatosEnCampos(producto.getNombre(), producto.getTalla(), producto.getPrecioVenta(), producto.getPrecioVenta(), producto.getDuracionDias());
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID debe ser un número.");
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idTextField.getText().trim());
                    String nombre = nombreTextField.getText();
                    String talla = tallaTextField.getText();
                    double precioVenta = Double.parseDouble(precioVentaTextField.getText());
                    double precioAlquiler = Double.parseDouble(precioAlquilerTextField.getText());
                    int diasMaximos = Integer.parseInt(diasMaximosTextField.getText());

                    boolean disponibilidad = true; 
                    LocalDate fechaAlquiler = null; 
                    int telefono = 0; 

                    controlador.modificarProductoAlquiler(id, nombre, talla, precioVenta, disponibilidad, fechaAlquiler, telefono, diasMaximos);
                    JOptionPane.showMessageDialog(null, "Producto modificado con éxito.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Verifique los datos ingresados.");
                }
            }
        });
    }

    public void setControlador(AlquilerModificacionProductoControlador controlador) {
        this.controlador = controlador;
    }

    public void cargarDatosEnCampos(String nombre, String talla, double precioVenta, double precioAlquiler, int diasMaximos) {
        nombreTextField.setText(nombre);
        tallaTextField.setText(talla);
        precioVentaTextField.setText(String.valueOf(precioVenta));
        precioAlquilerTextField.setText(String.valueOf(precioAlquiler));
        diasMaximosTextField.setText(String.valueOf(diasMaximos));
    }
}
