/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdm_project_s12024;

/**
 *
 * @author dangn
 */
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.sql.*;

public class ConnectSQL {

    static final String connectionUrl = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Attendance;"
            + "user=NhanHoa;password=123;"
            + "encrypt=true;trustServerCertificate=true;";

    public static void closeConnect(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection");
            }
        }
    }

    public static String crerateURL(String port, String DBName, String userName, String pass  ){
        return
        "jdbc:sqlserver://localhost:"+port+";"
        + "databaseName="+ DBName+ ";"
        + "user="+ userName+ ";password="+pass+";"
        + "encrypt=true;trustServerCertificate=true;";

    }
}
