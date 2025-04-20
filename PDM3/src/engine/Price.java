package engine;

import java.math.BigDecimal;
import java.sql.Date;

public class Price {
    
    private int id;
    private int product_ID;
    private BigDecimal price;
    private Date Date_Effective; 
    public Price(int id, int product_ID, BigDecimal price, Date Date_Effective){
        this.id = id ;
        this.product_ID = product_ID;
        this.price = price;
        this.Date_Effective = Date_Effective;
    }

    public boolean insertIntoDB(){
        return ConnectSQL.insertPrice(product_ID, price, Date_Effective);
        
    }

    @Override
    public String toString() {
        return "id =" + id + 
            " Product_ID = "+product_ID+
            " Price = "+ price+
            " Date_Effective = "+Date_Effective;
    }
    public Date getDate_Effective() {
        return Date_Effective;
    }
    public void setDate_Effective(Date date_Effective) {
        Date_Effective = date_Effective;
    }
    public int getId() {
        return id;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getProduct_ID() {
        return product_ID;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }
}
