package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.AltaCliente;

public class AltaClienteVista extends JPanel {
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JTextField telefonoTextField;
    private JTextField correoTextField;
    private JTextField codigoPostalTextField;
    private AltaCliente controlador;

    public AltaClienteVista() {
        setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(30, 30, 80, 25);
        add(nombreLabel);

        nombreTextField = new JTextField(15);
        nombreTextField.setBounds(120, 30, 160, 25);
        add(nombreTextField);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(30, 70, 80, 25);
        add(apellidoLabel);

        apellidoTextField = new JTextField(15);
        apellidoTextField.setBounds(120, 70, 160, 25);
        add(apellidoTextField);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(30, 110, 80, 25);
        add(telefonoLabel);

        telefonoTextField = new JTextField(15);
        telefonoTextField.setBounds(120, 110, 160, 25);
        add(telefonoTextField);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(30, 150, 80, 25);
        add(correoLabel);

        correoTextField = new JTextField(15);
        correoTextField.setBounds(120, 150, 160, 25);
        add(correoTextField);

        JLabel codigoPostalLabel = new JLabel("Código Postal:");
        codigoPostalLabel.setBounds(30, 190, 100, 25);
        add(codigoPostalLabel);

        codigoPostalTextField = new JTextField(15);
        codigoPostalTextField.setBounds(120, 190, 160, 25);
        add(codigoPostalTextField);

        JButton altaButton = new JButton("Alta");
        altaButton.setBounds(150, 230, 100, 30);
        add(altaButton);

        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreTextField.getText().trim();
                    String apellido = apellidoTextField.getText().trim();
                    String telefono = telefonoTextField.getText().trim();
                    String correo = correoTextField.getText().trim();
                    String codigoPostal = codigoPostalTextField.getText().trim();
                    
                    controlador.altaCliente(nombre, apellido, telefono, correo, codigoPostal);
                    JOptionPane.showMessageDialog(null, "Cliente dado de alta exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al dar de alta al cliente: " + ex.getMessage());
                }
            }
        });
    }

    public void setControlador(AltaCliente controlador) {
        this.controlador = controlador;
    }
}
