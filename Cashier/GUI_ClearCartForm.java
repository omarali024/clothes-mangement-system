package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI_ClearCartForm {
    GridPane grid = new GridPane(); // Class-level grid field

    public GUI_ClearCartForm() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Button to clear the cart
        Button clearButton = new Button("Clear Cart");
        grid.add(clearButton, 0, 0, 2, 1); // Span across two columns

        // Set the action for the Clear Cart button
        clearButton.setOnAction(e -> {
            clear();
            showAlert("Cart Cleared", "Your cart has been cleared.");
        });

        
    }

    // Method to clear the cart
    public void clear() {
        Cashier.getCart().clear();
        System.out.println("Cart cleared.");
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

