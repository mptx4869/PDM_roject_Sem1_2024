package engine;

public class Customer {
    private String name; 
    private String email;
    private String[] phoneNumber;
    
    public Customer (String name, String email, String[] phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber; 
    }

    public Customer (String name, String email, String phoneNumber){
        this(name, email, new String[]{phoneNumber} );
    }


    // set method 
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //get method
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String[] getPhoneNumber() {
        return phoneNumber;
    }
}   
