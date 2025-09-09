package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ProductoConsultarControlador;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto; 

public class ProductoConsultar extends JPanel {
    private JTextField idTextField;
    private ProductoConsultarControlador controlador;
    
    public ProductoConsultar() {
        super();
        setLayout(null);

        JLabel tituloLabel = new JLabel("Consulta Producto");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        tituloLabel.setBounds(86, 12, 201, 17);
        add(tituloLabel);

        JLabel idLabel = new JLabel("ID Producto:");
        idLabel.setBounds(38, 41, 100, 17);
        add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(140, 39, 114, 21);
        add(idTextField);
        
        JButton btnConsulta = new JButton("Consultar");
        btnConsulta.setBounds(106, 83, 105, 27);
        add(btnConsulta);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(38, 135, 60, 17);
        add(nombreLabel);

        JLabel precioLabel = new JLabel("Precio:");
        precioLabel.setBounds(38, 165, 60, 17);
        add(precioLabel);

        JLabel nombreValorLabel = new JLabel("");
        nombreValorLabel.setBounds(96, 135, 200, 17);
        add(nombreValorLabel);

        JLabel precioValorLabel = new JLabel("");
        precioValorLabel.setBounds(96, 165, 200, 17);
        add(precioValorLabel);

        btnConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idTextField.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El ID no puede estar vacío.");
                    return;
                }
                try {
                    int id = Integer.parseInt(idText);
                    Producto producto = controlador.consultaPorID(id);
                    if (producto != null) {
                        nombreValorLabel.setText(producto.getNombre());
                        precioValorLabel.setText(String.valueOf(producto.getPrecioVenta())); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                }
            }
        });
    }

    public void setControlador(ProductoConsultarControlador controlador) {
        this.controlador = controlador;
    }
}
