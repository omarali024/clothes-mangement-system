package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI_ProductDeleteForm {
    TextField searchField;
    GridPane grid = new GridPane(); // Class-level grid field

    public GUI_ProductDeleteForm() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the labels and input fields
        Label searchLabel = new Label("Product ID you want to delete:");
        searchField = new TextField();
        grid.add(searchLabel, 0, 0);
        grid.add(searchField, 1, 0);

        Button deleteButton = new Button("Delete Product");
        grid.add(deleteButton, 1, 1);

        // Set the action for the delete button
        deleteButton.setOnAction(e -> {
            String productID = searchField.getText();

            // Find and delete the product
            Product product = findProductByID(productID);
            if (product != null) {
                Admin.catalog.remove(product); // Remove the product from the catalog
                System.out.println("Product: " + product.getProductID() + " has been deleted successfully");
                showAlert("Success", "Product deleted successfully!");
                Admin.createCatalogFile();
            } else {
                showAlert("Error", "Product not found!");
            }

            // Clear the search field after deleting
            searchField.clear();
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
