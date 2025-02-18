package proyectoFront.gui;

import java.util.List;

import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroPracticas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegistrosController extends AppController {

	@FXML
	private Button botonConsulta;
	@FXML
	private CheckBox checkCompleta;
	@FXML
	private CheckBox checkNoCompleta;
	@FXML
	private CheckBox checkTodo;
	@FXML
	private DatePicker pickerDesde;
	@FXML
	private DatePicker pickerHasta;
	@FXML
	private TableView<RegistroPracticas> tablaRegistros;
	@FXML
	private TableColumn<RegistroPracticas, String> columnaDescripcion;
	@FXML
	private TableColumn<RegistroPracticas, Fecha> columnaFecha;
	@FXML
	private TableColumn<RegistroPracticas, Double> columnaHoras;

	private ObservableList<RegistroPracticas> datosTabla;
	private Alumno alumno = (Alumno) getParam("alumno");

	@FXML
	public void initialize() {
		List<RegistroPracticas> registros = alumno.getRegistrosPracticas();
		columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		columnaHoras.setCellValueFactory(new PropertyValueFactory<>("cantidadHoras"));
		
		datosTabla = FXCollections.observableArrayList();
		tablaRegistros.setItems(datosTabla);
		datosTabla.setAll(registros);
	}

}
