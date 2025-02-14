package com.arg.fct.model;

import java.time.LocalDate;

import com.arg.fct.model.enums.Evaluacion;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fechas")
public class Fecha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer uuid;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fecha;
	
	@Column(name = "anio")
	private int año;
	
	@Enumerated(EnumType.STRING)
	private Evaluacion evaluacion;

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	@Override
	public String toString() {
		return "Fecha [uuid=" + uuid + ", fecha=" + fecha + ", año=" + año + ", evaluacion=" + evaluacion + "]";
	}
	
	
}
