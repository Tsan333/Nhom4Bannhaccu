/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.SanPham;
import DA1.model.SanPhamChiTiet;
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
public class SPCTService {

    public static List<SanPhamChiTiet> selectAll() {
        List<SanPhamChiTiet> SanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT  CHITIETSANPHAM.ID, CHITIETSANPHAM.ID_SANPHAM, SANPHAM.TenSanPham,CHITIETSANPHAM.GiaBan, XuatXu.TenXuatXu, TenThuongHieu, KichThuoc,MauSac.tenMau, CHITIETSANPHAM.SoLuong FROM ChiTietSanPham\n"
                + "            LEFT JOIN SANPHAM ON ChiTietSanPham.ID_SANPHAM = SANPHAM.ID\n"
                + "        LEFT JOIN HINHANH ON ChiTietSanPham.ID_HinhAnh = HINHANH.ID\n"
                + "               LEFT JOIN THUONGHIEU ON ChiTietSanPham.ID_ThuongHieu = THUONGHIEU.ID\n"
                + "             LEFT JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID\n"
                + "              LEFT JOIN KICHTHUOC ON ChiTietSanPham.ID_KICHTHUOC = KICHTHUOC.ID\n"
                + "             LEFT JOIN XUATXU ON ChiTietSanPham.ID_XuatXu = XUATXU.ID;";
        try {
            Statement st = new DBconnext.DBconect().getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                String NSX = rs.getString("TenThuongHieu");
                String XX = rs.getString("TenXuatXu");
                long GiaBan = rs.getLong("GiaBan");
                String kt = rs.getString("KichThuoc");
                String mau = rs.getString("TenMau");
                int SL = rs.getInt("SoLuong");
                SanPhamChiTiet.add(new SanPhamChiTiet(ID, null, null, null, null, null, TenSanPham, null, NSX, XX, GiaBan, kt, mau, SL));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy db cho sản phẩm chi tiết:" + e);
        }
        return SanPhamChiTiet;
    }

    public static List<SanPhamChiTiet> selectOne(String tenSanPham) {
        List<SanPhamChiTiet> SanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT  \n"
                + "    CHITIETSANPHAM.ID, \n"
                + "    CHITIETSANPHAM.ID_SANPHAM, \n"
                + "    SANPHAM.TenSanPham,\n"
                + "    CHITIETSANPHAM.GiaBan, \n"
                + "    XuatXu.TenXuatXu, \n"
                + "    TenThuongHieu, \n"
                + "    KichThuoc,\n"
                + "    MauSac.TenMau, \n"
                + "    CHITIETSANPHAM.SoLuong \n"
                + "FROM ChiTietSanPham\n"
                + "LEFT JOIN SANPHAM ON ChiTietSanPham.ID_SANPHAM = SANPHAM.ID\n"
                + "LEFT JOIN HINHANH ON ChiTietSanPham.ID_HinhAnh = HINHANH.ID\n"
                + "LEFT JOIN THUONGHIEU ON ChiTietSanPham.ID_ThuongHieu = THUONGHIEU.ID\n"
                + "LEFT JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID\n"
                + "LEFT JOIN KICHTHUOC ON ChiTietSanPham.ID_KICHTHUOC = KICHTHUOC.ID\n"
                + "LEFT JOIN XUATXU ON ChiTietSanPham.ID_XuatXu = XUATXU.ID\n"
                + "WHERE SANPHAM.TenSanPham = ?;";
        try {
            PreparedStatement st = new DBconnext.DBconect().getConnnetion().prepareStatement(sql);
            st.setString(1, tenSanPham);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                String NSX = rs.getString("TenThuongHieu");
                String XX = rs.getString("TenXuatXu");
                long GiaBan = rs.getLong("GiaBan");
                String kt = rs.getString("KichThuoc");
                String mau = rs.getString("MauSac");
                int SL = rs.getInt("SoLuong");
                SanPhamChiTiet.add(new SanPhamChiTiet(ID, null, null, null, null, null, TenSanPham, null, NSX, XX, GiaBan, kt, mau, SL));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy db cho sản phẩm chi tiết:" + e);
        }
        return SanPhamChiTiet;
    }

    public static String add(SanPhamChiTiet spct) {
        String resultMessage = "Thêm Thất Bại";
        try (Connection con = DBconect.getConnnetion()) {
            String sql = "INSERT INTO ChiTietSanPham (ID_SANPHAM,ID_ThuongHieu,ID_KICHTHUOC,ID_MauSac, GiaBan, SoLuong) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, spct.getID_SanPham());
            st.setString(2, spct.getID_ThuongHieu());
            st.setString(3, spct.getID_KichThuoc());
            st.setString(4, spct.getID_MauSac());
            st.setLong(5, spct.getGiaBan());
            st.setInt(6, spct.getSoLuong());
            int result = st.executeUpdate();
            if (result > 0) {
                resultMessage = "Thêm Thành Công";
            }
        } catch (Exception e) {
            resultMessage = "Thêm Lỗi: " + e;
        }
        return resultMessage;
    }
}
