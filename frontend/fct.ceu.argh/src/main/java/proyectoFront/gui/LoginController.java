package proyectoFront.gui;

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
    
    
    @FXML
    void cambiarInfoUsuario(ActionEvent event) {
    	login(textContra.getText(), textUser.getText());
    	changeScene(FXML_MENU);

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
