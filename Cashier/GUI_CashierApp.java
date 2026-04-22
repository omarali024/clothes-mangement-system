package our_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI_CashierApp extends Application {

    public void start(Stage primaryStage) {

        Admin.loadProductsFromFile();

        // Create a TabPane to hold the forms
        TabPane tabPane = new TabPane();

        // Create the Add Product to cart Tab
        Tab AddToCartTab = new Tab("Add To Cart");
        AddToCartTab.setClosable(false);
        GUI_AddToCartForm addProductToCart = new GUI_AddToCartForm();
        AddToCartTab.setContent(addProductToCart.getRoot());

        // Create the Show cart Tab
        Tab ShowCartTab = new Tab("Show Cart");
        ShowCartTab.setClosable(false);
        GUI_ShowCartForm showCartContents = new GUI_ShowCartForm();
        ShowCartTab.setContent(showCartContents.getRoot());

        // Create the delete from cart Tab
        Tab RemoveFromCartTab = new Tab("Remove From Cart");
        RemoveFromCartTab.setClosable(false);
        GUI_RemoveFromCartForm removeProductFromCart = new GUI_RemoveFromCartForm();
        RemoveFromCartTab.setContent(removeProductFromCart.getRoot());

        // Create the Calculate Total Price Tab
        Tab CalculateTotalPriceTab = new Tab("Calculate Total Price");
        CalculateTotalPriceTab.setClosable(false);
        GUI_CalculateTotalPriceForm calculateTotalPrice = new GUI_CalculateTotalPriceForm();
        CalculateTotalPriceTab.setContent(calculateTotalPrice.getRoot());
        
        // Create the Clear Cart Tab
        Tab ClearCartTab = new Tab("Clear Cart");
        ClearCartTab.setClosable(false);
        GUI_ClearCartForm clear = new GUI_ClearCartForm();
        ClearCartTab.setContent(clear.getRoot());
        
        // Create the Clear Cart Tab
        Tab PaymentTab = new Tab("Payment");
        PaymentTab.setClosable(false);
        GUI_PaymentForm payment = new GUI_PaymentForm();
        PaymentTab.setContent(payment.getRoot());
        
        
        
        
        
        
        

        // Add the tabs to the TabPane
        tabPane.getTabs().addAll(
                AddToCartTab,
                ShowCartTab,
                RemoveFromCartTab,
                CalculateTotalPriceTab,
                ClearCartTab,
                PaymentTab
        
        );

        // Create the scene and show the stage
        Scene scene = new Scene(tabPane, 600, 400); // Increased size for better layout
        primaryStage.setTitle("Casheir App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
