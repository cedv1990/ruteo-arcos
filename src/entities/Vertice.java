package entities;

public class Vertice {
    private Integer costo;
    private Nodo siguiente;
    private Nodo anterior;
    private Boolean yaPaso;

    public Vertice(Nodo anterior, Nodo siguiente, Integer costo) {
        this.yaPaso = false;
        this.costo = costo;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Boolean getYaPaso() {
        return yaPaso;
    }

    public void setYaPaso(Boolean yaPaso) {
        this.yaPaso = yaPaso;
    }

	public Nodo getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo siguiente) {
		this.siguiente = siguiente;
	}

	public Nodo getAnterior() {
		return anterior;
	}

	public void setAnterior(Nodo anterior) {
		this.anterior = anterior;
	}
}
