package MyStore.Controllers.Sales;

import MyStore.Libs.MyConnection;
import MyStore.Models.Sale;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Sales_Controller implements Initializable {

    // Inputs
    @FXML
    public TextField articleTextfield;
    @FXML
    public TextField priceTextfield;
    @FXML
    public TextField brandTextfield;

    // Labels
    @FXML
    public Label idventasLabel;

    // Buttons
    @FXML
    private Button saveNewBtn;
    @FXML
    private Button updateSelectBtn;

    // Alert
    Alert alert;

    // Sources Views
    String indexView = "/MyStore/Views/Sales/Index.fxml";
    String newView = "/MyStore/Views/Sales/New.fxml";
    String editView = "/MyStore/Views/Sales/Edit.fxml";

    // Shared data
    public int idventas;

    // Table
    @FXML
    public TableView<Sale> table;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn articleColumn;
    @FXML
    public TableColumn priceColumn;
    @FXML
    public TableColumn brandColumn;

    @FXML
    public ObservableList<Sale> dataSales;


    // Connection variables
    public MyConnection connection;
    public Statement stmt;
    public ResultSet rs;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    protected void connect() {

        this.connection = new MyConnection();
        try {

            this.connection.setConnection();
            this.stmt = this.connection.getConnection().createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // CRUD of Sales
    @FXML
    public void indexData() {

        // Set the connection with the database
        this.connect();

        // Clear the data of the dataSales list
        dataSales.clear();

        try {

            this.rs = this.stmt.executeQuery("SELECT * FROM ventas");

            while (this.rs.next()) {
                dataSales.add(
                        new Sale(
                                this.rs.getInt("idventas"),
                                this.rs.getString("articulo"),
                                this.rs.getDouble("precio"),
                                this.rs.getString("marca")
                        )
                );

                System.out.println(
                        this.rs.getInt("idventas") + " - " +
                                this.rs.getString("articulo") + " - " +
                                this.rs.getDouble("precio") + " - " +
                                this.rs.getString("marca"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection.closeConnection();
    }

    @FXML
    public void newData() {
        if (!articleTextfield.getText().equals("") && !priceTextfield.getText().equals("") && !brandTextfield.getText().equals("")) {

            String query =
                    "INSERT INTO ventas(" +
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
            this.connection.setConnection();

            System.out.println(query);

            this.connection.execQuery(query);
            this.connection.closeConnection();

            alert = new Alert(Alert.AlertType.INFORMATION, "Registro agregado correctamente.");
            alert.setTitle("Agregando un nuevo artículo.");
            alert.show();

        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Faltan campor por llenar");
            alert.setTitle("Error");
            alert.show();
        }
    }

    @FXML
    private void updateWindow() {
        Sale saleSelect = table.getSelectionModel().getSelectedItem();

        if (saleSelect != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.editView));

            try {
                Stage w = new Stage();
                w.setScene(new Scene(fxmlLoader.load()));
                w.initModality(Modality.WINDOW_MODAL);
                w.initOwner(updateSelectBtn.getScene().getWindow());
                w.setTitle("Editando el artículo");
                w.show();

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("No se ha podido cargar el archivo: " + this.editView);
            }

            Edit_Controller edit_controller = fxmlLoader.getController();

            edit_controller.idventas = saleSelect.getId();

            edit_controller.idventasLabel.setText(Integer.toString(saleSelect.getId()));
            edit_controller.articleTextfield.setText(saleSelect.getArticle());
            edit_controller.priceTextfield.setText(Double.toString(saleSelect.getPrice()));
            edit_controller.brandTextfield.setText(saleSelect.getBrand());
        } else {
            alert = new Alert(Alert.AlertType.WARNING, "No se ha seleccionado ningún dato.");
            alert.show();
        }
    }

    @FXML
    public void updateData() {
        String query = "";
        if (!articleTextfield.getText().equals("") && !priceTextfield.getText().equals("") && !brandTextfield.getText().equals("")) {

            this.connect();

            try {

                query =
                        "UPDATE ventas SET" +
                                " articulo = '" + articleTextfield.getText() + "'" +
                                ", precio = '" + priceTextfield.getText() + "'" +
                                ", marca = '" + brandTextfield.getText() + "'" +
                                " WHERE idventas = '" + idventas + "'";

                System.out.println(query);
                this.stmt.executeUpdate(query);

                alert = new Alert(Alert.AlertType.INFORMATION, "Se ha actualizado el registro correctamente");
                alert.show();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.connection.closeConnection();

        } else {
            System.out.println("Faltan campos por llenar");
        }
    }

    @FXML
    public void deleteData() {

        Sale selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            this.connect();

            String query = "DELETE FROM ventas WHERE idventas = " + selectedItem.getId() + ";";

            try {

                stmt.executeUpdate(query);
                table.getItems().remove(selectedItem);

                alert = new Alert(Alert.AlertType.INFORMATION, "Se ha eliminado correctamente el registro");
                alert.setHeaderText("Eliminación de registro");
                alert.show();

                this.indexData();

            } catch (SQLException e) {

                e.printStackTrace();
                System.out.println("No se ha podido eliminar el registro en la base de datos");
                System.out.println("Query: " + query);

            }

            this.connection.closeConnection();
        } else {
            alert = new Alert(Alert.AlertType.WARNING, "No se ha seleccionado nungún dato");
            alert.show();
        }

    }

    @FXML
    public void clearData() {
        articleTextfield.clear();
        priceTextfield.clear();
        brandTextfield.clear();
    }

    @FXML
    private void indexWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.indexView));

        try {
            Stage w = new Stage();
            w.setScene(new Scene(fxmlLoader.load()));
            w.setTitle("Registros de la base de datos");
            w.initModality(Modality.WINDOW_MODAL);
            w.initOwner(saveNewBtn.getScene().getWindow());
            w.show();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("No se ha podido cargar el archivo: " + this.indexView);
        }
    }

    @FXML
    private void quitNewWindow() {
        Stage w = (Stage) articleTextfield.getScene().getWindow();
        w.close();
    }

    @FXML
    private void quitUpdateWindow() {
        Stage w = (Stage) idventasLabel.getScene().getWindow();
        w.close();
    }
}
