import java.nio.file.Paths;

import entities.Datos;
import solucion1.Archivo;

public class App {

    public static void main(String[] args) throws Exception {
        
        //[CAMBIO] Cambiar el siguiente código por lo que llegue en "args".
        String path = Paths.get("").toAbsolutePath() + "/src/datos/gdb1.dat";
        //String path = args[0]; //<---- Este es el parámetro 1 de ejecución del programa por consola

        Datos datos = new Archivo().leerDatos(path);

        solucion1.Grafo grafo = new solucion1.Grafo(datos.getDatos());
        grafo.interpretarDatos();
        grafo.obtenerCaminos();
        int total = grafo.calcularTotal();

        //[CAMBIO] Si no muestra en consola, cambiar esta línea
        System.out.printf("Total calculado: %d\nTotal esperado: %d\n", total, datos.getTotalEsperado());
    }
}
