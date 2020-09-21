package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Camino {
    private Integer total;
    private List<Integer> numeros;

    public Camino(Integer inicio) {
        this.total = 0;
        this.numeros = new ArrayList<Integer>();
        this.agregarNumero(inicio);
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void agregarNumero(Integer numero) {
        this.numeros.add(numero);
    }

    @Override
    public String toString() {
        return String.format(
            "Camino: %s\nTotal: %d",
            String.join(",", this.numeros.stream().map(x -> String.valueOf(x)).collect(Collectors.toList())),
            this.total
        );
    }
}
