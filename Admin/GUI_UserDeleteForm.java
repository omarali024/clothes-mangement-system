package our_project;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI_UserDeleteForm {
    TextField searchField;
    GridPane grid = new GridPane(); // Class-level grid field

    public GUI_UserDeleteForm() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the labels and input fields
        Label searchLabel = new Label("User Username you want to delete:");
        searchField = new TextField();
        grid.add(searchLabel, 0, 0);
        grid.add(searchField, 1, 0);

        Button deleteButton = new Button("Delete User");
        grid.add(deleteButton, 1, 1);

        // Set the action for the delete button
        deleteButton.setOnAction(e -> {
            String username = searchField.getText();

            // helper methods to acces the arrylists and delete the (admin,cashier or user) 
            User admin = findAdminByUsername(username);
            User cashier = findCashierByUsername(username);
            User customer = findCustomerByUsername(username);
            if (admin != null) {
                Admin.userList.remove(admin); // Remove the admin from the list
               
                    System.out.println("User: " + admin.getUsername() + " has been deleted successfully");
                    showAlert("Success", "User deleted successfully!");
                Admin.createUsersFile();
            } else if (cashier != null) {
                Cashier.CashierList.remove(cashier); // Remove the admin from the list
               
                    System.out.println("User: " + cashier.getUsername() + " has been deleted successfully");
                    showAlert("Success", "User deleted successfully!");
                Cashier.createCashiersFile();
            }else if (customer != null) {
                Customer.CustomerList.remove(customer); // Remove the admin from the list
               
                    System.out.println("User: " + customer.getUsername() + " has been deleted successfully");
                    showAlert("Success", "User deleted successfully!");
                Customer.createCustomersFile();
            }else {
                showAlert("Error", "User not found!");
            }

            // Clear the search field after deleting
            searchField.clear();
        });
    }
    private User findAdminByUsername(String username) {
        for (User admin : Admin.userList) { // Now accessing adminList from the registration form
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;  // Return null if not found
    }
    
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
    
    
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public GridPane getRoot() {
        return grid; // Ensure this returns the correct, populated GridPane
    }
}