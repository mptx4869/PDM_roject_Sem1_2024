package engine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductList {
    public static Map<Integer, Product> producMap ;
    public static ResultSet producResultSet;
    public static void updateProductList(){
        producResultSet = ConnectSQL.excuteQuery("Select * From Product");
        producMap = new HashMap<>();
        try {
            while (producResultSet.next()) {
                Product product = new Product(producResultSet.getInt("Product_ID"), 
                producResultSet.getString("Product_Name"), 
                producResultSet.getString("Category"), 
                producResultSet.getString("BarCode"),
                 producResultSet.getBigDecimal("Price"));

                 producMap.put(product.getId(), product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void showMap(){
        for (Map.Entry<Integer,Product> entry : producMap.entrySet()){
            System.out.println(entry.getValue().toString());
        }
    }
    public static void main(String[] args) {
        updateProductList();
        showMap();
    }
}
