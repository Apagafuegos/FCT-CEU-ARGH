package proyectoFront.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MenuController extends AppController{
	
	@FXML
	BorderPane panel;
	
	
	@FXML
    public void initialize() {
		panel.setCenter(loadScene(FXML_LOGIN));
		
	}
}
