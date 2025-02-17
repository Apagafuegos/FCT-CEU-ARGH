package proyectoFront.gui;

import java.util.Iterator;

import org.openapitools.client.model.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InformacionAlumnoController extends AppController{
	
    @FXML
    private TextField textoAnyoCurso;

    @FXML
    private TextField textoCiclo;

    @FXML
    private TextField textoEmpresa;

    @FXML
    private TextField textoEvaluacion;

    @FXML
    private TextField textoHRealizadas;

    @FXML
    private TextField textoHRealizar;

    @FXML
    private TextField textoHorasPendientes;

    @FXML
    private TextField textoNombre;

    @FXML
    private TextField textoTutor;
    public void initialize() {
    	Usuario user = (Usuario) getParam("usuario");
    	textoAnyoCurso.setText(user.getAlumno().getAño().toString());
    	textoCiclo.setText(user.getAlumno().getCiclo().getValue());
    	textoEmpresa.setText(user.getAlumno().getEmpresa().getNombreEmpresa());
    	textoEvaluacion.setText(user.getAlumno().getEvaluacion().getValue());
    	Double v = 0.0;
    	for (int i = 0; i < user.getAlumno().getRegistrosPracticas().size(); i++) {
			v = v + user.getAlumno().getRegistrosPracticas().get(i).getCantidadHoras();
		}
    	textoHRealizadas.setText(v +"");
    	
    	textoNombre.setText(user.getAlumno().getNombreCompleto());
    	textoTutor.setText(user.getTutor().getNombreCompleto());
    	
    }
}
