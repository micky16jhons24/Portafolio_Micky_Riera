package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ModificarDatosCliente;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;

public class ModificacionClienteVista extends JPanel {
    private JTextField dniTextField; // Campo para DNI
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JTextField telefonoTextField;   // Campo para teléfono
    private JTextField correoTextField;      // Campo para correo
    private JTextField codigoPostalTextField; // Campo para código postal
    private JButton modificarButton;
    private ModificarDatosCliente controlador;

    // Constructor
    public ModificacionClienteVista() {
        super();
        setLayout(null);
        
        JLabel tituloLabel = new JLabel("Modificación Cliente");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(132, 12, 164, 17);
        add(tituloLabel);
        
        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(40, 39, 60, 17);
        add(dniLabel);
        
        dniTextField = new JTextField(); // Cambiar a JTextField para el DNI
        dniTextField.setBounds(140, 37, 114, 21);
        add(dniTextField);
        
        // Campos para modificar nombre, apellido, teléfono, correo y código postal
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(40, 70, 60, 17);
        add(nombreLabel);
        
        nombreTextField = new JTextField();
        nombreTextField.setBounds(140, 70, 114, 21);
        add(nombreTextField);
        nombreTextField.setVisible(false); // Ocultar inicialmente
        
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(40, 100, 60, 17);
        add(apellidoLabel);
        
        apellidoTextField = new JTextField();
        apellidoTextField.setBounds(140, 100, 114, 21);
        add(apellidoTextField);
        apellidoTextField.setVisible(false); // Ocultar inicialmente
        
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(40, 130, 60, 17);
        add(telefonoLabel);
        
        telefonoTextField = new JTextField();
        telefonoTextField.setBounds(140, 130, 114, 21);
        add(telefonoTextField);
        telefonoTextField.setVisible(false); // Ocultar inicialmente
        
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(40, 160, 60, 17);
        add(correoLabel);
        
        correoTextField = new JTextField();
        correoTextField.setBounds(140, 160, 114, 21);
        add(correoTextField);
        correoTextField.setVisible(false); // Ocultar inicialmente
        
        JLabel codigoPostalLabel = new JLabel("Código Postal:");
        codigoPostalLabel.setBounds(40, 190, 100, 17);
        add(codigoPostalLabel);
        
        codigoPostalTextField = new JTextField();
        codigoPostalTextField.setBounds(140, 190, 114, 21);
        add(codigoPostalTextField);
        codigoPostalTextField.setVisible(false); // Ocultar inicialmente
        
        modificarButton = new JButton("Modificar");
        modificarButton.setBounds(140, 220, 114, 21);
        add(modificarButton);
        modificarButton.setVisible(false); // Ocultar inicialmente
        
        // Botón de búsqueda
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

    // Método para buscar el cliente por DNI
    private void buscarCliente() {
        String dni = dniTextField.getText().trim();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un DNI.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Consultar cliente usando el controlador
        ClienteUsuario cliente = controlador.consultaPorDNI(dni);
        if (cliente != null) {
            // Llenar los campos con los datos del cliente encontrado
            nombreTextField.setText(cliente.getNombre());
            apellidoTextField.setText(cliente.getApellido());
            telefonoTextField.setText(cliente.getTelefono());
            correoTextField.setText(cliente.getCorreo());
            codigoPostalTextField.setText(cliente.getCodigoPostal());

            // Hacer visibles los campos y el botón de modificar
            nombreTextField.setVisible(true);
            apellidoTextField.setVisible(true);
            telefonoTextField.setVisible(true);
            correoTextField.setVisible(true);
            codigoPostalTextField.setVisible(true);
            modificarButton.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para modificar el cliente
    private void modificarCliente() {
        // Aquí debes agregar la lógica para modificar el cliente
        String dni = dniTextField.getText().trim();
        String nombre = nombreTextField.getText().trim();
        String apellido = apellidoTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();
        String correo = correoTextField.getText().trim();
        String codigoPostal = codigoPostalTextField.getText().trim();

        // Llama al controlador para modificar el cliente
        boolean exito = controlador.modificarCliente(dni, nombre, apellido, telefono, correo, codigoPostal);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Cliente modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para establecer el controlador
    public void setControlador(ModificarDatosCliente controlador) {
        this.controlador = controlador;
    }
}
