package co.edu.unbosque.shopease_app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes")
@Schema(description = "Modelo que representa una orden de compra o venta")
public class OrdenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la orden", example = "1")
    private int id;

    @Column(name = "id_inversionista")
    @Schema(description = "ID del inversionista", example = "101")
    private int idInversionista;

    @Column(name = "id_comisionista")
    @Schema(description = "ID del comisionista", example = "202")
    private Integer idComisionista;

    @Column(name = "id_empresa")
    @Schema(description = "ID de la empresa", example = "303")
    private int idEmpresa;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo de orden: compra o venta", example = "compra")
    private TipoOrden tipo;

    @Schema(description = "Cantidad de acciones", example = "50")
    private int cantidad;

    @Column(name = "precio_accion")
    @Schema(description = "Precio por acción", example = "1000.5")
    private Double precioAccion;

    @Schema(description = "Total de la orden (cantidad * precio)", example = "50000.0")
    private Double total;

    @Column(name = "comision")
    @Schema(description = "Comisión calculada automáticamente (10% del total)", example = "5000.0", accessMode = Schema.AccessMode.READ_ONLY)
    private Double comision;

    @Column(name = "fecha_creacion")
    @Schema(description = "Fecha de creación de la orden", example = "2025-05-19T14:00:00")
    private LocalDateTime fechaCreacion;

    public enum TipoOrden {
        compra, venta
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public int getIdInversionista() {
        return idInversionista;
    }

    public void setIdInversionista(int idInversionista) {
        this.idInversionista = idInversionista;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public TipoOrden getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrden tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioAccion() {
        return precioAccion;
    }

    public void setPrecioAccion(Double precioAccion) {
        this.precioAccion = precioAccion;
    }

    public double getTotal() {
        return total;
    }

    // Cuando se establece el total, se recalcula automáticamente la comisión
    public void setTotal(double total) {
        this.total = total;
        this.comision = total * 0.10;
    }

    public double getComision() {
        return comision;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdComisionista() {
        return idComisionista;
    }

    public void setIdComisionista(int idComisionista) {
        this.idComisionista = idComisionista;
    }

    // Método para calcular total a partir de cantidad y precio, si se desea
    // automatizar aún más
    public void calcularTotalYComision() {
        if (this.cantidad > 0 && this.precioAccion != null) {
            setTotal(this.cantidad * this.precioAccion);
        }
    }
}
