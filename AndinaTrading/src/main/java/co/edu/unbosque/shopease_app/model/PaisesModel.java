package co.edu.unbosque.shopease_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Getter
@Setter

@Table(name="paises")
public class PaisesModel {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

	public String getSituacion_economica() {
		return situacion_economica;
	}

	public void setSituacion_economica(String situacion_economica) {
		this.situacion_economica = situacion_economica;
	}

	@NotNull(message = "El nombre de la categoría no puede ser nulo")
    private String nombre;

    private String situacion_economica;

}
