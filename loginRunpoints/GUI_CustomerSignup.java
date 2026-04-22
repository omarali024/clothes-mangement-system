package our_project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI_CustomerSignup extends Application {

    TextField nameField, passwordField, phoneField, emailField;

    GridPane grid = new GridPane(); // Make grid a field to access it in getRoot()

    public GUI_CustomerSignup() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        passwordField = new TextField();
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        Label phoneLabel = new Label("Phone:");
        phoneField = new TextField();
        grid.add(phoneLabel, 0, 2);
        grid.add(phoneField, 1, 2);

        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        grid.add(emailLabel, 0, 3);
        grid.add(emailField, 1, 3);

        Button eraseButton = new Button("Erase All");
        grid.add(eraseButton, 1, 4);

        Button addCustomerButton = new Button("Add Customer");
        grid.add(addCustomerButton, 0, 4);

        addCustomerButton.setOnAction(e -> {
            // Validate input before adding Customer
            if (!isValidInput(nameField.getText(), passwordField.getText(), phoneField.getText(),emailField.getText())) {
                return; // Exit if validation fails
            }

            // Create a new Customer object
            Customer customer = new Customer(
                nameField.getText(),
                passwordField.getText(),
                phoneField.getText(),
                emailField.getText()
            );

            Customer.CustomerList.add(customer);
            showAlert("Success", "Customer added successfully!");

            // Save the user to the file
            Customer.createCustomersFile();
            
            // Clear the fields
            nameField.clear();
            passwordField.clear();
            phoneField.clear();
            emailField.clear();
            openCustomerAppStage((Stage) grid.getScene().getWindow());
        });

        eraseButton.setOnAction(e -> {
            // Clear all the input fields
            nameField.clear();
            passwordField.clear();
            phoneField.clear();
            emailField.clear();
        });
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getRoot(), 400, 300); // Create a scene with the grid as the root
        primaryStage.setTitle("Customer Signup"); // Set the title of the stage
        primaryStage.setScene(scene); // Set the scene on the stage
        primaryStage.show(); // Show the stage
    }

    // Method to return the root grid
    public GridPane getRoot() {
        return grid;
    }

    private void openCustomerAppStage(Stage primaryStage) {
        GUI_CustomerApp CustomerApp = new GUI_CustomerApp();
        try {
            CustomerApp.start(primaryStage); // Load Main App Stage
        } catch (Exception ex) {
            showAlert("Error", "Unable to load the Main App stage.");
        }
    }

   // Method to validate input fields
    private boolean isValidInput(String name, String password, String phone,String email) {
        if (name == null || name.trim().isEmpty()) {
            showAlert("Invalid Input", "Name cannot be empty.");
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            showAlert("Invalid Input", "Password cannot be empty.");
            return false;
        }
        if (name.length() < 3 || name.length() > 15) {
            showAlert("Invalid Input", "Username must be between 3 and 15 characters.");
            return false;
        }
        if (password.length() < 4) {
            showAlert("Invalid Input", "Password must be at least 4 characters long.");
            return false;
        }
        if (phone == null || phone.trim().isEmpty()) {
            showAlert("Invalid Input", "Phone number cannot be empty.");
            return false;
        }
        if (phone.length() != 11) {
            showAlert("Invalid Input", "Phone number must be exactly 11 digits.");
            return false;
        }
        if (email == null || email.trim().isEmpty()) {
            showAlert("Invalid Input", "email cannot be empty.");
            return false;
        }
        
        
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args); // Launch the application
    }
}
