/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utility;

import static Utility.DbConnection.conn;
import static Utility.DbConnection.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thanh Tum
 */
public class DBConnection1 {
public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "DuAn1_QLDienThoai";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "123456";

   
    public static Connection getConnection() {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DBNAME + ";encrypt=true;trustservercertificate=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } // Handle any errors that may have occurred.
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static PreparedStatement getPS(String sql, Object...arg){
    
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < arg.length; i++) {
                ps.setObject(i+1, arg[i]);               
            }
            return ps;
        } catch (Exception ex) {
            return null;
        }
        
    }
    
    public static ResultSet getDataFormQuery(String sql, Object...arg) throws SQLException{
        
        PreparedStatement ps = getPS(sql, arg);
        return ps.executeQuery();
        
    }
    
    public static int getRowDataForm(String sql, Object...arg){
    
        PreparedStatement ps = getPS(sql, arg);
        try {
            return ps.executeUpdate();
        } catch (Exception ex) {
            return 0;
        }
        
    }

    
    public static void main(String[] args) {
        getConnection();
    }
}
