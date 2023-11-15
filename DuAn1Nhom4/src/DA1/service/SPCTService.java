/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.SanPham;
import DA1.model.SanPhamChiTiet;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author namtr
 */
public class SPCTService {

    public static List<SanPhamChiTiet> selectAll() {
        List<SanPhamChiTiet> SanPhamChiTiet = new ArrayList<>();
        String sql = "SELECT ID,ID_SANPHAM,\n"
                + "FROM ChiTietSanPham\n"
                + "LEFT JOIN SANPHAM ON ChiTietSanPham.SanPhamID = SANPHAM.ID\n"
                + "LEFT JOIN HINHANH ON ChiTietSanPham.HinhAnhID = HINHANH.ID\n"
                + "LEFT JOIN THUONGHIEU ON ChiTietSanPham.ThuongHieuID = THUONGHIEU.ID\n"
                + "LEFT JOIN DAYDAN ON ChiTietSanPham.DayDanID = DAYDAN.ID\n"
                + "LEFT JOIN KICHTHUOC ON ChiTietSanPham.KichThuocID = KICHTHUOC.ID\n"
                + "LEFT JOIN XUATXU ON ChiTietSanPham.XuatXuID = XUATXU.ID;";
        try {
            Statement st = new DBconnext.DBconect().getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenSanPham = rs.getString("TenSanPham");
                long GiaBan = rs.getLong("GiaBan");
//                SanPhamChiTiet.add(new SanPham(ID, TenSanPham, GiaBan));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return SanPhamChiTiet;
    }
}
