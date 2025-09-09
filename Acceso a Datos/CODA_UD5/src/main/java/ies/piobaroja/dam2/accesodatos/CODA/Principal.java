package ies.piobaroja.dam2.accesodatos.CODA;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaCODA ventana = new VentanaCODA();
            ventana.setSize(600, 400);
            ventana.setLocationRelativeTo(null); // Centrar la ventana
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
            ventana.setVisible(true); // Mostrar la ventana
        });
    }
}
