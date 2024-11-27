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
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ConnectSQL {
    static final String url = "jdbc:mysql://localhost:3306/Invoice_Bill"; 
    static final String user = "NhanHoa"; 
    static final String password = "1234";
    static  Connection con = null;
    static  ResultSet rs = null ; 
    static  PreparedStatement stmt = null ;
    
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

    // Excute Query method
    public static ResultSet excuteQuery(String query){
        

        try {
            con = DriverManager.getConnection(url,user,password);
            if(con.isValid(5) && con != null) System.out.println(" ket noi thanh cong");
            System.out.println(con.toString());

            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return rs;
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
        // for (Map.Entry<Integer, String> entry : map.entrySet()) 
        // { System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue()); }
        return map;
    }
    // excute update method 

    public static boolean insert(String query){
        try {
            con = DriverManager.getConnection(url,user,password);
            System.out.println(con.toString());
            
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    public static boolean excuteUpdate(String query ){
        Connection con = null;
        ResultSet rs = null ; 
        PreparedStatement stmt = null ;

        try {
            con = DriverManager.getConnection(url,user,password);
            stmt = con.prepareStatement(query);
           // int isSuccess = stmt.excuteUpdate();
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
        } finally {
            closeConnect(con);
            closeStatement(stmt);
        }

        return true;
    }

// test the connection 
    public static void main(String[] args) {
        ResultSet rs= excuteQuery("Select * From customer");
       getProductIDName();
    }
    
}
