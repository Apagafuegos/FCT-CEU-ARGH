package com.arg.fct.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Fecha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String uuid;
	private LocalDate fecha;
	private int año;
	private String evaluacion;
}
