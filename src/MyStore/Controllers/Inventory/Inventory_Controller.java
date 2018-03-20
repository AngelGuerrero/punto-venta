package MyStore.Controllers.Inventory;

import MyStore.Main;
import MyStore.Models.Inventory_Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Inventory_Controller extends Main implements Initializable {

    // Buttons
    @FXML
    public Button quitBtn;

    // Sources views
    String indexView = "/MyStore/Views/Inventory/Index.fxml";

    // Table
    @FXML
    public TableView<Inventory_Model> inventoryTable;
    @FXML
    public TableColumn idInventoryColumn;
    @FXML
    public TableColumn officeColumn;
    @FXML
    public TableColumn productColumn;
    @FXML
    public TableColumn quantityColumn;
    @FXML
    public TableColumn brandColumn;
    @FXML
    public TableColumn providerColumn;

    @FXML
    public ObservableList<Inventory_Model> dataInventoryModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    // CRUD of Inventory
    @FXML
    public void indexData() {
        // Set the connection with the database
        this.connect();

        // Clear the data of the dataInventoryModel list
        dataInventoryModel.clear();

        try {
            this.rs = this.stmt.executeQuery("SELECT * FROM inventarios");

            while (this.rs.next()) {
                dataInventoryModel.add(
                        new Inventory_Model(
                                this.rs.getInt("idinventario"),
                                this.rs.getString("sucursal"),
                                this.rs.getString("producto"),
                                this.rs.getInt("cantidad"),
                                this.rs.getString("marca"),
                                this.rs.getString("proveedor")
                        )
                );


                System.out.println(
                        this.rs.getInt("idinventario") + " - " +
                        this.rs.getString("sucursal") + " - " +
                        this.rs.getString("producto") + " - " +
                        this.rs.getInt("cantidad") + " - " +
                        this.rs.getString("marca") + " - " +
                        this.rs.getString("proveedor")
                );
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error al tratar de obtener los datos de los inventarios");
        }
        this.connection.closeConnection();
    }

    // Creates the new windows
    @FXML
    public void indexWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.indexView));

        try {
            Stage w = new Stage();
            w.setScene(new Scene(fxmlLoader.load()));
            w.setTitle("Registros de inventarios");
            w.initModality(Modality.WINDOW_MODAL);
            w.initOwner(this.quitBtn.getScene().getWindow());
            w.show();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("No se ha podido cargar el archivo: " + this.indexView);
            e.printStackTrace();
        }
    }
}
