package our_project;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GUI_ShowUsers {
    GridPane grid = new GridPane(); // Class-level grid field
    TextArea userListArea; // To display the user list
    
    public GUI_ShowUsers() {
        // Initialize and configure the class-level grid
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(30);
        grid.setVgap(10);

        // Add the "View User List" label
        Label viewLabel = new Label("View User List:");
        grid.add(viewLabel, 0, 0);

        // Create the "Show Users" button
        Button showButton = new Button("Show User List");
        grid.add(showButton, 1, 1);
        
        // Create the result area to display the user list
        userListArea = new TextArea();
        userListArea.setEditable(false);  // Make the area read-only
        userListArea.setPrefHeight(150);  // Set the height for better visibility
        grid.add(userListArea, 0, 2, 2, 1);  // Span across two columns


        // Set the action for the "Show User List" button
        showButton.setOnAction(e -> {
            // Display user list in the console
            showUserList();
        });
    }

    private void showUserList() {
        StringBuilder userListText = new StringBuilder();
        if (Admin.userList == null || Admin.userList.isEmpty()) {
        userListText.append("No admins found.\n");
        } else {
            for (User admin : Admin.userList) {
                userListText.append("Admin: ").append(admin.getUsername()).append("\n");
            }
        }
        if (Cashier.CashierList == null || Cashier.CashierList.isEmpty()) {
            userListText.append("No cashiers found.\n");
        } else {
        for (User cashier : Cashier.CashierList) {
            userListText.append("Cashier: ").append(cashier.getUsername()).append("\n");
        }
        }
        if (Customer.CustomerList == null || Customer.CustomerList.isEmpty()) {
            userListText.append("No customers found.\n");
        } else {
        for (User customer : Customer.CustomerList) {
            userListText.append("Customer: ").append(customer.getUsername()).append("\n");
        }
        }
        userListArea.setText(userListText.toString());
        }
    

    // Return the GridPane to use it in the TabPane
    public GridPane getRoot() {
        return grid; // Ensure this returns the correct, populated GridPane
    }
}
