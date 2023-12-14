/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.DangDan;
import DA1.model.KichThuoc;
import DA1.model.DangDan;
import DA1.service.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author namtr
 */
public class DangDanService {
     public static ArrayList<DangDan> selectTblThuoTinh() {
        ArrayList<DangDan> listDangDan = new ArrayList<>();
        String sql = "SELECT * FROM dangdan where xoa = 1"; // Sửa câu lệnh SQL ở đây
        try {
            Statement st = DBcontext.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("id");
                String TenMau = rs.getString("dangdan");
                listDangDan.add(new DangDan(ID, TenMau));
                System.out.println("Dẫ thêm vào bảng thuoc tính dangdan");
            }
        } catch (Exception e) {
            System.out.println("Lỗi phần bảng thuộc tính dangdan:" + e);
        }
        return listDangDan;
    }

    public static ArrayList<DangDan> selectTblThungRacThuoTinh() {
        ArrayList<DangDan> listDangDan = new ArrayList<>();
        String sql = "SELECT * FROM dangdan WHERE xoa = 0 ";
        try {
            Statement st = DBcontext.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("id");
                String TenMau = rs.getString("dangdan");
                listDangDan.add(new DangDan(ID, TenMau));
                System.out.println("Dẫ thêm vào bảng thuoc tính dangdan");
            }
        } catch (Exception e) {
            System.out.println("Lỗi: phần bảng thùng rác Lỗi phần bảng thuộc tính dangdan" + e);
        }
        return listDangDan;
    }

    public static String add(String DangDan) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBcontext.getConnection()) {
            String sql = "INSERT INTO dangdan (dangdan) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, DangDan);
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi: " + e;
        }
        return resultMessage;
    }

    public static String delete(String ID_DangDan) {
        Connection con = DBcontext.getConnection();
        String sql = "DELETE FROM dangdan WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ID_DangDan);
            int result = st.executeUpdate();
            if (result > 0) {
                return "Xoa Thanh Cong";
            }
            return "Xoa That Bai";
        } catch (Exception e) {
            return "Xoa Loi: " + e;
        }
    }

    public static String update(DangDan dangDan) {
        Connection con = DBcontext.getConnection();
        String sql = "UPDATE dangdan SET dangdan = ? WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, dangDan.getDangDan());
            st.setInt(2, dangDan.getID());
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
