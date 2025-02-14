package com.arg.fct.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arg.fct.model.Alumno;
import com.arg.fct.model.RegistroPracticas;
import com.arg.fct.model.Usuario;
import com.arg.fct.service.UsuariosService;
import com.arg.fct.service.exceptions.IncorrectPasswordException;
import com.arg.fct.service.exceptions.UserNotAuthorisedException;
import com.arg.fct.service.exceptions.UsuarioNotFoundException;
import com.arg.fct.service.exceptions.UsuariosServiceException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/fct")
@SecurityRequirement(name = "Authorization")
public class FctApiService {

	@Autowired
	private UsuariosService service;

	@GetMapping("/login")
	@Operation(summary = "Login de usuario", description = "Método que devuelve un usuario que coincida con el username y la contraseña pasadas por parámetros")
	public Usuario login(@Valid @RequestParam String username, @Valid @RequestParam String password)
			throws UsuarioNotFoundException, UsuariosServiceException, IncorrectPasswordException {
		return service.login(username, password);
	}

	@PutMapping
	@Operation(summary = "Cambio de contraseña", description = "Método que devuelve un request cambiando la contraseña del usuario que lo solicite")
	public Usuario changePassword(@Valid @RequestBody ChangePasswordRequest request)
			throws UsuarioNotFoundException, IncorrectPasswordException, UsuariosServiceException {
		System.out.println(request);
		return service.changePassword(request.getUserId(), request.getOldPassword(), request.getNewPassword());
	}

	@GetMapping("/detail/{idUser}")
	public List<RegistroPracticas> getDetail(@Valid @PathVariable Integer idUser)
			throws UsuariosServiceException, UserNotAuthorisedException {
		return service.getRegistrosPracticas(idUser);
	}

	@GetMapping("/detail/alumno/{idUser}")
	public Alumno getDatosAlumno(@PathVariable @Valid Integer idUser)
			throws UsuariosServiceException, UserNotAuthorisedException {
		return service.getDatosAlumno(idUser);
	}

	@PostMapping("/registro/{idUser}")
	public void addRegistroPracticas(@PathVariable @Valid Integer idUser,
			@RequestBody @Valid RegistroPracticas registro)
			throws UsuariosServiceException, UserNotAuthorisedException {
		service.addRegistroPracticas(idUser, registro);
	}
}
