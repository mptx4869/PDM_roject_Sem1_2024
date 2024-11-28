package engine;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String product_name;
    private String category;
    private String barCode;
    private BigDecimal price;

    public Product(int id, String productName,String category, String barCode,BigDecimal price){
        this.id = id ;
        this.product_name = productName;
        this.category = category;
        this.barCode = barCode; 
        this.price = price;
    }
    @Override
    public String toString() {
        return "id = "+ id+
            ", ProductName = "+ product_name+
            ", category = " + category+ 
            ", Bar Code = " + barCode+
            ", Price = " + price;
    }
    public String getBarCode() {
        return barCode;
    }
    public String getCategory() {
        return category;
    }
    public int getId() {
        return id;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
