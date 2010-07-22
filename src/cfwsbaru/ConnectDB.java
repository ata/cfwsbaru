/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cfwsbaru;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.*;

public class ConnectDB {
    
    public static void main (String [] args) throws Exception {
        ConnectDB db = new ConnectDB();
    }
    
    public ConnectDB () throws Exception {
        insertFile();
    }
    
    public Connection getConnection() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/skripsi_cfws";
        String username = "root";
        String password = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    
    private void insertFile() throws Exception {
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = null;
        File f = new File ("src/cfwsbaru/frequent/");
        File files[] = f.listFiles();
        for (File fi : files) {
            FileInputStream fis = new FileInputStream (fi);
            pstmt = conn.prepareStatement("insert into cluster_candidate(name_file, file) values (?, ?)");
            pstmt.setString(1, (String) fi.getName());
            pstmt.setAsciiStream(2, fis, (int) fi.length());
            pstmt.executeUpdate();
            conn.commit();
        }
    }

}
