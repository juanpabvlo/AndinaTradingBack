package co.edu.unbosque.shopease_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Data
@Entity
@Getter
@Setter
@ToString
@Table(name = "inversionistas")
public class InversionistaModel {

    @Id
    private int id_inversionista;

    private Date fecha_registro;

    private double saldo;

    private double saldo_anterior;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuario;

    public int getId_inversionista() {
        return id_inversionista;

    }

    public void setId_inversionista(int id_inversionista) {
        this.id_inversionista = id_inversionista;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo_anterior() {
        return saldo_anterior;
    }

    public void setSaldo_anterior(double saldo_anterior) {
        this.saldo_anterior = saldo_anterior;
    }
}
