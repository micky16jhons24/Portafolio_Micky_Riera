package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setLayout(null); 

        JLabel tituloLabel = new JLabel("Asignar Producto a Cliente");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(90, 20, 250, 30);
        add(tituloLabel);

        JLabel idProductoLabel = new JLabel("ID Producto:");
        idProductoLabel.setBounds(30, 70, 100, 25);
        add(idProductoLabel);

        idProductoTextField = new JTextField();
        idProductoTextField.setBounds(140, 70, 150, 25);
        add(idProductoTextField);

        JLabel idClienteLabel = new JLabel("ID Cliente:");
        idClienteLabel.setBounds(30, 110, 100, 25);
        add(idClienteLabel);

        idClienteTextField = new JTextField();
        idClienteTextField.setBounds(140, 110, 150, 25);
        add(idClienteTextField);

        JButton asignarButton = new JButton("Asignar");
        asignarButton.setBounds(100, 160, 100, 30);
        add(asignarButton);

        asignarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idProductoText = idProductoTextField.getText().trim();
                String idClienteText = idClienteTextField.getText().trim();

                if (idProductoText.isEmpty() || idClienteText.isEmpty()) {
                    JOptionPane.showMessageDialog(AsignarProductoVista.this, "Ambos campos deben estar llenos.");
                    return;
                }

                try {
                    int idProducto = Integer.parseInt(idProductoText);
                    int idCliente = Integer.parseInt(idClienteText);

                    if (controlador != null) {
                        controlador.asignarProducto(idCliente, idProducto);

                        JOptionPane.showMessageDialog(AsignarProductoVista.this, "Producto asignado correctamente al cliente.");
                    } else {
                        JOptionPane.showMessageDialog(AsignarProductoVista.this, "Controlador no configurado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AsignarProductoVista.this, "El ID del producto y el ID del cliente deben ser números enteros.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AsignarProductoVista.this, "Error al asignar el producto: " + ex.getMessage());
                }
            }
        });
    }

    public void setControlador(AsignarProductoControlador controlador) {
        this.controlador = controlador;
    }
}
