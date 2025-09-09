package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.AsignarProductoControlador;

public class AsignarProductoVista extends JPanel {
    private AsignarProductoControlador controlador;
    private JTextField idProductoTextField;
    private JTextField idClienteTextField;

    // Constructor
    public AsignarProductoVista() {
        setLayout(null); // Configura el diseño de la vista.

        // Etiqueta de título
        JLabel tituloLabel = new JLabel("Asignar Producto a Cliente");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(90, 20, 250, 30);
        add(tituloLabel);

        // Etiqueta y campo de texto para el ID del producto
        JLabel idProductoLabel = new JLabel("ID Producto:");
        idProductoLabel.setBounds(30, 70, 100, 25);
        add(idProductoLabel);

        idProductoTextField = new JTextField();
        idProductoTextField.setBounds(140, 70, 150, 25);
        add(idProductoTextField);

        // Etiqueta y campo de texto para el DNI del cliente
        JLabel idClienteLabel = new JLabel("DNI Cliente:");
        idClienteLabel.setBounds(30, 110, 100, 25);
        add(idClienteLabel);

        idClienteTextField = new JTextField();
        idClienteTextField.setBounds(140, 110, 150, 25);
        add(idClienteTextField);

        // Botón para asignar el producto
        JButton asignarButton = new JButton("Asignar");
        asignarButton.setBounds(100, 160, 100, 30);
        add(asignarButton);

        // Acción al hacer clic en el botón
        asignarButton.addActionListener((ActionEvent e) -> {
            String idProductoText = idProductoTextField.getText().trim();
            String dniClienteText = idClienteTextField.getText().trim();

            // Validación de campos vacíos
            if (idProductoText.isEmpty() || dniClienteText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ambos campos deben estar llenos.");
                return;
            }

            try {
                int idProducto = Integer.parseInt(idProductoText);

                if (controlador != null) {
                    boolean exito = controlador.asignarProducto(idProducto, dniClienteText);

                    if (exito) {
                        JOptionPane.showMessageDialog(this, "Producto asignado correctamente al cliente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo asignar el producto. Verifique los datos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Controlador no configurado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID del producto debe ser un número.");
            }
        });
    }

    // Método para asignar el controlador
    public void setControlador(AsignarProductoControlador controlador) {
        this.controlador = controlador;
    }
}
