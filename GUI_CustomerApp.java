 package our_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUI_CustomerApp extends Application {

  public void start(Stage primaryStage) {
        Admin.loadProductsFromFile();
        
        // Create a TabPane to hold the forms
        TabPane tabPane = new TabPane();
        
        // Create the View Product Catalog Tab
        Tab viewCatalogTab = new Tab("View Product Catalog");
        viewCatalogTab.setClosable(false);
        GUI_ShowCatalog viewCatalogForm = new GUI_ShowCatalog();
        viewCatalogTab.setContent(viewCatalogForm.getRoot());
        
        // Create the Search Product Tab
        Tab searchProductTab = new Tab("Search Product");
        searchProductTab.setClosable(false);
        GUI_SearchProduct searchProductForm = new GUI_SearchProduct();
        searchProductTab.setContent(searchProductForm.getRoot());
        
        // Add the tabs to the TabPane
        tabPane.getTabs().addAll(
            viewCatalogTab,
            searchProductTab // Added Search Product Tab
        );
        
        // Create the scene and show the stage
        Scene scene = new Scene(tabPane, 600, 400); // Increased size for better layout
        primaryStage.setTitle("Customer App");
        primaryStage.setScene(scene);
        primaryStage.show();
        
  }
  
  public static void main(String[] args) {
        launch(args);
  }
    
    
}
