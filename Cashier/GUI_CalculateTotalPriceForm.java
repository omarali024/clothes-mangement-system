package our_project;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI_CalculateTotalPriceForm {
    Label totalPriceLabel; // Label to display the total price
    GridPane grid = new GridPane(); // Class-level grid field

    public GUI_CalculateTotalPriceForm() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the label for displaying the total price
        totalPriceLabel = new Label("Total Price: $0.00");
        grid.add(totalPriceLabel, 0, 0, 2, 1);  // Span across two columns

        // Button to calculate the total price
        Button calculateButton = new Button("Calculate Total Price");
        grid.add(calculateButton, 0, 1, 2, 1); // Span across two columns

        // Set the action for the Calculate Total Price button
        calculateButton.setOnAction(e -> {
            double totalPrice = calculateTotalPrice();
            totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));
        });

       
    }

    // Method to calculate the total price of the cart
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Order order : Cashier.getCart()) {
            totalPrice += order.getPrice() * order.getQuantity();
        }
        System.out.println("Final price: " + totalPrice);
        return totalPrice;
    }

    // Return the root GridPane
    public GridPane getRoot() {
        return grid;  // Ensure this returns the correct, populated GridPane
    }
}

