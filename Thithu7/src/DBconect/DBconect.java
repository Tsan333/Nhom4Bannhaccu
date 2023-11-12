/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBconect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DBconect {
    public static String USER = "sa";
    public static String PASS = "123456789";
    public static String URL = "jdbc:sqlserver://localhost:1433;databaseName=DA_BanGhita";
    static{
        try {
            Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBconect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static Connection getConnection(){
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException ex) {
            Logger.getLogger(DBconect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    } 
    public static void main(String[] args) {
        Connection cn = getConnection();
        if(cn != null){
            System.out.println("thanh cong");
        }else{
            System.out.println("that bai");
        }
    }
}
