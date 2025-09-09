package ies.piobaroja.dam2.accesodatos.CODA.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;

public class TiendaDAO {
	
	private Connection conexion;
	private final String URL="jdbc:mariadb://localhost:3306/coda"; 
	private final String usuario="codaAdmin";
	private final String password="12345678";

    public TiendaDAO() throws SQLException {
      conexion=DriverManager.getConnection(URL,usuario,password);
    }

  

    public List<Producto> consultarProductosPorDisponibilidad(boolean disponibilidad) {
		return null;
       
    }

    public void altaCliente(ClienteUsuario cliente) throws SQLException {
       String query="Insert into clienteusuario(dni, apellido, codigoPostal, correo, nombre, telefono)" + 
    "values(?,?,?,?,?,?)";
       
       PreparedStatement pstmt=conexion.prepareStatement(query);
       pstmt.setString(1, cliente.getDni());
       pstmt.setString(1, cliente.getApellido());
       pstmt.setString(1, cliente.getCodigoPostal());
       pstmt.setString(1, cliente.getCorreo());
       pstmt.setString(1, cliente.getNombre());
       pstmt.setString(1, cliente.getTelefono());
       pstmt.executeUpdate();
    }

    public ClienteUsuario consultaClienteDNI(String dni) {
		ClienteUsuario cliente=null;
    	String query="Select * from ClienteUsuario m " + "Where dni=?";
		try {
			PreparedStatement pstmt=conexion.prepareStatement(query);
			pstmt.setString(1, dni);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				cliente=new ClienteUsuario(rs.getString("dni"),rs.getString("apellido"),rs.getString("CodigoPostal"), rs.getString("correo"),rs.getString("nombre"),rs.getString("telefono"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
        
    }

    public boolean modificacion(ClienteUsuario cliente) {
		return false;
       
    }

    public boolean bajaCliente(String dni) {
		return false;
        
    }

    public void altaProductoAlquiler(AlquilerProducto alquilerProducto) {
        
    }

    public AlquilerProducto consultaProductoAlquiler(int id) {
		return null;
        
    }

    public boolean modificarProductoAlquiler(AlquilerProducto alquilerProducto) {
		return false;
        
    }

    public boolean bajaProductoAlquiler(int id) {
		return false;
        
    }

    public void altaProducto(Producto producto) {

    }

    public boolean bajaProducto(int id) {
		return false;
        
    }

    public Producto consultaProducto(int id) {
		return null;
       
    }

    public boolean modificarProducto(Producto productoModificado) {
		return false;
       
    }

    public boolean asignarProductoACliente(int productoId, String clienteDni) {
		return false;
       
    }


    public void close() {
        
    }
}
