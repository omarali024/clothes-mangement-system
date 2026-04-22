package our_project;

import java.util.ArrayList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI_RemoveFromCartForm {
    TextField searchField; // Field to enter Product ID
    GridPane grid = new GridPane(); // Class-level grid field

    public GUI_RemoveFromCartForm() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the labels and input fields
        Label searchLabel = new Label("Product ID to remove from cart:");
        searchField = new TextField();
        grid.add(searchLabel, 0, 0);
        grid.add(searchField, 1, 0);

        Button removeButton = new Button("Remove from Cart");
        grid.add(removeButton, 1, 1);

        // Set the action for the remove button
        removeButton.setOnAction(e -> {
            String productID = searchField.getText();

            // Call the removeProductFromCart method to handle removal
            boolean productRemoved = removeProductFromCart(productID);

            // Show appropriate alert based on removal success
            if (productRemoved) {
                showAlert("Product Removed", "Product has been removed from your cart.");
            } else {
                showAlert("Error", "Product with ID " + productID + " not found in the cart.");
            }

            // Clear the search field after processing
            searchField.clear();
        });

        

        
    }

    // Utility method to remove the product from the cart
    private boolean removeProductFromCart(String productID) {
        // Access the cart from the Cashier class
        ArrayList<Order> cart = Cashier.getCart();  // Accessing static cart from Cashier class

        // Loop through the cart to find and remove the product
        for (Order order : cart) {
            if (order.getProductID().equals(productID)) {
                cart.remove(order);  // Remove the product from the cart
                System.out.println("Product removed from cart: " + order.getProductName());
                return true;  // Return true indicating the product was removed
            }
        }

        // If product is not found, return false
        return false;
    }

    // Utility method to display alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Return the root GridPane
    public GridPane getRoot() {
        return grid;  // Ensure this returns the correct, populated GridPane
    }
}

