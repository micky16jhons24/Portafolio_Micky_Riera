package ies.piobaroja.dam2.accesodatos.CODA;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ies.piobaroja.dam2.accesodatos.CODA.modelo.Producto;

public class Queries {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mensajeria");
        EntityManager em = emf.createEntityManager();

        // Cambia el JPQL según la estructura de tu clase Producto
        String jpql = "SELECT p FROM Producto p WHERE p.disponibilidad = :disponible";
        Query query = em.createQuery(jpql);

        // Solicitar disponibilidad de producto
        System.out.println("¿Está disponible el producto? (true/false):");
        boolean disponibilidad = sc.nextBoolean();

        // Establecer el parámetro en la consulta
        query.setParameter("disponible", disponibilidad);

        // Ejecutar la consulta y obtener la lista de productos
        List<Producto> productos = query.getResultList();

        // Mostrar resultados
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos con la disponibilidad especificada.");
        } else {
            for (Producto p : productos) {
                System.out.println(p);
            }
        }

        // Cerrar recursos
        em.close();
        emf.close();
        sc.close();
    }
}
