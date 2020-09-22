package entities;

public class DatosArchivo {
    private Integer origen;
    private Integer destino;
    private Integer valor;

    public Integer extraerTotalEsperado(String lineaTotalEsperado, String nombreProp) {
        String total = lineaTotalEsperado.replaceAll(String.format("\\s*(%s)\\s*:\\s*", nombreProp), "");
        return Integer.parseInt(total);
    }

    public void extraerInfoDesdeString(String datos) {
        //( 1, 2)  coste 13 
        String[] secciones = datos.split("coste");
        for (int i = 0; i < secciones.length; i++) {
            secciones[i] = secciones[i].replaceAll(" ", "").replaceAll("(\\(|\\))", "");
        }
        this.valor = Integer.parseInt(secciones[1]);
        String[] numeros = secciones[0].split(",");
        this.origen = Integer.parseInt(numeros[0]);
        this.destino = Integer.parseInt(numeros[1]);
    }

    public Integer getOrigen() {
        return origen;
    }

    public Integer getDestino() {
        return destino;
    }

    public Integer getValor() {
        return valor;
    }
}
