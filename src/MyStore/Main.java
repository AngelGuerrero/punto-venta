package MyStore;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
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
    MenuItem closeAppBtn;

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

    public void launchFormsSales() {

       FXMLLoader root = new FXMLLoader(getClass().getResource("/MyStore/Views/Sales/New.fxml"));

       Stage w = new Stage();

        try {
            w.setScene(new Scene(root.load(), 400,400));
            w.setTitle("Formulario para un nuevo artículo");
            w.setResizable(false);
            w.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeApp() {
        Platform.exit();
        System.exit(0);
    }
}
