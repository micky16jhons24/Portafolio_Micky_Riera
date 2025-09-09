package galeria;

import java.util.HashMap;
import java.util.Scanner;


import java.util.*;

public class Principal {
	
	private static final HashMap<String,TiposObra> obras=new HashMap<String,TiposObra>() {{
	    put("PINTURA", TiposObra.PINTURA);
	    put("ESCULTURA", TiposObra.ESCULTURA);
	    put("ARTE_DIGITAL", TiposObra.ARTE_DIGITAL);
	    put("INSTALACION", TiposObra.INSTALACION);
	}};
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		String menu="1-Alta galeria\n"+
				"2-Alta obra\n"+
				"3-Asignar obra a Galeria\n"+
				"4-Cambio de galeria\n"+
				"5-Mostrar las galerias de una localizacion\n"+
				"6-Consultar las obras de una galeria ordenadas por fecha de creacion\n"+			
				"7-Mostrar la cantidad de los distintos tipo de obra de una galeria\n"+
				"8-Averiguar si una obra pertence a una galería concreta\n "+
				"9-Salir\n";

		String nombre="";
		String localizacion="";
		String titulo="";
		String autor="";
		String fechaCreacion="";
		String tipoObra;
		int idGaleria;
		int idObra;
		int idGaleriaNueva;

		
		init();
		int opcion=-1;
		do {
			System.out.println(menu);
			opcion=sc.nextInt();
			switch(opcion) {
			case 1:
				System.out.println("Introduce nombre:");
				nombre=sc.next();
				System.out.println("Introduce localizacion:");
				localizacion=sc.next();
				altaGaleria(nombre,localizacion);
				break;
			case 2:
				System.out.println("Introduce titulo:");
				titulo=sc.next();
				System.out.println("Introduce autor:");
				autor=sc.next();
				System.out.println("Introduce fecha de creacion:");
				fechaCreacion=sc.next();
				System.out.println("Introduce tipo(PINTURA, ESCULTURA, ARTE_DIGITAL, INSTALACION):");
				tipoObra=sc.next();
				altaObra(titulo,autor,fechaCreacion,obras.get(tipoObra));
				break;
			case 3:
				System.out.println("Introduce id Galeria:");
				idGaleria=sc.nextInt();
				System.out.println("Introduce id Obra:");
				idObra=sc.nextInt();
				asignarObraGaleria(idGaleria, idObra);
				break;
			case 4:
				System.out.println("Introduce id Galeria:");
				idGaleria=sc.nextInt();
				System.out.println("Introduce id Obra:");
				idObra=sc.nextInt();
				System.out.println("Introduce id nueva Galeria:");
				idGaleriaNueva=sc.nextInt();
				trasladarObra(idObra, idGaleria, idGaleriaNueva);
				break;
			case 5:
				System.out.println("Introduce localizacion:");
				localizacion=sc.next();
				mostrarGaleriasLocalizacion(localizacion);
				break;
			case 6:
				System.out.println("Introduce id Galeria:");
				idGaleria=sc.nextInt();
				mostrarObrasGaleriaOrdenadasPorFecha(idGaleria);
				break;
			case 7:
				System.out.println("Introduce id Galeria:");
				idGaleria=sc.nextInt();
				mostrarCantidadTipoObras(idGaleria);
				break;
			case 8:
				System.out.println("Introduce id Obra:");
				idObra=sc.nextInt();
				obtenerGaleriaObra(idObra);
				break;
			case 9:
				System.out.println("Exit.");
				break;
			default:
				System.out.println("Opcion incorrecta.");
			}
			
		}while(opcion!=9);
	}
	
	private static void init() {
	}
	
	
	//TODO 5
	private static void altaGaleria(String nombre, String localizacion) {
	}

	//TODO 6
	private static void altaObra(String titulo, String autor, String fechaCreacion, TiposObra tipo) {
	}
	
	//TODO 7
	private static void asignarObraGaleria(long idGaleria, long idObra) {
	}
	
	//TODO 8
	public static void trasladarObra(long idObra, long idGaleria, long idGaleriaNueva) {
		
	}

	//TODO 9
	private static void mostrarGaleriasLocalizacion(String localizacion) {
	}
	
	//TODO 10
	private static void mostrarObrasGaleriaOrdenadasPorFecha(long idGaleria) {
	}		
	
	
	//TODO 11
	private static void mostrarCantidadTipoObras(long idGaleria) {
	}
	
	
	//TODO 12
	private static void obtenerGaleriaObra(long idObra) {
	}
	

	
}
