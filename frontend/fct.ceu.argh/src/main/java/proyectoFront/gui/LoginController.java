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
		Usuario user = null;
		try {
			user = login(textUser.getText(), textContra.getText());
			addParam("usuario", user);
			changeScene(FXML_MENU);
		} catch (ApiException e) {
			showAlert("Usuario o contraseña incorrectos");
		}

	}

	public Usuario login(String username, String password) throws ApiException {
		password = DigestUtils.sha256Hex(password);
		return api.login(username, password);
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
