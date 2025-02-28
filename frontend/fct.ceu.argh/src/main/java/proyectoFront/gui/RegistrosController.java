package proyectoFront.gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openapitools.client.model.Alumno;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroPracticas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
	private TableColumn<RegistroPracticas, LocalDate> columnaFecha;

	@FXML
	private TableColumn<RegistroPracticas, Double> columnaHoras;

	@FXML
	private ComboBox<String> checkFechas;

	private ObservableList<RegistroPracticas> datosTabla;
	private Alumno alumno = (Alumno) getParam("alumno");

	// Como no tengo modo de tener las fechas sin completar, he puesto el rango de fechas de las practicas
	// Comienzo y fin
	private LocalDate fechaInicio = LocalDate.of(2025, 3, 3);
	private LocalDate fechaFin = LocalDate.of(2025, 5, 30);

	@FXML
	public void initialize() {
		checkFechas.getItems().add("Sólo fechas completas");
		checkFechas.getItems().add("Sólo fechas sin completar");
		checkFechas.getItems().add("Todas");

		columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		columnaHoras.setCellValueFactory(new PropertyValueFactory<>("cantidadHoras"));

		columnaFecha.setCellValueFactory(cellData -> {
			Fecha fecha = cellData.getValue().getFecha();
			return javafx.beans.binding.Bindings.createObjectBinding(() -> fecha != null ? fecha.getFecha() : null);
		});
		
		datosTabla = FXCollections.observableArrayList();
		tablaRegistros.setItems(datosTabla);
		actualizarTabla();
		
		//Doble click
	    tablaRegistros.setRowFactory(tv -> {
	        TableRow<RegistroPracticas> row = new TableRow<>();
	        row.setOnMouseClicked(event -> {
	            if (event.getClickCount() == 2 && !row.isEmpty()) {
	                RegistroPracticas registroSeleccionado = row.getItem();
	                abrirDetalleRegistro(registroSeleccionado);
	            }
	        });
	        return row;
	    });
		
		
	}
	private void abrirDetalleRegistro(RegistroPracticas registro) {
	    addParam("registro", registro);

	    changeScene(FXML_REGISTRO_DETALLE);
	}

	private void actualizarTabla() {
		List<RegistroPracticas> registros = alumno.getRegistrosPracticas();

		List<RegistroPracticas> todasLasFechas = new ArrayList<>();
		for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
			RegistroPracticas registro = new RegistroPracticas();
			Fecha fechaObj = new Fecha();
			fechaObj.setFecha(fecha);
			registro.setFecha(fechaObj);
			registro.setDescripcion("");
			registro.setCantidadHoras(0.0);
			todasLasFechas.add(registro);
		}

		for (RegistroPracticas registroRegistrado : registros) {
			LocalDate fechaRegistrada = registroRegistrado.getFecha().getFecha();
			for (RegistroPracticas fechaSinRegistrar : todasLasFechas) {
				if (fechaSinRegistrar.getFecha().getFecha().equals(fechaRegistrada)) {
					fechaSinRegistrar.setDescripcion(registroRegistrado.getDescripcion());
					fechaSinRegistrar.setCantidadHoras(registroRegistrado.getCantidadHoras());
					break;
				}
			}
		}

		datosTabla.setAll(todasLasFechas);
	}

	@FXML
	void getFechasFiltradas(ActionEvent event) {
		String seleccion = checkFechas.getSelectionModel().getSelectedItem();

		if (seleccion == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Información");
			alert.setHeaderText(null);
			alert.setContentText("No puedes no seleccionar ninguna opción en el combobox.");
			alert.showAndWait();
		} else {
			LocalDate fechaDesde = pickerDesde.getValue();
			LocalDate fechaHasta = pickerHasta.getValue();

			List<RegistroPracticas> registrosFiltrados = new ArrayList<>();
			actualizarTabla();
			switch (seleccion) {
			case "Sólo fechas completas":
				for (RegistroPracticas registro : datosTabla) {
					if (!registro.getDescripcion().isEmpty() && registro.getCantidadHoras() > 0) {
						registrosFiltrados.add(registro);
					}
				}
				break;
			case "Sólo fechas sin completar":
				for (RegistroPracticas registro : datosTabla) {
					if (registro.getDescripcion().isEmpty() && registro.getCantidadHoras() == 0) {
						registrosFiltrados.add(registro);
					}
				}
				break;
			case "Todas":
				registrosFiltrados.addAll(datosTabla);
				break;
			default:
				break;
			}

			if (fechaDesde != null || fechaHasta != null) {
				registrosFiltrados = filtrarPorRango(registrosFiltrados, fechaDesde, fechaHasta);
			}

			datosTabla.setAll(registrosFiltrados);
		}
	}

	private List<RegistroPracticas> filtrarPorRango(List<RegistroPracticas> registros, LocalDate fechaDesde,
			LocalDate fechaHasta) {
		List<RegistroPracticas> registrosFiltrados = new ArrayList<>();

		for (RegistroPracticas registro : registros) {
			LocalDate fechaRegistro = registro.getFecha().getFecha();
			//No debe ser ni null ni deben tener el orden cambiado
			boolean cumpleDesde = (fechaDesde == null) || !fechaRegistro.isBefore(fechaDesde);
			boolean cumpleHasta = (fechaHasta == null) || !fechaRegistro.isAfter(fechaHasta);

			if (cumpleDesde && cumpleHasta) {
				registrosFiltrados.add(registro);
			}
		}

		return registrosFiltrados;
	}
}