package proyectoFront.gui;

import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.FctApiServiceApi;
import org.openapitools.client.model.Usuario;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends AppController {
	@FXML
	private Button botonRestablecerContra;

	@FXML
	private Button buttonCerrar;

	@FXML
	private PasswordField textContra;

	@FXML
	private TextField textUser;

	@FXML
	private Button entrarUsuarioButton;

	private FctApiServiceApi api;

	public LoginController() {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("http://localhost:8080");
		apiClient.setApiKey("EghAcof");
		api = new FctApiServiceApi(apiClient);
		addParam("apiServicio", apiClient); // para poder acceder en otras pantallas
	}

	@FXML
	void cambiarInfoUsuario(ActionEvent event) {
		//habias puesto al reves usuario y contraseña
		if (login(textUser.getText(), textContra.getText()) != null) {
			changeScene(FXML_MENU);
		} else {
			// enseña popup si el user es null
		}

	}

	public Usuario login(String username, String password) {
		password = DigestUtils.sha256Hex(password);
		System.out.println(username + " " + password);
		try {
			return api.login(username, password);
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
	}

	@FXML
	void cerrarPestaña(ActionEvent event) {
		Platform.exit();

	}

	@FXML
	void cambiarRestablecerContra(ActionEvent event) {
		changeScene(FXML_RESTABLECER_CONTRA);
	}

}
