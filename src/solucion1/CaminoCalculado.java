package solucion1;

import java.util.ArrayList;
import java.util.List;

public class CaminoCalculado {
    private int numero;
    private int costo;
    private boolean bajando;
    private List<CaminoCalculado> subCamino;

    private boolean visitado;

    public CaminoCalculado(int numero, int costo, boolean bajando) {
        this.numero = numero;
        this.costo = costo;
        this.bajando = bajando;
        this.subCamino = new ArrayList<>();
        this.visitado = false;
    }

    public int getNumero() {
        return numero;
    }

    public int getCosto() {
        return costo;
    }

    public boolean getBajando() {
        return bajando;
    }

    public boolean getVisitado() {
        return visitado;
    }

    public List<CaminoCalculado> getSubCamino() {
        return subCamino;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    @Override
    public String toString() {
        return String.format("N: %d, V: %s", this.numero, this.visitado ? "S" : "N");
    }
}
