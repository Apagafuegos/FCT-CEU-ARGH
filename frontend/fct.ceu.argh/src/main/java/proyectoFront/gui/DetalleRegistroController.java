package proyectoFront.gui;

import org.openapitools.client.model.RegistroPracticas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DetalleRegistroController extends AppController{

    @FXML
    private TextArea descText;

    @FXML
    private Text descripcionTexto;

    @FXML
    private TextField fechaText;

    @FXML
    private TextField horasText;
    private RegistroPracticas registro = (RegistroPracticas) getParam("registro");
    
    
    
	@FXML
	public void initialize() {
	descText.setText(registro.getDescripcion());
	fechaText.setText(registro.getFecha().getFecha() + "");
	horasText.setText(registro.getCantidadHoras() + "");
		
	}
    @FXML
    void borrarRegistro(ActionEvent event) {
    	//No puedo hacerlo de otro modo porque el back no tiene un borrarRegistro :D:D
    	
    	changeScene(FXML_MENU);
    }

    @FXML
    void volverAlMenu(ActionEvent event) {
    	changeScene(FXML_MENU);
    }
}
