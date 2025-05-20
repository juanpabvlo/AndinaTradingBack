package co.edu.unbosque.shopease_app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes")
public class OrdenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_inversionista")
    private int idInversionista;

    @Column(name = "id_comisionista")
    private int idComisionista;

    @Column(name = "id_empresa")
    private int idEmpresa;

    @Enumerated(EnumType.STRING)
    private TipoOrden tipo;

    private int cantidad;

    @Column(name = "precio_accion")
    private Double precioAccion;

    private Double total;
    @Column(name = "comision")
    private double comision;

    @Column(name = "fecha_creacion")
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public int getIdComisionista() {
        return idComisionista;
    }

    public void setIdComisionista(int idComisionista) {
        this.idComisionista = idComisionista;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void getComision(double comision) {
        this.comision = comision;
    }

    public void setComision(int comision) {
        this.comision = total * 0.01;
    }

}
