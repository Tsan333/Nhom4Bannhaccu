/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.MauSac;
import DA1.model.NhaSanXuat;
import DA1.model.XuatXu;
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
public class XuatXuService {
    public static ArrayList<XuatXu> selectTblThuocTinh() {
        ArrayList<XuatXu> listXX = new ArrayList<>();
        String sql = "SELECT * FROM xuatxu WHERE xoa = 1"; // Sửa câu lệnh SQL ở đây
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String XuatXu = rs.getString("TenXuatXu");
                listXX.add(new XuatXu(ID, XuatXu));
            }
        } catch (Exception e) {
            System.out.println("Lỗi phần bảng thuộc tính thương hiệu:" + e);
        }
        return listXX;
    }
    
public static ArrayList<XuatXu> selectTblThungRacThuoTinh() {
        ArrayList<XuatXu> listXX = new ArrayList<>();
        String sql = "SELECT * FROM xuatxu WHERE xoa = '0'";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String TenMau = rs.getString("TenXuatXu");
                listXX.add(new XuatXu(ID, TenMau));
            }
        } catch (Exception e) {
            System.out.println("Lỗi: phần bảng thùng rác" + e);
        }
        return listXX;
    }

    public static String add(String XuatXu) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBconect.getConnnetion()) {
            String sql = "INSERT INTO xuatxu(TenXuatXu,xoa) VALUES (?,1)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, XuatXu);
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi phần add: " + e;
        }
        return resultMessage;
    }
    
    public static String returnItem(Integer ID_XX) {
    Connection con = DBconect.getConnnetion();
    String sql = "UPDATE xuatxu SET xoa = 1 WHERE Id = ?";
    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, ID_XX);
        st.executeUpdate();
        con.commit();

        return "Trả lại thành công";
    } catch (Exception e) {
        return "Trả lại lỗi: " + e;
    }
}
    
    public static String DayVaoThungRac(Integer ID_XX) {
    Connection con = DBconect.getConnnetion();
    String sql = "UPDATE xuatxu SET xoa = 0 WHERE Id = ?";
    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, ID_XX);
        st.executeUpdate();
        con.commit();

        return "Xóa thành công";
    } catch (Exception e) {
        return "Xóa lỗi: " + e;
    }
}
    
    public static String delete(Integer ID_XX) {
    Connection con = DBconect.getConnnetion();
    String sql = "DELETE FROM xuatxu WHERE Id = ?";

    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, ID_XX);
        st.executeUpdate();
        con.commit();

        return "Xóa thành công";
    } catch (Exception e) {
        return "Xóa lỗi: " + e;
    }
}

 public static String update(XuatXu xx) {
        Connection con = DBconect.getConnnetion();
        String sql = "UPDATE THUONGHIEU SET tenThuongHieu = ? WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, xx.getTenXuatXu());
            st.setInt(2, xx.getId());
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
