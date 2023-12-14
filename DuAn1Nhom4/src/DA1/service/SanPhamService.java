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
        String sql = "SELECT \n"
                + "SANPHAM.ID,\n"
                + "TenSanPham,\n"
                + "sanpham.thoigiancapnhat,\n"
                + "SUM(CHITIETSANPHAM.SoLuong) AS SoLuong\n"
                + "FROM \n"
                + "    SANPHAM\n"
                + "FULL JOIN \n"
                + "    CHITIETSANPHAM ON SANPHAM.ID = CHITIETSANPHAM.ID_SANPHAM\n"
                + "WHERE \n"
                + "    sanpham.xoa = 1\n"
                + "GROUP BY \n"
                + "    SANPHAM.ID,\n"
                + "    sanpham.thoigiancapnhat,\n"
                + "    TenSanPham\n"
                + "ORDER BY \n"
                + "    sanpham.thoigiancapnhat DESC;";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                int soLuong = (int) rs.getLong("SoLuong");
                listSanPham.add(new SanPham(ID, TenSanPham, soLuong));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listSanPham;
    }

    public static List<SanPham> DataThungRacSanPham() {
        List<SanPham> listSanPham = new ArrayList<>();
        String sql = "SELECT \n"
                + "SANPHAM.ID,\n"
                + "TenSanPham,\n"
                + "sanpham.thoigiancapnhat,\n"
                + "SUM(CHITIETSANPHAM.SoLuong) AS SoLuong\n"
                + "FROM \n"
                + "    SANPHAM\n"
                + "FULL JOIN \n"
                + "    CHITIETSANPHAM ON SANPHAM.ID = CHITIETSANPHAM.ID_SANPHAM\n"
                + "WHERE \n"
                + "    sanpham.xoa = 0\n"
                + "GROUP BY \n"
                + "    SANPHAM.ID,\n"
                + "    sanpham.thoigiancapnhat,\n"
                + "    TenSanPham\n"
                + "ORDER BY \n"
                + "    sanpham.thoigiancapnhat DESC;";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                int soLuong = (int) rs.getLong("SoLuong");
                listSanPham.add(new SanPham(ID, TenSanPham, soLuong));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listSanPham;
    }

    public static String add(SanPham sanPham) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBconect.getConnnetion()) {
            String sql = "INSERT INTO SANPHAM (TenSanPham,taoboi,xoa) VALUES (? ,?,1 )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sanPham.getTenSP());
            st.setInt(2, sanPham.getId_NhanVien());
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi: " + e;
        }
        return resultMessage;
    }

    public static String delete(int IDSanPham) {
        Connection con = DBconect.getConnnetion();
        String sql = "DELETE FROM SANPHAM WHERE ID = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, (IDSanPham));
            int result = st.executeUpdate();
            if (result > 0) {
                return "Xoa Thanh Cong";
            }
            return "Xoa That Bai";
        } catch (Exception e) {
            return "Xoa Loi: " + e;
        }
    }

    public static String GetToGaber(Integer IDSanPham) {
        Connection con = DBconect.getConnnetion();
        String sql = "UPDATE SANPHAM SET xoa = 0 WHERE Id = ?";

        try {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, IDSanPham);
            st.executeUpdate();
            con.commit();

            return "Xóa thành công";
        } catch (Exception e) {
            return "Xóa lỗi: " + e;
        }
    }
    
    public static String returnItem(Integer IDSanPham) {
    Connection con = DBconect.getConnnetion();
    String sql = "UPDATE SANPHAM SET xoa = 1 WHERE Id = ?";

    try {
        con.setAutoCommit(false);
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, IDSanPham);
        st.executeUpdate();
        con.commit();

        return "Trả lại thành công";
    } catch (Exception e) {
        return "Trả lại lỗi: " + e;
    }
}

    public String update(SanPham sanPham) {
        Connection con = DBconect.getConnnetion();
        String sql = "UPDATE SANPHAM \n"
                + "SET TenSanPham = ?, \n"
                + "    capnhatboi = ?, \n"
                + "    thoigiancapnhat = GETDATE() \n"
                + "WHERE ID = ?;";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sanPham.getTenSP());
            st.setInt(2, sanPham.getId_NhanVien());
            st.setString(3, sanPham.getID());
            int result = st.executeUpdate();
            if (result > 0) {
                return "Cap Nhat Thanh Cong";
            }
            return "Cap Nhat That Bai";
        } catch (Exception e) {
            return "Cap Nhat Loi: " + e;
        }
    }

//    public static List<SanPham> search(String keyword) {
//    List<SanPham> listSanPham = new ArrayList<>();
//    String sql = "SELECT ID, TenSanPham, SUM(CHITIETSANPHAM.SoLuong) as SoLuong\n"
//            + "FROM SANPHAM \n"
//            + "JOIN CHITIETSANPHAM \n"
//            + "ON SANPHAM.ID = CHITIETSANPHAM.ID_SANPHAM\n"
//            + "WHERE LOWER(SANPHAM.ID) LIKE ? OR LOWER(TenSanPham) LIKE ?\n"
//            + "GROUP BY SANPHAM.ID, TenSanPham";
//    try {
//        PreparedStatement st = DBconect.getConnnetion().prepareStatement(sql);
//        st.setString(1, "%" + keyword.toLowerCase() + "%");
//        st.setString(2, "%" + keyword.toLowerCase() + "%");
//        ResultSet rs = st.executeQuery();
//        while (rs.next()) {
//            String ID = rs.getString("ID");
//            String TenSanPham = rs.getString("TenSanPham");
//            int soLuong = (int) rs.getLong("SoLuong");
//            listSanPham.add(new SanPham(ID, TenSanPham, soLuong));
//        }
//    } catch (Exception e) {
//        System.out.println("Lỗi:" + e);
//    }
//    return listSanPham;
//}
}
