package entities;

public class Response {
    private Integer costoTotal;
    private Integer arcosTotal;

    public Response(Integer costoTotal, Integer arcosTotal) {
        this.costoTotal = costoTotal;
        this.arcosTotal = arcosTotal;
    }

    public Integer getCostoTotal() {
        return costoTotal;
    }

    public Integer getArcosTotal() {
        return arcosTotal;
    }
}
