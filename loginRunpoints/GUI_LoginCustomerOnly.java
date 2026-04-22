package our_project;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI_LoginCustomerOnly extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        BorderPane root = new BorderPane();

        // Welcome label
        Label welcomeLabel = new Label("Welcome! Please Log In as Customer");
        root.setTop(welcomeLabel);

        // Login form
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button signupButton = new Button("SignUp");

        loginGrid.add(usernameLabel, 0, 0);
        loginGrid.add(usernameField, 1, 0);
        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);
        loginGrid.add(loginButton, 1, 2);
        loginGrid.add(signupButton, 1, 3);

        root.setCenter(loginGrid);

        // Add login button functionality
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Validate input
            if (!isValidInput(username, password)) {
                return; // Exit if input is invalid
            }

            // Check credentials
            if (isCustomer(username, password)) {
                openCustomerAppStage(primaryStage); // Open Main App Stage
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        });

        signupButton.setOnAction(e -> openCustomerSignup(primaryStage));

        // Scene setup
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Customer Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to validate username and password input
    private boolean isValidInput(String username, String password) {
        if ((username == null || username.trim().isEmpty()) &&
            (password == null || password.trim().isEmpty())) {
            showAlert("Invalid Input", "Both Username and Password cannot be empty.");
            return false;
        }
        if (username == null || username.trim().isEmpty()) {
            showAlert("Invalid Input", "Username cannot be empty.");
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            showAlert("Invalid Input", "Password cannot be empty.");
            return false;
        }
        if (username.length() < 3 || username.length() > 15) {
            showAlert("Invalid Input", "Username must be between 3 and 15 characters.");
            return false;
        }
        if (password.length() < 4) {
            showAlert("Invalid Input", "Password must be at least 4 characters long.");
            return false;
        }
        return true;
    }

    // Method to check if the user is a Customer
    private boolean isCustomer(String username, String password) {
        for (User customer : Customer.CustomerList) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method to open Customer App Stage
    private void openCustomerAppStage(Stage primaryStage) {
        GUI_CustomerApp customerApp = new GUI_CustomerApp();
        try {
            customerApp.start(primaryStage); // Load Main App Stage
        } catch (Exception ex) {
            showAlert("Error", "Unable to load the Main App stage.");
        }
    }

    // Method to open Customer Signup Stage
    private void openCustomerSignup(Stage primaryStage) {
        GUI_CustomerSignup signupApp = new GUI_CustomerSignup();
        try {
            signupApp.start(primaryStage); // Load Signup Stage
        } catch (Exception ex) {
            showAlert("Error", "Unable to load the Signup stage.");
        }
    }

    // Method to show alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // Load initial customer data (make sure Customer.loadCustomersFromFile is implemented)
        Customer.loadCustomersFromFile();
        launch(args);
    }
}
