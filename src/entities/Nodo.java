package entities;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private Integer numero;
    private List<Vertice> verticesSiguientes;
    private List<Vertice> verticesAnteriores;

    public Nodo(Integer numero) {
        this.verticesSiguientes = new ArrayList<Vertice>();
        this.verticesAnteriores = new ArrayList<Vertice>();
        this.numero = numero;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public List<Vertice> getVerticesSiguientes() {
        return verticesSiguientes;
    }

    public void setVerticesSiguientes(List<Vertice> verticesSiguientes) {
        this.verticesSiguientes = verticesSiguientes;
    }

    public List<Vertice> getVerticesAnteriores() {
        return verticesAnteriores;
    }

    public void setVerticesAnteriores(List<Vertice> verticesAnteriores) {
        this.verticesAnteriores = verticesAnteriores;
    }

    public void addVerticeSiguiente(Vertice verticeSiguiente) {
        this.verticesSiguientes.add(verticeSiguiente);
    }

    public void addVerticeAnterior(Vertice verticeAnterior) {
        this.verticesAnteriores.add(verticeAnterior);
    }
}
