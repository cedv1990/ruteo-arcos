package solucion1;

import java.util.ArrayList;
import java.util.List;

public class CaminoCalculado {
    private int numero;
    private int costo;
    private boolean bajando;
    private List<CaminoCalculado> subCamino;

    public CaminoCalculado(int numero, int costo, boolean bajando) {
        this.numero = numero;
        this.costo = costo;
        this.bajando = bajando;
        this.subCamino = new ArrayList<>();
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

    public List<CaminoCalculado> getSubCamino() {
        return subCamino;
    }

    @Override
    public String toString() {
        return String.format("N: %d", this.numero);
    }
}
