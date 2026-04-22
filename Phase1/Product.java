package our_project;

import java.io.Serializable;


public  class Product implements Serializable{
    
    protected String productName;
    protected String color;
    protected double price;
    protected String size;
    protected String productID;
    protected String supplier;
    protected String supplierID;
    protected int rating;
    
    public Product(String productName,String productID, String color, double price, String size){
this.productName=productName;
this.productID=productID;
this.color=color;
this.price=price;
this.size=size;
this.rating=0;
    }
   public Product(){
       this.productName = "";
       this.color = "";
       this.price = 0.00;
       this.size="";
       this.rating=0;
   }
    
   public void setProductName(String productName){
       this.productName = productName;
   }
   
   public void setColor(String color){
       this.color = color;
   }
   
   public void setPrice(double price){
       this.price = price;
   }
 
public String getProductName(){
       return productName;
   }
public void setProductID(String productID){
    this.productID=productID;
}
public String getProductID(){
    return productID;
}
   
   public String getColor(){
       return color;
   }
   public double getPrice(){
       return price;
   }
   public void setSize(String size){
       this.size=size;
   }
   public String getSize(){
       return size;
   }
   public void setSupplier(String supplier){
       this.supplier=supplier;
   }
   public String getSupplier(){
       return supplier;
   }
   public void setSupplierID(String supplierID){
       this.supplierID=supplierID;
   }
   public String getSupplierID(){
       return supplierID;
   }
   public void setRating(int rating){
   this.rating=rating;
   }
  public int getRating(){
      return rating;
  }
  
   @Override
    public String toString() {
        return 
                "productName= " + productName + " " +
                ", productID='" + productID + " " +
                ", color='" + color + " " +
                ", price=" + price +
                ", size='" + size ;
               
    }
  
   
}