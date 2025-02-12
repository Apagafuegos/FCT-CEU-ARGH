package com.arg.fct.model;

import java.util.List;

import com.arg.fct.model.enums.Ciclo;
import com.arg.fct.model.enums.Evaluacion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nombreCompleto;
	private Ciclo ciclo;
	private Evaluacion evaluacion;
	private int año;

	@OneToOne
	@JoinColumn(name = "id_tutor_docente")
	private Tutor tutor;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@OneToMany(mappedBy = "alumno")
	private List<RegistroPracticas> registrosPracticas;
}