package com.arg.fct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.arg.fct.model.Usuario;
import com.arg.fct.service.IncorrectPasswordException;
import com.arg.fct.service.UsuariosService;
import com.arg.fct.service.exceptions.UsuarioNotFoundException;
import com.arg.fct.service.exceptions.UsuariosServiceException;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		/*ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		UsuariosService service = context.getBean(UsuariosService.class);
		try {
			Usuario user = service.login("csantos6952", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
			System.out.println(user.getNombreUsuario() + "-" + user.getAlumno().getNombreCompleto());
		} catch (UsuarioNotFoundException | UsuariosServiceException | IncorrectPasswordException e) {
			System.err.println("Cagaste");
		}*/
		SpringApplication.run(App.class, args);
	}

}
