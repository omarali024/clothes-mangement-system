package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import static our_project.Cashier.cart;

public class GUI_ShowCartForm {
    GridPane grid = new GridPane(); // Class-level grid field
    TextArea cartArea; // To display the shopping cart contents
     
    // Constructor accepting cart and main menu scene
    public GUI_ShowCartForm() {
       
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the "View Cart" label
        Label viewLabel = new Label("Shopping Cart:");
        grid.add(viewLabel, 0, 0);

        // Create the result area to display the cart contents
        cartArea = new TextArea();
        cartArea.setEditable(false);  // Make the area read-only
        cartArea.setPrefHeight(150);  // Set the height for better visibility
        grid.add(cartArea, 0, 1, 2, 1);  // Span across two columns

        // Set the action to show cart contents
        Button showCartButton = new Button("Show Cart");
        grid.add(showCartButton, 0, 2, 2, 1);  // Span across two columns
        showCartButton.setOnAction(e -> {
            showCartContents();
        });

        
    }

    // Method to display the shopping cart contents in the TextArea
    private void showCartContents() {
        StringBuilder cartText = new StringBuilder();
        
        // Check if the cart is empty
        if (cart == null || cart.isEmpty()) {
            cartText.append("Your shopping cart is empty.");
        } else {
            // Iterate through the cart and display each order's details
            for (Order order : cart) {
                cartText.append("Product Name: ").append(order.getProductName()).append("\n")
                        .append("Product ID: ").append(order.getProductID()).append("\n")
                        .append("Color: ").append(order.getColor()).append("\n")
                        .append("Size: ").append(order.getSize()).append("\n")
                        .append("Quantity: ").append(order.getQuantity()).append("\n")
                        .append("Price: $").append(order.getPrice()).append("\n")
                        .append("Total Price: $").append(order.getPrice() * order.getQuantity()).append("\n\n");
            }
        }
        
        // Set the cart contents in the TextArea
        cartArea.setText(cartText.toString());
    }

    // Return the GridPane to use it in the TabPane
    public GridPane getRoot() {
        return grid; // Ensure this returns the correct, populated GridPane
    }
}

