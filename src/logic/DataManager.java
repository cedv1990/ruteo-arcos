package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import entities.Camino;
import entities.DatosArchivo;
import entities.Nodo;
import entities.Response;
import entities.Vertice;

public class DataManager {
    private Nodo inicial;

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

    public void interpretarDatos(List<DatosArchivo> datos) {
        Set<Nodo> nodos = new HashSet<Nodo>();
        Set<Vertice> vertices = new HashSet<Vertice>();

        for (DatosArchivo datosArchivo : datos) {
            Nodo nodoOrigen = this.crearNodo(nodos, datosArchivo.getOrigen());
            Nodo nodoDestino = this.crearNodo(nodos, datosArchivo.getDestino());
            nodos.add(nodoOrigen);
            nodos.add(nodoDestino);

            Vertice vertice = this.crearVertice(vertices, datosArchivo.getValor(), nodoOrigen, nodoDestino);
            vertices.add(vertice);
        }

        for (Nodo nodo : nodos) {
            List<Vertice> verticesAnteriores = this.obtenerVerticesDondeEsSiguiente(vertices, nodo);
            List<Vertice> verticesSiguientes = this.obtenerVerticesDondeEsAnterior(vertices, nodo);
            nodo.setVerticesAnteriores(verticesAnteriores);
            nodo.setVerticesSiguientes(verticesSiguientes);
        }

        this.inicial = nodos.stream().filter(x -> x.getNumero() == datos.get(0).getOrigen()).findFirst().get();


        /*Nodo uno = new Nodo(1);
        Nodo dos = new Nodo(2);
        Nodo tres = new Nodo(3);
        Nodo cuatro = new Nodo(4);
        Nodo cinco = new Nodo(5);
        Nodo seis = new Nodo(6);

        Vertice unoDos = new Vertice(uno, dos, 3);
        Vertice unoTres = new Vertice(uno, tres, 4);
        Vertice unoSeis = new Vertice(uno, seis, 4);
        Vertice unoCuatro = new Vertice(uno, cuatro, 7);
        Vertice unoCinco = new Vertice(uno, cinco, 5);

        uno.addVerticeSiguiente(unoDos);
        uno.addVerticeSiguiente(unoTres);
        uno.addVerticeSiguiente(unoSeis);
        uno.addVerticeSiguiente(unoCuatro);
        uno.addVerticeSiguiente(unoCinco);
        
        Vertice dosSeis = new Vertice(dos, seis, 3);
        dos.addVerticeAnterior(unoDos);
        dos.addVerticeSiguiente(dosSeis);

        Vertice tresSeis = new Vertice(tres, seis, 5);
        tres.addVerticeAnterior(unoTres);
        tres.addVerticeSiguiente(tresSeis);

        Vertice cuatroSeis = new Vertice(cuatro, seis, 5);
        cuatro.addVerticeAnterior(unoCuatro);
        cuatro.addVerticeSiguiente(cuatroSeis);

        Vertice cincoSeis = new Vertice(cinco, seis, 6);
        cinco.addVerticeAnterior(unoCinco);
        cinco.addVerticeSiguiente(cincoSeis);

        seis.addVerticeAnterior(dosSeis);
        seis.addVerticeAnterior(tresSeis);
        seis.addVerticeAnterior(unoSeis);
        seis.addVerticeAnterior(cuatroSeis);
        seis.addVerticeAnterior(cincoSeis);

        this.inicial = uno;*/
    }

    public Response realizarRecorrido() {
        Integer costo = 0;

        for (Integer i = 0; i < this.inicial.getVerticesSiguientes().size(); i++) {
            //Integer res = this.recorrerNodo(this.inicial, null, false, true);
            Camino camino = new Camino(this.inicial.getNumero());
            Integer total = this.recorrer(camino, this.inicial, null, false, true);
            camino.setTotal(total);
            //System.out.println(res);
            System.out.println(camino);
            //costo += res;
            costo += total;
        }

        System.out.println("Total: " + costo);

        return new Response(0, 0);
    }

    private Integer recorrer(Camino camino, Nodo actual, Nodo origen, Boolean inverso, Boolean marcarPaso) {
        if (actual == null)
            return 0;

        Vertice vertice = null;
        List<Vertice> vertices;
        List<Vertice> verticesLibres;
        inverso = !inverso ? actual.getVerticesSiguientes().size() == 0 : inverso;
        if (!inverso) {
            vertices = actual.getVerticesSiguientes();
            verticesLibres = vertices.stream().filter(x -> !x.getYaPaso() && x.getSiguiente() != null)
                                .collect(Collectors.toList());
        }
        else {
            vertices = actual.getVerticesAnteriores();
            verticesLibres = vertices.stream().filter(x -> !x.getYaPaso() && x.getAnterior() != null)
                                .collect(Collectors.toList());

            if (verticesLibres.size() == 0) {
                verticesLibres = vertices.stream().filter(x -> x.getAnterior() != null)
                                .collect(Collectors.toList());
            }

            List<Integer> costos = new ArrayList<Integer>();
            for (Vertice verticeL : verticesLibres) {
                costos.add(verticeL.getCosto() + recorrer(new Camino(actual.getNumero()), verticeL.getAnterior(), actual, true, false));
            }
            if (costos.size() > 0) {
                Integer min = Collections.min(costos);
                Integer index = costos.indexOf(min);
                vertice = verticesLibres.get(index);
            }
        }

        if (verticesLibres.size() > 0) {
            if (vertice == null)
                vertice = verticesLibres.stream().min(Comparator.comparing(Vertice::getCosto)).get();

            if (marcarPaso)
                vertice.setYaPaso(true);

            Integer costo = vertice.getCosto();

            Nodo siguiente = vertice.getSiguiente();
            if (siguiente == null || inverso) {
                siguiente = vertice.getAnterior();
            }

            camino.agregarNumero(siguiente.getNumero());

            if (siguiente != origen && siguiente != this.inicial) {
                costo += recorrer(camino, siguiente, actual, inverso, marcarPaso);
            }

            return costo;
        }
        return 0;
    }
}
