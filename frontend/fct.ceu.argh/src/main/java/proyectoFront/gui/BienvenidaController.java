package proyectoFront.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class BienvenidaController extends AppController{
    @FXML
    private Button botonInicio;
    @FXML
    private CheckBox checkAlumno;

    @FXML
    void entrarLoggin(ActionEvent event) {
    	if(checkAlumno.isSelected()) {
    	changeScene(FXML_LOGIN);
    	}else {
    	Alert a = new Alert (AlertType.ERROR);
    	a.setHeaderText(null);
    	a.setContentText("Selecciona que eres un alumno");
    	a.setTitle("Error 404");
    	a.showAndWait();
    }
    }

}
