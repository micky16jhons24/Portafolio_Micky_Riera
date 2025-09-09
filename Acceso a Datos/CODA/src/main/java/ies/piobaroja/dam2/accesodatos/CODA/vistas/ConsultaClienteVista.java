package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ConsultarCliente;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ConsultaClienteVista extends JPanel {
    private JTextField dniTextField;
    private ConsultarCliente controlador;

    public ConsultaClienteVista() {
        super();
        setLayout(null);
        
        JLabel tituloLabel = new JLabel("Consulta cliente");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(86, 12, 201, 17);
        add(tituloLabel);
        
        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(38, 41, 60, 17);
        add(dniLabel);
        
        dniTextField = new JTextField();
        dniTextField.setBounds(96, 39, 114, 21);
        add(dniTextField);
        dniTextField.setColumns(10);
        
        JButton btnConsulta = new JButton("Consultar");
        btnConsulta.setBounds(106, 83, 105, 27);
        add(btnConsulta);
        
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(38, 135, 60, 17);
        add(nombreLabel);
        
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(38, 165, 60, 17);
        add(apellidoLabel);
        
        JLabel nombreValorLabel = new JLabel("");
        nombreValorLabel.setBounds(96, 135, 200, 17);
        add(nombreValorLabel);
        
        JLabel apellidoValorLabel = new JLabel("");
        apellidoValorLabel.setBounds(96, 165, 200, 17);
        add(apellidoValorLabel);
        
        btnConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniTextField.getText().trim();
                
                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(ConsultaClienteVista.this, "El DNI no puede estar vacío.");
                    return;
                }

                // Llamar al controlador con el DNI
                ClienteUsuario cliente = controlador.consultaPorDNI(dni);
                
                if (cliente != null) {
                    // Mostrar los valores en las etiquetas
                    nombreValorLabel.setText(cliente.getNombre());
                    apellidoValorLabel.setText(cliente.getApellido());
                } else {
                    // Mostrar mensaje de cliente no encontrado
                    JOptionPane.showMessageDialog(ConsultaClienteVista.this, "Cliente no encontrado.");
                }
            }
        });
    }

    public void setControlador(ConsultarCliente controlador) {
        this.controlador = controlador;
    }
}

