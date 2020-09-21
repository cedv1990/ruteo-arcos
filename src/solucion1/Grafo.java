package solucion1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.DatosArchivo;

public class Grafo {

    private Nodo inicial;

    private Set<Nodo> nodos;
    private Set<Vertice> vertices;

    private List<DatosArchivo> datos;

    public Grafo(List<DatosArchivo> datos) {
        this.datos = datos;
    }

    public Nodo obtenerNodo(Set<Nodo> nodos, Integer numero) {
        List<Nodo> filtrados = nodos.stream().filter(x -> x.getNumero() == numero).collect(Collectors.toList());
        if (filtrados.size() > 0)
            return filtrados.get(0);
        return null;
    }

    public Vertice obtenerVertice(Set<Vertice> vertices, Nodo origen, Nodo destino) {
        List<Vertice> filtrados =  vertices.stream().filter(x -> 
            x.getAnterior().getNumero() == origen.getNumero()
            &&
            x.getSiguiente().getNumero() == destino.getNumero()
        ).collect(Collectors.toList());
        if (filtrados.size() > 0)
            return filtrados.get(0);
        return null;
    }

    public Nodo crearNodo(Set<Nodo> nodos, Integer numero) {
        Nodo nodo = this.obtenerNodo(nodos, numero);
        if (nodo == null){
            nodo = new Nodo(numero);
        }
        return nodo;
    }

    public Vertice crearVertice(Set<Vertice> vertices, Integer costo, Nodo origen, Nodo destino) {
        Vertice vertice = this.obtenerVertice(vertices, origen, destino);
        if (vertice == null) {
            vertice = new Vertice(origen, destino, costo);
        }
        return vertice;
    }

    public List<Vertice> obtenerVerticesDondeEsSiguiente(Set<Vertice> vertices, Nodo nodo) {
        return vertices.stream().filter(x -> x.getSiguiente().getNumero() == nodo.getNumero()).collect(Collectors.toList());
    }

    public List<Vertice> obtenerVerticesDondeEsAnterior(Set<Vertice> vertices, Nodo nodo) {
        return vertices.stream().filter(x -> x.getAnterior().getNumero() == nodo.getNumero()).collect(Collectors.toList());
    }

    public void interpretarDatos() {
        this.nodos = new HashSet<Nodo>();
        this.vertices = new HashSet<Vertice>();
        
        Nodo nodoOrigen,
             nodoDestino;

        Vertice vertice;

        for (DatosArchivo datosArchivo : datos) {
            nodoOrigen = this.crearNodo(nodos, datosArchivo.getOrigen());
            nodoDestino = this.crearNodo(nodos, datosArchivo.getDestino());
            nodos.add(nodoOrigen);
            nodos.add(nodoDestino);

            vertice = this.crearVertice(vertices, datosArchivo.getValor(), nodoOrigen, nodoDestino);
            vertices.add(vertice);
        }

        List<Vertice> verticesAnteriores,
                      verticesSiguientes;

        for (Nodo nodo : nodos) {
            verticesAnteriores = this.obtenerVerticesDondeEsSiguiente(vertices, nodo);
            verticesSiguientes = this.obtenerVerticesDondeEsAnterior(vertices, nodo);
            nodo.setAnteriores(verticesAnteriores);
            nodo.setSiguientes(verticesSiguientes);
        }

        this.inicial = nodos.stream().filter(x -> x.getNumero() == datos.get(0).getOrigen()).findFirst().get();
    }
    
    public void obtenerCaminos() {
        Set<Integer> previos = new HashSet<>();
        List<List<CaminoCalculado>> caminosPlanos;
        List<DatosArchivo> destinos = filtrarPorNumero(this.inicial.getNumero(), previos, true);

        for (DatosArchivo destino : destinos) {
            previos = new HashSet<>();
            caminosPlanos = new ArrayList<>();
            List<CaminoCalculado> lis = new ArrayList<>();
            List<CaminoCalculado> caminosTemp = new ArrayList<>();
            recorrerCaminos(destino.getDestino(), destino.getValor(), previos, caminosTemp, true, caminosPlanos, lis);
            if (caminosTemp.size() > 0) {
                CaminoCalculado camino = caminosTemp.get(0);
                this.inicial.agregarCamino(camino);
                this.inicial.agregarCaminosPlanosPermitidos(caminosPlanos, 5);
            }
        }
    }

    public int calcularTotal() {
        int total = 0;

        for (int i = 0; i < this.inicial.getCaminoPlano().size(); i++) {
            List<CaminoCalculado> lista = this.inicial.getCaminoPlano().get(i);
            List<Integer> totales = new ArrayList<>();
            int costo = 0;
            for (CaminoCalculado caminoCalculado : lista) {
                
            }
        }

        return total;
    }

    private void recorrerCaminos(int numero, int costo, Set<Integer> previos, List<CaminoCalculado> caminos, boolean derecho, List<List<CaminoCalculado>> caminosPlanos, List<CaminoCalculado> listado) {
        if (numero == 1) {
            caminos.add(new CaminoCalculado(numero, costo, derecho));
            listado.add(new CaminoCalculado(numero, costo, derecho));
            if (listado.size() - 1 == previos.size()) {
                caminosPlanos.add(listado);
            }
            return;
        }
    
        List<DatosArchivo> siguientes = filtrarPorNumero(numero, previos, derecho);

        if (siguientes.size() == 0) {
            derecho = false;
            siguientes = filtrarPorNumero(numero, previos, derecho);
        }

        CaminoCalculado camino = new CaminoCalculado(numero, costo, derecho);
        if (siguientes.size() > 0) {
            caminos.add(camino);
            previos.add(numero);
            listado.add(new CaminoCalculado(numero, costo, derecho));
        }

        for (DatosArchivo siguiente : siguientes) {
            int num;
            if (derecho) {
                num = siguiente.getDestino();
            }
            else {
                num = siguiente.getOrigen();
            }
            Set<Integer> prevs = new LinkedHashSet<>(previos);
            
            recorrerCaminos(num, siguiente.getValor(), prevs, camino.getSubCamino(), derecho, caminosPlanos, new ArrayList<>(listado));
            
            if (camino.getSubCamino().size() == 0) {
                caminos.remove(camino);
                listado.clear();
            }
        }
    }

    private List<DatosArchivo> filtrarPorNumero(int numero, Set<Integer> previos, boolean porOrigen) {
        Stream<DatosArchivo> filtrado;
        if (porOrigen) {
            filtrado = this.datos.stream().filter(x -> x.getOrigen() == numero && !previos.stream().anyMatch(n -> n == numero));
        }
        else {
            filtrado = this.datos.stream().filter(x -> x.getDestino() == numero && !previos.stream().anyMatch(n -> n == numero));
        }
        return filtrado.collect(Collectors.toList());
    }

    
}
