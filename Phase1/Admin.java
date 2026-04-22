package our_project;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Admin extends User implements Serializable {
    // Initialize the static userList only once
    protected static ArrayList<User> userList = new ArrayList<>();
    protected static ArrayList<Product> catalog = new ArrayList<>();

    public Admin() {
        super();
    }

    public Admin(String username, String password, String phone, String email) {
        super(username, password, phone, email);
    }

    public void addUser(User user) {
        userList.add(user);
        
        Admin.createUsersFile();
    }

    public void removeUser(User user) {
        if (userList.remove(user)) {
            System.out.println("User " + user.getUsername() + " removed.");
            Admin.createUsersFile();
        } else {
            System.out.println("User not found.");
        }
    }

    public void showallUsers() {
        Admin.loadUsersFromFile();
        for (User user : userList) {
            System.out.print(user.toString()+" ");
        }
    }

    public void addproduct(Product product) {
        catalog.add(product);
        System.out.println("Product " + product.getProductName() + " added.");
    }

    public void removeproduct(Product product) {
        if (catalog.remove(product)) {
            System.out.println("Product " + product.getProductName() + " removed.");
        } else {
            System.out.println("Product not found.");
        }
    }
    
    public static void createUsersFile() {
    File file = new File("Users.dat");
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(userList);
        System.out.println("Users saved to Users.dat successfully.");
    } catch (IOException e) {
        System.out.println("Something went wrong while saving users.");
    }
}
    
    public static void loadUsersFromFile() {
    File file = new File("Users.dat");
    if (!file.exists()) {
        System.out.println("Users.dat not found. No users to load.");
        return;
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        userList = (ArrayList<User>) ois.readObject(); // Cast to the appropriate type
        System.out.println("Users loaded successfully from Users.dat.");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Something went wrong while loading users.");
        e.printStackTrace();
    }
}

    public static void createCatalogFile() {
        
        File file = new File("Catalog.dat");
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(catalog);
        System.out.println("Products saved to Catalog.dat successfully.");
    } catch (IOException e) {
        System.out.println("Something went wrong while saving products.");
    }
    }
    
    public static void loadProductsFromFile() {
    File file = new File("Catalog.dat");
    if (!file.exists()) {
        System.out.println("Products.dat not found. No Products to load.");
        return;
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        catalog = (ArrayList<Product>) ois.readObject(); // Cast to the appropriate type
        System.out.println("Products loaded successfully from Catalog.dat.");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Something went wrong while loading Products.");
    }
}

    public void editProduct(String newName, String productID, String newColor, double newPrice, String newSize) {
        for (Product product : catalog) {
            if (product.getProductID().equals(productID)) {
                product.setProductName(newName);
                product.setColor(newColor);
                product.setPrice(newPrice);
                product.setSize(newSize);
                System.out.println("Product with ID " + productID + " has been updated.");
                return;
            }
        }

        System.out.println("Product with ID " + productID + " not found.");
    }

    public void showCatalog() {
        Admin.loadProductsFromFile();
        if (catalog.isEmpty()) {
            System.out.println("Inventory is empty");
        } else {
            System.out.println("Products: ");
            for (Product product : catalog) {
                System.out.println("Name: " + product.getProductName() + ", Price: " + product.getPrice());
            }
        }
    }

    public void searchProduct(String criterion, String value) {
        boolean found = false;

        System.out.println("Searching for products by " + criterion + ": " + value);

        for (Product product : catalog) {
            switch (criterion.toLowerCase()) {
                case "productid":
                    if (product.getProductID().equalsIgnoreCase(value)) {
                        System.out.println(product);
                        found = true;
                    }
                    break;
                case "productname":
                    if (product.getProductName().equalsIgnoreCase(value)) {
                        System.out.println(product);
                        found = true;
                    }
                    break;
                case "color":
                    if (product.getColor().equalsIgnoreCase(value)) {
                        System.out.println(product);
                        found = true;
                    }
                    break;
                case "size":
                    if (product.getSize().equalsIgnoreCase(value)) {
                        System.out.println(product);
                        found = true;
                    }
                    break;
                case "supplier":
                    if (product.getSupplier().equalsIgnoreCase(value)) {
                        System.out.println(product);
                        found = true;
                    }
                    break;
                default:
                    System.out.println("Invalid search criterion: " + criterion);
                    return;
            }
        }

        if (!found) {
            System.out.println("No products found for the given " + criterion + ": " + value);
        }
    }

    @Override
    public String toString() {
        return "User: " + getUsername();
    }
}
