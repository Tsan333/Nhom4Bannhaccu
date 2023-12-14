/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DA1.model.KhachHang;
import DA1.service.DBcontext;
import DA1.model.HoaDon;
import java.beans.Statement;

/**
 *
 * @author khanh
 */
public class KhachHangService {

    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> lst = new ArrayList<>();
        String sql = "select * from KHACHHANG ";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setID(rs.getInt("ID"));
                kh.setTen(rs.getString("Ten"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setEmail(rs.getString("Email"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setSDT(rs.getString("SDT"));
                lst.add(kh);
            }
        } catch (Exception ex) {
            System.out.println("GetallKK");
        }
        return lst;
    }

    public Integer DELETEKH(int ID) {
        Integer row = null;
        String sql = "delete hoadon where id_kh = ?;\n"
                + "Delete khachhang\n"
                + "where id = ?";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, ID);
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer update(KhachHang kh) {
        Integer row = null;
        String sql = "update KhachHang\n" + "set Ten=?\n , DiaChi=?\n, Email=?\n, GioiTinh=?\n, SDT=?\n" + "where ID=?\n";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, kh.getTen());
            pstm.setString(2, kh.getDiaChi());
            pstm.setString(3, kh.getEmail());
            pstm.setBoolean(4, kh.isGioiTinh());
            pstm.setString(5, kh.getSDT());
            pstm.setInt(6, kh.getID());
            row = pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return row;
    }

    public Integer addKhachHang(KhachHang kh) {
        Integer row = null;
        String sql = "insert into KhachHang (Ten, DiaChi, Email,GioiTinh,SDT)\n" + "values(?,?,?,?,?)";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, kh.getTen());
            pstm.setString(2, kh.getDiaChi());
            pstm.setString(3, kh.getEmail());
            pstm.setBoolean(4, kh.isGioiTinh());
            pstm.setString(5, kh.getSDT());

            row = pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return row;
    }

    public KhachHang findById(int ma) {
        String sql = "SELECT * FROM KHACHHANG WHERE ID = " + ma;
        try {
            
            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String dc = rs.getString("diachi");
                String em = rs.getString("email");
                boolean gt = rs.getBoolean("gioitinh");
                String  sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(id, ten, dc, em, gt, sdt);
                return kh;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<HoaDon> lienKetHoaDon(int id) {
        ArrayList<HoaDon> listHD = new ArrayList<>();
        Connection cn = DBcontext.getConnection();
        int i = 1;
        String sql = "select *  from HoaDon\n"
                + "JOin KhachHang ON HoaDon.id_kh= KhachHang.id\n"
                + "where KhachHang.id = ?";
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                listHD.add(new HoaDon(rs.getInt("id"),rs.getInt("id_nhanvien"), rs.getInt("id_kh"), rs.getDate("ngaytao"), rs.getDate("ngaythanhtoan"), rs.getFloat("tongtien"), rs.getString("tennv"), rs.getString("tenkh"), rs.getString("sdt"), rs.getString("pttt"), rs.getString("trangthai")));
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        return listHD;
    }

}
