package com.arg.fct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arg.fct.model.Usuario;
import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

	List<Usuario> findOneByNombreUsuario(String nombreUsuario);

}
