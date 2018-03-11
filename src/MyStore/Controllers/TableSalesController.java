package MyStore.Controllers;

import MyStore.Libs.MyConnection;
import MyStore.Models.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TableSalesController implements Initializable {
    @FXML
    private TableView<Sale> table;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn articleColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn brandColumn;

    @FXML
    ObservableList<Sale> dataSales;

    private MyConnection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataSales = FXCollections.observableArrayList(
                new Sale(10,"Guajolotes", 30.0, "De los buenos"),
                new Sale(11, "Enchiladas", 15.50, "La mejor"));

        idColumn.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("id"));
        articleColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("article"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Sale, Double>("price"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("brand"));

        table.setItems(dataSales);
        getData();
    }

    private void getData() {
        connection = new MyConnection();
        connection.setConnection();

        dataSales.clear();
        try {
            Statement stmt = connection.getConnection().createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM ventas");

            while (rs.next()) {
                dataSales.add(
                        new Sale(
                                rs.getInt("idventas"),
                                rs.getString("articulo"),
                                rs.getDouble("precio"),
                                rs.getString("marca")
                        )
                );

                System.out.println(
                        rs.getInt("idventas") + " - " +
                        rs.getString("articulo") + " - " +
                        rs.getDouble("precio") + " - " +
                        rs.getString("marca"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.closeConnection();
    }
}
