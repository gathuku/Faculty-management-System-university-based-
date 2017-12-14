package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gathuku
 */
public class connect {

    public Connection conn;
   public PreparedStatement pst;
    public Statement st ;
    public ResultSet rs ;

    public void connectdb() {

        String dbname = "faculty_system";
        String username = "root";
        String password = "";
        String Driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";

        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(url + dbname, username, password);
          JOptionPane.showMessageDialog(null,"Connection Successfull");

         

        } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());
           
        }

    }

}
