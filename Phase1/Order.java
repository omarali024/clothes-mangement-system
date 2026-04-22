package our_project;




 public class Order  extends Product {
    protected String orderID;
    protected int quantity;
    protected double totalPrice;
    
    public Order(){
        super();
        this.quantity=0;
    }
    public Order(String productName,String productID, String color, double price, String size, String orderID ,int quantity){
        super( productName, productID,  color,  price,  size);
       this.orderID=orderID;
        this.quantity=quantity;
    }
    
    
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
     double getTotal(){
       totalPrice= price*quantity;
       return totalPrice;
    }
     
    public int getQuantity(){
        return quantity;
    }
    public String getOrderID(){
        return orderID;
    }
     public void setOrderID(String orderID){
         this.orderID=orderID;
     }
      
    @Override
public String toString() {
    return ("Order Details:\n" +
            "Product Name: " + productName + "\n" +
           "Color: " + color + "\n" +
           "Price: " + price + "\n" +
           "Size: " + size + "\n"+
           "Quantity: " + quantity + "\n" +
           "Total Price: " + getTotal()+"\n"+"Order ID: "+orderID);
}

         

}

