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

    public static List<KichThuoc> selectAll() {
        List<KichThuoc> listKichThuoc = new ArrayList<>();
        String sql = "SELECT * FROM KichThuoc";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String KichThuoc = rs.getString("KichThuoc");

                listKichThuoc.add(new KichThuoc(ID, KichThuoc));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listKichThuoc;
    }

    public static String add(String KichThuoc) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBconect.getConnnetion()) {
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
        Connection con = DBconect.getConnnetion();
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
        Connection con = DBconect.getConnnetion();
        String sql = "UPDATE KichThuoc SET KichThuoc = ? WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, KichThuoc.getKichThuoc());
            st.setString(2, KichThuoc.getID());
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
