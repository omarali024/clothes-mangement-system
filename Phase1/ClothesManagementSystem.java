package our_project;

import java.util.ArrayList;
import java.util.Scanner;

public class ClothesManagementSystem {
    
    
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin123", "1234567890", "admin@example.com");
        Customer customer = new Customer("customer1", "cust123", "0987654321", "customer1@example.com");
        Cashier cashier = new Cashier("cashier1", "cash123", "1122334455", "cashier1@example.com");
        ArrayList<Product> catalog = new ArrayList<>();
        
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Clothes Management System ---");
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Cashier Menu");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    adminMenu(sc, admin, catalog);
                    break;
                case 2:
                    customerMenu(sc, customer, catalog);
                    break;
                case 3:
                    cashierMenu(sc, cashier, catalog);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    // Admin menu
    public static void adminMenu(Scanner sc, Admin admin, ArrayList<Product> catalog) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. Show All Users");
            System.out.println("4. Add Product");
            System.out.println("5. Remove Product");
            System.out.println("6. Edit Product");
            System.out.println("7. Show Catalog");
            System.out.println("8. Search Product");
             System.out.println("9. Create Users File");
            System.out.println("10. Create Catalog File");
             System.out.println("11. Exit");
           
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                    case 1:
                    System.out.println("Enter username: ");
                    String username = sc.nextLine();
                    System.out.println("Enter password: ");
                    String password = sc.nextLine();
                    System.out.println("Enter phone: ");
                    String phone = sc.nextLine();
                    System.out.println("Enter email: ");
                    String email = sc.nextLine();

                    User newUser = new User(username, password, phone, email);
                    admin.addUser(newUser);
                    System.out.println("User added successfully.");
                    break;

                case 2:
                    System.out.println("Enter username of the user to remove: ");
                    String removeUsername = sc.nextLine();

                    // Find user by username
                    User userToRemove = null;
                    for (User user : admin.userList) { 
                        if (user.getUsername().equals(removeUsername)) {
                            userToRemove = user;
                            break;
                        }
                    }

                    if (userToRemove != null) {
                        admin.removeUser(userToRemove);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 3:
                    System.out.println("Showing all users:");
                    admin.showallUsers();
                    break;
                
                case 4:
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter color: ");
                    String color = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter size: ");
                    String size = sc.nextLine();
                    Product product = new Product(name, id, color, price, size);
                    admin.addproduct(product);
                    catalog.add(product);
                    break;
                case 5:
                    System.out.print("Enter product ID to remove: ");
                    String removeId = sc.nextLine();
                    Product toRemove = catalog.stream()
                            .filter(p -> p.getProductID().equals(removeId))
                            .findFirst()
                            .orElse(null);
                    if (toRemove != null) {
                        admin.removeproduct(toRemove);
                        catalog.remove(toRemove);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter product ID to edit: ");
                    String editId = sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new color: ");
                    String newColor = sc.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter new size: ");
                    String newSize = sc.nextLine();
                    admin.editProduct(newName, editId, newColor, newPrice, newSize);
                    break;
                case 7:
                    admin.showCatalog();
                    break;
                case 8:
                    System.out.print("Enter search criterion (productID, productName, color, size, supplier): ");
                    String criterion = sc.nextLine();
                    System.out.print("Enter search value: ");
                    String value = sc.nextLine();
                    admin.searchProduct(criterion, value);
                    break;
               
                case 9: 
    admin.createUsersFile();
    break;
                case 10: 
    admin.createCatalogFile();
    break;
    case 11:
           back = true;
           break;
           default:
               System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Customer menu
    public static void customerMenu(Scanner sc, Customer customer, ArrayList<Product> catalog) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Catalog");
            System.out.println("2. View Order History");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    customer.viewCatalog(catalog);
                    break;
                case 2:
                    customer.viewOrderHistory();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Cashier menu
public static void cashierMenu(Scanner sc, Cashier cashier, ArrayList<Product> catalog) {
    boolean back = false;
    while (!back) {
        System.out.println("\n--- Cashier Menu ---");
        System.out.println("1. Add to Cart");
        System.out.println("2. Remove from Cart");
        System.out.println("3. Calculate Total Price");
        System.out.println("4. Clear Cart");
        System.out.println("5. Show Cart Content");
        System.out.println("6. Payment");
        System.out.println("7. Back");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.print("Enter product ID to add to cart: ");
                String productId = sc.nextLine();
                cashier.addToCart(productId, catalog);
                break;
            case 2:
                System.out.print("Enter order ID to remove from cart: ");
                String orderId = sc.nextLine();
                cashier.removeFromCart(orderId);
                break;
            case 3:
                System.out.println("Total Price: " + cashier.calculateTotalPrice());
                break;
            case 4:
                cashier.clear();
                System.out.println("Cart cleared.");
                break;
            
            case 5:
                
                cashier.showCart();
                
                break;
                
            case 6:
                
                System.out.print("Enter payment amount: ");
                double paymentAmount = sc.nextDouble();
                cashier.payment(paymentAmount); // Call payment method
                break;
            
            case 7:
                back = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
}