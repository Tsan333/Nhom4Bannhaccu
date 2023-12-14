/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.MauSac;
import DA1.service.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author namtr
 */
public class MauSacService {

    public static List<MauSac> selectTblThuoTinh() {
        List<MauSac> listMauSac = new ArrayList<>();
        String sql = "SELECT * FROM mausac WHERE xoa = '1'"; // Sửa câu lệnh SQL ở đây
        try {
            Statement st = DBcontext.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("id");
                String TenMau = rs.getString("tenmau");
                listMauSac.add(new MauSac(ID, TenMau));
            }
        } catch (Exception e) {
            System.out.println("Lỗi phần bảng thuộc tính:" + e);
        }
        return listMauSac;
    }

    public static List<MauSac> selectTblThungRacThuoTinh() {
        List<MauSac> listMauSac = new ArrayList<>();
        String sql = "SELECT * FROM mausac WHERE xoa = '0'";
        try {
            Statement st = DBcontext.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("id");
                String TenMau = rs.getString("tenmau");
                listMauSac.add(new MauSac(ID, TenMau));
            }
        } catch (Exception e) {
            System.out.println("Lỗi: phần bảng thùng rác" + e);
        }
        return listMauSac;
    }

    public static String add(String MauSac) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBcontext.getConnection()) {
            String sql = "INSERT INTO mausac(TenMau,xoa) VALUES (?,1)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, MauSac);
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi phần add: " + e;
        }
        return resultMessage;
    }

   public static String returnItem(Integer IDMau) {
    Connection con = DBcontext.getConnection();
    String sql = "UPDATE mausac SET xoa = 1 WHERE Id = ?";

    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, IDMau);
        st.executeUpdate();
        con.commit();

        return "Trả lại thành công";
    } catch (Exception e) {
        return "Trả lại lỗi: " + e;
    }
}

   
   public static String GetToGaber(Integer IDMau) {
    Connection con = DBcontext.getConnection();
    String sql = "UPDATE mausac SET xoa = 0 WHERE Id = ?";

    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, IDMau);
        st.executeUpdate();
        con.commit();

        return "Xóa thành công";
    } catch (Exception e) {
        return "Xóa lỗi: " + e;
    }
}
   
    public static String delete(Integer IDMau) {
    Connection con = DBcontext.getConnection();
    String sql = "DELETE FROM mausac WHERE Id = ?";

    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, IDMau);
        st.executeUpdate();
        con.commit();

        return "Xóa thành công";
    } catch (Exception e) {
        return "Xóa lỗi: " + e;
    }
}

    public static String update(MauSac tt) {
        Connection con = DBcontext.getConnection();
        String sql = "UPDATE mausac SET tenmau = ? WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tt.getTenMau());
            st.setInt(2, tt.getId());
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
