package proyectoFront.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    	changeScene(FXML_MENU);
    	}
    }

}
