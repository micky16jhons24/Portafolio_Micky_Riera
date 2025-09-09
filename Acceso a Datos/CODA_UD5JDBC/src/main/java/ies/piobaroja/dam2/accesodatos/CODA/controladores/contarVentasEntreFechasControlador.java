package ies.piobaroja.dam2.accesodatos.CODA.controladores;

import java.sql.Date;
import java.time.LocalDate;

import ies.piobaroja.dam2.accesodatos.CODA.dao.TiendaDAO;

public class contarVentasEntreFechasControlador {
	 
	private TiendaDAO tiendaDAO;

	    public contarVentasEntreFechasControlador() {
	        tiendaDAO = new TiendaDAO();
	    }
	    
	    public long contarVentasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
	        return tiendaDAO.contarVentasEntreFechas(fechaInicio, fechaFin);
	    }


	    
}
