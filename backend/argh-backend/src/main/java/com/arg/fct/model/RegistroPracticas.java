package com.arg.fct.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "registro")
public class RegistroPracticas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	@JoinColumn(name = "id_fecha")
	private Fecha fecha;

	@Column(name = "total_horas")
	@Min(value = 0)
	@Max(value = 8)
	private double cantidadHoras;

	@NotNull
	@NotBlank
	@Size(max = 200)
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public double getCantidadHoras() {
		return cantidadHoras;
	}

	public void setCantidadHoras(double cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "RegistroPracticas [id=" + id + ", fecha=" + fecha + ", cantidadHoras=" + cantidadHoras
				+ ", descripcion=" + descripcion + "]";
	}
	
	

}
