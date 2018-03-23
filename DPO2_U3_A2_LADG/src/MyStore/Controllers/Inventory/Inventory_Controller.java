package MyStore.Controllers.Inventory;

import MyStore.App;
import MyStore.Models.Inventory_Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Inventory_Controller extends App implements Initializable {

    // Buttons
    @FXML
    public Button quitBtn;
    @FXML
    public Button updateBtn;
    @FXML
    public Button clearBtn;
    @FXML
    public Button insertBtn;
    @FXML
    public Button deleteBtn;

    // Edit window Buttons
    @FXML
    public Button cancelEditBtn;
    @FXML
    public Button confirmEditBtn;

    // Texts Fields
    @FXML
    public TextField idinventoryTextfield;
    @FXML
    public TextField officeTextfield;
    @FXML
    public TextField productTextfield;
    @FXML
    public TextField quantityTextfield;
    @FXML
    public TextField brandTextfield;
    @FXML
    public TextField providerTextfield;

    // Sources views
    String indexView = "/MyStore/Views/Inventory/Index.fxml";
    String editView = "/MyStore/Views/Inventory/Edit.fxml";

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

    // Alert
    Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    // CRUD of Inventory
    @FXML
    public void indexData() {
        System.out.println("Actualizando tabla de inventarios.");

        // Set the connection with the database
        this.connect();

        // Clear the data of the dataInventoryModel list
        dataInventoryModel.clear();

        try {
            this.rs = this.stmt.executeQuery("SELECT * FROM inventarios");

            while (this.rs.next()) {
                dataInventoryModel.add(
                        new Inventory_Model(
                                this.rs.getString("idinventario"),
                                this.rs.getString("sucursal"),
                                this.rs.getString("producto"),
                                this.rs.getInt("cantidad"),
                                this.rs.getString("marca"),
                                this.rs.getString("proveedor")
                        )
                );

                System.out.println(
                        this.rs.getString("idinventario") + " - " +
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

    @FXML
    public void newData() {
        this.connect();

        if (!idinventoryTextfield.getText().isEmpty() &&
                !officeTextfield.getText().isEmpty() &&
                !productTextfield.getText().isEmpty() &&
                !quantityTextfield.getText().isEmpty() &&
                !brandTextfield.getText().isEmpty() &&
                !providerTextfield.getText().isEmpty()) {

            String query = "INSERT INTO inventarios(" +
                    "idinventario, " +
                    "sucursal, " +
                    "producto, " +
                    "cantidad, " +
                    "marca, " +
                    "proveedor) " +
                    "VALUES ( '" +
                    idinventoryTextfield.getText() + "','" +
                    officeTextfield.getText() + "','" +
                    productTextfield.getText() + "','" +
                    quantityTextfield.getText() + "','" +
                    brandTextfield.getText() + "','" +
                    providerTextfield.getText() + "'" +
                    ")";

            System.out.println("Ejecutando query...");
            System.out.println(query);
            this.connection.execQuery(query);
            System.out.println("Se ingresaron correctamente los datos.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Se ingresaron correctamente los datos.");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se han ingresado los datos necesarios.");
            alert.show();
        }

        this.connection.closeConnection();
    }

    @FXML
    public void updateData() {
        String query =
                "UPDATE inventarios SET " +
                        "idinventario = '" + idinventoryTextfield.getText() + "', " +
                        "sucursal = '" + officeTextfield.getText() + "', " +
                        "producto = '" + providerTextfield.getText() + "', " +
                        "cantidad = " + quantityTextfield.getText() + ", " +
                        "marca = '" + brandTextfield.getText() + "', " +
                        "proveedor = '" + providerTextfield.getText() + "' " +
                        " WHERE idinventario = '" + idinventoryTextfield.getText() + "'";

        System.out.println("Ejecutando query: " + query);

        if (!idinventoryTextfield.getText().isEmpty() &&
            !officeTextfield.getText().isEmpty() &&
            !productTextfield.getText().isEmpty() &&
            !quantityTextfield.getText().isEmpty() &&
            !brandTextfield.getText().isEmpty() &&
            !providerTextfield.getText().isEmpty()) {

            this.connect();

            try {
                this.stmt.executeUpdate(query);

                alert = new Alert(Alert.AlertType.INFORMATION, "Se ha actualizado correctamente el registro.");
                alert.show();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            this.connection.closeConnection();
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Faltan campos por llenar");
            alert.show();
        }
    }

    @FXML
    public void updateWindow() {
        Inventory_Model inventoryModelSelect = inventoryTable.getSelectionModel().getSelectedItem();

        if (inventoryModelSelect != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.editView));

            try {
                Stage w = new Stage();
                w.setScene(new Scene(fxmlLoader.load()));
                w.initModality(Modality.WINDOW_MODAL);
                w.initOwner(updateBtn.getScene().getWindow());
                w.setTitle("Edición de inventario");
                w.show();

                Edit_Controller edit_controller = fxmlLoader.getController();

                edit_controller.idinventoryTextfield.setText(inventoryModelSelect.getId().toString());
                edit_controller.officeTextfield.setText(inventoryModelSelect.getOffice());
                edit_controller.productTextfield.setText(inventoryModelSelect.getProduct());
                edit_controller.quantityTextfield.setText(Integer.toString(inventoryModelSelect.getQuantity()));
                edit_controller.brandTextfield.setText(inventoryModelSelect.getBrand());
                edit_controller.providerTextfield.setText(inventoryModelSelect.getProvider());


            } catch (IOException e) {
                System.out.println("No se ha podido cargar el archvivo de Editar.fxml");
            }
        } else {
            alert = new Alert(Alert.AlertType.WARNING, "No se ha seleccionado ningún elemento de la tabla");
            alert.show();
        }
    }

    @FXML
    public void deleteData() {

        Inventory_Model selectedItem = inventoryTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            this.connect();

            String query = "DELETE FROM inventarios WHERE idinventario = '" + selectedItem.getId() + "';";

            try {
                stmt.executeUpdate(query);
                inventoryTable.getItems().remove(selectedItem);

                alert = new Alert(Alert.AlertType.INFORMATION, "Se ha eliminado correctamente el registro.");
                alert.show();

                indexData();

            } catch (SQLException e) {

                System.out.println("Ha ocurrido un error al tratar de eliminar el registro.");
                System.out.println(query);

            }

            this.connection.closeConnection();

        } else {
            alert = new Alert(Alert.AlertType.WARNING, "No se ha seleccionado ningún elemento.");
            alert.show();
        }
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

    @FXML
    public void clearData() {
        System.out.println("Limpiando los datos del formulario");
        idinventoryTextfield.clear();
        officeTextfield.clear();
        productTextfield.clear();
        quantityTextfield.clear();
        brandTextfield.clear();
        productTextfield.clear();
        providerTextfield.clear();
    }

    @FXML
    public void quitNewInventory() {
        Stage w = (Stage) quitBtn.getScene().getWindow();
        w.close();
    }

    @FXML
    public void quitEditWindow() {
        Stage w = (Stage) cancelEditBtn.getScene().getWindow();
        w.close();
    }
}
