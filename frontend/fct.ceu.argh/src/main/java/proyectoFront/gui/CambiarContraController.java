package proyectoFront.gui;

import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.FctApiServiceApi;
import org.openapitools.client.model.ChangePasswordRequest;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CambiarContraController extends AppController {

	private FctApiServiceApi api = (FctApiServiceApi) getParam("apiServicio");

	@FXML
	private Button buttonContra;

	@FXML
	private TextField antiguaField;

	@FXML
	private TextField nuevaField;

	@FXML
	void confirmarContra(ActionEvent event) {
		Usuario user = (Usuario) getParam("usuario");
		ChangePasswordRequest req = new ChangePasswordRequest();
		String contraseñaActual = DigestUtils.sha256Hex(antiguaField.getText());
		String contraseñaNueva = DigestUtils.sha256Hex(nuevaField.getText());

		req.setNewPassword(contraseñaNueva);
		req.setUserId(user.getId());
		req.setOldPassword(contraseñaActual);
		try {
			api.changePassword(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

}
