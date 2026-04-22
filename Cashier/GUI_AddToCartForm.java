package our_project;

import java.util.ArrayList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.UUID;

public class GUI_AddToCartForm {
    TextField productIDField; // To input the product ID
    
    
    private GridPane grid = new GridPane(); // Layout grid

    // Constructor accepting primaryStage and mainMenuScene
    public GUI_AddToCartForm() {

        // Configure the grid layout
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));

        // Product ID label and input field
        Label productIDLabel = new Label("Product ID:");
        productIDField = new TextField();
        grid.add(productIDLabel, 0, 0);
        grid.add(productIDField, 1, 0);

        // Buttons for adding to cart and returning to the main menu
        Button addButton = new Button("Add to Cart");
        
        grid.add(addButton, 0, 1);
        

        // Add to Cart button action
        addButton.setOnAction(e -> {
            String productID = productIDField.getText();
            addProductToCart(productID); // Add the product to the cart

            // Clear the input field after processing
            productIDField.clear();
        });

       
        
    }

    // Method to return the root GridPane
    public GridPane getRoot() {
        return grid;
    }

    // Utility method to add the product to the cart
    private void addProductToCart(String productID) {
        boolean productAdded = false;

        // Access the cart and catalog from Cashier and Admin classes
        ArrayList<Order> cart = Cashier.getCart();  // Accessing static cart from Cashier class
        ArrayList<Product> catalog = Admin.catalog;  // Accessing static catalog from Admin class

        // Check if the product is already in the cart
        for (Order order : cart) {
            if (order.getProductID().equals(productID)) {
                // Increment quantity if found
                order.setQuantity(order.getQuantity() + 1);
                System.out.println("Product quantity updated in cart: " + order.getProductName() + 
                                   " (Quantity: " + order.getQuantity() + ")");
                showAlert("Product Added", "Product quantity updated in cart: " + order.getProductName());
                productAdded = true;
                break;
            }
        }

        // If not found in the cart, check in the catalog and add
        if (!productAdded) {
            for (Product product : catalog) {
                if (product.getProductID().equals(productID)) {
                    String orderID = UUID.randomUUID().toString(); // Generate a unique order ID
                    Order newOrder = new Order(product.getProductName(), product.getProductID(), product.getColor(),
                            product.getPrice(), product.getSize(), orderID, 1);
                    cart.add(newOrder);
                    System.out.println("Product added to cart: " + product.getProductName());
                    showAlert("Product Added", "Product " + product.getProductName() + " has been added to your cart.");
                    productAdded = true;
                    break;
                }
            }
        }

        // If the product was not found in the catalog
        if (!productAdded) {
            showAlert("Error", "Product with ID " + productID + " not found.");
        }
    }

    // Utility method to display alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
