/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.KichThuoc;
import DA1.model.SanPham;
import DA1.service.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author namtr
 */
public class KichThuocServiec {

   public static ArrayList<KichThuoc> selectTblThuoTinh() {
        ArrayList<KichThuoc> listKichThuoc = new ArrayList<>();
        String sql = "SELECT * FROM kichthuoc where xoa = 1"; // Sửa câu lệnh SQL ở đây
        try {
            Statement st = DBcontext.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("id");
                String TenMau = rs.getString("kichthuoc");
                listKichThuoc.add(new KichThuoc(ID, TenMau));
                System.out.println("Dẫ thêm vào bảng thuoc tính kích thước");
            }
        } catch (Exception e) {
            System.out.println("Lỗi phần bảng thuộc tính kích thước:" + e);
        }
        return listKichThuoc;
    }

    public static ArrayList<KichThuoc> selectTblThungRacThuoTinh() {
        ArrayList<KichThuoc> listKichThuoc = new ArrayList<>();
        String sql = "SELECT * FROM kichthuoc WHERE xoa = 0 ";
        try {
            Statement st = DBcontext.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("id");
                String TenMau = rs.getString("kichthuoc");
                listKichThuoc.add(new KichThuoc(ID, TenMau));
                System.out.println("Dẫ thêm vào bảng thuoc tính kích thước");
            }
        } catch (Exception e) {
            System.out.println("Lỗi: phần bảng thùng rác Lỗi phần bảng thuộc tính kích thước" + e);
        }
        return listKichThuoc;
    }

    public static String add(String KichThuoc) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBcontext.getConnection()) {
            String sql = "INSERT INTO KichThuoc (KichThuoc) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, KichThuoc);
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi: " + e;
        }
        return resultMessage;
    }

    public static String delete(String IDKichThuoc) {
        Connection con = DBcontext.getConnection();
        String sql = "DELETE FROM KichThuoc WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDKichThuoc);
            int result = st.executeUpdate();
            if (result > 0) {
                return "Xoa Thanh Cong";
            }
            return "Xoa That Bai";
        } catch (Exception e) {
            return "Xoa Loi: " + e;
        }
    }

    public static String update(KichThuoc KichThuoc) {
        Connection con = DBcontext.getConnection();
        String sql = "UPDATE KichThuoc SET KichThuoc = ? WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, KichThuoc.getKichThuoc());
            st.setInt(2, KichThuoc.getID());
            int result = st.executeUpdate();
            if (result > 0) {
                return "Cap Nhat Thanh Cong";
            }
            return "Cap Nhat That Bai";
        } catch (Exception e) {
            return "Cap Nhat Loi: " + e;
        }
    }
}
