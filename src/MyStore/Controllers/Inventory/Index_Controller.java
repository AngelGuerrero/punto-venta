package MyStore.Controllers.Inventory;

import MyStore.Models.Inventory_Model;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Index_Controller extends Inventory_Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataInventoryModel = FXCollections.observableArrayList();

        idInventoryColumn.setCellValueFactory(new PropertyValueFactory<Inventory_Model, Integer>("id"));

        officeColumn.setCellValueFactory(new PropertyValueFactory<Inventory_Model, String>("office"));
        productColumn.setCellValueFactory(new PropertyValueFactory<Inventory_Model, String>("product"));

        quantityColumn.setCellValueFactory(new PropertyValueFactory<Inventory_Model, Integer>("quantity"));

        brandColumn.setCellValueFactory(new PropertyValueFactory<Inventory_Model, String>("brand"));
        providerColumn.setCellValueFactory(new PropertyValueFactory<Inventory_Model, String>("provider"));

        inventoryTable.setItems(dataInventoryModel);
        indexData();
    }
}
