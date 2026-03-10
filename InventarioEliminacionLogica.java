import java.io.*;
import java.util.Scanner;

public class InventarioEliminacionLogica {

    public static void mostrarArchivo(String nombreArchivo) {
    // Método para mostrar contenido de un archivo

        System.out.println("\n--- Contenido de: " + nombreArchivo + " ---");
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        System.out.println("------------------------------------------");
    }
    // Métodos a implementar
    // 1. Marcar un registro como eliminado agregando '#' al inicio si coincide con el producto indicado
    public static void marcarEliminado(String nombreArchivo, String productoEliminar) {
        File original = new File(nombreArchivo);
        File temp = new File("temporal_inventario.txt"); 

        try (BufferedReader br = new BufferedReader(new FileReader(original));
             PrintWriter pw = new PrintWriter(new FileWriter(temp))) {

            String linea;
            while ((linea = br.readLine()) != null) {

                if (linea.startsWith(productoEliminar + ",")) {
                    pw.println("#" + linea);
                } else {
                    pw.println(linea); 
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (original.delete()) {
            temp.renameTo(original);
        }
    }
    // 2. Crear un nuevo archivo sin los registros marcados como eliminados
    public static void crearArchivoSinEliminados(String archivoOriginal, String archivoNuevo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             PrintWriter pw = new PrintWriter(new FileWriter(archivoNuevo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith("#")) {
                    pw.println(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al crear archivo actualizado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String archivo = "inventario.txt";

        mostrarArchivo(archivo);

        System.out.print("Ingrese el nombre del producto para eliminar lógicamente: ");
        String productoEliminar = sc.nextLine();

        marcarEliminado(archivo, productoEliminar);
        mostrarArchivo(archivo);

        String archivoActualizado = "inventario_actualizado.txt";
        crearArchivoSinEliminados(archivo, archivoActualizado);
        mostrarArchivo(archivoActualizado);

        sc.close();
    }
}