package controllers;

import controllers.product.ProductController;
import entities.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainViewController {
    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    @FXML
    private Button saveButton;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Long> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    private final ProductController productController = new ProductController();
    private final ObservableList<Product> products = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configure table columns to bind with Product fields
        idColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPrice()));

        // Set observable list to table
        productTable.setItems(products);
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        String name = productNameField.getText();
        String priceText = productPriceField.getText();

        if (name.isEmpty() || priceText.isEmpty()) {
            showAlert("Validation Error", "Name and price must be filled.");
            return;
        }

        try {
            Double price = Double.parseDouble(priceText);
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);

            // Save product and add to table
            Product savedProduct = productController.save(product);
            products.add(savedProduct);

            clearForm();
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Price must be a valid number.");
        } catch (Exception e) {
            showAlert("Error", "An error occurred while saving the product: " + e.getMessage());
        }
    }

    private void clearForm() {
        productNameField.clear();
        productPriceField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
