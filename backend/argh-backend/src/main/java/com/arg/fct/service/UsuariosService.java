package com.arg.fct.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.arg.fct.model.Alumno;
import com.arg.fct.model.Fecha;
import com.arg.fct.model.RegistroPracticas;
import com.arg.fct.model.Usuario;
import com.arg.fct.repository.FechasRepository;
import com.arg.fct.repository.UsuariosRepository;
import com.arg.fct.service.exceptions.IncorrectPasswordException;
import com.arg.fct.service.exceptions.UserNotAuthorisedException;
import com.arg.fct.service.exceptions.UsuarioNotFoundException;
import com.arg.fct.service.exceptions.UsuariosServiceException;

@Service
public class UsuariosService {

	private static final Logger LOG = LoggerFactory.getLogger(UsuariosService.class);
	@Autowired
	UsuariosRepository repo;
	@Autowired
	FechasRepository fechaRepo;

	public Usuario login(String nombreUsuario, String password)
			throws UsuarioNotFoundException, UsuariosServiceException, IncorrectPasswordException {
		List<Usuario> user = null;
		LOG.debug("Realizando login de usuario");
		try {
			user = repo.findOneByNombreUsuario(nombreUsuario);
		} catch (DataAccessException e) {
			LOG.error("Ha habido un error en la base de datos", e);
			throw new UsuariosServiceException("Error con la BBDD", e);
		}
		if (user.isEmpty()) {
			LOG.error("Error al hacer Login, no hay usuarios con ese nombre");
			throw new UsuarioNotFoundException("No existe un usuario con ese nombre");
		} else {
			Usuario usuario = user.getFirst();
			if (usuario.getContraseña().equals(password)) {
				LOG.debug("Usuario logado con el nombre" + usuario.getNombreUsuario());
				return usuario;
			} else {
				LOG.error("Error al hacer Login, la contraseña es incorrecta");
				throw new IncorrectPasswordException("La contraseña introducida no es correcta");
			}
		}
	}

	public Usuario changePassword(Integer idUser, String oldPassword, String newPassword)
			throws UsuarioNotFoundException, IncorrectPasswordException, UsuariosServiceException {

		Usuario user;
		LOG.debug("Realizando cambio de contraseña");
		try {
			try {
				user = repo.findById(idUser)
						.orElseThrow(() -> new UsuarioNotFoundException("No existe un usuario con ese ID"));
			} catch (UsuarioNotFoundException e) {
				LOG.error("Error al cambiar la contraseña, no existe un usuario con ese ID");
				throw e;
			}
		} catch (DataAccessException e) {
			LOG.error("Ha habido un error en la base de datos", e);
			throw new UsuariosServiceException("Error con la BBDD", e);
		}
		if (user.getContraseña().equals(oldPassword)) {
			user.setContraseña(newPassword);
			LOG.debug("Contraseña cambiada correctamente");
			return repo.save(user);
		} else {
			LOG.error("Error al cambiar la contraseña, la contraseña indicada no es correcta");
			throw new IncorrectPasswordException("La contraseña introducida es incorrecta");

		}

	}

	public Alumno getDatosAlumno(Integer idUser) throws UsuariosServiceException, UserNotAuthorisedException {
		Usuario user = null;
		LOG.debug("Obteniendo datos del alumno con el ID" + idUser);
		try {
			try {
				user = repo.findById(idUser)
						.orElseThrow(() -> new UsuariosServiceException("No existe un usuario con ese ID"));
			} catch (UsuariosServiceException e) {
				LOG.error("Error al obtener datos del alumno, no existe ese id");
				throw e;
			}
		} catch (DataAccessException e) {
			LOG.error("Ha habido un error en la base de datos", e);
			throw new UsuariosServiceException("Error con la BBDD", e);
		}

		if (user.getAlumno() == null) {
			LOG.error("Error al solicitar los datos, el usuario no es un alumno.");
			throw new UserNotAuthorisedException("Este usuario no es un alumno");
		} else {
			LOG.debug("Datos del alumno devueltos correctamente. " + user.getAlumno());
			return user.getAlumno();
		}
	}

	public List<RegistroPracticas> getRegistrosPracticas(Integer idUser)
			throws UsuariosServiceException, UserNotAuthorisedException {

		Usuario user = null;
		LOG.debug("Obteniendo datos de registro de practicas con el ID" + idUser);
		try {
			user = repo.findById(idUser)
					.orElseThrow(() -> new UsuariosServiceException("No existe un usuario con ese ID"));
		} catch (DataAccessException e) {
			LOG.error("Ha habido un error en la base de datos", e);
			throw new UsuariosServiceException("Error con la BBDD", e);
		}

		if (user.getAlumno() == null) {
			LOG.error("Error al solicitar los registros, no existen datos asociados en el ID");
			throw new UserNotAuthorisedException("Este usuario no registra estos datos");
		} else {
			LOG.debug("Datos de las prácticas del alumno " + user.getAlumno() + " devueltos correctamente. "
					+ user.getAlumno().getRegistrosPracticas());
			return user.getAlumno().getRegistrosPracticas();
		}
	}

	public void addRegistroPracticas(Integer idUser, RegistroPracticas registro)
			throws UsuariosServiceException, UserNotAuthorisedException {
		Usuario user = null;
		LOG.debug("Añadiendo datos de registro de practicas " + registro);
		try {
			try {
				user = repo.findById(idUser)
						.orElseThrow(() -> new UsuariosServiceException("No existe un usuario con ese ID"));
			} catch (UsuariosServiceException e) {
				LOG.error("Error, no existe ningún usuario con el ID" + idUser);
				throw e;
			}
		} catch (DataAccessException e) {
			LOG.error("Ha habido un error en la base de datos", e);
			throw new UsuariosServiceException("Error guardando el registro de prácticas", e);
		}
		if (user.getAlumno() == null) {
			LOG.error("Error, el usuario no es un alumno");
			throw new UserNotAuthorisedException("Este usuario no puede registrar estos datos");
		} else {
			try {
				Fecha fecha = fechaRepo.findOneByFecha(registro.getFecha().getFecha())
						.orElseThrow(() -> new UsuariosServiceException());
				registro.setFecha(fecha);
				user.getAlumno().getRegistrosPracticas().add(registro);
				repo.save(user);
				LOG.debug("Datos de registros añadidos correctamente");
			} catch (DataAccessException e) {
				LOG.error("Ha habido un error en la base de datos", e);
				throw new UsuariosServiceException("Error guardando el registro de prácticas", e);
			}
		}

	}

	// Métodos de administrador

	public List<Usuario> getUsers() throws UsuariosServiceException {
		LOG.debug("Buscando usuarios administradores...");
		try {
			LOG.debug("Usuarios administradores devueltos correctamente");
			return repo.findAll();
		} catch (DataAccessException e) {
			LOG.error("Ha habido un error en la base de datos", e);
			throw new UsuariosServiceException("Error con la BBDD", e);
		}
	}

	public Usuario changeUserPassword(Integer idUser, String newPassword)
			throws UsuarioNotFoundException, UsuariosServiceException {
		LOG.debug("Cambiando contraseña...");
		try {
			Usuario user = repo.findById(idUser)
					.orElseThrow(() -> new UsuarioNotFoundException("No existe un usuario con ese ID"));
			user.setContraseña(newPassword);
			LOG.debug("Contraseña cambiada correctamente");
			return repo.save(user);

		} catch (DataAccessException e) {
			LOG.error("Ha habido un error en la base de datos", e);
			throw new UsuariosServiceException("Error con la BBDD", e);
		}
	}

}
