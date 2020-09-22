package solucion1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Datos;
import entities.DatosArchivo;

public class Archivo {
    public Datos leerDatos() {
        int total = 0;
        Datos retorno = new Datos();
        List<DatosArchivo> listaDatosArchivo = new ArrayList<DatosArchivo>();
        DatosArchivo datosLinea;
        try {
            File file = new File(Paths.get("").toAbsolutePath() + "/src/datos/gdb1.dat");
            Scanner myReader = new Scanner(file);
            Boolean empezarExtraccion = false;
            while (myReader.hasNextLine()) {
                datosLinea = new DatosArchivo();
                String data = myReader.nextLine();
                if (data.contains("COSTE_TOTAL_REQ")) {
                    total = datosLinea.extraerTotalEsperado(data, "COSTE_TOTAL_REQ");
                }
                if (!empezarExtraccion) {
                    empezarExtraccion = data.contains("LISTA_ARISTAS_REQ");
                }
                else if(!data.contains("DEPOSITO")) {
                    datosLinea.extraerInfoDesdeString(data.substring(0, data.indexOf("demanda")));
                    listaDatosArchivo.add(datosLinea);
                }
            }
            myReader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        retorno.setDatos(listaDatosArchivo);
        retorno.setTotalEsperado(total);

        return retorno;
    }
}
