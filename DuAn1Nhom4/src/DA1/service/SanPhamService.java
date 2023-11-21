/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.SanPham;
import DBconnext.DBconect;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author namtr
 */
public class SanPhamService {

    public static List<SanPham> selectAll() {
        List<SanPham> listSanPham = new ArrayList<>();
        String sql = "SELECT CONCAT('SP', CAST(ID AS varchar)) AS ID, TenSanPham, GiaBan FROM SANPHAM";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                long GiaBan = rs.getLong("GiaBan");
                listSanPham.add(new SanPham(ID, TenSanPham));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listSanPham;
    }

    public String add(SanPham sanPham) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBconect.getConnnetion()) {
            String sql = "INSERT INTO SANPHAM (TenSanPham) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sanPham.getTenSP());
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi: " + e;
        }
        return resultMessage;
    }

    public static String delete(String IDSanPham) {
        Connection con = DBconect.getConnnetion();
        String sql = "DELETE FROM SANPHAM WHERE ID = ?";
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

    public String update(SanPham sanPham) {
        Connection con = DBconect.getConnnetion();
        String sql = "UPDATE SANPHAM SET TenSanPham = ? WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sanPham.getTenSP());
            st.setString(2, sanPham.getID());
            int result = st.executeUpdate();
            if (result > 0) {
                return "Cap Nhat Thanh Cong";
            }
            return "Cap Nhat That Bai";
        } catch (Exception e) {
            return "Cap Nhat Loi: " + e;
        }
    }

    public static List<SanPham> search(String keyword) {
        List<SanPham> listSanPham = new ArrayList<>();
        String sql = "SELECT CONCAT('SP', CAST(ID AS varchar)) AS ID, TenSanPham, GiaBan, TenSanPham, GiaBan FROM SANPHAM WHERE LOWER(ID) LIKE ? OR LOWER(TenSanPham) LIKE ?";
        try {
            PreparedStatement st = DBconect.getConnnetion().prepareStatement(sql);
            st.setString(1, "%" + keyword.toLowerCase() + "%");
            st.setString(2, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                long GiaBan = rs.getLong("GiaBan");
                listSanPham.add(new SanPham(ID, TenSanPham));
            }
        } catch (Exception e) {
            System.out.println("SANPHAM SERVICE ERROR SEARCH:" + e);
        }
        return listSanPham;
    }
}
