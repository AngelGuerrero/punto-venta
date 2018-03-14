package MyStore.Controllers.Sales;

import MyStore.Models.Sale;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Index_Controller extends Sales_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataSales = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Sale, Double>("id"));
        articleColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("article"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Sale, Double>("price"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("brand"));

        table.setItems(dataSales);
        this.indexData();
    }


}
