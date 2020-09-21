package solucion1;

public class Vertice {
    private Nodo siguiente;
    private Nodo anterior;
    private int costo;

    public Vertice(Nodo anterior, Nodo siguiente, int costo) {
        this.costo = costo;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public int getCosto() {
        return costo;
    }
}
