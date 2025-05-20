package co.edu.unbosque.shopease_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Getter
@Setter
@ToString
@Table(name="empresas")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;


    @Column(nullable = false)
    private double valor_accion;

    @Column(nullable = false)
    private double valor_anterior;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getValor_accion() {
		return valor_accion;
	}

	public void setValor_accion(double valor_accion) {
		this.valor_accion = valor_accion;
	}

	public double getValor_anterior() {
		return valor_anterior;
	}

	public void setValor_anterior(double valor_anterior) {
		this.valor_anterior = valor_anterior;
	}
}
