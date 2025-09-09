package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ProductoEliminarControlador;

public class ProductoEliminar extends JPanel {
    private ProductoEliminarControlador controlador;  
    private JTextField idTextField;

    public ProductoEliminar() {
        setLayout(null);

        JLabel tituloLabel = new JLabel("Eliminar Producto");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(120, 23, 139, 17);
        add(tituloLabel);

        JLabel idLabel = new JLabel("ID Producto:");
        idLabel.setBounds(57, 61, 100, 17);
        add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(113, 59, 114, 21);
        add(idTextField);  
        idTextField.setColumns(10);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(113, 115, 105, 27);
        add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = idTextField.getText().trim();  
                int id;

                if (idString.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        JOptionPane.getFrameForComponent(eliminarButton),
                        "El ID del producto no puede estar vacío."
                    );
                    return;
                }

                try {
                    id = Integer.parseInt(idString);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        JOptionPane.getFrameForComponent(eliminarButton),
                        "El ID del producto debe ser un número válido."
                    );
                    return;
                }

                if (controlador != null) {
                    if (controlador.eliminarProducto(id)) {
                        JOptionPane.showMessageDialog(
                            JOptionPane.getFrameForComponent(eliminarButton),
                            "Eliminación correcta"
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                            JOptionPane.getFrameForComponent(eliminarButton),
                            "ID del producto no encontrado"
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        JOptionPane.getFrameForComponent(eliminarButton),
                        "Controlador no configurado."
                    );
                }
            }
        });
    }

    public void setControlador(ProductoEliminarControlador controlador) {
        this.controlador = controlador;
    }
}

