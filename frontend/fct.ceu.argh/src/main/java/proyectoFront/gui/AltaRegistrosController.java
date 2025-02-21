package proyectoFront.gui;


import java.time.format.DateTimeFormatter;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.FctApiServiceApi;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroPracticas;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

public class AltaRegistrosController extends AppController	{
    @FXML
    private DatePicker dateSeleccionarFecha;

    @FXML
    private Spinner<Double> elegirHoras;

    @FXML
    private TextArea textoLargo;
    
    @FXML
    public void initialize() {
    	  // Definimos un valor de 0.5 como incremento y los límites 0 y 8
	    SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5, 8.0, 0.5, 0.5);
	    
	    // Asignamos el valor al Spinner
	    elegirHoras.setValueFactory(valueFactory);
 
	    // Configuramos el estilo para que el spinner tenga el comportamiento esperado
 
	    // También podemos añadir un listener para realizar acciones adicionales cuando cambie el valor
	    elegirHoras.valueProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            // Asegurarnos de que el valor esté en el rango válido
	            if (newValue <= 0) {
	            	elegirHoras.getValueFactory().setValue(0.5);
	            } else if (newValue > 8) {
	            	elegirHoras.getValueFactory().setValue(8.0);
	            }
	        }
	    });
    }
    

    @FXML
    void registrarRegistro(ActionEvent event) {
    	System.out.println(dateSeleccionarFecha.getValue());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        Integer anyo = Integer.parseInt(dateSeleccionarFecha.getValue().format(formatter));
    	FctApiServiceApi api = (FctApiServiceApi) getParam("apiServicio");
    	Usuario user = (Usuario) getParam("usuario");
    	RegistroPracticas registro = new RegistroPracticas();
    	registro.setCantidadHoras(elegirHoras.getValue());
    	registro.setDescripcion(textoLargo.getText());
    	Fecha fecha = new Fecha();
    	fecha.setAño(anyo);
    	fecha.setFecha(dateSeleccionarFecha.getValue());
    	registro.setFecha(fecha);
    	System.out.println(registro.getFecha());
    	try {
			api.addRegistroPracticas(user.getId(), registro);
		} catch (ApiException e) {
			e.printStackTrace();
		}
    	
    }
    
    

}
