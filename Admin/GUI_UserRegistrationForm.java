package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;

public class GUI_UserRegistrationForm {
    TextField nameField, passwordField, phoneField, emailField;

    GridPane grid = new GridPane();  // Make gridpane a field to access its content by the get root() method in other files   

    public GUI_UserRegistrationForm() {
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

        Button addButton = new Button("Add Admin");
        Button eraseButton = new Button("Erase All");
        grid.add(addButton, 0, 4);
        grid.add(eraseButton, 3, 4);
        
        Button addCashierButton = new Button("Add Cashier");
        grid.add(addCashierButton, 1, 4);
        
        Button addCustomerButton = new Button("Add Customer");
        grid.add(addCustomerButton, 2, 4);

        addButton.setOnAction(e -> {
            // Validate input before adding Admin
            if (!isValidInput(nameField.getText(), passwordField.getText(), phoneField.getText(),emailField.getText())) {
                return; // Exit if validation fails
            }

            // Create a new Admin object
            Admin admin = new Admin(
                nameField.getText(),
                passwordField.getText(),
                phoneField.getText(),
                emailField.getText()
            );

            // Add the new admin to the Admin.UserList
            Admin.userList.add(admin);
            showAlert("Success", "Admin added successfully!");
            
            // Save the user to the file
            Admin.createUsersFile();

            // Clear the fields
            clearFields();
        });
        
        addCashierButton.setOnAction(e -> {
            // Validate input before adding Cashier
            if (!isValidInput(nameField.getText(), passwordField.getText(), phoneField.getText(),emailField.getText())) {
                return; // Exit if validation fails
            }

            // Create a new Cashier object
            Cashier cashier = new Cashier(
                nameField.getText(),
                passwordField.getText(),
                phoneField.getText(),
                emailField.getText()
            );

            Cashier.CashierList.add(cashier);
            showAlert("Success", "Cashier added successfully!");
            
            // Save the user to the file
            Cashier.createCashiersFile();

            // Clear the fields
            clearFields();
        });
        
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
            clearFields();
        });

        eraseButton.setOnAction(e -> {
            // Clear all the input fields
            clearFields();
        });
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

    // Method to clear all fields
    private void clearFields() {
        nameField.clear();
        passwordField.clear();
        phoneField.clear();
        emailField.clear();
    }

    // Method to return the root grid
    public GridPane getRoot() {
        return grid;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
