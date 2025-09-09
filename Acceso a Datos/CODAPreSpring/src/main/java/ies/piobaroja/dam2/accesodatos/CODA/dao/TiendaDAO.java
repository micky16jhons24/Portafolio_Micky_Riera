package ies.piobaroja.dam2.accesodatos.CODA.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.AlquilerProducto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.ClienteUsuario;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;
import ies.piobaroja.dam2.accesodatos.CODA.modelo.VentaProducto;

public class TiendaDAO {
	
//	private static TiendaDAO instancia=new TiendaDAO();
//	
//	public static TiendaDAO getInstance() {
//		return instancia;
//	}
//	
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("CODA");;
    private EntityManager em;
 
    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }



    public List<Producto> consultarProductosPorDisponibilidad(boolean disponibilidad) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery(
                    "SELECT p FROM Producto p WHERE p.disponibilidad = :disponibilidad",
                    Producto.class
            );
            query.setParameter("disponibilidad", disponibilidad);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void altaCliente(ClienteUsuario cliente) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(cliente);; 
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public ClienteUsuario consultaClienteDNI(int dni) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClienteUsuario.class, dni);
        } finally {
            em.close();
        }
    }

    public boolean modificacion(ClienteUsuario cliente) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            ClienteUsuario clienteExistente = em.find(ClienteUsuario.class, cliente.getDni());
            if (clienteExistente != null) {
                clienteExistente.setNombre(cliente.getNombre());
                clienteExistente.setApellido(cliente.getApellido());
                clienteExistente.setTelefono(cliente.getTelefono());
                clienteExistente.setCorreo(cliente.getCorreo());
                clienteExistente.setCodigoPostal(cliente.getCodigoPostal());
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false; 
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }



    public boolean bajaCliente(int dni) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            ClienteUsuario cliente = em.find(ClienteUsuario.class, dni);
            if (cliente != null) {
                em.remove(cliente);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false; 
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public void altaProductoAlquiler(AlquilerProducto alquilerProducto) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(alquilerProducto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public AlquilerProducto consultaProductoAlquiler(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AlquilerProducto.class, id);
        } finally {
            em.close();
        }
    }

    public boolean modificarProductoAlquiler(AlquilerProducto alquilerProducto) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            AlquilerProducto alquilerExistente = em.find(AlquilerProducto.class, alquilerProducto.getId());
            if (alquilerExistente != null) {
                alquilerExistente.setNombre(alquilerProducto.getNombre());
                alquilerExistente.setTalla(alquilerProducto.getTalla());
                alquilerExistente.setPrecioVenta(alquilerProducto.getPrecioVenta());
                alquilerExistente.setDisponibilidad(alquilerProducto.isDisponibilidad());
                alquilerExistente.setFechaAlquiler(alquilerProducto.getFechaAlquiler());
                alquilerExistente.setTelefono(alquilerProducto.getTelefono());
                alquilerExistente.setDuracionDias(alquilerProducto.getDuracionDias());
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false; 
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean bajaProductoAlquiler(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            AlquilerProducto alquilerProducto = em.find(AlquilerProducto.class, id);
            if (alquilerProducto != null) {
                em.remove(alquilerProducto);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false; 
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public void altaProducto(Producto producto) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(producto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public boolean bajaProducto(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Producto producto = em.find(Producto.class, id);
            if (producto != null) {
                em.remove(em.contains(producto) ? producto : em.merge(producto));
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false; 
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public Producto consultaProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public boolean modificarProducto(Producto productoModificado) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Producto productoExistente = em.find(Producto.class, productoModificado.getId());
            if (productoExistente != null) {
                productoExistente.setNombre(productoModificado.getNombre());
                productoExistente.setTalla(productoModificado.getTalla());
                productoExistente.setPrecioVenta(productoModificado.getPrecioVenta());
                productoExistente.setDisponibilidad(productoModificado.isDisponibilidad());
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false; 
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
    
   

    public boolean asignarProductoACliente(ClienteUsuario cliente, Producto producto) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            ClienteUsuario clienteExistente = em.find(ClienteUsuario.class, cliente.getDni());
            if (clienteExistente == null) {
                throw new IllegalArgumentException("El cliente no existe en la base de datos");
            }

            Producto productoExistente = em.find(Producto.class, producto.getId());
            if (productoExistente == null) {
                throw new IllegalArgumentException("El producto no existe en la base de datos");
            }
            if (!productoExistente.isDisponibilidad()) {
                throw new IllegalArgumentException("El producto no está disponible");
            }

            clienteExistente.addProducto(productoExistente);
            productoExistente.setDisponibilidad(false); 

            em.merge(clienteExistente);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    
    public boolean realizarVenta(int cantidad, Date fechaVenta, int idProducto, int idCliente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Producto producto = em.find(Producto.class, idProducto);
            ClienteUsuario cliente = em.find(ClienteUsuario.class, idCliente);

            if (producto == null) {
                throw new Exception("Producto no encontrado en la base de datos.");
            }
            if (cliente == null) {
                throw new Exception("Cliente no encontrado en la base de datos.");
            }

            if (!producto.isDisponibilidad()) {
                throw new Exception("Producto no disponible.");
            }

            VentaProducto venta = new VentaProducto(fechaVenta, cantidad, cliente, producto);
            em.persist(venta);

            producto.setDisponibilidad(false);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }







    public List<ClienteUsuario> obtenerTodosLosClientes() {
        EntityManager em = getEntityManager();
        try {
            List<ClienteUsuario> clientes = em.createQuery("SELECT c FROM ClienteUsuario c", ClienteUsuario.class).getResultList();

            for (ClienteUsuario cliente : clientes) {
                cliente.getProductos().size(); 
            }
            
            return clientes;
        } finally {
            em.close();
        }
    }

  //public long contar cuantos clientes hay dados de alta en la tienda 
    public long contarClientesQuery() {
    	
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CODA");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT COUNT(c) FROM ClienteUsuario c";
        Query query = em.createQuery(jpql);

        long totalClientes = (Long) query.getSingleResult();

        em.close();
        emf.close();

        return totalClientes;
    }
    
    
    //public long Ventas realizadas entre EJ: 2023-02-12 Entre 2023-03-23
    public long contarVentasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT COUNT(v) FROM VentaProducto v WHERE v.fechaVenta BETWEEN :fechaInicio AND :fechaFin";
            Query query = em.createQuery(jpql);
            query.setParameter("fechaInicio", java.sql.Date.valueOf(fechaInicio));
            query.setParameter("fechaFin", java.sql.Date.valueOf(fechaFin));
            return (long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(); 
            return 0;
        } finally {
            em.close();
        }
    }
    
    //public long clientes con mas de un producto comprado
    public long ClienteConMasDeUnProductoComprado() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT v.cliente " +
                          "FROM VentaProducto v " +
                          "GROUP BY v.cliente " +
                          "HAVING COUNT(DISTINCT v.producto) > 1";
            Query query = em.createQuery(jpql);
            List<ClienteUsuario> clientes = query.getResultList();

            return clientes.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0; 
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }






    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}





