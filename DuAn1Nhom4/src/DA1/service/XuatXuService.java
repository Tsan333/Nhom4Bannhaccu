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
    public static List<XuatXu> selectAll() {
        List<XuatXu> listXuatXu = new ArrayList<>();
        String sql = "SELECT * FROM XuatXu";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String XuatXu = rs.getString("TenXuatXu");

                listXuatXu.add(new XuatXu(ID, XuatXu));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listXuatXu;
    }
    
     public static String add(String XuatXu) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBconect.getConnnetion()) {
            String sql = "INSERT INTO xuatxu VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, XuatXu);
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi: " + e;
        }
        return resultMessage;
    }
     
     
     public static String update(XuatXu xx) {
        Connection con = DBconect.getConnnetion();
        String sql = "UPDATE xuatxu SET TenXuatXu = N'?' WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, xx.getTenXuatXu());
            st.setString(3, xx.getId());
            int result = st.executeUpdate();
            if (result > 0) {
                return "Cap Nhat Thanh Cong";
            }
            return "Cap Nhat That Bai";
        } catch (Exception e) {
            return "Cap Nhat Loi: " + e;
        }
    }
     
     public static String delete(String IDSanPham) {
        Connection con = DBconect.getConnnetion();
        String sql = "DELETE FROM XuatXu WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDSanPham);
            int result = st.executeUpdate();
            if (result > 0) {
                return "Xoa Thanh Cong";
            }
            return "Xoa That Bai";
        } catch (Exception e) {
            return "Xoa Loi: " + e;
        }
    }
}
