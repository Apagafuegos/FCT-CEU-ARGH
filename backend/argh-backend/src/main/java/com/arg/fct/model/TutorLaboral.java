package com.arg.fct.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class TutorLaboral {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nombreCompleto;
	private String email;
	private int telefono;
	
	@OneToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
}
