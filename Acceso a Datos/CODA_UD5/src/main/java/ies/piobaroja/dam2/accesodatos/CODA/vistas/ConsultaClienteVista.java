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
    private ConsultarCliente controlador; // Controlador para consultar clientes

    public ConsultaClienteVista() {
        // Configuración del panel
        dniTextField = new JTextField(15);
        JButton consultaButton = new JButton("Consultar");
        
        // Agregar componentes al panel
        add(new JLabel("DNI:"));
        add(dniTextField);
        add(consultaButton);
        
        consultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniTextField.getText().trim();
                try {
                    ClienteUsuario cliente = controlador.consultaPorDNI(dni); // Asegúrate de que controlador no sea null
                    if (cliente != null) {
                        // Mostrar datos del cliente
                        JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.getNombre());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en la consulta: " + ex.getMessage());
                }
            }
        });
    }

    // Método para establecer el controlador
    public void setControlador(ConsultarCliente controlador) {
        this.controlador = controlador;
    }
}

