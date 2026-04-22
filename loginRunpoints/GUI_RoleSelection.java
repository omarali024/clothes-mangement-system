package our_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage; 


public class GUI_RoleSelection extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Admin.loadUsersFromFile();
        Admin.loadProductsFromFile();
        Customer.loadCustomersFromFile();
        Cashier.loadCashiersFromFile();
        // Root layout
        VBox root = new VBox(20); // Spacing between buttons
        root.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        // Buttons for role selection
        Button adminLoginButton = new Button("Admin Login");
        Button customerLoginButton = new Button("Customer Login");
        Button cashierLoginButton = new Button("Cashier Login");

        // Set button actions
        adminLoginButton.setOnAction(e -> openAdminLoginStage(primaryStage));
        customerLoginButton.setOnAction(e -> openCustomerLoginStage(primaryStage)); // Placeholder
        cashierLoginButton.setOnAction(e -> openCashierLoginStage(primaryStage));   // Placeholder

        // Add buttons to the root
        root.getChildren().addAll(adminLoginButton, customerLoginButton, cashierLoginButton);

        // Scene setup
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Role Selection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to open Admin Login Stage
    private void openAdminLoginStage(Stage primaryStage) {
        GUI_LoginAdminOnly adminLogin = new GUI_LoginAdminOnly();
        try {
            adminLogin.start(primaryStage); // Load Admin Login Stage
        } catch (Exception ex) {
            showAlert("Error", "Unable to load the Admin Login stage.");
        }
    }

    // Placeholder for Customer Login Stage
    private void openCustomerLoginStage(Stage primaryStage) {
        GUI_LoginCustomerOnly customerLogin = new GUI_LoginCustomerOnly();
        try {
            customerLogin.start(primaryStage); // Load Cashier Login Stage
        } catch (Exception ex) {
            showAlert("Error", "Unable to load the Customer Login stage.");
        }
    }

    // Placeholder for Cashier Login Stage
    private void openCashierLoginStage(Stage primaryStage) {
        GUI_LoginCashierOnly cashierLogin = new GUI_LoginCashierOnly();
        try {
            cashierLogin.start(primaryStage); // Load Cashier Login Stage
        } catch (Exception ex) {
            showAlert("Error", "Unable to load the Cashier Login stage.");
        }
    }

    // Method to show alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}