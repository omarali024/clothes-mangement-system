package our_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUI_MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load data from files
        Admin.loadUsersFromFile();
        Admin.loadProductsFromFile();
        Cashier.loadCashiersFromFile();
        Customer.loadCustomersFromFile();

        // Create the main TabPane
        TabPane mainTabPane = new TabPane();

        // Create User Management Tab
        Tab userManagementTab = new Tab("User Management");
        userManagementTab.setClosable(false);
        userManagementTab.setContent(createUserManagementTabs());

        // Create Product Management Tab
        Tab productManagementTab = new Tab("Product Management");
        productManagementTab.setClosable(false);
        productManagementTab.setContent(createProductManagementTabs());

        // Add user mangement and product mangemet to the main TabPane
        mainTabPane.getTabs().addAll(userManagementTab, productManagementTab);

        // Create the scene and show the stage
        Scene scene = new Scene(mainTabPane, 800, 600);
        primaryStage.setTitle("Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//  2 helper methods to create the sub tabs for each main tabs product and user and thier content from other classes
    private TabPane createUserManagementTabs() {
        TabPane userTabPane = new TabPane();

        // User Registration Tab
        Tab registrationTab = new Tab("User Registration");
        registrationTab.setClosable(false);
        GUI_UserRegistrationForm registrationForm = new GUI_UserRegistrationForm();
        registrationTab.setContent(registrationForm.getRoot());

        // User Edit Tab
        Tab editTab = new Tab("User Edit");
        editTab.setClosable(false);
        GUI_UserEditForm editForm = new GUI_UserEditForm();
        editTab.setContent(editForm.getRoot());

        // User Delete Tab
        Tab deleteTab = new Tab("User Delete");
        deleteTab.setClosable(false);
        GUI_UserDeleteForm deleteForm = new GUI_UserDeleteForm();
        deleteTab.setContent(deleteForm.getRoot());

        // View User List Tab
        Tab viewUserListTab = new Tab("View User List");
        viewUserListTab.setClosable(false);
        GUI_ShowUsers viewUserListForm = new GUI_ShowUsers();
        viewUserListTab.setContent(viewUserListForm.getRoot());

        userTabPane.getTabs().addAll(registrationTab, editTab, deleteTab, viewUserListTab);

        return userTabPane;
    }

    private TabPane createProductManagementTabs() {
        TabPane productTabPane = new TabPane();

        // Product Addition Tab
        Tab productAdditionTab = new Tab("Product Addition");
        productAdditionTab.setClosable(false);
        GUI_ProductAdditionForm productAdditionForm = new GUI_ProductAdditionForm();
        productAdditionTab.setContent(productAdditionForm.getRoot());

        // Product Edit Tab
        Tab productEditTab = new Tab("Product Edit");
        productEditTab.setClosable(false);
        GUI_ProductEditForm productEditForm = new GUI_ProductEditForm();
        productEditTab.setContent(productEditForm.getRoot());

        // Product Delete Tab
        Tab productDeleteTab = new Tab("Product Delete");
        productDeleteTab.setClosable(false);
        GUI_ProductDeleteForm productDeleteForm = new GUI_ProductDeleteForm();
        productDeleteTab.setContent(productDeleteForm.getRoot());

        // View Product Catalog Tab
        Tab viewCatalogTab = new Tab("View Product Catalog");
        viewCatalogTab.setClosable(false);
        GUI_ShowCatalog viewCatalogForm = new GUI_ShowCatalog();
        viewCatalogTab.setContent(viewCatalogForm.getRoot());

        // Search Product Tab
        Tab searchProductTab = new Tab("Search Product");
        searchProductTab.setClosable(false);
        GUI_SearchProduct searchProductForm = new GUI_SearchProduct();
        searchProductTab.setContent(searchProductForm.getRoot());

        productTabPane.getTabs().addAll(productAdditionTab, productEditTab, productDeleteTab, viewCatalogTab, searchProductTab);

        return productTabPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}