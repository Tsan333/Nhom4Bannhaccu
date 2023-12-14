/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBconnext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author namtr
 */
public class DBconect {

    private static String user = "sa";
    private static String password = "123123123a";
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=DA_BanGuita2;\n"
            + "encrypt=true;trustServerCertificate=true;";

    //Chạy khối mã trong static trước khi chạy cod trong hàm main
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Lỗi Driver");
            ex.printStackTrace();
        }
    }

    public static Connection getConnnetion() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Lỗi liên kết ko thành công");
            ex.printStackTrace();
        }
        return cn;
    }

    public static void main(String[] args) {
        Connection cn = getConnnetion();
        if (cn != null) {
            System.out.println("Kết nối DB thành công");
        } else {
            System.out.println("Lỗi liên kết ko thành công");
        }
    }
}
