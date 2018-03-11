package MyStore.Controllers;

import MyStore.Libs.MyConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class SalesController implements Initializable {

    @FXML
    private TextField articleTextfield;
    @FXML
    private TextField priceTextfield;
    @FXML
    private TextField brandTextfield;


    private MyConnection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inicializando...");
    }

    @FXML
    public void insertData() {
        if (!articleTextfield.getText().equals("") && !priceTextfield.getText().equals("") && !brandTextfield.getText().equals("")) {

            String query = "INSERT INTO ventas(" +
                    "idventas, " +
                    "articulo, " +
                    "precio, " +
                    "marca) " +
                    "VALUES " + // Values
                    "(null, '" +
                    articleTextfield.getText() + "', '" +
                    priceTextfield.getText() + "', '" +
                    brandTextfield.getText() + "');";

            this.connection = new MyConnection();
            connection.setConnection();

            System.out.println(query);
            connection.execQuery(query);
            connection.closeConnection();
        } else {
            System.out.println("Faltan campos por llenar");
        }
    }

    @FXML
    public void clearData() {
        articleTextfield.clear();
        priceTextfield.clear();
        brandTextfield.clear();
    }

    @FXML
    private void showTable() throws IOException {
        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/MyStore/Views/TableSalesView.fxml"));
        primaryStage.setTitle("Tabla alternativa");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void quit() {
        Stage w = (Stage) articleTextfield.getScene().getWindow();
        w.close();
    }

}
