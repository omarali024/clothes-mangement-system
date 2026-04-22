package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI_PaymentForm {
    TextField paymentField; // Field to enter payment amount
    GridPane grid = new GridPane(); // Class-level grid field

    public GUI_PaymentForm() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the labels and input fields
        Label paymentLabel = new Label("Enter Payment Amount:");
        paymentField = new TextField();
        grid.add(paymentLabel, 0, 0);
        grid.add(paymentField, 1, 0);

        // Button to process the payment
        Button paymentButton = new Button("Make Payment");
        grid.add(paymentButton, 0, 1, 2, 1); // Span across two columns

        // Set the action for the Make Payment button
        paymentButton.setOnAction(e -> {
            double payment = Double.parseDouble(paymentField.getText());
            payment(payment);
        });

        
    }

    // Method to handle payment
    public void payment(double payment) {
        double totalPrice = new GUI_CalculateTotalPriceForm().calculateTotalPrice(); // Calculate total price of the cart
        if (payment >= totalPrice) {
            double change = payment - totalPrice; 
            System.out.println("Transaction successful");
            System.out.println("Change: " + change);
            showAlert("Transaction Successful", "Your payment was successful "+"\nChange: "+change);
        } else {
            System.out.println("Transaction failed");
            showAlert("Transaction Failed", "Insufficient payment.");
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

    // Return the root GridPane
    public GridPane getRoot() {
        return grid;  // Ensure this returns the correct, populated GridPane
    }
}

