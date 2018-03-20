package MyStore.Controllers.Sales;

import MyStore.Models.Sale_Model;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Index_Controller extends Sales_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataSaleModels = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Sale_Model, Integer>("id"));
        articleColumn.setCellValueFactory(new PropertyValueFactory<Sale_Model, String>("article"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Sale_Model, Double>("price"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<Sale_Model, String>("brand"));

        table.setItems(dataSaleModels);
        this.indexData();
    }


}
