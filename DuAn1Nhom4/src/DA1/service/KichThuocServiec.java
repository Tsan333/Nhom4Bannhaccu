/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.KichThuoc;
import DA1.model.SanPham;
import DBconnext.DBconect;
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
            Statement st = DBconect.getConnnetion().createStatement();
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
            Statement st = DBconect.getConnnetion().createStatement();
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
        try (Connection con = DBconect.getConnnetion()) {
            String sql = "INSERT INTO KichThuoc(KichThuoc,xoa) VALUES (?,1)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, KichThuoc);
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi phần add: " + e;
        }
        return resultMessage;
    }
    
    public static String returnItem(Integer ID_KT) {
    Connection con = DBconect.getConnnetion();
    String sql = "UPDATE KichThuoc SET xoa = 1 WHERE Id = ?";
    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, ID_KT);
        st.executeUpdate();
        con.commit();

        return "Trả lại thành công";
    } catch (Exception e) {
        return "Trả lại lỗi: " + e;
    }
}

    public static String DayVaoThungRac(Integer ID_KT) {
    Connection con = DBconect.getConnnetion();
    String sql = "UPDATE KichThuoc SET xoa = 0 WHERE Id = ?";
    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, ID_KT);
        st.executeUpdate();
        con.commit();

        return "Xóa thành công";
    } catch (Exception e) {
        return "Xóa lỗi: " + e;
    }
}
    
    
     public static String delete(Integer ID_DD) {
    Connection con = DBconect.getConnnetion();
    String sql = "DELETE FROM dangdan WHERE Id = ?";

    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, ID_DD);
        st.executeUpdate();
        con.commit();

        return "Xóa thành công";
    } catch (Exception e) {
        return "Xóa lỗi: " + e;
    }
}

 public static String update(KichThuoc kt) {
        Connection con = DBconect.getConnnetion();
        String sql = "UPDATE THUONGHIEU SET tenThuongHieu = ? WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, kt.getKichThuoc());
            st.setInt(2, kt.getID());
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
