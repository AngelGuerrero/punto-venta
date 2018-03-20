package MyStore.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Inventory_Model {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty office = new SimpleStringProperty();
    private final SimpleStringProperty product = new SimpleStringProperty();
    private final SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private final SimpleStringProperty brand = new SimpleStringProperty();
    private final SimpleStringProperty provider = new SimpleStringProperty();

    public Inventory_Model(int id, String office, String product, int quantity, String brand, String provider) {
        this.id.set(id);
        this.office.set(office);
        this.product.set(product);
        this.quantity.set(quantity);
        this.brand.set(brand);
        this.provider.set(provider);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getOffice() {
        return office.get();
    }

    public SimpleStringProperty officeProperty() {
        return office;
    }

    public void setOffice(String office) {
        this.office.set(office);
    }

    public String getProduct() {
        return product.get();
    }

    public SimpleStringProperty productProperty() {
        return product;
    }

    public void setProduct(String product) {
        this.product.set(product);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getProvider() {
        return provider.get();
    }

    public SimpleStringProperty providerProperty() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider.set(provider);
    }
}
