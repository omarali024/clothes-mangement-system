package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI_UserEditForm {
    TextField usernameField, passwordField, phoneField, emailField;
    TextField searchField;
    GridPane grid = new GridPane(); // Class-level grid field

    public GUI_UserEditForm() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the labels and input fields
        Label searchLabel = new Label("User Username you want to edit:");
        searchField = new TextField();
        grid.add(searchLabel, 0, 0);
        grid.add(searchField, 1, 0);

        Label nameLabel = new Label("New Name:");
        usernameField = new TextField();
        grid.add(nameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        Label passwordLabel = new Label("New Password:");
        passwordField = new TextField();
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        Label phoneLabel = new Label("New Phone:");
        phoneField = new TextField();
        grid.add(phoneLabel, 0, 3);
        grid.add(phoneField, 1, 3);

        Label emailLabel = new Label("New Email:");
        emailField = new TextField();
        grid.add(emailLabel, 0, 4);
        grid.add(emailField, 1, 4);

        // Add the save button
        Button saveButton = new Button("Save Data");
        grid.add(saveButton, 1, 5);

        // Set the action for the "Save Data" button
        saveButton.setOnAction(e -> {
            String username = searchField.getText(); // Use the username to find the admin
            User admin = findAdminByUsername(username);
            User cashier = findCashierByUsername(username);
            User customer = findCustomerByUsername(username);
            if (admin != null) {
                // Set the updated data to the Admin object
                admin.setUsername(usernameField.getText());
                admin.setPassword(passwordField.getText());
                admin.setPhone(phoneField.getText());
                admin.setEmail(emailField.getText());
 System.out.println("User data updated successfully for user: " + admin.getUsername());
                 Admin.createUsersFile();
                showAlert("Success", "Admin data updated successfully!");
            } else if (cashier != null) {
                // Set the updated data to the Admin object
                cashier.setUsername(usernameField.getText());
                cashier.setPassword(passwordField.getText());
                cashier.setPhone(phoneField.getText());
                cashier.setEmail(emailField.getText());
 System.out.println("User data updated successfully for user: " + cashier.getUsername());
                 Cashier.createCashiersFile();
                showAlert("Success", "Cashier data updated successfully!");
            }else if (customer != null) {
                // Set the updated data to the Admin object
                customer.setUsername(usernameField.getText());
                customer.setPassword(passwordField.getText());
                customer.setPhone(phoneField.getText());
                customer.setEmail(emailField.getText());
 System.out.println("User data updated successfully for user: " + customer.getUsername());
                 Customer.createCustomersFile();
                showAlert("Success", "Cashier data updated successfully!");
            }
            else
            {
                showAlert("Error", "User not found!");
            }

            // Clear the fields after saving
            searchField.clear();
            usernameField.clear();
            passwordField.clear();
            phoneField.clear();
            emailField.clear();
        });
    }

    // Helper method to find the admin by username
    private User findAdminByUsername(String username) {
        for (User admin : Admin.userList) { // Now accessing adminList from the registration form
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;  // Return null if not found
    }
    
    // Helper method to find the admin by username
    private User findCashierByUsername(String username) {
        for (User cashier : Cashier.CashierList) { // Now accessing adminList from the registration form
            if (cashier.getUsername().equals(username)) {
                return cashier;
            }
        }
        return null;  // Return null if not found
    }
    
    private User findCustomerByUsername(String username) {
        for (User customer : Customer.CustomerList) { // Now accessing adminList from the registration form
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;  // Return null if not found
    }

    // Helper method to display an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Return the GridPane to use it in the TabPane
    public GridPane getRoot() {
        return grid; // Ensure this returns the correct, populated GridPane
    }
}
