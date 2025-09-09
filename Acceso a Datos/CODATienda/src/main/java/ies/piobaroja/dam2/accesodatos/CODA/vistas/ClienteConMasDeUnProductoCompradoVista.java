package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.ClientesConMasDeUnProductoCompradoControlador;

public class ClienteConMasDeUnProductoCompradoVista extends JPanel {
    private ClientesConMasDeUnProductoCompradoControlador controlador;
    private JButton consultarButton;

    public ClienteConMasDeUnProductoCompradoVista() {
        super();
        setLayout(null);

        controlador = new ClientesConMasDeUnProductoCompradoControlador();

        JLabel tituloLabel = new JLabel("CLIENTES CON MÁS DE UN PRODUCTO COMPRADO");
        tituloLabel.setFont(new Font("Castellar", Font.PLAIN, 16));
        tituloLabel.setBounds(50, 20, 500, 30);
        add(tituloLabel);

        consultarButton = new JButton("Consultar Clientes");
        consultarButton.setBounds(150, 80, 200, 30);
        add(consultarButton);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long totalClientes = controlador.contarClientesConMasDeUnProductoComprado();

                    JOptionPane.showMessageDialog(
                        null,
                        "El total de clientes con más de un producto comprado es: " + totalClientes,
                        "Consulta Exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Ocurrió un error al realizar la consulta: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    public void setControlador(ClientesConMasDeUnProductoCompradoControlador controlador) {
        this.controlador = controlador;
    }
}
