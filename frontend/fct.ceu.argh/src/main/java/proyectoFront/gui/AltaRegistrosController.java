package proyectoFront.gui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.FctApiServiceApi;
import org.openapitools.client.model.Fecha;
import org.openapitools.client.model.RegistroPracticas;
import org.openapitools.client.model.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class AltaRegistrosController extends AppController {

    @FXML
    private DatePicker dateSeleccionarFecha;

    @FXML
    private Spinner<Double> elegirHoras;

    @FXML
    private TextArea textoLargo;

    @FXML
    public void initialize() {
        SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(
                0.5, 8.0, 0.5, 0.5);
        elegirHoras.setValueFactory(valueFactory);

        LocalDate fechaInicio = LocalDate.of(2025, 3, 3);
        LocalDate fechaFin = LocalDate.of(2025, 5, 30);

        dateSeleccionarFecha.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(fechaInicio) || date.isAfter(fechaFin)) {
                    setDisable(true); 
                    setStyle("-fx-background-color: #ffc0cb;");
                }

                if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ff0000;");
                }
            }
        });

        elegirHoras.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
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
        LocalDate fechaSeleccionada = dateSeleccionarFecha.getValue();
        LocalDate fechaInicio = LocalDate.of(2025, 3, 3);
        LocalDate fechaFin = LocalDate.of(2025, 5, 30);

        if (fechaSeleccionada == null || fechaSeleccionada.isBefore(fechaInicio)
                || fechaSeleccionada.isAfter(fechaFin)
                || fechaSeleccionada.getDayOfWeek() == DayOfWeek.SATURDAY
                || fechaSeleccionada.getDayOfWeek() == DayOfWeek.SUNDAY) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una fecha válida entre el 2025-03-03 y el 2025-05-30, excluyendo sábados y domingos.");
            alert.setTitle("Cuidao");
            alert.showAndWait();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
            Integer anyo = Integer.parseInt(fechaSeleccionada.format(formatter));
            FctApiServiceApi api = (FctApiServiceApi) getParam("apiServicio");
            Usuario user = (Usuario) getParam("usuario");
            RegistroPracticas registro = new RegistroPracticas();
            registro.setCantidadHoras(elegirHoras.getValue());
            registro.setDescripcion(textoLargo.getText());

            Fecha fecha = new Fecha();
            fecha.setAño(anyo);
            fecha.setFecha(fechaSeleccionada);
            registro.setFecha(fecha);


            try {
                api.addRegistroPracticas(user.getId(), registro);
            } catch (ApiException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Ese día ya está registrado en la BBDD");
				alert.setTitle("Ten paciencia...");
				alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
}