package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;


public class GUI_ProductAdditionForm {
    TextField nameField, idField, colorField, priceField, sizeField;

    GridPane grid = new GridPane(); // Grid for layout

    public GUI_ProductAdditionForm() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(10);

        // Name field
        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        // ID field
        Label idLabel = new Label("ID:");
        idField = new TextField();
        grid.add(idLabel, 0, 1);
        grid.add(idField, 1, 1);

        // Color field
        Label colorLabel = new Label("Color:");
        colorField = new TextField();
        grid.add(colorLabel, 0, 2);
        grid.add(colorField, 1, 2);

        // Price field
        Label priceLabel = new Label("Price:");
        priceField = new TextField();
        grid.add(priceLabel, 0, 3);
        grid.add(priceField, 1, 3);

        // Size field
        Label sizeLabel = new Label("Size:");
        sizeField = new TextField();
        grid.add(sizeLabel, 0, 4);
        grid.add(sizeField, 1, 4);

        // Buttons
        Button addButton = new Button("Add Product");
        Button eraseButton = new Button("Erase All");
        grid.add(addButton, 0, 5);
        grid.add(eraseButton, 1, 5);

        // Add button action
        addButton.setOnAction(e -> {
            try {
                // Validate inputs
                if (nameField.getText().isEmpty() || idField.getText().isEmpty() ||
                    colorField.getText().isEmpty() || priceField.getText().isEmpty() || sizeField.getText().isEmpty()) {
                    showAlert("Error", "All fields must be filled!");
                    return;
                }

                // Parse price
                double price = Double.parseDouble(priceField.getText());

                // Create new Product object
                Product product = new Product(
                    nameField.getText(),
                    idField.getText(),
                    colorField.getText(),
                    price,
                    sizeField.getText()
                );

                // Add to catalog
                Admin.catalog.add(product);
                System.out.println("Product: " + product.getProductName()+" has been added successfully");
                showAlert("Success", "Product added successfully!");
                
                Admin.createCatalogFile();
                // Clear fields
                nameField.clear();
                idField.clear();
                colorField.clear();
                priceField.clear();
                sizeField.clear();

            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid price!");
            }
        });

        // Erase button action
        eraseButton.setOnAction(e -> {
            nameField.clear();
            idField.clear();
            colorField.clear();
            priceField.clear();
            sizeField.clear();
        });
    }

    // Method to return the root grid
    public GridPane getRoot() {
        return grid;
    }

    // Utility method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
