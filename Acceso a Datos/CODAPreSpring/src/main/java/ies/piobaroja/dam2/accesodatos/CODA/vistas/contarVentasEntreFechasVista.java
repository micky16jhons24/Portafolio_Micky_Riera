package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.contarVentasEntreFechasControlador;

public class contarVentasEntreFechasVista extends JPanel {
    
    private contarVentasEntreFechasControlador controlador;
    private JTextField fechaInicioTextField;
    private JTextField fechaFinTextField;
    private JButton consultarButton;

    public contarVentasEntreFechasVista() {
        super();
        setLayout(null);

        controlador = new contarVentasEntreFechasControlador();

        JLabel tituloLabel = new JLabel("VENTAS ENTRE FECHAS");
        tituloLabel.setFont(new Font("Castellar", Font.ITALIC, 18));
        tituloLabel.setBounds(120, 20, 300, 30);
        add(tituloLabel);

        JLabel fechaInicioLabel = new JLabel("Fecha Inicio (YYYY-MM-DD):");
        fechaInicioLabel.setBounds(50, 70, 200, 25);
        add(fechaInicioLabel);

        fechaInicioTextField = new JTextField();
        fechaInicioTextField.setBounds(250, 70, 150, 25);
        add(fechaInicioTextField);

        JLabel fechaFinLabel = new JLabel("Fecha Fin (YYYY-MM-DD):");
        fechaFinLabel.setBounds(50, 100, 200, 25);
        add(fechaFinLabel);

        fechaFinTextField = new JTextField();
        fechaFinTextField.setBounds(250, 100, 150, 25);
        add(fechaFinTextField);

        consultarButton = new JButton("Consultar Ventas");
        consultarButton.setBounds(150, 150, 150, 30);
        add(consultarButton);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    LocalDate fechaInicioLocal = LocalDate.parse(fechaInicioTextField.getText(), formatter);
                    LocalDate fechaFinLocal = LocalDate.parse(fechaFinTextField.getText(), formatter);

                    Date fechaInicio = Date.valueOf(fechaInicioLocal);
                    Date fechaFin = Date.valueOf(fechaFinLocal);

                    long totalVentas = controlador.contarVentasEntreFechas(fechaInicioLocal, fechaFinLocal);

                    JOptionPane.showMessageDialog(
                        null,
                        "El total de ventas entre las fechas es: " + totalVentas,
                        "Consulta Exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Error al parsear las fechas. Por favor, introduce un formato válido (YYYY-MM-DD).",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
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

    public void setControlador(contarVentasEntreFechasControlador controlador) {
        this.controlador = controlador;
    }
}
