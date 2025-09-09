package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.ConsultarCliente;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ConsultaClienteVista extends JPanel {
    private JTextField dniTextField;
    private ConsultarCliente controlador; 

    public ConsultaClienteVista() {
        dniTextField = new JTextField(15);
        JButton consultaButton = new JButton("Consultar");
        
        add(new JLabel("DNI:"));
        add(dniTextField);
        add(consultaButton);
        
        consultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dniStr = dniTextField.getText().trim();
                
                try {
                    int dni = Integer.parseInt(dniStr);
                    
                    if (controlador != null) {
                        ClienteUsuario cliente = controlador.consultaPorDNI(dni);
                        if (cliente != null) {
                            JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.getNombre());
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Controlador no configurado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El DNI debe ser un número entero.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en la consulta: " + ex.getMessage());
                }
            }
        });
    }

    public void setControlador(ConsultarCliente controlador) {
        this.controlador = controlador;
    }
}
