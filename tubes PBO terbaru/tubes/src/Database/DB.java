/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Nasya Kirana M
 */
public class DB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/task";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "@Brownsugar4";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
    public DB() throws SQLException{
        try{
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            stmt = conn.createStatement();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,""+e.getMessage(),"Connection Error",
            JOptionPane.WARNING_MESSAGE);
            throw e;
        }
    }
    public ResultSet getData(String SQLString) throws Exception{
        try{
            rs = stmt.executeQuery(SQLString);
        }
        catch(Exception e){ 
        JOptionPane.showMessageDialog(null,"Error:"+e.getMessage(),
        "Communication Error",JOptionPane.WARNING_MESSAGE);
        throw e;
        }
        return rs;
    }
    public void query (String SQLString) throws Exception{
        try{
            stmt.executeUpdate(SQLString);
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error:"+e.getMessage(),
        "Communication Error", JOptionPane.WARNING_MESSAGE);
        throw e;
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
