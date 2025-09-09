package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.ProductoModificarControlador;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto; 

public class ProductoModificar extends JPanel {
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JTextField tallaTextField;
    private JTextField precioTextField;
    private JTextField disponibilidadTextField;
    private JButton modificarButton, cargarButton;
    private ProductoModificarControlador controlador;

    public ProductoModificar() {
        setLayout(new GridLayout(6, 2, 10, 10)); // Ajusta el espaciado entre componentes
        
        // Etiqueta y campo ID
        add(new JLabel("ID Producto"));
        idTextField = new JTextField();
        add(idTextField);

        // Etiqueta y campo Nombre
        add(new JLabel("Nombre"));
        nombreTextField = new JTextField();
        add(nombreTextField);

        // Etiqueta y campo Talla
        add(new JLabel("Talla"));
        tallaTextField = new JTextField();
        add(tallaTextField);

        // Etiqueta y campo Precio de Venta
        add(new JLabel("Precio Venta"));
        precioTextField = new JTextField();
        add(precioTextField);

        // Etiqueta y campo Disponibilidad
        add(new JLabel("Disponibilidad"));
        disponibilidadTextField = new JTextField();
        add(disponibilidadTextField);

        // Botones de acción
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
                    // Método para cargar los datos del producto en los campos
                    Producto producto = controlador.consultarDatosProducto(id);
                    if (producto != null) {
                        cargarDatosEnCampos(producto.getNombre(), producto.getTalla(), producto.getPrecioVenta(), producto.isDisponibilidad());
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
                    double precioVenta = Double.parseDouble(precioTextField.getText());
                    boolean disponibilidad = Boolean.parseBoolean(disponibilidadTextField.getText());
                    
                    // Llamada al método para modificar el producto
                    controlador.modificarProducto(id, nombre, talla, precioVenta, disponibilidad);
                    JOptionPane.showMessageDialog(null, "Producto modificado con éxito.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Verifique los datos ingresados.");
                }
            }
        });
    }

    public void setControlador(ProductoModificarControlador controlador) {
        this.controlador = controlador;
    }

    public void cargarDatosEnCampos(String nombre, String talla, double precioVenta, boolean disponibilidad) {
        nombreTextField.setText(nombre);
        tallaTextField.setText(talla);
        precioTextField.setText(String.valueOf(precioVenta));
        disponibilidadTextField.setText(String.valueOf(disponibilidad));
    }
}
