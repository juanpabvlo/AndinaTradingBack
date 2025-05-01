package co.edu.unbosque.shopease_app.dto;

public class AccionHistoricaDTO {

    private String fecha;
    private Double precio;

    public AccionHistoricaDTO() {
    }

    public AccionHistoricaDTO(String fecha, Double precio) {
        this.fecha = fecha;
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
