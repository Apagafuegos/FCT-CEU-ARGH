package com.arg.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arg.fct.model.Fecha;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface FechasRepository extends JpaRepository<Fecha, Integer> {
	List<Fecha> findOneByFecha(LocalDate fecha);
}
