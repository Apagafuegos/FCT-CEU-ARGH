package proyectoFront.gui;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MenuController extends AppController {

	@FXML
	private MenuItem cerrarApp;

	@FXML
	private MenuItem contraPanel;

	@FXML
	private MenuItem altaRegistroPanel;

	@FXML
	private MenuItem informacionPanel;

	@FXML
	private BorderPane panel;
	@FXML
	private MenuItem cerrarSesionPanel;

	@FXML
	private MenuItem registroPanel;

	@FXML
	void cambiarRegistrosAlta(ActionEvent event) {
		panel.setCenter(loadScene(FXML_ALTA_REGISTROS));
	}

	@FXML
	void cambiarInformacion(ActionEvent event) {
		panel.setCenter(loadScene(FXML_INFO_ALUMNO));
	}

	@FXML
	void cambiarRegistros(ActionEvent event) {
		panel.setCenter(loadScene(FXML_REGISTROS));
	}

	@FXML
	void cambiarPaginaContra(ActionEvent event) {

		panel.setCenter(loadScene(FXML_RESTABLECER_CONTRA));

	}

	@FXML
	void cerrarSesion(ActionEvent event) {
		changeScene(FXML_INICIO);
	}

	@FXML
	void cerrarAplicacion(ActionEvent event) {
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText(null);
		a.setContentText("¿Deseas cerrar la aplicación?");
		a.setTitle("Confirmación");
		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.exit(0);
		}
	}

	@FXML
	public void initialize() {
		panel.setCenter(loadScene(FXML_INFO_ALUMNO));

	}
}
