/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBcontext {

    public static String USER = "sa";
    public static String PASSWORD = "123456789";
    public static String URL = "jdbc:sqlserver://localhost:1433;databaseName=DA_BanGuita2;encrypt=false";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Loi "+ex);
        }
    }

    public static Connection getConnection() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Loi "+ex);
        }
        return cn;
    }

    public static void main(String[] args) {
        Connection  cn = getConnection();
        if (cn!=null) {
            System.out.println("Kết nối thành công");
            
        } else {
            System.out.println("Lỗi kết nối");
        }
    }

}
