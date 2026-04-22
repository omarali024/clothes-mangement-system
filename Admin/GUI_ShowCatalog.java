package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI_ShowCatalog {
    GridPane grid = new GridPane(); // Class-level grid field
    TextArea catalogListArea; // To display the user list
    public GUI_ShowCatalog() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the "View Catalog" label
        Label viewLabel = new Label("View Product Catalog:");
        grid.add(viewLabel, 0, 0);

        // Create the "Show Catalog" button
        Button showButton = new Button("Show Catalog");
        grid.add(showButton, 1, 1);

         // Create the result area to display the user list
        catalogListArea = new TextArea();
        catalogListArea.setEditable(false);  // Make the area read-only
        catalogListArea.setPrefHeight(150);  // Set the height for better visibility
        grid.add(catalogListArea, 0, 2, 2, 1);  // Span across two columns
        
        // Set the action for the "Show Catalog" button
        showButton.setOnAction(e -> {
            // Display product catalog in the console
            showCatalog();
        });
    }

    // Method to display the product catalog in the console
     private void showCatalog() {
        StringBuilder catalogText = new StringBuilder();
        if (Admin.catalog == null || Admin.catalog.isEmpty()) {
            catalogText.append("No products found.");
        } else {
            for (Product product : Admin.catalog) {
                catalogText.append(product.toString()).append("\n");
            }
        }
        catalogListArea.setText(catalogText.toString());
     }


    // Return the GridPane to use it in the TabPane
    public GridPane getRoot() {
        return grid; // Ensure this returns the correct, populated GridPane
    }
}
