package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.BajaCliente;

public class BajaClienteVista extends JPanel {
    private BajaCliente controlador; // Controlador para la baja de clientes
    private JTextField dniTextField; // Cambiado el nombre a dniTextField para mayor claridad

    // Constructor
    public BajaClienteVista() {
        setLayout(null);
        
        JLabel tituloLabel = new JLabel("Baja cliente");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(120, 23, 139, 17);
        add(tituloLabel);
        
        JButton bajaButton = new JButton("Baja");
        bajaButton.setBounds(113, 115, 105, 27);
        add(bajaButton);
        
        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(57, 61, 60, 17);
        add(dniLabel);
        
        dniTextField = new JTextField();
        dniTextField.setBounds(113, 59, 114, 21);
        add(dniTextField);
        dniTextField.setColumns(10);
        
        // Acción al presionar el botón "Baja"
        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniTextField.getText().trim();
                
                // Verificar que el campo no esté vacío
                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(bajaButton), "El DNI no puede estar vacío.");
                    return;
                }
                
                // Llamada al controlador para realizar la baja del cliente
                if (controlador != null) {
                    if (controlador.baja(dni)) {  // Pasar el DNI como String
                        JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(bajaButton), "Baja correcta");
                    } else {
                        JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(bajaButton), "DNI no encontrado");
                    }
                } else {
                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(bajaButton), "Controlador no configurado.");
                }
            }
        });
    }
    

    // Método para asignar el controlador
    public void setControlador(BajaCliente controlador) {
        this.controlador = controlador;
    }
}

