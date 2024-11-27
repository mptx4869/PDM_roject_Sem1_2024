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

    static final String connectionUrl
            = "jdbc:sqlserver://localhost:1433;"
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

    

    public static void showQuery(String query, JTable resultTable) {
        System.out.println("Start showQuery method query = "+ query);
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println(con.toString());
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            System.out.println(rs.toString());
            if (!rs.next()) {
                
                JOptionPane.showMessageDialog(null, "Query return nothing! Please press clear all and do again!", "Message", JOptionPane.WARNING_MESSAGE);
            } else {
                //System.out.println(rs.getString("StudentName"));
                resultTable.setModel(DbUtils.resultSetToTableModel(rs));
                JOptionPane.showMessageDialog(null, "Run query successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "The database return error: "
                    + e.toString(), "Error", JOptionPane.WARNING_MESSAGE);
        } finally {
            closeConnect(con);
        }
        System.out.println("end showQuery method");
    }
}
