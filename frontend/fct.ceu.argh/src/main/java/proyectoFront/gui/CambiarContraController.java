package proyectoFront.gui;

import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.FctApiServiceApi;
import org.openapitools.client.model.ChangePasswordRequest;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class CambiarContraController extends AppController {

	private FctApiServiceApi api = (FctApiServiceApi) getParam("apiServicio");

	@FXML
	private PasswordField antiguaField;

	@FXML
	private Button buttonContra;

	@FXML
	private PasswordField nuevaField;

	@FXML
	private PasswordField repeatContra;

	@FXML
	void confirmarContra(ActionEvent event) {
		Usuario user = (Usuario) getParam("usuario");
		ChangePasswordRequest req = new ChangePasswordRequest();
		String contraseñaActual = DigestUtils.sha256Hex(antiguaField.getText());
		String contraseñaNueva = DigestUtils.sha256Hex(nuevaField.getText());

		req.setNewPassword(contraseñaNueva);
		req.setUserId(user.getId());
		req.setOldPassword(contraseñaActual);
		if(nuevaField.getText().length() >=  8) {
		
		if (nuevaField.getText().equals(repeatContra.getText())) {

			try {
				api.changePassword(req);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("Se ha cambiado la contraseña con éxito");
				alert.setTitle("Perfecto");
				alert.showAndWait();
			} catch (ApiException e) {
				e.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("La contraseña introducia como actual no es correcta");
				alert.setTitle("Error fatal");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Las contraseñas no coinciden");
			alert.setTitle("Error fatal");
			alert.showAndWait();
		}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("La contraseña debe medir 8 dígitos o más");
			alert.setTitle("Cuidao!");
			alert.showAndWait();
		}
	}

}
