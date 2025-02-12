package com.arg.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nombreUsuario;
	private String contraseña;

	@OneToOne
	@JoinColumn(name = "id_alumno_asociado", nullable = true)
	private Alumno alumno;

	@OneToOne
	@JoinColumn(name = "id_tutor_asociado", nullable = true)
	private Tutor tutor;

	private boolean activo;

}
