package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.AlquilerConsultaProductoControlador;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;

public class AlquilerConsultaProducto extends JPanel {
    private AlquilerConsultaProductoControlador controlador;
    private JTextField idTextField;
    private JTextArea resultTextArea;

    public AlquilerConsultaProducto() {
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Consultar Producto de Alquiler");
        tituloLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(tituloLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("ID Producto:"));
        idTextField = new JTextField(10);
        inputPanel.add(idTextField);

        JButton consultarButton = new JButton("Consultar");
        inputPanel.add(consultarButton);
        add(inputPanel, BorderLayout.CENTER);

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        add(new JScrollPane(resultTextArea), BorderLayout.SOUTH);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idTextField.getText().trim();
                try {
                    int id = Integer.parseInt(idText);
                    AlquilerProducto producto = controlador.consultarProducto(id);
                    mostrarResultado(producto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID debe ser un número.");
                }
            }
        });
    }

    public void setControlador(AlquilerConsultaProductoControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarResultado(AlquilerProducto producto) {
        if (producto != null) {
            resultTextArea.setText("Producto encontrado:\n" + producto.toString());
        } else {
            resultTextArea.setText("Producto no encontrado.");
        }
    }
}
