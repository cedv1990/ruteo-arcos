package solucion1;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private int numero;

    private List<Vertice> siguientes;
    private List<Vertice> anteriores;

    private List<CaminoCalculado> caminos;

    public Nodo(int num) {
        this.numero = num;
        this.caminos = new ArrayList<>();
        this.siguientes = new ArrayList<>();
        this.anteriores = new ArrayList<>();
    } 
    
    public int getNumero() {
        return numero;
    }

    public List<Vertice> getSiguientes() {
        return this.siguientes;
    }

    public List<Vertice> getAnteriores() {
        return this.anteriores;
    }

    public List<CaminoCalculado> getCaminos() {
        return caminos;
    }

    public void setSiguientes(List<Vertice> siguientes) {
        this.siguientes = siguientes;
    }

    public void setAnteriores(List<Vertice> anteriores) {
        this.anteriores = anteriores;
    }

    public void agregarCamino(CaminoCalculado camino) {
        this.caminos.add(camino);
    }

    public void agregarSiguiente(Vertice siguiente) {
        this.siguientes.add(siguiente);
    }

    public void agregarAnterior(Vertice anterior) {
        this.anteriores.add(anterior);
    }
}
