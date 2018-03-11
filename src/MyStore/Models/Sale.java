package MyStore.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Sale {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty article = new SimpleStringProperty("");
    private final SimpleDoubleProperty price = new SimpleDoubleProperty(0.0);
    private final SimpleStringProperty brand = new SimpleStringProperty("");

    public Sale(int id, String article, Double price, String brand) {
        this.id.set(id);
        this.article.set(article);
        this.price.set(price);
        this.brand.set(brand);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getArticle() {
        return article.get();
    }

    public SimpleStringProperty articleProperty() {
        return article;
    }

    public void setArticle(String article) {
        this.article.set(article);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
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
}
