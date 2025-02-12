package com.arg.fct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.arg.fct.model.Usuario;
import com.arg.fct.repository.UsuariosRepository;
import com.arg.fct.service.exceptions.UsuarioNotFoundException;
import com.arg.fct.service.exceptions.UsuariosServiceException;

@Service
public class UsuariosService {

	@Autowired
	UsuariosRepository repo;

	public Usuario login(String nombreUsuario, String password)
			throws UsuarioNotFoundException, UsuariosServiceException, IncorrectPasswordException {
		List<Usuario> user = null;
		try {
			user = repo.findOneByNombreUsuario(nombreUsuario);
		} catch (DataAccessException e) {
			throw new UsuariosServiceException("Error con la BBDD", e);
		}
		if (user.isEmpty()) {
			throw new UsuarioNotFoundException("No existe un usuario con ese nombre");
		} else {
			Usuario usuario = user.getFirst();
			if (usuario.getContraseña().equals(password)) {
				return usuario;
			} else {
				throw new IncorrectPasswordException("La contraseña introducida no es correcta");
			}
		}
	}

}
