package com.arg.fct.api;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arg.fct.fakeService.FakeService;
import com.arg.fct.model.RegistroPracticas;
import com.arg.fct.model.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/fct")
@SecurityRequirement(name = "Authorization")
public class FctApiService {
	
	@Autowired
	private FakeService service;
	
	@GetMapping("/login")
	@Operation(summary = "Login de usuario" , description = "Método que devuelve un usuario que coincida con el username y la contraseña pasadas por parámetros" )
	public Usuario login(@Valid @RequestParam String username,@Valid @RequestParam String password) {
		return service.login(username,password);
	}
	@PutMapping
	@Operation(summary = "Cambio de contraseña", description= "Método que devuelve un request cambiando la contraseña del usuario que lo solicite")
	public Usuario changePassword(ChangePasswordRequest request) {
		Usuario user = service.changePassword(request.getUserId(), request.getOldPassword(), request.getNewPassword());
		return user;
	}
	
	@GetMapping("/detail/{id}")
	public List<RegistroPracticas> getDetail(@Valid @PathVariable Long id) {
		return service.getDetalles(id);
	}
}
