package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI_ProductEditForm {
    TextField searchField, nameField, idField, colorField, priceField, sizeField;
    GridPane grid = new GridPane(); // Class-level grid field

    public GUI_ProductEditForm() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the labels and input fields
        Label searchLabel = new Label("Product ID you want to edit:");
        searchField = new TextField();
        grid.add(searchLabel, 0, 0);
        grid.add(searchField, 1, 0);

        Label nameLabel = new Label("New Name:");
        nameField = new TextField();
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);

        Label colorLabel = new Label("New Color:");
        colorField = new TextField();
        grid.add(colorLabel, 0, 2);
        grid.add(colorField, 1, 2);

        Label priceLabel = new Label("New Price:");
        priceField = new TextField();
        grid.add(priceLabel, 0, 3);
        grid.add(priceField, 1, 3);

        Label sizeLabel = new Label("New Size:");
        sizeField = new TextField();
        grid.add(sizeLabel, 0, 4);
        grid.add(sizeField, 1, 4);

        // Add the save button
        Button saveButton = new Button("Save Data");
        grid.add(saveButton, 1, 5);

        // Set the action for the "Save Data" button
        saveButton.setOnAction(e -> {
            String productID = searchField.getText(); // Use the product ID to find the product
            Product product = findProductByID(productID);

            if (product != null) {
                // Set the updated data to the Product object
                product.setProductName(nameField.getText());
                product.setColor(colorField.getText());
                product.setPrice(Double.parseDouble(priceField.getText()));
                product.setSize(sizeField.getText());
                Admin.createCatalogFile();
                System.out.println("Product data updated successfully for product: " + product.getProductID());
                showAlert("Success", "Product data updated successfully!");
            } else {
                showAlert("Error", "Product not found!");
            }

            // Clear the fields after saving
            searchField.clear();
            nameField.clear();
            colorField.clear();
            priceField.clear();
            sizeField.clear();
        });
    }

    // Helper method to find the product by ID
    private Product findProductByID(String productID) {
        for (Product product : Admin.catalog) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;  // Return null if not found
    }

    // Helper method to display an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Return the GridPane to use it in the TabPane
    public GridPane getRoot() {
        return grid; // Ensure this returns the correct, populated GridPane
    }
}
