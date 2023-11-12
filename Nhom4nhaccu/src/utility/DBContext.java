/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khanh
 */
public class DBContext {
      public static String USER_NAME = "sa";
    public static String PASSWORD = "khanh";
    public static String URL = "jdbc:sqlserver://localhost:1433;databaseName= DA_BanGhita;encrypt=true;trustServerCertificate=true;";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }

    public static void main(String[] args) {
        Connection cn = getConnection();
        if (cn != null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Lỗi kết nối");
        }
    }

}
