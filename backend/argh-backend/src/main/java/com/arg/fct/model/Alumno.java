package com.arg.fct.model;

import java.util.List;

import com.arg.fct.model.enums.Ciclo;
import com.arg.fct.model.enums.Evaluacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nombreCompleto;

	@Enumerated(EnumType.STRING)
	private Ciclo ciclo;

	@Enumerated(EnumType.STRING)
	private Evaluacion evaluacion;

	@Column(name = "anio_curso")
	private int año;

	@OneToOne
	@JoinColumn(name = "id_tutor_docente")
	private Tutor tutor;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@OneToMany(mappedBy = "alumno")
	private List<RegistroPracticas> registrosPracticas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Ciclo getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<RegistroPracticas> getRegistrosPracticas() {
		return registrosPracticas;
	}

	public void setRegistrosPracticas(List<RegistroPracticas> registrosPracticas) {
		this.registrosPracticas = registrosPracticas;
	}

}