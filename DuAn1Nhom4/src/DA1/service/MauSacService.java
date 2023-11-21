/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;


import DA1.model.MauSac;
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
public class MauSacService {
    public static List<MauSac> selectAll() {
        List<MauSac> listMauSac = new ArrayList<>();
        String sql = "SELECT * FROM mausac";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenMau = rs.getString("TenMau");

                listMauSac.add(new MauSac(ID,TenMau));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listMauSac;
    }

    public static String add(String MauSac) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBconect.getConnnetion()) {
            String sql = "INSERT INTO mausac VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, MauSac);
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi: " + e;
        }
        return resultMessage;
    }

    public static String delete(String IDMau) {
        Connection con = DBconect.getConnnetion();
        String sql = "DELETE FROM mausac WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDMau);
            int result = st.executeUpdate();
            if (result > 0) {
                return "Xoa Thanh Cong";
            }
            return "Xoa That Bai";
        } catch (Exception e) {
            return "Xoa Loi: " + e;
        }
    }

    public static String update(MauSac MauSac) {
        Connection con = DBconect.getConnnetion();
        String sql = "UPDATE mausac SET tenmau = ? WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, MauSac.getTenMau());
            st.setString(3, MauSac.getId());
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
