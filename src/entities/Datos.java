package entities;

import java.util.List;

public class Datos {
    private List<DatosArchivo> datos;
    private int totalEsperado;

    public List<DatosArchivo> getDatos() {
        return datos;
    }

    public void setDatos(List<DatosArchivo> datos) {
        this.datos = datos;
    }

    public int getTotalEsperado() {
        return totalEsperado;
    }

    public void setTotalEsperado(int totalEsperado) {
        this.totalEsperado = totalEsperado;
    }
}
