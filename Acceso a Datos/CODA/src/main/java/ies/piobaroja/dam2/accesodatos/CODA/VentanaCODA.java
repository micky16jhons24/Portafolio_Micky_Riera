package ies.piobaroja.dam2.accesodatos.CODA;

import javax.swing.*;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.AltaCliente;  
import ies.piobaroja.dam2.accesodatos.CODA.controladores.BajaCliente;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ConsultarCliente;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ExportarTiendaControlador;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ImportaraTiendaControlador;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.ModificarDatosCliente;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.AltaClienteVista;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.BajaClienteVista;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.ConsultaClienteVista;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.ExportTiendaVista;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.ImportTiendaVista;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.ModificacionClienteVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCODA extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu clienteMenu; 
    private JMenuItem altaClienteJMenuItem;
    private JMenuItem bajaClienteJMenuItem;
    private JMenuItem consultaClienteJMenuItem;
    private JMenuItem modificacionClienteJMenuItem;
    private JMenu ficheroMenu;
    private JMenuItem exportarFicheroJMenuItem; 
    private JMenuItem importarFicheroJMenuItem;

    // Vistas
    private AltaClienteVista altaClienteVista;
    private BajaClienteVista bajaClienteVista;
    private ConsultaClienteVista consultaClienteVista;
    private ModificacionClienteVista modificacionClienteVista;
    private ExportTiendaVista exportTiendaVista;
    private ImportTiendaVista importTiendaVista;
    
    // Controladores
    private AltaCliente altaClienteControlador;
    private BajaCliente bajaClienteControlador;
    private ConsultarCliente consultarClienteControlador;
    private ModificarDatosCliente modificarDatosClienteControlador;

    public VentanaCODA() {
        // Inicialización de la barra de menú
        setTitle("CODA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        /*---------MENÚ COMBINADO---------*/
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        // Menú "Cliente"
        clienteMenu = new JMenu("Cliente");
        menuBar.add(clienteMenu);

        altaClienteJMenuItem = new JMenuItem("Alta Cliente");
        bajaClienteJMenuItem = new JMenuItem("Baja");
        consultaClienteJMenuItem = new JMenuItem("Consulta");
        modificacionClienteJMenuItem = new JMenuItem("Modificación");

        clienteMenu.add(altaClienteJMenuItem);
        clienteMenu.add(bajaClienteJMenuItem);
        clienteMenu.add(consultaClienteJMenuItem);
        clienteMenu.add(modificacionClienteJMenuItem);

        altaClienteJMenuItem.addActionListener(this);
        bajaClienteJMenuItem.addActionListener(this);
        consultaClienteJMenuItem.addActionListener(this);
        modificacionClienteJMenuItem.addActionListener(this);
        
        // Menú "Fichero"
        ficheroMenu = new JMenu("Fichero");
        menuBar.add(ficheroMenu);
        
        exportarFicheroJMenuItem = new JMenuItem("Exportar");
        importarFicheroJMenuItem = new JMenuItem("Importar");

        ficheroMenu.add(exportarFicheroJMenuItem);
        ficheroMenu.add(importarFicheroJMenuItem);

        exportarFicheroJMenuItem.addActionListener(this);
        importarFicheroJMenuItem.addActionListener(this);

        // Inicializar vistas
        altaClienteVista = new AltaClienteVista();
        bajaClienteVista = new BajaClienteVista();
        consultaClienteVista = new ConsultaClienteVista();
        modificacionClienteVista = new ModificacionClienteVista();
        exportTiendaVista = new ExportTiendaVista();
        importTiendaVista = new ImportTiendaVista();
        
        // Inicializar controladores
        altaClienteControlador = new AltaCliente();
        bajaClienteControlador = new BajaCliente();
        consultarClienteControlador = new ConsultarCliente();
        modificarDatosClienteControlador = new ModificarDatosCliente();

        // Asignar controladores a las vistas
        altaClienteVista.setControlador(altaClienteControlador);
        bajaClienteVista.setControlador(bajaClienteControlador);
        consultaClienteVista.setControlador(consultarClienteControlador);
        modificacionClienteVista.setControlador(modificarDatosClienteControlador);


        // Inicializar controladores ficheros
        ExportarTiendaControlador exportarControlador = new ExportarTiendaControlador();
        ImportaraTiendaControlador importarControlador = new ImportaraTiendaControlador();
        
        // Asignar controladores a las vistas de fichero
        exportTiendaVista.setControlador(exportarControlador);
        importTiendaVista.setControlador(importarControlador);

        // Configurar el panel inicial
        setContentPane(altaClienteVista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == altaClienteJMenuItem) {
            System.out.println("Alta.");
            setContentPane(altaClienteVista);
        } else if (e.getSource() == bajaClienteJMenuItem) {
            System.out.println("Baja.");
            setContentPane(bajaClienteVista);
        } else if (e.getSource() == consultaClienteJMenuItem) {
            System.out.println("Consulta.");
            setContentPane(consultaClienteVista);
        } else if (e.getSource() == modificacionClienteJMenuItem) {
            System.out.println("Modificación.");
            setContentPane(modificacionClienteVista);
        } else if (e.getSource() == exportarFicheroJMenuItem) {
            System.out.println("Exportar fichero.");
            setContentPane(exportTiendaVista);
        } else if (e.getSource() == importarFicheroJMenuItem) {
            System.out.println("Importar fichero.");
            setContentPane(importTiendaVista);
        } else {
            System.out.println("Evento no reconocido.");
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaCODA ventana = new VentanaCODA();
            ventana.setVisible(true);
        });
    }
}

    

