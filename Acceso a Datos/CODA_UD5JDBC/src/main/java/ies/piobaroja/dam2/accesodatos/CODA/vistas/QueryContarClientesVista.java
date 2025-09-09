package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.QueryContarClientesControlador;

public class QueryContarClientesVista extends JPanel {

    private JButton consultarButton;
    private QueryContarClientesControlador controlador;

    public QueryContarClientesVista() {
        super();
        setLayout(null);


        JLabel tituloLabel = new JLabel("TOTAL DE CLIENTES");
        tituloLabel.setFont(new Font("Castellar", Font.ITALIC, 18));
        tituloLabel.setBounds(120, 20, 250, 30);
        add(tituloLabel);

        consultarButton = new JButton("Consultar Total");
        consultarButton.setBounds(150, 120, 150, 30);
        add(consultarButton);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    long totalClientes = controlador.contarClientes();
                    JOptionPane.showMessageDialog(
                        null,
                        "El total de clientes es: " + totalClientes,
                        "Consulta Exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Controlador no configurado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

   
    public void setControlador(QueryContarClientesControlador controlador) {
        this.controlador = controlador;
    }
}
