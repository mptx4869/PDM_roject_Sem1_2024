/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdmProject;
import java.sql.*;
/**
 *
 * @author ZBook
 */
public class SQLConnector {
    public static Connection getCon(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/invoice_bill","root","Lphh2801$");
                return con;
            } catch (Exception e) {
                return null;
            }
}
}
