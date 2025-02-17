package proyectoFront.gui;

import org.apache.commons.codec.digest.DigestUtils;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.FctApiServiceApi;
import org.openapitools.client.model.ChangePasswordRequest;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CambiarContraController extends AppController {
	
	private  FctApiServiceApi api;

    @FXML
    private Button buttonContra;

    @FXML
    private TextField textNexContra;

    @FXML
    private TextField textRepeatContra;

    @FXML
    void confirmarContra(ActionEvent event) {
    	Usuario user = (Usuario) getParam("usuario");
    	ChangePasswordRequest req = new ChangePasswordRequest();
    	String contra =  DigestUtils.sha256Hex(textNexContra.getText());
    	String contraDos =  DigestUtils.sha256Hex(textRepeatContra.getText());

    	req.setNewPassword(contra);
    	req.setUserId(user.getId());
    	req.setOldPassword(contraDos);
    		ApiClient client = (ApiClient) getParam("apiServicio");
    		api = new FctApiServiceApi(client);
    		try {
				api.changePassword(req);
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

}
