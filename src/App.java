import entities.Datos;
import solucion1.Archivo;

public class App {

    public static void main(String[] args) throws Exception {
        Datos datos = new Archivo().leerDatos();

        solucion1.Grafo grafo = new solucion1.Grafo(datos.getDatos());
        grafo.interpretarDatos();
        grafo.obtenerCaminos();
        int total = grafo.calcularTotal();

        System.out.printf("Total calculado: %d\nTotal esperado: %d\n", total, datos.getTotalEsperado());
    }
}
