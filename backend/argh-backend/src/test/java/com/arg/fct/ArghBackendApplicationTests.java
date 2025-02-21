package com.arg.fct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.arg.fct.model.Alumno;
import com.arg.fct.model.Empresa;
import com.arg.fct.model.Fecha;
import com.arg.fct.model.RegistroPracticas;
import com.arg.fct.model.Tutor;
import com.arg.fct.model.Usuario;
import com.arg.fct.model.enums.Ciclo;
import com.arg.fct.model.enums.Evaluacion;
import com.arg.fct.repository.UsuariosRepository;
import com.arg.fct.service.UsuariosService;
import com.arg.fct.service.exceptions.IncorrectPasswordException;
import com.arg.fct.service.exceptions.UserNotAuthorisedException;
import com.arg.fct.service.exceptions.UsuarioNotFoundException;
import com.arg.fct.service.exceptions.UsuariosServiceException;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArghBackendApplicationTests {
	@Autowired
	private UsuariosService service;
	
	private Usuario user;
	
	@Autowired
	private UsuariosRepository repoUser;


	
	@Test
	void contextLoads() {
	}

	@BeforeEach
	void initUser() {
		user = new Usuario();
		user.setNombreUsuario("eibol");
		user.setActivo(true);
		user.setContraseña("123456789");
		user.setAlumno(new Alumno());
		user.getAlumno().setNombreCompleto("Eibol del valle Domínguez");
		user.getAlumno().setAño(2025);
		user.getAlumno().setCiclo(Ciclo.DAM);
		user.getAlumno().setEvaluacion(Evaluacion.MARZO);
		user.getAlumno().setEmpresa(new Empresa());
		user.getAlumno().getEmpresa().setActivo(true);
		user.getAlumno().getEmpresa().setNombreEmpresa("tier wuan");
		user.getAlumno().setRegistrosPracticas(new ArrayList<RegistroPracticas>());
		user.getAlumno().getRegistrosPracticas().add(new RegistroPracticas());
		user.getAlumno().getRegistrosPracticas().get(0).setCantidadHoras(33);
		user.getAlumno().getRegistrosPracticas().get(0).setFecha(new Fecha());
		user.getAlumno().getRegistrosPracticas().get(0).getFecha().setAño(2025);
		user.getAlumno().getRegistrosPracticas().get(0).getFecha().setEvaluacion(Evaluacion.MARZO);
		user.getAlumno().getRegistrosPracticas().get(0).getFecha().setFecha(LocalDate.now());
		user.getAlumno().getRegistrosPracticas().get(0).setDescripcion("Hoy he aprendido a hacer syso");
		user.getAlumno().setTutor(new Tutor());
		user.getAlumno().getTutor().setActivo(true);
		user.getAlumno().getTutor().setNombreCompleto("Don Rafel Nadal Parera");
		
		
		
	}
	@Test
	@Order(1)
	void insertUser() {
		user.setAlumno(null);
		repoUser.save(user);
	}
	@Test
	@Order(3)
	void testLoginMalUsername()  {
		assertThrows(UsuarioNotFoundException.class, () -> service.login("quistian", "123456789"));
	}
	
	@Test
	@Order(4)
	void testLoginPassMal() {
		assertThrows(IncorrectPasswordException.class, () -> service.login("eibol", "548734578475785"));
	}
	@Test
	@Order(2)
	void testLoginBien() throws UsuarioNotFoundException, UsuariosServiceException, IncorrectPasswordException {
		user = service.login(user.getNombreUsuario(), user.getContraseña());
		assertNotNull(user);
		assertEquals("eibol",user.getNombreUsuario());
		assertEquals("123456789", user.getContraseña());
	}
	
	@Test
	@Order(6)
	void changePassMalId()  {
		
		assertThrows(UsuarioNotFoundException.class, () -> service.changePassword(33, "123456789", "987654321"));
	}
	
	@Test
	@Order(7)
	void changePassMal()  {
		assertThrows(IncorrectPasswordException.class, () -> service.changePassword(1, "1234555555", "1234567777"));
	}
	
	@Test
	@Order(5)
	void changePassBien() throws UsuarioNotFoundException, IncorrectPasswordException, UsuariosServiceException {
		user = service.changePassword(1, user.getContraseña(), "987654321");
		assertNotNull(user);
		assertEquals("987654321",user.getContraseña());
		assertEquals(1, user.getId());
	}
	@Test
	@Order(8)
	void getDatosBien() throws UsuariosServiceException, UserNotAuthorisedException {
		Alumno a  = service.getDatosAlumno(1);
		assertNotNull(a);
		assertEquals(1, user.getId());
	}
	
	@Test
	@Order(9)
	void getDatosAlumnoIdMal() {
		assertThrows(UsuarioNotFoundException.class, () -> service.getDatosAlumno(3));
	}
	
	@Test
	@Order(10)
	void getDatosAlumnoTutorMal() {
		assertThrows(UserNotAuthorisedException.class, () -> service.getDatosAlumno(2));
	}
	
	@Test
	@Order(11)
	void addRegistroBien() throws UsuariosServiceException, UserNotAuthorisedException {
		
			service.addRegistroPracticas(1, user.getAlumno().getRegistrosPracticas().get(0));
			assertNotNull(user.getAlumno().getRegistrosPracticas().get(0));
			assertEquals(1, user.getAlumno().getRegistrosPracticas().size());
	
			
	}
	@Test
	@Order(12)
	void getRegistrosBien() throws UsuariosServiceException, UserNotAuthorisedException {
		List<RegistroPracticas> resultado = service.getRegistrosPracticas(1);
		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(resultado));
	}
	@Test
	@Order(13)
	void getRegistrosIdMal() {
		assertThrows(UsuariosServiceException.class, () ->  service.getRegistrosPracticas(3));
	}
}
