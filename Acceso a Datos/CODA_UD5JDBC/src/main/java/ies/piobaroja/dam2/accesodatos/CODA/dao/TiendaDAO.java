package ies.piobaroja.dam2.accesodatos.CODA.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.VentaProducto;

public class TiendaDAO {
  

	private Connection conexion;
	private final String URL="jdbc:mariadb://localhost:3306/coda";
	private final String usuario="codaAdmin";
	private final String password="12345678";
	
	
	public TiendaDAO() {
		try {
			conexion=DriverManager.getConnection(URL, usuario, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public List<Producto> consultarProductosPorDisponibilidad(boolean disponibilidad) {
	    List<Producto> productos = new ArrayList<>();
	    String query = "SELECT * FROM producto WHERE disponibilidad = ?";
	    try (PreparedStatement pstmt = conexion.prepareStatement(query)) { // por que esta entre el try el PreparedStatementpor por que  está dentro de un try-with-resources.
	    	//que significa eso En este bloque, cualquier recurso que implementa la interfaz AutoCloseable (como PreparedStatement y ResultSet) será cerrado automáticamente al final del bloque, incluso si ocurre una excepción.
	        pstmt.setBoolean(1, disponibilidad); // Filtrar por disponibilidad
	        try (ResultSet rs = pstmt.executeQuery()) { //
	            while (rs.next()) {
	                Producto producto = new Producto(
	                    rs.getString("nombre"),
	                    rs.getString("talla"),
	                    rs.getDouble("precioVenta"),
	                    rs.getBoolean("disponibilidad")
	                );
	                producto.setId(rs.getInt("id")); //Esto asegura de asignar el ID del producto
	                productos.add(producto); // Aqui Agregamos a la lista
	            }
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al consultar productos por disponibilidad: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return productos; // Devolvemos la lista de productos ya que no tenemos un void
	}


   public void altaCliente(ClienteUsuario cliente) {
	    String query = "INSERT INTO ClienteUsuario (apellido, codigoPostal, correo, nombre, telefono)"
	                 + " VALUES (?, ?, ?, ?, ?)";
	    try {
	        PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, cliente.getApellido());
	        pstmt.setString(2, cliente.getCodigoPostal());
	        pstmt.setString(3, cliente.getCorreo());
	        pstmt.setString(4, cliente.getNombre());
	        pstmt.setString(5, cliente.getTelefono());
	        pstmt.executeUpdate();
	        // aqui obtenemos el ID generado
	        ResultSet keys = pstmt.getGeneratedKeys();
	        if (keys.next()) {
	            cliente.setDni(keys.getInt(1)); // Asignamos aqui el ID
	        }
	        System.out.println("Cliente dado de alta con ID: " + cliente.getDni());
	    } catch (SQLException e) {
	        System.out.println("Error al insertar cliente: " + e.getMessage());
	    }
	}


   public ClienteUsuario consultaClienteDNI(int dni) {
	    ClienteUsuario cliente = null;
	    String query = "SELECT * FROM clienteusuario WHERE dni = ?";
	    try {
	    	PreparedStatement pstmt = conexion.prepareStatement(query);
	        pstmt.setInt(1, dni);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                cliente = new ClienteUsuario(
	                    rs.getString("nombre"),
	                    rs.getString("apellido"),
	                    rs.getString("telefono"),
	                    rs.getString("correo"),
	                    rs.getString("codigoPostal")
	                );
	                cliente.setDni(dni); // Asegúrar de asignar el DNI sino da un error
	            } else {
	                System.err.println("No se encontró cliente con DNI: " + dni);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al consultar cliente con DNI: " + dni);
	        e.printStackTrace();
	    }
	    return cliente;
	}


    public boolean modificacion(ClienteUsuario cliente) {
        String query = "UPDATE Clienteusuario SET apellido=?, codigoPostal=?, correo=?, nombre=?, telefono=? WHERE dni=?";
        PreparedStatement pstmt;

        try {
            pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, cliente.getApellido());
            pstmt.setString(2, cliente.getCodigoPostal());
            pstmt.setString(3, cliente.getCorreo());
            pstmt.setString(4, cliente.getNombre());
            pstmt.setString(5, cliente.getTelefono());
            pstmt.setInt(6, cliente.getDni()); // Aquí usamos dni solo para localizar al cliente
            int filasAfectadas = pstmt.executeUpdate();//con esto sabemos si se a realizado bien todo mediante execuuteUpdate
            System.out.println("Filas afectadas: " + filasAfectadas);
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean bajaCliente(int dni) {
        String queryDesvincularProductos = "UPDATE producto SET cliente_id = NULL WHERE cliente_id = ?"; //primero cambiamos el dni de id_cliente de producto a null , para desvincular la relacion                       
        String queryEliminarVentas = "DELETE FROM ventaproducto WHERE cliente_id = ?"; //Luego borramos cliente_id de la tabla VENTAPRODUCTO
        String queryEliminarCliente = "DELETE FROM clienteusuario WHERE dni = ?"; //Luego borramos DNI  de la tabla clienteUsuario
        try {
            // Comenzar una transacción
            conexion.setAutoCommit(false);
            // Paso 1: Desvincular los productos del cliente
            try (PreparedStatement pstmtProductos = conexion.prepareStatement(queryDesvincularProductos)) {
                pstmtProductos.setInt(1, dni);
                pstmtProductos.executeUpdate();
            }
            // Paso 2: Eliminar las ventas asociadas al cliente
            try (PreparedStatement pstmtVentas = conexion.prepareStatement(queryEliminarVentas)) {
                pstmtVentas.setInt(1, dni);
                pstmtVentas.executeUpdate();
            }
            // Paso 3: Eliminar el cliente
            try (PreparedStatement pstmtCliente = conexion.prepareStatement(queryEliminarCliente)) {
                pstmtCliente.setInt(1, dni);
                int rowsAffected = pstmtCliente.executeUpdate();
                if (rowsAffected > 0) {
                    // Confirmar la transacción si se eliminó el cliente correctamente
                    conexion.commit();
                    System.out.println("Cliente con DNI " + dni + " eliminado correctamente.");
                    return true;
                } else {
                    // Si no se eliminó el cliente, deshacer la transacción
                    conexion.rollback();
                    System.err.println("No se encontró cliente con DNI: " + dni);
                }
            }
        } catch (SQLException e) {
            // Manejamos errores y deshacemos la transacción si ocurre algún problema
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            // Restaurar el modo de confirmación automática
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public void altaProductoAlquiler(AlquilerProducto alquilerProducto) {
        String query = "INSERT INTO producto (nombre, talla, precioVenta, disponibilidad, fechaAlquiler, telefono, duracionDias, DTYPE) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Asignar los valores al PreparedStatement
            pstmt.setString(1, alquilerProducto.getNombre());
            pstmt.setString(2, alquilerProducto.getTalla());
            pstmt.setDouble(3, alquilerProducto.getPrecioVenta());
            pstmt.setBoolean(4, alquilerProducto.isDisponibilidad());
            pstmt.setDate(5, java.sql.Date.valueOf(alquilerProducto.getFechaAlquiler())); // fechaAlquiler
            pstmt.setInt(6, alquilerProducto.getTelefono());
            pstmt.setInt(7, alquilerProducto.getDuracionDias()); 
            pstmt.setString(8, "AlquilerProducto"); // Campo discriminador DTYPE , cambia si es prodicto  o alquiler producto
            // Ejecutar la inserción
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Asignar el ID autoincremental al objeto , si no realizo esto me da un fallo por que crea el alquiler sin ID
                        alquilerProducto.setId(rs.getInt(1));
                        System.out.println("AlquilerProducto insertado con ID: " + alquilerProducto.getId());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar AlquilerProducto: " + e.getMessage());
            e.printStackTrace();
        }
    }




    public AlquilerProducto consultaProductoAlquiler(int id) {
        AlquilerProducto alquilerProducto = null;
        String query = "SELECT * FROM producto WHERE id = ? AND DTYPE = 'AlquilerProducto'";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Aqui Recuperamos fechaAlquiler asegurándonos de que no sea null
                    java.sql.Date fechaAlquilerSql = rs.getDate("fechaAlquiler");
                    LocalDate fechaAlquiler = (fechaAlquilerSql != null) ? fechaAlquilerSql.toLocalDate() : null;

                    alquilerProducto = new AlquilerProducto(
                        rs.getString("nombre"),
                        rs.getString("talla"),
                        rs.getDouble("precioVenta"),
                        rs.getBoolean("disponibilidad"),
                        fechaAlquiler,
                        rs.getInt("telefono"),
                        rs.getInt("duracionDias")
                    );
                    alquilerProducto.setId(id); // Aqui asignamos el ID al objeto
                } else {
                    System.err.println("No se encontró producto de alquiler con ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al consultar producto de alquiler con ID: " + id);
        }

        return alquilerProducto;
    }

    public boolean modificarProductoAlquiler(AlquilerProducto alquilerProducto) {
        String query = "UPDATE producto SET nombre=?, talla=?, precioVenta=?, disponibilidad=? WHERE id=?";
        PreparedStatement pstmt = null;
        try {
            // Recuperar los valores actuales de teléfono y fechaAlquiler si son nulos en el objeto, ya que si no los recupero se modifican y se quedan en null y 0 
            String consultaValoresActuales = "SELECT telefono, fechaAlquiler FROM producto WHERE id = ?";
            try (PreparedStatement consultaStmt = conexion.prepareStatement(consultaValoresActuales)) {
                consultaStmt.setInt(1, alquilerProducto.getId());
                try (ResultSet rs = consultaStmt.executeQuery()) {
                    if (rs.next()) {
                        // Si no se proporcionan valores, usamos los actuales de la base de datos
                        if (alquilerProducto.getTelefono() == 0) {
                            alquilerProducto.setTelefono(rs.getInt("telefono"));
                        }
                        if (alquilerProducto.getFechaAlquiler() == null) {
                            java.sql.Date fechaAlquilerSql = rs.getDate("fechaAlquiler");
                            alquilerProducto.setFechaAlquiler(
                                (fechaAlquilerSql != null) ? fechaAlquilerSql.toLocalDate() : null
                            );
                        }
                    } else {
                        System.out.println("No se encontró el producto con ID: " + alquilerProducto.getId());
                        return false; // Salir si no existe el producto
                    }
                }
            }
            // Modificamos los datos que si se pueden modificar 
            query = "UPDATE producto SET nombre=?, talla=?, precioVenta=?, disponibilidad=? WHERE id=?";
            pstmt = conexion.prepareStatement(query);
            // Asignamos los valores
            pstmt.setString(1, alquilerProducto.getNombre());
            pstmt.setString(2, alquilerProducto.getTalla());
            pstmt.setDouble(3, alquilerProducto.getPrecioVenta());
            pstmt.setBoolean(4, alquilerProducto.isDisponibilidad());
            pstmt.setInt(5, alquilerProducto.getId());

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Producto modificado correctamente. Filas afectadas: " + filasAfectadas);
                return true;
            } else {
                System.out.println("No se encontró el producto con ID: " + alquilerProducto.getId());
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public boolean bajaProductoAlquiler(int id) {
        System.out.println("Intentando borrar producto con ID: " + id);
        String query = "DELETE FROM producto WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);
            conexion.commit(); // Aseguramos el commit manual
            return filasAfectadas > 0;
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            System.err.println("Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }


    public void altaProducto(Producto producto) {
        String query = "INSERT INTO producto (nombre, talla, precioVenta, disponibilidad, cliente_id, DTYPE) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getTalla());
            pstmt.setDouble(3, producto.getPrecioVenta());
            pstmt.setBoolean(4, producto.isDisponibilidad());
            pstmt.setObject(5, producto.getCliente() != null ? producto.getCliente().getDni() : null);
            pstmt.setString(6, "Producto"); // Asignamos el valor adecuado para DTYPE

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                // Recuperar el ID generado automáticamente
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        producto.setId(generatedId); // Asigna el ID generado al objeto, ya que si no se realiza este paso de errores mas adelante
                        System.out.println("Producto dado de alta correctamente: " + producto);
                    } else {
                        System.err.println("No se pudo obtener el ID generado.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al registrar el producto.");
        }
    }

    public boolean bajaProducto(int id) {
        String queryEliminarVentas = "DELETE FROM ventaproducto WHERE producto_id = ?";
        String queryEliminarProducto = "DELETE FROM producto WHERE id = ?";
        try {
            // Comenzamos una transacción
            conexion.setAutoCommit(false);
            // Paso 1: Eliminar las ventas asociadas al producto
            try (PreparedStatement pstmtVentas = conexion.prepareStatement(queryEliminarVentas)) {
                pstmtVentas.setInt(1, id);
                pstmtVentas.executeUpdate(); // Eliminar registros en ventaproducto
            }
            // Paso 2: Eliminar el producto
            try (PreparedStatement pstmtProducto = conexion.prepareStatement(queryEliminarProducto)) {
                pstmtProducto.setInt(1, id);
                int rowsAffected = pstmtProducto.executeUpdate();

                if (rowsAffected > 0) {
                    // Confirmar la transacción si el producto se eliminó correctamente
                    conexion.commit();
                    System.out.println("Producto con ID " + id + " eliminado correctamente.");
                    return true;
                } else {
                    // Si no se eliminó el producto, deshacer la transacción
                    conexion.rollback();
                    System.err.println("No se encontró producto con ID: " + id);
                }
            }
        } catch (SQLException e) {
            // Manejar errores y deshacer la transacción si ocurre algún problema
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            // Restaurar el modo de confirmación automática
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Retornar false si algo falla
    }


    public Producto consultaProducto(int id) {
        Producto producto = null;
        String query = "SELECT * FROM producto WHERE id = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(
                        rs.getString("nombre"),
                        rs.getString("talla"),
                        rs.getDouble("precioVenta"),
                        rs.getBoolean("disponibilidad")
                    );
                    producto.setId(id); 
                } else {
                    System.err.println("No se encontró producto con ID: " + id);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar producto con ID: " + id);
            e.printStackTrace();
        }

        return producto;
    }

    public boolean modificarProducto(Producto productoModificado) {
    	 String query = "UPDATE producto SET nombre=?, talla=?, PrecioVenta=?, disponibilidad=? WHERE id=?";
         PreparedStatement pstmt;

         try {
             pstmt = conexion.prepareStatement(query);
             pstmt.setString(1, productoModificado.getNombre());
             pstmt.setString(2, productoModificado.getTalla());
             pstmt.setDouble(3, productoModificado.getPrecioVenta());
             pstmt.setBoolean(4, productoModificado.isDisponibilidad());
             pstmt.setInt(5, productoModificado.getId()); // Aquí usamos dni solo para localizar al cliente
             int filasAfectadas = pstmt.executeUpdate();
             System.out.println("Filas afectadas: " + filasAfectadas);

             return filasAfectadas > 0;
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return false;
    }
    
   

    public boolean asignarProductoACliente(ClienteUsuario cliente, Producto producto) {
        // Verificar si el cliente y el producto son nulos
        if (cliente == null || producto == null) {
            System.err.println("Cliente o producto no pueden ser nulos.");
            return false;
        }
        // Consultamos SQL para actualizar el producto y asignarlo al cliente
        String query = "UPDATE producto SET cliente_id = ?, disponibilidad = false WHERE id = ? AND disponibilidad = true";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            // Asignar los parámetros de la consulta
            pstmt.setInt(1, cliente.getDni()); // Cliente es encontrado por su DNI
            pstmt.setInt(2, producto.getId()); // Producto es encontrado por su ID
            // Ejecutar la actualización
            int filasAfectadas = pstmt.executeUpdate();
            // Mostrar mensajes de depuración
            if (filasAfectadas > 0) {
                System.out.println("Producto asignado correctamente al cliente.");
            } else {
                System.err.println("No se pudo asignar el producto al cliente. Verifica si el producto está disponible.");
            }
            return filasAfectadas > 0; // Devuelve true si se asignó el producto
        } catch (SQLException e) {
            // Capturar excepciones SQL y mostrar el mensaje de error
            System.err.println("Error al asignar el producto al cliente: " + e.getMessage());
            e.printStackTrace();
            return false; // Devuelve false si ocurre un error
        }
    }

    
    public boolean realizarVenta(int cantidadVendida, Date fechaVenta, int idProducto, int idCliente) {
        String queryInsertVenta = "INSERT INTO ventaproducto (fechaVenta, cantidadVendida, cliente_id, producto_id) VALUES (?, ?, ?, ?)"; //Paso 1: insertar en la tabla el producto y el cliente
        String queryUpdateProducto = "UPDATE producto SET disponibilidad = ? WHERE id = ?"; //Paso 2: modificar la disponibilidad de producto
        PreparedStatement pstmtInsertVenta = null;
        PreparedStatement pstmtUpdateProducto = null;
        try {
            // Deshabilitar autocommit para manejar la transacción manualmente
            conexion.setAutoCommit(false);
            //Paso 1
            // Verificamos si el producto existe y está disponible
            String queryCheckProducto = "SELECT disponibilidad FROM producto WHERE id = ?";
            PreparedStatement pstmtCheckProducto = conexion.prepareStatement(queryCheckProducto);
            pstmtCheckProducto.setInt(1, idProducto);
            ResultSet rs = pstmtCheckProducto.executeQuery();
            if (!rs.next()) {
                throw new Exception("Producto no encontrado en la base de datos.");
            }
            //Paso 2
            // Verificamos si el producto está disponible
            boolean disponibilidad = rs.getBoolean("disponibilidad");
            if (!disponibilidad) {
                throw new Exception("Producto no disponible.");
            }
            //Paso 3
            // Verificar si el cliente existe
            String queryCheckCliente = "SELECT dni FROM clienteusuario WHERE dni = ?";
            PreparedStatement pstmtCheckCliente = conexion.prepareStatement(queryCheckCliente);
            pstmtCheckCliente.setInt(1, idCliente); // Aquí pasa el DNI del cliente
            ResultSet rsCliente = pstmtCheckCliente.executeQuery();
            if (!rsCliente.next()) {
                throw new Exception("Cliente no encontrado en la base de datos.");
            }
            //Paso 4
            // Insertar la venta
            pstmtInsertVenta = conexion.prepareStatement(queryInsertVenta);
            pstmtInsertVenta.setDate(1, new java.sql.Date(fechaVenta.getTime()));
            pstmtInsertVenta.setInt(2, cantidadVendida);
            pstmtInsertVenta.setInt(3, idCliente); // Usamos DNI del cliente
            pstmtInsertVenta.setInt(4, idProducto);
            pstmtInsertVenta.executeUpdate();
            // Actualizar la disponibilidad del producto
            pstmtUpdateProducto = conexion.prepareStatement(queryUpdateProducto);
            // Cambiar disponibilidad a false, ya que el producto se vendió y si se queda con true puede causar errores
            pstmtUpdateProducto.setBoolean(1, false); // Producto ya no disponible
            pstmtUpdateProducto.setInt(2, idProducto);
            pstmtUpdateProducto.executeUpdate();
            // Confirmar la transacción
            conexion.commit();
            System.out.println("Venta realizada con éxito.");
            return true;
        } catch (Exception e) {
            try {
                if (conexion != null) {
                    conexion.rollback(); // Revertir cambios en caso de error
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmtInsertVenta != null) pstmtInsertVenta.close();
                if (pstmtUpdateProducto != null) pstmtUpdateProducto.close();
                conexion.setAutoCommit(true); // Restaurar autocommit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener todos los clientes esta relacioando con VentaProducto
    public List<ClienteUsuario> obtenerTodosLosClientes() {
        String queryClientes = "SELECT * FROM clienteusuario";
        String queryProductos = "SELECT * FROM producto WHERE cliente_id = ?";
        List<ClienteUsuario> listaClientes = new ArrayList<>();
        PreparedStatement pstmtClientes = null;
        PreparedStatement pstmtProductos = null;
        try {
            pstmtClientes = conexion.prepareStatement(queryClientes);
            ResultSet rsClientes = pstmtClientes.executeQuery();
            while (rsClientes.next()) {
                ClienteUsuario cliente = new ClienteUsuario();
                cliente.setDni(rsClientes.getInt("dni")); 
                cliente.setNombre(rsClientes.getString("nombre"));
                cliente.setTelefono(rsClientes.getString("telefono"));
                cliente.setDni(rsClientes.getInt("dni"));
                // Aqui obtenemos productos asociados al cliente
                pstmtProductos = conexion.prepareStatement(queryProductos);
                pstmtProductos.setInt(1, cliente.getDni());
                ResultSet rsProductos = pstmtProductos.executeQuery();
                List<Producto> productos = new ArrayList<>();
                while (rsProductos.next()) {
                    Producto producto = new Producto();
                    producto.setId(rsProductos.getInt("id"));
                    producto.setNombre(rsProductos.getString("nombre"));
                    producto.setTalla(rsProductos.getString("talla"));
                    producto.setPrecioVenta(rsProductos.getDouble("precioVenta"));
                    producto.setDisponibilidad(rsProductos.getBoolean("disponibilidad"));
                    // Creamos objeto ClienteUsuario y asignarlo al producto
                    int clienteId = rsProductos.getInt("cliente_id");
                    ClienteUsuario clienteProducto = new ClienteUsuario();
                    clienteProducto.setDni(clienteId); // Usamos DNI
                    producto.setCliente(clienteProducto);
                    productos.add(producto);
                }
                cliente.setProductos(productos);
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmtClientes != null) pstmtClientes.close();
                if (pstmtProductos != null) pstmtProductos.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaClientes;
    }
    
    // Consulta para contar los clientes dados de alta
    public long contarClientesQuery() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Usamos la conexión ya establecida
            String query = "SELECT COUNT(*) FROM clienteusuario";
            pstmt = conexion.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1); // Devuelve el conteo de clientes
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    // Consulta para contar ventas entre dos fechas
    public long contarVentasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Usamos la conexión ya establecida
            String query = "SELECT COUNT(*) FROM ventaproducto WHERE fechaVenta BETWEEN ? AND ?";
            pstmt = conexion.prepareStatement(query);
            pstmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
            pstmt.setDate(2, java.sql.Date.valueOf(fechaFin));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1); // Devuelve el conteo de ventas
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
 // Consulta para contar los clientes con más de un producto comprado
    public long ClienteConMasDeUnaCompra() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // esto es para contar cuántas veces aparece cada cliente en la tabla ventaproducto
            String query = "SELECT cliente_id, COUNT(*) as cantidad_compras " +
                           "FROM ventaproducto " +
                           "GROUP BY cliente_id " +
                           "HAVING COUNT(*) > 1";

            pstmt = conexion.prepareStatement(query);
            rs = pstmt.executeQuery();
            long count = 0;
            // Con esto contamos cuántos clientes cumplen la condición de tener más de una compra
            while (rs.next()) {
                count++;
            }
            return count; // Número total de clientes con más de una compra registrada
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Método para cerrar la conexión (opcional, si la conexión se maneja en otro lugar)
    public void close() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





