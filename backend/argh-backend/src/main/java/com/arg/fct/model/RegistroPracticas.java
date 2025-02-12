package com.arg.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class RegistroPracticas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_alumno_asociado")
	private Alumno alumno;

	@ManyToOne
	@JoinColumn(name = "id_fecha")
	private Fecha fecha;
	
	private int cantidadHoras;
	private String descripcion;
}
