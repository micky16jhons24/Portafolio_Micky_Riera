package ies.piobaroja.dam2.accesodatos.CODA.vistas;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.AltaCliente;

public class AltaClienteVista extends JPanel {
    private JTextField dniTextField;
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JTextField telefonoTextField;   // Nuevo campo para teléfono
    private JTextField correoTextField;      // Nuevo campo para correo
    private JTextField codigoPostalTextField; // Nuevo campo para código postal
    private AltaCliente controlador;

    public AltaClienteVista() {
        super();
        setLayout(null);
        
        JLabel tituloLabel = new JLabel("Alta Cliente");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(132, 12, 164, 17);
        add(tituloLabel);
        
        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(40, 39, 60, 17);
        add(dniLabel);
        
        dniTextField = new JTextField();
        dniTextField.setBounds(140, 37, 114, 21);
        add(dniTextField);
        
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(40, 70, 60, 17);
        add(nombreLabel);
        
        nombreTextField = new JTextField();
        nombreTextField.setBounds(140, 70, 114, 21);
        add(nombreTextField);
        
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(40, 100, 60, 17);
        add(apellidoLabel);
        
        apellidoTextField = new JTextField();
        apellidoTextField.setBounds(140, 100, 114, 21);
        add(apellidoTextField);
        
        // Nuevos campos para teléfono, correo y código postal
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(40, 130, 60, 17);
        add(telefonoLabel);
        
        telefonoTextField = new JTextField();
        telefonoTextField.setBounds(140, 130, 114, 21);
        add(telefonoTextField);
        
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(40, 160, 60, 17);
        add(correoLabel);
        
        correoTextField = new JTextField();
        correoTextField.setBounds(140, 160, 114, 21);
        add(correoTextField);
        
        JLabel codigoPostalLabel = new JLabel("C. Postal:");
        codigoPostalLabel.setBounds(40, 190, 60, 17);
        add(codigoPostalLabel);
        
        codigoPostalTextField = new JTextField();
        codigoPostalTextField.setBounds(140, 190, 114, 21);
        add(codigoPostalTextField);
        
        JButton altaButton = new JButton("Dar de alta");
        altaButton.setBounds(140, 220, 120, 27);
        add(altaButton);

        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniTextField.getText();
                String nombre = nombreTextField.getText();
                String apellido = apellidoTextField.getText();
                String telefono = telefonoTextField.getText(); // Obtener teléfono
                String correo = correoTextField.getText();     // Obtener correo
                String codigoPostal = codigoPostalTextField.getText(); // Obtener código postal
                
                if (controlador != null) {
                    controlador.altaCliente(dni, nombre, apellido, telefono, correo, codigoPostal);
                    JOptionPane.showMessageDialog(AltaClienteVista.this, "Cliente dado de alta correctamente");
                } else {
                    JOptionPane.showMessageDialog(AltaClienteVista.this, "Controlador no configurado.");
                }
            }
        });
    }

    // Método para asignar el controlador
    public void setControlador(AltaCliente controlador) {
        this.controlador = controlador;
    }
}
