package our_project;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class Customer extends User implements Serializable{
 ArrayList<Order>cart=new ArrayList<>();
 protected static ArrayList<User> CustomerList = new ArrayList<>();
 
    public Customer(){
        super();
    }
    public Customer(String username, String password, String phone, String email){
         super(username , password, phone , email);
    }
@Override
public String toString(){
    return "Username: "+username+"\n";
}
   public ArrayList<Order> getCart() {
        return cart;
    }
   public void viewCatalog(ArrayList<Product> catalog) {
        System.out.println("Catalog:");
        for (Product product : catalog) {
            System.out.println(product);
        }
    }

    public void viewOrderHistory() {
        if (cart.isEmpty()) {
            System.out.println("No orders in your history.");
        } else {
            System.out.println("Order History:");
            for (Order order : cart) {
                System.out.println(order.toString());
            }
        }
    }
    public void getOrderDetails(String orderID) {
        for (Order order : cart) {
            if (order.getOrderID().equals(orderID)) {
                System.out.println(order.toString());
                return;
            }
        }
        System.out.println("Order with ID " + orderID + " not found in cart.");
    }

      public static void createCustomersFile() {
    File file = new File("Customers.dat");
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        oos.writeObject(CustomerList);
        System.out.println("Customers saved to Customers.dat successfully.");
    } catch (IOException e) {
        System.out.println("Something went wrong while saving Customers.");
        e.printStackTrace();
    }
   
    
}
      public static void loadCustomersFromFile() {
    File file = new File("Customers.dat");
    if (!file.exists()) {
        System.out.println("Customers.dat not found. No users to load.");
        return;
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        CustomerList = (ArrayList<User>) ois.readObject(); // Deserialize the list
        System.out.println("Customers loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Something went wrong while loading Customers.");
        e.printStackTrace();
    }
}
 
    
   
}