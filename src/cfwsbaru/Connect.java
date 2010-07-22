/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ata
 */
public class Connect {
    private static Connection connection = null;
    private static Connect instance = null;
    public static Connection getConnection(){
        if(connection == null){
            try {
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost/skripsi_cfws";
                String username = "root";
                String password = "root";
                try {
                    Class.forName(driver);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
                }
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }

    public static Connect getInstance(){
        if(instance == null){
            instance = new Connect();
        }
        return instance;
    }

    protected  Connection connection(){
        return Connect.getConnection();
    }

    

    public ResultSet query(String query){
        
        try {
            Statement statement = connection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public  int count(String table, String condition){
        int count = 0;
        try {
            ResultSet result = query("SELECT COUNT(*) FROM " + table 
                    + " WHERE " + condition);
            while (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int count(String table){
        return count(table, "1");
    }
    
}
