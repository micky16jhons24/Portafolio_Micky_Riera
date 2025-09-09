package ies.piobaroja.dam2.accesodatos.CODA;

import javax.swing.*;
import ies.piobaroja.dam2.accesodatos.CODA.controladores.*;
import ies.piobaroja.dam2.accesodatos.CODA.vistas.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaCODA extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu clienteMenu, alquilerMenu, productoMenu;
    private JMenuItem altaClienteJMenuItem, bajaClienteJMenuItem, consultaClienteJMenuItem, modificacionClienteJMenuItem;
    private JMenuItem altaAlquilerJMenuItem, bajaAlquilerJMenuItem, consultaAlquilerJMenuItem, modificacionAlquilerJMenuItem;
    private JMenuItem altaProductoJMenuItem, bajaProductoJMenuItem, consultaProductoJMenuItem, modificacionProductoJMenuItem;
    private JMenuItem ventaProductoJMenuItem;
    private JMenuItem asignarProductoJMenuItem;


    public VentanaCODA() {
        setTitle("CODA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        clienteMenu = new JMenu("Cliente");
        menuBar.add(clienteMenu);

        altaClienteJMenuItem = new JMenuItem("Alta Cliente");
        bajaClienteJMenuItem = new JMenuItem("Baja Cliente");
        consultaClienteJMenuItem = new JMenuItem("Consulta Cliente");
        modificacionClienteJMenuItem = new JMenuItem("Modificación Cliente");

        clienteMenu.add(altaClienteJMenuItem);
        clienteMenu.add(bajaClienteJMenuItem);
        clienteMenu.add(consultaClienteJMenuItem);
        clienteMenu.add(modificacionClienteJMenuItem);

        altaClienteJMenuItem.addActionListener(this);
        bajaClienteJMenuItem.addActionListener(this);
        consultaClienteJMenuItem.addActionListener(this);
        modificacionClienteJMenuItem.addActionListener(this);

        alquilerMenu = new JMenu("Alquiler");
        menuBar.add(alquilerMenu);

        altaAlquilerJMenuItem = new JMenuItem("Alta Alquiler");
        bajaAlquilerJMenuItem = new JMenuItem("Baja Alquiler");
        consultaAlquilerJMenuItem = new JMenuItem("Consulta Alquiler");
        modificacionAlquilerJMenuItem = new JMenuItem("Modificación Alquiler");

        alquilerMenu.add(altaAlquilerJMenuItem);
        alquilerMenu.add(bajaAlquilerJMenuItem);
        alquilerMenu.add(consultaAlquilerJMenuItem);
        alquilerMenu.add(modificacionAlquilerJMenuItem);

        altaAlquilerJMenuItem.addActionListener(this);
        bajaAlquilerJMenuItem.addActionListener(this);
        consultaAlquilerJMenuItem.addActionListener(this);
        modificacionAlquilerJMenuItem.addActionListener(this);

        productoMenu = new JMenu("Producto");
        menuBar.add(productoMenu);

        altaProductoJMenuItem = new JMenuItem("Alta Producto");
        bajaProductoJMenuItem = new JMenuItem("Baja Producto");
        consultaProductoJMenuItem = new JMenuItem("Consulta Producto");
        modificacionProductoJMenuItem = new JMenuItem("Modificación Producto");
        ventaProductoJMenuItem = new JMenuItem("Venta Producto");

        productoMenu.add(altaProductoJMenuItem);
        productoMenu.add(bajaProductoJMenuItem);
        productoMenu.add(consultaProductoJMenuItem);
        productoMenu.add(modificacionProductoJMenuItem);
        productoMenu.add(ventaProductoJMenuItem);

        altaProductoJMenuItem.addActionListener(this);
        bajaProductoJMenuItem.addActionListener(this);
        consultaProductoJMenuItem.addActionListener(this);
        modificacionProductoJMenuItem.addActionListener(this);
        ventaProductoJMenuItem.addActionListener(this);
        
        asignarProductoJMenuItem = new JMenuItem("Asignar Producto a Cliente");
        productoMenu.add(asignarProductoJMenuItem);
        asignarProductoJMenuItem.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == altaClienteJMenuItem) {
            AltaClienteVista altaClienteVista = new AltaClienteVista();
            altaClienteVista.setControlador(new AltaCliente()); // Establecer controlador
            cambiarVista(altaClienteVista);
        } else if (e.getSource() == bajaClienteJMenuItem) {
            BajaClienteVista bajaClienteVista = new BajaClienteVista();
            bajaClienteVista.setControlador(new BajaCliente()); // Establecer controlador
            cambiarVista(bajaClienteVista);
        } else if (e.getSource() == consultaClienteJMenuItem) {
            ConsultaClienteVista consultaClienteVista = new ConsultaClienteVista();
            consultaClienteVista.setControlador(new ConsultarCliente()); // Establecer controlador
            cambiarVista(consultaClienteVista);
        } else if (e.getSource() == modificacionClienteJMenuItem) {
            ModificacionClienteVista modificacionClienteVista = new ModificacionClienteVista();
            modificacionClienteVista.setControlador(new ModificarDatosCliente()); // Establecer controlador
            cambiarVista(modificacionClienteVista);
        } else if (e.getSource() == altaAlquilerJMenuItem) {
            AlquilerAltaProducto alquilerAltaProductoVista = new AlquilerAltaProducto();
            alquilerAltaProductoVista.setControlador(new AlquilerAltaProductoControlador()); // Establecer controlador
            cambiarVista(alquilerAltaProductoVista);
        } else if (e.getSource() == bajaAlquilerJMenuItem) {
            AlquilerBajaProducto alquilerBajaProductoVista = new AlquilerBajaProducto();
            alquilerBajaProductoVista.setControlador(new AlquilerBajaProductoControlador()); // Establecer controlador
            cambiarVista(alquilerBajaProductoVista);
        } else if (e.getSource() == consultaAlquilerJMenuItem) {
            AlquilerConsultaProducto alquilerConsultaProductoVista = new AlquilerConsultaProducto();
            alquilerConsultaProductoVista.setControlador(new AlquilerConsultaProductoControlador()); // Establecer controlador
            cambiarVista(alquilerConsultaProductoVista);
        } else if (e.getSource() == modificacionAlquilerJMenuItem) {
            AlquilerModificacionProducto alquilerModificacionProductoVista = new AlquilerModificacionProducto();
            alquilerModificacionProductoVista.setControlador(new AlquilerModificacionProductoControlador()); // Establecer controlador
            cambiarVista(alquilerModificacionProductoVista);
        } else if (e.getSource() == altaProductoJMenuItem) {
            ProductoAlta productoAltaVista = new ProductoAlta();
            productoAltaVista.setControlador(new ProductoAltaControlador()); // Establecer controlador
            cambiarVista(productoAltaVista);
        } else if (e.getSource() == bajaProductoJMenuItem) {
            ProductoEliminar productoEliminarVista = new ProductoEliminar();
            productoEliminarVista.setControlador(new ProductoEliminarControlador()); // Establecer controlador
            cambiarVista(productoEliminarVista);
        } else if (e.getSource() == consultaProductoJMenuItem) {
            ProductoConsultar productoConsultarVista = new ProductoConsultar();
            try {
				productoConsultarVista.setControlador(new ProductoConsultarControlador());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // Establecer controlador
            cambiarVista(productoConsultarVista);
        } else if (e.getSource() == modificacionProductoJMenuItem) {
            ProductoModificar productoModificacionVista = new ProductoModificar();
            productoModificacionVista.setControlador(new ProductoModificarControlador()); // Ajustar aquí si el controlador requiere un parámetro
            cambiarVista(productoModificacionVista);
        }else if (e.getSource() == asignarProductoJMenuItem) {
            AsignarProductoVista asignarProductoVista = new AsignarProductoVista();
            asignarProductoVista.setControlador(new AsignarProductoControlador()); // Establecer controlador
            cambiarVista(asignarProductoVista);
        }

//        } else if (e.getSource() == ventaProductoJMenuItem) {
//            VentaProductoVista ventaProductoVista = new VentaProductoVista(null, null);
//            ventaProductoVista.setControlador(new VentaProductoControlador(null)); // Establecer controlador
//            cambiarVista(ventaProductoVista);
//        }
    }

    private void cambiarVista(JPanel nuevaVista) {
        getContentPane().removeAll();
        getContentPane().add(nuevaVista);
        revalidate();
        repaint();
    }
}

