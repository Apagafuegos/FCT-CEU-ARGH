package proyectoFront.gui;

import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.RegistroPracticas;
import org.openapitools.client.model.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InformacionAlumnoController extends AppController {

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
		Alumno alumno = user.getAlumno();
		addParam("alumno", alumno);

		textoAnyoCurso.setText(alumno.getAño().toString());
		textoCiclo.setText(alumno.getCiclo().getValue());
		textoEmpresa.setText(alumno.getEmpresa().getNombreEmpresa());
		textoEvaluacion.setText(alumno.getEvaluacion().getValue());

		double cantidadHorasAlumno = 0D;
		for (RegistroPracticas registro : alumno.getRegistrosPracticas()) {
			cantidadHorasAlumno += registro.getCantidadHoras();
		}

		textoHRealizar.setText("370");
		textoHRealizadas.setText(Double.toString(cantidadHorasAlumno));
		textoHorasPendientes.setText(Double.toString(370D - cantidadHorasAlumno));

		textoNombre.setText(user.getAlumno().getNombreCompleto());
		textoTutor.setText(user.getAlumno().getTutor().getNombreCompleto());

	}
}
