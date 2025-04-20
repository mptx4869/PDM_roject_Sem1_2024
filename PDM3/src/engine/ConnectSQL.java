/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package engine;

/**
 *
 * @author dangn
 */
import net.proteanit.sql.DbUtils;
import javax.swing.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ConnectSQL {
    static final String url = "jdbc:mysql://localhost:3306/Invoice_Bill"; 
    static final String user = "root"; 
    static final String password = "Lphh2801$";
    static  Connection con = null;
    static  ResultSet rs = null ; 
    static  PreparedStatement stmt = null ;
    
    public static void setUpConnection(){
        if(con == null)
            try {
                con = DriverManager.getConnection(url,user,password);
                System.out.println("Connect Database success");
            } catch (SQLException e) {
                System.out.println("Connect Database fail :"+ e.getMessage());
            }
    }
    public static void closeConnect(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection");
            }
        }
    }
    public static void closeStatement(Statement stmt ){
        if (stmt != null)
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("ERROR closing statement : " + stmt);
            }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("ERROR closing ResultSet : ");
            }
    }

    public static void closeAllResource(){
        closeStatement(stmt);
        closeConnect(con);
        closeResultSet(rs);
    }

// Excute Query method-----------------------------------------
    public static ResultSet excuteQuery(String query){
        

        try {
            setUpConnection();
            stmt = con.prepareStatement(query);
            System.out.println(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
        }

        return rs;
    }

    public static ResultSet excuteQuery(PreparedStatement pStatement){
        try {
            return pStatement.executeQuery();
          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
        }

        return null;
    }

    public static Map<Integer, String> getProductIDName(){
        rs  = excuteQuery("Select Product_ID, Product_Name From Product");
        Map<Integer,String> map = new HashMap<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("Product_ID");
                String name = rs.getString("Product_Name");
                map.put(id, name);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    public static ResultSet selectInvoice(int customer_ID, Date date, BigDecimal Total_Amount){
        String query ;
        query = "SELECT * FROM Invoice WHERE Customer_ID = ? AND Date = ? AND Total_Amount = ?";
        try {
            setUpConnection();
            stmt = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, customer_ID);
            stmt.setDate(2, date);
            stmt.setBigDecimal(3, Total_Amount);
            return stmt.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
        
    }
    public static Product getProductByID (int ID ){
        rs = excuteQuery("Select * From product WHERE Product_ID = "+ID );
        Product product = null;
        try {
            if(rs.next() ){
                product = new Product(rs.getInt("Product_ID"), 
                rs.getString("Product_Name"), 
                rs.getString("Category"), 
                rs.getString("BarCode"),
                rs.getBigDecimal("Price"));
            }
            return product;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public static ResultSet getAllPrice(){
        return excuteQuery("Select * From Price_List ");
    }
    
// excute update method ---------------------------------------------
    public static boolean update(PreparedStatement pStatement){
        try {
            
            if ( pStatement.executeUpdate() >0 ){
                
                return true;
            }else{
                
                return false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateProduct( Product product, boolean isUpdatePrice){
        String query = "Update Product Set Product_Name=?,Category=?,BarCode=?,Price=?  where  Product_ID = ?";
        try {
            System.out.println("update Product");
            setUpConnection();
            System.out.println(con.toString());
            stmt = con.prepareStatement(query);
            
            stmt.setString(1, product.getProduct_name());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getBarCode());
            stmt.setBigDecimal(4, product.getPrice());
            stmt.setInt(5, product.getId());
            boolean isSuccess =update(stmt);
            if(isSuccess && isUpdatePrice){
                    isSuccess = insertPrice(product.getId(), product.getPrice(), Date.valueOf(LocalDate.now()));
            }
            if(isSuccess){
                JOptionPane.showMessageDialog(null, "update success");
            }else
                JOptionPane.showMessageDialog(null, "update fail");
            return isSuccess;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    public static int isExitProdct(Product product){
        rs = excuteQuery("Select Product_ID From product Where Product_Name = '"+ product.getProduct_name() + 
                    "' AND Category= '"+product.getCategory()+
                    "' AND BarCode='"+product.getBarCode()+
                    "' AND Price="+product.getPrice());

        int i =0;
        try {
            while (rs.next()) {
                i=rs.getInt("Product_ID");                    
            }
            System.out.println(i);
            return i;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return i;
    }
    public static boolean insertProduct(Product product){
        try {
            System.out.println("insert Product");
            String query = "INSERT INTO Product (Product_Name, Category, BarCode, Price) VALUES (?,?,?,?)";
            int i=isExitProdct(product);
            if(i > 1){
                JOptionPane.showMessageDialog(null, "the Product has been exit");
                //System.out.println("1111111111111111111");
                return false;
            }

            setUpConnection();
            stmt = con.prepareStatement(query);
        
            stmt.setString(1, product.getProduct_name());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getBarCode());
            stmt.setBigDecimal(4, product.getPrice());
            if (update(stmt)){
                i = isExitProdct(product);
                if(insertPrice(i, product.getPrice(), Date.valueOf(LocalDate.now()))){
                    JOptionPane.showMessageDialog(null, "insert Success");
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null, "insert Fail");
                }
            }else{
                JOptionPane.showMessageDialog(null, "insert Fail");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    // public static ResultSet insertInvoice(int customer_ID, long totalAmount){
    //     int isSuccess =0;
    //     Date date = Date.valueOf(LocalDate.now());
    //     BigDecimal ttamount = new BigDecimal(totalAmount);
    //     try {
    //         setUpConnection();
    //         stmt = con.prepareStatement("INSERT INTO Invoice (Customer_ID, Date, Total_Amount) VALUES(?,?,?)");
    //         stmt.setInt(1, customer_ID);
    //         stmt.setDate(2, Date.valueOf(LocalDate.now()));
    //         stmt.setBigDecimal(3, ttamount);
    //         System.out.println();
    //         isSuccess = stmt.executeUpdate();
    //     } catch (SQLException e) {
    //         JOptionPane.showMessageDialog(
    //                 null, "The database return error: "
    //                 + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
    //     }
    //     if(isSuccess > 0 )
    //         return selectInvoice(customer_ID, date, ttamount);
    //     else    
    //         return null;

    // }
    
    public static boolean insertPrice(int productID, BigDecimal price, Date dateEffective){
        int isSuccess =0;
        try {
            System.out.println("insert price");
            setUpConnection();
            stmt = con.prepareStatement("INSERT INTO Price_List (Product_ID, Price, Date_Effective)VALUES(?,?,?)");
            stmt.setInt(1, productID);
            stmt.setDate(3,dateEffective );
            stmt.setBigDecimal(2, price);
            System.out.println();
            isSuccess = stmt.executeUpdate();
            return isSuccess > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
    }


// test the connection 
    public static void main(String[] args) {
        
    }
    
}
