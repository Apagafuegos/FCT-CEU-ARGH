package com.arg.fct.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.arg.fct.model.Alumno;
import com.arg.fct.model.RegistroPracticas;
import com.arg.fct.model.Usuario;
import com.arg.fct.repository.UsuariosRepository;
import com.arg.fct.service.exceptions.UserNotAuthorisedException;
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

	public Usuario changePassword(Integer idUser, String oldPassword, String newPassword)
			throws UsuarioNotFoundException, IncorrectPasswordException, UsuariosServiceException {

		Usuario user;
		try {
			user = repo.findById(idUser)
					.orElseThrow(() -> new UsuarioNotFoundException("No existe un usuario con ese ID"));
		} catch (DataAccessException e) {
			throw new UsuariosServiceException("Error con la BBDD", e);
		}
		if (user.getContraseña().equals(oldPassword)) {
			user.setContraseña(newPassword);
			return repo.save(user);
		} else {
			throw new IncorrectPasswordException("La contraseña introducida es incorrecta");
		}

	}

	public Alumno getDatosAlumno(Integer idUser) throws UsuariosServiceException, UserNotAuthorisedException {
		Usuario user = null;
		try {
			user = repo.findById(idUser).orElseThrow(() -> new UsuariosServiceException("Error con la BBDD"));
		} catch (DataAccessException e) {
			throw new UsuariosServiceException("Error con la BBDD", e);
		}

		if (user.getAlumno() == null) {
			throw new UserNotAuthorisedException("Este usuario no es un alumno");
		} else {
			return user.getAlumno();
		}
	}

	public List<RegistroPracticas> getRegistrosPracticas(Integer idUser)
			throws UsuariosServiceException, UserNotAuthorisedException {

		Usuario user = null;
		try {
			user = repo.findById(idUser).orElseThrow(() -> new UsuariosServiceException("Error con la BBDD"));
		} catch (DataAccessException e) {
			throw new UsuariosServiceException("Error con la BBDD", e);
		}

		if (user.getAlumno() == null) {
			throw new UserNotAuthorisedException("Este usuario no registra estos datos");
		} else {
			return user.getAlumno().getRegistrosPracticas();
		}
	}

	// Métodos de administrador

	public List<Usuario> getUsers() throws UsuariosServiceException {
		try {
			return repo.findAll();
		} catch (DataAccessException e) {
			throw new UsuariosServiceException("Error con la BBDD", e);
		}
	}

	public Usuario changeUserPassword(Integer idUser, String newPassword)
			throws UsuarioNotFoundException, UsuariosServiceException {
		try {
			Usuario user = repo.findById(idUser)
					.orElseThrow(() -> new UsuarioNotFoundException("No existe un usuario con ese ID"));
			user.setContraseña(newPassword);
			return repo.save(user);
		} catch (DataAccessException e) {
			throw new UsuariosServiceException("Error con la BBDD", e);
		}
	}

}
