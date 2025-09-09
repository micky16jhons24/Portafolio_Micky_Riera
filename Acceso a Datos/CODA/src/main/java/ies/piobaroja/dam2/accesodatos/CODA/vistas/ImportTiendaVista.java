package ies.piobaroja.dam2.accesodatos.CODA.vistas;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ies.piobaroja.dam2.accesodatos.CODA.controladores.ImportaraTiendaControlador;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class ImportTiendaVista extends JPanel {
    
	
	private JTextField nombreFicherotextField;
    private JRadioButton xmlTextField;
    private JRadioButton objTextField;
    private JRadioButton csvTextField;
    private JRadioButton jsonTextField;
   
    
    // Controlador de la vista
    private ImportaraTiendaControlador controlador;

    public ImportTiendaVista() {
        super();
        setLayout(null);

        // Inicializar el controlador
        controlador = new ImportaraTiendaControlador();

        // Crear y configurar componentes
        JLabel lblImportar = new JLabel("Importar");
        lblImportar.setFont(new Font("Dialog", Font.BOLD, 14));
        lblImportar.setBounds(223, 31, 164, 17);
        add(lblImportar);

        JLabel nombreFicherolNewLabel = new JLabel("Nombre Fichero");
        nombreFicherolNewLabel.setBounds(65, 89, 120, 17);
        add(nombreFicherolNewLabel);
        
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(38, 194, 60, 17);
        add(nombreLabel);
        
        
        
        JLabel nombreValorLabel = new JLabel("");
        nombreValorLabel.setBounds(96, 194, 200, 17);
        add(nombreValorLabel);
        
      

        nombreFicherotextField = new JTextField();
        nombreFicherotextField.setColumns(10);
        nombreFicherotextField.setBounds(201, 87, 114, 21);
        add(nombreFicherotextField);

        xmlTextField = new JRadioButton(".xml");
        xmlTextField.setBounds(75, 114, 84, 77);
        add(xmlTextField);

        jsonTextField = new JRadioButton(".json");
        jsonTextField.setBounds(167, 114, 84, 77);
        add(jsonTextField);

        objTextField = new JRadioButton(".obj");
        objTextField.setBounds(247, 116, 84, 77);
        add(objTextField);

        csvTextField = new JRadioButton(".csv");
        csvTextField.setBounds(335, 114, 84, 77);
        add(csvTextField);


        // Agrupando los botones de opci�n
        ButtonGroup bgTipoFichero = new ButtonGroup();
        bgTipoFichero.add(xmlTextField);
        bgTipoFichero.add(jsonTextField);
        bgTipoFichero.add(objTextField);
        bgTipoFichero.add(csvTextField);

        JButton importarButton = new JButton("Importar");
        importarButton.setBounds(156, 194, 120, 30);
        add(importarButton);
        
        // Lógica del botón "Generar"
        importarButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
			@Override
            public void actionPerformed(ActionEvent e) {
            	
            	String nombreFichero = nombreFicherotextField.getText();
               
            	// Validar que el nombre del fichero no esté vacío
                if (!nombreFichero.isEmpty()) {
                    try {
                        if (xmlTextField.isSelected()) {
                            System.out.println("Fichero XML importado");
                            controlador.importXML(nombreFichero);
                            JOptionPane.showMessageDialog(ImportTiendaVista.this, "El fichero '" + nombreFichero + "' ha sido importado como XML.", 
                                    "Importaci�n Exitosa", 
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if (jsonTextField.isSelected()) {
                        	System.out.println("Fichero JSON importado");
                            controlador.importarJSON(nombreFichero);
                            JOptionPane.showMessageDialog(ImportTiendaVista.this,"El fichero '" + nombreFichero + "' ha sido importado como JSON.", 
                                    "Importaci�n Exitosa", 
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if (csvTextField.isSelected()) {
                        	 System.out.println("Fichero CSV importado");
                            controlador.importCSV(nombreFichero);
                            JOptionPane.showMessageDialog(ImportTiendaVista.this, "El fichero '" + nombreFichero + "' ha sido importado como CSV.", 
                                    "Importaci�n Exitosa", 
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if (objTextField.isSelected()) {
                            System.out.println("Fichero OBJ importado");
                            controlador.importOBJ(nombreFichero);  // Se llama al método importOBJ aquí
                            JOptionPane.showMessageDialog(ImportTiendaVista.this,
                                "El fichero '" + nombreFichero + "' ha sido importado como OBJ.",
                                "Importación Exitosa", 
                                JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (Exception var3) {
                        JOptionPane.showMessageDialog(ImportTiendaVista.this,"Ocurri� un error al importar el fichero.", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                } if (nombreFichero != null) {
                    // Mostrar los valores en las etiquetas
                    nombreValorLabel.setText(nombreFichero);
                }            
                else {
                    JOptionPane.showMessageDialog(ImportTiendaVista.this,    "El nombre del fichero no puede estar vac�o.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

	public void setControlador(ImportaraTiendaControlador controlador) {
		this.controlador = controlador;
	}
    
    
    
}
