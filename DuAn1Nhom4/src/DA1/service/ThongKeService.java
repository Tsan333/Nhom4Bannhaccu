/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import java.util.ArrayList;
import DA1.model.ThongKe;
import java.sql.*;

/**
 *
 * @author Welcome
 */
public class ThongKeService {

    public ArrayList<ThongKe> getAll() {
        Connection cn = DBcontext.getConnection();
        ArrayList<ThongKe> list = new ArrayList<>();
        String sql = "select hd.NgayThanhToan, SUM(ct.sl*ct.gia) as 'DoanhThu' from HoaDonChiTiet ct\n"
                + "JOIN HoaDon hd \n"
                + "on ct.ID_HD=hd.ID\n"
                + "group by hd.NgayThanhToan";
        try {
            PreparedStatement ps = cn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThongKe(rs.getString("NgayThanhToan"), rs.getFloat("DoanhThu")));
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e);
        }
        return list;
    }

    public float doanhThu() {
        float dt = 0;
        Connection cn = DBcontext.getConnection();
        String sql = "select sum(hoadonchitiet.sl * hoadonchitiet.gia) as 'Doanh thu' from hoadonchitiet\n"
                + "JOIN hoadon on hoadonchitiet.id_hd = hoadon.id\n"
                + "where hoadon.trangthai = N'Đã thanh toán'";
        try {
            Statement ps = DBcontext.getConnection().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                dt = rs.getFloat("Doanh thu");
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e);
        }
        return dt;
    }

    public int soKhach() {
        int sk = 0;
        Connection cn = DBcontext.getConnection();
        String sql = "select count(id) - 1 as 'sokhach' from khachhang";
        try {
            Statement ps = DBcontext.getConnection().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                sk = rs.getInt("sokhach");
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e);
        }
        return sk;
    }

    public int soDon() {
        int sd = 0;
        Connection cn = DBcontext.getConnection();
        String sql = "select count(id) as 'sodon' from hoadon";
        try {
            Statement ps = DBcontext.getConnection().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                sd = rs.getInt("sodon");
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e);
        }
        return sd;
    }

    public int soDonDaXong() {
        int sdx = 0;
        Connection cn = DBcontext.getConnection();
        String sql = "select count(id) as 'sodon' from hoadon\n"
                + "where hoadon.trangthai = N'Đã thanh toán'";
        try {
            Statement ps = DBcontext.getConnection().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                sdx = rs.getInt("sodon");
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e);
        }
        return sdx;
    }
}
