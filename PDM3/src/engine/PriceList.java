package engine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PriceList {
    public static ResultSet priceListResultSet;
    public static Map<Integer,Price> priceListMap; 
    
    public static void updatePriceList(){
        priceListResultSet=  ConnectSQL.excuteQuery("Select * From price_list");
        
        priceListMap = new HashMap<>();
        try {
            
            while (priceListResultSet.next()) {
                Price price = new Price(priceListResultSet.getInt("PriceList_ID"),
                 priceListResultSet.getInt("Product_ID"),
                  priceListResultSet.getBigDecimal("Price"), 
                  priceListResultSet.getDate("Date_Effective"));

                priceListMap.put(price.getProduct_ID(), price);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void showMap(){
        for (Map.Entry<Integer,Price> entry : priceListMap.entrySet()){
            System.out.println(entry.getValue().toString());
        }
    }

    public static void main(String[] args) {
        updatePriceList();
        showMap();
    }
}
