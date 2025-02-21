package proyectoFront.gui;

import java.util.List;

import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroPracticas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegistrosController extends AppController {

	@FXML
	private Button botonConsulta;
    @FXML
    private RadioButton radioCompletas;

    @FXML
    private RadioButton radioSinCompletar;

    @FXML
    private RadioButton radioTodas;

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
	
    @FXML
    private ComboBox<String> checkFechas;

	private ObservableList<RegistroPracticas> datosTabla;
	private Alumno alumno = (Alumno) getParam("alumno");
	


	@FXML
	public void initialize() {		
		checkFechas.getItems().add("Fecha Completa");
		checkFechas.getItems().add("Fecha sin completar");
		checkFechas.getItems().add("Todas");
		
		List<RegistroPracticas> registros = alumno.getRegistrosPracticas();
		columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		columnaHoras.setCellValueFactory(new PropertyValueFactory<>("cantidadHoras"));
		
		datosTabla = FXCollections.observableArrayList();
		tablaRegistros.setItems(datosTabla);
		datosTabla.setAll(registros);
	}
	
    @FXML
    void getFechasFiltradas(ActionEvent event) {
    	if(checkFechas.getSelectionModel().getSelectedItem().equals("Fecha Completa") ) {
    		
    	}
    	else if (checkFechas.getSelectionModel().getSelectedItem().equals("Fecha sin completar")) {
    		
    	}else if (checkFechas.getSelectionModel().getSelectedItem().equals("Todas")){
    		
    	}
    	
    }
	
	

}
