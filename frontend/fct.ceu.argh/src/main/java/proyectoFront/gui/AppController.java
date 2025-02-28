package proyectoFront.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openapitools.client.model.Usuario;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import proyectoFront.App;

public class AppController {

	public static final String FXML_INICIO = "/app/inicio.fxml";
	public static final String FXML_LOGIN = "/app/login.fxml";
	public static final String FXML_MENU = "/app/menu.fxml";
	public static final String FXML_RESTABLECER_CONTRA = "/app/restablecerContra.fxml";
	public static final String FXML_REGISTROS = "/app/registro.fxml";
	public static final String FXML_ALTA_REGISTROS = "/app/altaRegistro.fxml";
	public static final String FXML_INFO_ALUMNO = "/app/infoAlumno.fxml";
	public static final String FXML_REGISTRO_DETALLE = "/app/detalleRegistro.fxml";

	protected static Stage primaryStage;

	private Usuario user;

	public AppController() {
		user = new Usuario();
	}

	public AppController(Stage stage) {
		primaryStage = stage;

	}

	public Parent loadScene(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
			Scene scene = new Scene(loader.load());
			return scene.getRoot();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void changeScene(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addParam(String key, Object param) {
		Map<String, Object> mapa = (Map<String, Object>) primaryStage.getUserData();
		if (mapa == null) {
			mapa = new HashMap<String, Object>();
			primaryStage.setUserData(mapa);
		}
		mapa.put(key, param);
	}

	public Object getParam(String key) {
		Map<String, Object> mapa = (Map<String, Object>) primaryStage.getUserData();
		return mapa.get(key);
	}

	public void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setContentText(message);
		alert.showAndWait();
	}

}
