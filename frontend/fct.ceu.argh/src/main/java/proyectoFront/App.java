package proyectoFront;

import javafx.application.Application;
import javafx.stage.Stage;
import proyectoFront.gui.AppController;

public class App extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		AppController controller = new AppController(primaryStage);
		
		controller.changeScene(AppController.FXML_INICIO);
		
		primaryStage.setResizable(true);
		primaryStage.initStyle(javafx.stage.StageStyle.TRANSPARENT);
		primaryStage.setWidth(700);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch();
	}

}
