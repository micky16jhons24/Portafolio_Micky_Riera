package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.ExportarTiendaControlador;

public class ExportTiendaVista extends JPanel {
    
    private JTextField nombreFicheroField;
    private JRadioButton rdbtnXml;
    private JRadioButton rdbtnJson;
    private JRadioButton rdbtnObj;
    private JRadioButton rdbtnCsv;
    private ExportarTiendaControlador controlador;

    public ExportTiendaVista() {
        setLayout(null);

        // Inicializar controlador
        controlador = new ExportarTiendaControlador();

        // Título
        JLabel tituloLabel = new JLabel("EXPORTAR FICHERO");
        tituloLabel.setFont(new Font("Castellar", Font.PLAIN, 18));
        tituloLabel.setBounds(120, 20, 250, 30);
        add(tituloLabel);

        // Campo de texto para nombre del fichero
        JLabel nombreFicheroLabel = new JLabel("Nombre Fichero:");
        nombreFicheroLabel.setBounds(50, 80, 150, 20);
        add(nombreFicheroLabel);

        nombreFicheroField = new JTextField();
        nombreFicheroField.setBounds(156, 80, 150, 20);
        add(nombreFicheroField);


        // Radio buttons para formatos de archivo
        rdbtnXml = new JRadioButton(".xml");
        rdbtnXml.setBounds(50, 120, 70, 25);
        add(rdbtnXml);

        rdbtnJson = new JRadioButton(".json");
        rdbtnJson.setBounds(130, 120, 70, 25);
        add(rdbtnJson);

        rdbtnObj = new JRadioButton(".obj");
        rdbtnObj.setBounds(210, 120, 70, 25);
        add(rdbtnObj);

        rdbtnCsv = new JRadioButton(".csv");
        rdbtnCsv.setBounds(290, 120, 70, 25);
        add(rdbtnCsv);
        
        
        
        

        // Agrupar los RadioButtons para que solo se pueda seleccionar uno
        ButtonGroup bgTipoFichero = new ButtonGroup();
        bgTipoFichero.add(rdbtnXml);
        bgTipoFichero.add(rdbtnJson);
        bgTipoFichero.add(rdbtnObj);
        bgTipoFichero.add(rdbtnCsv);
        
        // Botón para generar exportación
        JButton btnGenerar = new JButton("Exportar");
        btnGenerar.setBounds(167, 153, 105, 27);
        add(btnGenerar);
        

        // Lógica para el botón "Generar"
        btnGenerar.addActionListener(new ActionListener() {
        
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
                String nombreFichero = nombreFicheroField.getText();
                if (!nombreFichero.equals("")) {
                    try {
                        if (rdbtnXml.isSelected()) {
                        	System.out.println("Fichero XML generado");
                            controlador.exportarXML(nombreFichero);
                            JOptionPane.showMessageDialog(ExportTiendaVista.this, "Fichero XML '" + nombreFichero + "' generado con �xito.",
                                    "Exportaci�n Exitosa",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if (rdbtnJson.isSelected()) {
                        	System.out.println("Fichero JSON generado");
                            controlador.exportarJSON(nombreFichero);
                            JOptionPane.showMessageDialog(ExportTiendaVista.this, "Fichero JSON '" + nombreFichero + "' generado con �xito.",
                                    "Exportaci�n Exitosa",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if (rdbtnObj.isSelected()) {
                        	System.out.println("Fichero OBJ generado");
                            controlador.exportarObj(nombreFichero);
                            JOptionPane.showMessageDialog(ExportTiendaVista.this, "Fichero OBJ '" + nombreFichero + "' generado con �xito.",
                                    "Exportaci�n Exitosa",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if (rdbtnCsv.isSelected()) {
                        	System.out.println("Fichero CSV generado");
                            controlador.exportarCSV(nombreFichero);
                            JOptionPane.showMessageDialog(ExportTiendaVista.this, "Fichero CSV '" + nombreFichero + "' generado con �xito.",
                                    "Exportaci�n Exitosa",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ExportTiendaVista.this, "Error al exportar el fichero" + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                        
                    }
                } else {
                    JOptionPane.showMessageDialog(ExportTiendaVista.this, "El nombre del fichero es obligatorio","Error" ,JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

	public void setControlador(ExportarTiendaControlador controlador) {
		this.controlador = controlador;
	}
    

}
