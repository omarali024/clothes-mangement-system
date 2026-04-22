package our_project;

import java.io.Serializable;



     public class User implements Serializable {
   protected String username;
    protected String password; 
    protected String email;
    protected String phone;
    public User( ){
        this.username="";
        this.password="";
        this.email="";
        System.out.println("User added ! ");
        System.out.println("Hello "+username);
    }
    
    public User(String username, String password, String phone, String email ){
        this.username=username;
        this.password=password;
        this.phone=phone; //add to gui 
        this.email=email;
         System.out.println("User added ! ");
        System.out.println("Hello "+username);
    }
      //abstract void setUsername(String username);
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public void setPassword(String password){
    this.password=password;
    }
   
   public  void setEmail(String email){
        this.email=email;
    } 
    public void setPhone(String phone){
        this.phone=phone;
    } 
    public String getUsername(){
        return username;
    }
       // abstract void setPassword(String password);
   public String getPassword(){ 
    return password;   
   }
   
  // abstract void setEmail(String email);
  public String getEmail(){
      return email;
  }
  //abstract void setID(String id);
  public String getPhone(){
      return phone;
  }
  @Override
    public String toString(){
        return(username+" " + password+" "+phone+" "+email);
    }
    
    public void getInfo(){
        System.out.println("Username: "+username+"\n"+"Email: "+email+"\n"+"Phone: "+phone+"\n");
    }
   
    public void logout(){
        System.out.println("Logged out!");
    }
  
}