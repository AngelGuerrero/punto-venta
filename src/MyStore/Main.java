package MyStore;

import MyStore.Controllers.SalesController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @FXML
    Button salesBtn;
    @FXML
    Button inventoryBtn;
    @FXML
    Button personalBtn;

    @FXML
    BorderPane mainBorder;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MyStore/Views/MainView.fxml"));
        primaryStage.setTitle("Inicio - Menú de opciones");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    // Lanza la ventana de ventas
    public void launchFormsSales() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyStore/Views/SalesView.fxml"));
        Parent root = loader.load();

        // Obtiene la instancia del controlador ventas
        SalesController SalesController = loader.getController();

        Scene scene = new Scene(root);

        Stage formsWindow = new Stage();

        // Obtiene la instancia de la ventana principal a través de un botón
        Stage mainWindow = (Stage) mainBorder.getScene().getWindow();

        // Inicia la ventana en modo MODAL
        formsWindow.setTitle("Formulario de ventas");
        formsWindow.initModality(Modality.WINDOW_MODAL);
        formsWindow.initOwner(mainWindow);
        formsWindow.setScene(scene);
        formsWindow.setResizable(false);
        formsWindow.showAndWait();
    }
}
