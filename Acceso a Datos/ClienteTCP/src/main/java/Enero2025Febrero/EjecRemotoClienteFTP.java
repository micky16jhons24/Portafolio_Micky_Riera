package Enero2025Febrero;

import java.io.*;
import org.apache.commons.net.ftp.*;

public class EjecRemotoClienteFTP {

    private static String IP_servidor = "127.0.0.1"; // Cambia según tu servidor
    private static int puerto = 21; // Puerto FTP (21 por defecto)
    private static final String usuario = "micky"; // Usuario FTP
    private static final String contrasenna = "alumno"; // Contraseña FTP
    private static final String Direccion = "C:\\Users\\Usuario\\Desktop\\ClienteTCP\\"; // Carpeta local
    private static final String direccion_servidor = "/"; // Directorio raíz FTP

    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();

        try {
            // Conectar al servidor FTP
            cliente.connect(IP_servidor, puerto);
            int respuesta = cliente.getReplyCode();
            if (!FTPReply.isPositiveCompletion(respuesta)) {
                System.out.println("Error en la conexión FTP. Código: " + respuesta);
                return;
            }

            // Iniciar sesión en el servidor FTP
            boolean login = cliente.login(usuario, contrasenna);
            if (!login) {
                System.out.println("Error: Usuario o contraseña incorrectos.");
                return;
            }

            // Modo pasivo para evitar problemas de firewall
            cliente.enterLocalPassiveMode();

            // Cambiar a modo binario
            cliente.setFileType(FTP.BINARY_FILE_TYPE);

            System.out.println("Conectado exitosamente a " + IP_servidor);

            // Listar archivos en el servidor FTP
            listarArchivos(cliente);

            // Subir un archivo al servidor FTP
            String archivoSubir = "Micky.txt";
            subirArchivo(cliente, archivoSubir);

            // Descargar un archivo del servidor FTP
            String archivoDescargar = "Micky.txt";
            descargarArchivo(cliente, archivoDescargar);

            // Cerrar sesión y desconectarse
            cliente.logout();
            cliente.disconnect();
            System.out.println("Conexión cerrada.");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Método para listar archivos en el servidor FTP
    private static void listarArchivos(FTPClient cliente) throws IOException {
        System.out.println("Archivos en el servidor:");
        FTPFile[] archivos = cliente.listFiles(direccion_servidor);
        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay archivos en el servidor.");
        } else {
            for (FTPFile archivo : archivos) {
                System.out.println(" - " + archivo.getName());
            }
        }
    }

    // Método para subir un archivo al servidor FTP
    private static void subirArchivo(FTPClient cliente, String nombreArchivo) {
        File archivoLocal = new File(Direccion + "\\" + nombreArchivo);

        if (!archivoLocal.exists()) {
            System.err.println("Error: El archivo no existe en la ruta: " + archivoLocal.getAbsolutePath());
            return;
        }

        try (InputStream input = new FileInputStream(archivoLocal)) {
            System.out.println("Subiendo archivo: " + nombreArchivo);
            boolean hecho = cliente.storeFile(direccion_servidor + nombreArchivo, input);
            if (hecho) {
                System.out.println("Archivo subido correctamente.");
            } else {
                System.out.println("Error al subir el archivo.");
            }
        } catch (IOException e) {
            System.err.println("Error al subir archivo: " + e.getMessage());
        }
    }

    // Método para descargar un archivo del servidor FTP
    private static void descargarArchivo(FTPClient cliente, String nombreArchivo) {
        File archivoLocal = new File(Direccion + "\\" + nombreArchivo+"pp");

        try (OutputStream output = new FileOutputStream(archivoLocal)) {
            System.out.println("Descargando archivo: " + nombreArchivo +" en "+Direccion + "\\" + nombreArchivo+"pp");
            boolean hecho = cliente.retrieveFile(direccion_servidor + nombreArchivo, output);
            if (hecho) {
                System.out.println("Archivo descargado correctamente.");
            } else {
                System.out.println("Error al descargar el archivo.");
            }
        } catch (IOException e) {
            System.err.println("Error al descargar archivo: " + e.getMessage());
        }
    }
}
