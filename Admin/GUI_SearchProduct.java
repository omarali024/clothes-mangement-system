package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI_SearchProduct {
    GridPane grid = new GridPane(); // Class-level grid field
    ComboBox<String> searchCriterionComboBox;
    TextField searchValueField;
    TextArea ProductListArea; // To display the user list
    public GUI_SearchProduct() {
        // Initialize and configure the class-level grid
        
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the "Search Criterion" label and ComboBox
        Label searchCriterionLabel = new Label("Select search criterion:");
        searchCriterionComboBox = new ComboBox<>();
        searchCriterionComboBox.getItems().addAll(
            "productID", "productName", "color", "size"
        );
        searchCriterionComboBox.getSelectionModel().selectFirst(); // Default to the first criterion
        grid.add(searchCriterionLabel, 0, 0);
        grid.add(searchCriterionComboBox, 1, 0);

        // Add the "Search Value" label and field
        Label searchValueLabel = new Label("Enter search value:");
        searchValueField = new TextField();
        grid.add(searchValueLabel, 0, 1);
        grid.add(searchValueField, 1, 1);
        
         // Create the result area to display the user list
        ProductListArea = new TextArea();
        ProductListArea.setEditable(false);  // Make the area read-only
        ProductListArea.setPrefHeight(150);  // Set the height for better visibility
        grid.add(ProductListArea, 0, 2, 2, 1);  // Span across two columns

        // Create the "Search" button
        Button searchButton = new Button("Search Product");
        grid.add(searchButton, 3, 1);

        // Set the action for the "Search" button
        searchButton.setOnAction(e -> {
            // Get the selected search criterion and the entered search value
            String criterion = searchCriterionComboBox.getValue();
            String value = searchValueField.getText().trim();

            // Perform the search
            searchProduct(criterion, value);
        });
    }

    // Method to search for the product based on the given criterion and value
    private void searchProduct(String criterion, String value) {
        boolean found = false;

        System.out.println("Searching for products by " + criterion + ": " + value);

        // Loop through the catalog and search based on the criterion
        for (Product product : Admin.catalog) {
            boolean matchFound = false;

            switch (criterion.toLowerCase()) {
                case "productid":
                    if (String.valueOf(product.getProductID()).equals(value)) {
                        matchFound = true;
                    }
                    break;
                case "productname":
                    if (product.getProductName().equalsIgnoreCase(value)) {
                        matchFound = true;
                    }
                    break;
                case "color":
                    if (product.getColor().equalsIgnoreCase(value)) {
                        matchFound = true;
                    }
                    break;
                case "size":
                    if (product.getSize().equalsIgnoreCase(value)) {
                        matchFound = true;
                    }
                    break;
                
                default:
                    System.out.println("Invalid search criterion.");
                    return;
            }

            if (matchFound) {
                System.out.println("Found Product: " + product.toString());
                found = true;
                ProductListArea.setText(product.toString());
            }
        }

        if (!found) {
            System.out.println("No products found matching the search criteria.");
        }
    }

    // Return the GridPane to use it in the TabPane
    public GridPane getRoot() {
        return grid; // Ensure this returns the correct, populated GridPane
    }
}
