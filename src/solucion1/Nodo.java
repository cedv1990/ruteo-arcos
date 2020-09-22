package solucion1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Nodo {
    private int numero;

    private List<Vertice> siguientes;
    private List<Vertice> anteriores;

    private List<CaminoCalculado> caminos;
    private List<List<CaminoCalculado>> caminoPlano;

    public Nodo(int num) {
        this.numero = num;
        this.caminos = new ArrayList<>();
        this.siguientes = new ArrayList<>();
        this.anteriores = new ArrayList<>();
        this.caminoPlano = new ArrayList<>();
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

    public List<List<CaminoCalculado>> getCaminoPlano() {
        return caminoPlano;
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

    public void agregarCaminosPlanosPermitidos(List<List<CaminoCalculado>> caminos, int cantidad){
        for (List<CaminoCalculado> list : caminos) {
            if (
                list.stream().filter(x -> !x.getVisitado()).collect(Collectors.toList()).size() <= cantidad 
                //list.size() <= cantidad
                &&
                list.size() > 2
            ) {
                this.caminoPlano.add(list);
            }
        }
    }
}
