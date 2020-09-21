import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.DatosArchivo;

public class App {
    public static void main(String[] args) throws Exception {

        List<DatosArchivo> datos = leerDatos();

        solucion1.Grafo grafo = new solucion1.Grafo(datos);
        grafo.interpretarDatos();
        grafo.obtenerCaminos();
        int total = grafo.calcularTotal();
        System.out.println(total);
    }

    private static List<DatosArchivo> leerDatos() {
        List<DatosArchivo> retorno = new ArrayList<DatosArchivo>();
        try {
            File file = new File(Paths.get("").toAbsolutePath() + "/src/datos/gdb0.dat");
            Scanner myReader = new Scanner(file);
            Boolean empezarExtraccion = false;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!empezarExtraccion) {
                    empezarExtraccion = data.contains("LISTA_ARISTAS_REQ");
                }
                else if(!data.contains("DEPOSITO")) {
                    DatosArchivo datosLinea = new DatosArchivo();
                    datosLinea.extraerInfoDesdeString(data.substring(0, data.indexOf("demanda")));
                    retorno.add(datosLinea);
                }
            }
            myReader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        return retorno;
    }
}
