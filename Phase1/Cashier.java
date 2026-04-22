package our_project;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.*;
import java.util.ArrayList;

public class Cashier extends User implements Serializable {
   protected static ArrayList<Order>cart=new ArrayList<>();
   protected static ArrayList<User> CashierList = new ArrayList<>();
    public Cashier(){
        super();
    }
    public Cashier(String username, String password, String phone, String email){
        super(username,password,phone,email);
    }
    
    public void addToCart(String productID, ArrayList<Product> catalog) {
        Admin.loadProductsFromFile();
        // Check if the product is already in the cart
        for (Order order : cart) {
            if (order.getProductID().equals(productID)) {
                // Increment quantity if found
                order.setQuantity(order.getQuantity() + 1);
                System.out.println("Product quantity updated in cart: " + order.getProductName() + 
                                   " (Quantity: " + order.getQuantity() + ")");
                return;
            }
        }
        
        

        // If not found in the cart, add a new product
        for (Product product : catalog) {
            if (product.getProductID().equals(productID)) {
                cart.add(new Order(product.getProductName(), product.getProductID(), product.getColor(),
                        product.getPrice(), product.getSize(),"" ,1));
                System.out.println("Product added to cart: " + product.getProductName());
                return;
            }
    }
        System.out.println("Product with ID " + productID + " not found in catalog.");
    }
    public void removeFromCart(String orderID) {
        for (Order order : cart) {
            if (order.getOrderID().equals(orderID)) {
                cart.remove(order);
                System.out.println("Order removed from cart: " + order.getProductName() + " (Order ID: " + orderID + ")");
                return;
            }
        }
        System.out.println("Order with ID " + orderID + " not found in cart.");
    }
   public void showCart() {
    if (cart.isEmpty()) {
        System.out.println("The cart is empty.");
        return;
    }

    System.out.println("Current cart contents:");
    System.out.printf("Product Name ");


    for (Order order : cart) {
        System.out.printf(
                          order.getProductName(), 
                          order.getProductID(), 
                          order.getColor(), 
                          order.getPrice(), 
                          order.getSize(), 
                          order.getQuantity());
    }
}

    
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Order order : cart) {
            totalPrice += order.getPrice() * order.getQuantity();
        }
        System.out.print("Final price: ");
        return totalPrice;
    }
    public void clear(){
        cart.clear();
    }
    public void payment(double payment){
        for(Order order: cart){
            if(payment>=order.price){
                System.out.println("Transaction successful");
            }
            else {
                System.out.println("Transaction failed");
            }
        }
    }
       
      
      public static void createCashiersFile() {
    File file = new File("Cashiers.dat");
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(CashierList);
        System.out.println("Cashiers saved to Cashiers.dat successfully.");
    } catch (IOException e) {
        System.out.println("Something went wrong while saving Cashiers.");
    }
   
    
}
      
    public static void loadCashiersFromFile() {
    File file = new File("Cashiers.dat");
    if (!file.exists()) {
        System.out.println("Cashiers.dat not found. No users to load.");
        return;
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        CashierList = (ArrayList<User>) ois.readObject(); // Deserialize the list
        System.out.println("Cashiers loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Something went wrong while loading Cashiers.");
    }
}
// Getter for cart
    public static ArrayList<Order> getCart() {
        return cart;
    }
}