package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ModificarDatosCliente;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ModificacionClienteVista extends JPanel {
    private JTextField dniTextField;
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JTextField telefonoTextField;
    private JTextField correoTextField;
    private JTextField codigoPostalTextField;
    private JButton modificarButton;
    private ModificarDatosCliente controlador;

    public ModificacionClienteVista() {
        super();
        setLayout(null);
        controlador = new ModificarDatosCliente(); 
        
        JLabel tituloLabel = new JLabel("Modificación Cliente");
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
        nombreTextField.setVisible(false);
        
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(40, 100, 60, 17);
        add(apellidoLabel);
        
        apellidoTextField = new JTextField();
        apellidoTextField.setBounds(140, 100, 114, 21);
        add(apellidoTextField);
        apellidoTextField.setVisible(false);
        
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(40, 130, 60, 17);
        add(telefonoLabel);
        
        telefonoTextField = new JTextField();
        telefonoTextField.setBounds(140, 130, 114, 21);
        add(telefonoTextField);
        telefonoTextField.setVisible(false);
        
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(40, 160, 60, 17);
        add(correoLabel);
        
        correoTextField = new JTextField();
        correoTextField.setBounds(140, 160, 114, 21);
        add(correoTextField);
        correoTextField.setVisible(false);
        
        JLabel codigoPostalLabel = new JLabel("Código Postal:");
        codigoPostalLabel.setBounds(40, 190, 100, 17);
        add(codigoPostalLabel);
        
        codigoPostalTextField = new JTextField();
        codigoPostalTextField.setBounds(140, 190, 114, 21);
        add(codigoPostalTextField);
        codigoPostalTextField.setVisible(false);
        
        modificarButton = new JButton("Modificar");
        modificarButton.setBounds(140, 220, 114, 21);
        add(modificarButton);
        modificarButton.setVisible(false);
        
        JButton buscarButton = new JButton("Buscar Cliente");
        buscarButton.setBounds(140, 240, 114, 21);
        add(buscarButton);
        
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarCliente();
            }
        });
    }

    private void buscarCliente() {
        String dniStr = dniTextField.getText().trim();
        if (dniStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un DNI.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int dni = Integer.parseInt(dniStr);
            ClienteUsuario cliente = controlador.consulta(dni);
            if (cliente != null) {
                nombreTextField.setText(cliente.getNombre());
                apellidoTextField.setText(cliente.getApellido());
                telefonoTextField.setText(cliente.getTelefono());
                correoTextField.setText(cliente.getCorreo());
                codigoPostalTextField.setText(cliente.getCodigoPostal());

                nombreTextField.setVisible(true);
                apellidoTextField.setVisible(true);
                telefonoTextField.setVisible(true);
                correoTextField.setVisible(true);
                codigoPostalTextField.setVisible(true);
                modificarButton.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El DNI debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarCliente() {
        int dni = Integer.parseInt(dniTextField.getText().trim());
        String nombre = nombreTextField.getText().trim();
        String apellido = apellidoTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();
        String correo = correoTextField.getText().trim();
        String codigoPostal = codigoPostalTextField.getText().trim();

        boolean exito = controlador.modificar(dni,nombre, apellido, telefono, correo, codigoPostal);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Cliente modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        clearFields();
    }

    private void clearFields() {
        dniTextField.setText("");
        nombreTextField.setText("");
        apellidoTextField.setText("");
        telefonoTextField.setText("");
        correoTextField.setText("");
        codigoPostalTextField.setText("");

        nombreTextField.setVisible(false);
        apellidoTextField.setVisible(false);
        telefonoTextField.setVisible(false);
        correoTextField.setVisible(false);
        codigoPostalTextField.setVisible(false);
        modificarButton.setVisible(false);
    }

    public void setControlador(ModificarDatosCliente modificarDatosCliente) {
        this.controlador = modificarDatosCliente;
    }
}
