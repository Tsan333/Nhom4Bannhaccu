/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.service.DBcontext;
import java.util.ArrayList;
import DA1.model.HoaDon;
import DA1.model.HoaDonChiTiet;
import DA1.model.TimeLine;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author Welcome
 */
public class HoaDonService {

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection cn = DBcontext.getConnection();
        String sql = "select * from HOADON ORDER BY \n" +
"    hoadon.thoigiancapnhat DESC";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt("id"), rs.getInt("id_nhanvien"), rs.getInt("id_kh"), rs.getDate("ngaytao"), rs.getDate("ngaythanhtoan"), rs.getFloat("tongtien"), rs.getString("tennv"), rs.getString("tenkh"), rs.getString("sdt"), rs.getString("pttt"), rs.getString("trangthai")));
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        return list;
    }

    public HoaDon findById(int maHoaDon) {
        String sql = "SELECT * FROM HOADON WHERE id = " + maHoaDon;
        try {
            Statement statement = new DBcontext().getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int idnv = rs.getInt("id_nhanvien");
                int idkh = rs.getInt("id_kh");
                java.util.Date thoiGianTao = rs.getDate("ngaytao");
                java.util.Date thoiGianThanhToan = rs.getDate("ngaythanhtoan");
                float tong = rs.getFloat("tongtien");
                String tennv = rs.getString("tennv");
                String tenkh = rs.getString("tenkh");
                String sdt = rs.getString("sdt");
                String pttt = rs.getString("pttt");
                String tt = rs.getString("trangthai");

                HoaDon hoaDon = new HoaDon(id, idnv, idkh, thoiGianTao, thoiGianTao, tong, tennv, tenkh, sdt, pttt, tt);
                return hoaDon;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<HoaDon> selectAllByNV(int maNV) {
        ArrayList<HoaDon> listNV = new ArrayList<>();
        Connection cn = DBcontext.getConnection();
        String sql = "select hoadon.* from nhanvien\n"
                + "JOIN hoadon on nhanvien.id = hoadon.id_nhanvien\n"
                + "where nhanvien.id = ?;";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, maNV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listNV.add(new HoaDon(rs.getInt("id"), rs.getInt("id_nhanvien"), rs.getInt("id_kh"), rs.getDate("ngaytao"), rs.getDate("ngaythanhtoan"), rs.getFloat("tongtien"), rs.getString("tennv"), rs.getString("tenkh"), rs.getString("sdt"), rs.getString("pttt"), rs.getString("trangthai")));
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        return listNV;
    }

    public ArrayList<HoaDon> locHoaDon(String pttt, String tt) {
        ArrayList<HoaDon> listL = new ArrayList<>();
        Connection cn = DBcontext.getConnection();
        String sql = "select * from Hoadon where PTTT =? and TrangThai = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, pttt);
            ps.setString(2, tt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listL.add(new HoaDon(rs.getInt("id"), rs.getInt("id_nhanvien"), rs.getInt("id_kh"), rs.getDate("ngaytao"), rs.getDate("ngaythanhtoan"), rs.getFloat("tongtien"), rs.getString("tennv"), rs.getString("tenkh"), rs.getString("sdt"), rs.getString("pttt"), rs.getString("trangthai")));
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        return listL;
    }

    public ArrayList<HoaDonChiTiet> lienKetHoaDon(int id) {
        ArrayList<HoaDonChiTiet> listCT = new ArrayList<>();
        Connection cn = DBcontext.getConnection();
        String sql = "select * from hoadonchitiet\n"
                + "where hoadonchitiet.id_hd = ?";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listCT.add(new HoaDonChiTiet(rs.getInt("id"), rs.getInt("id_chitietsanpham"), rs.getInt("id_hd"), rs.getString("tensp"), rs.getString("hang"), rs.getString("kichthuoc"), rs.getString("kieudang"), rs.getString("xuatxu"), rs.getString("mau"), rs.getInt("sl"), rs.getFloat("gia")));
            }

        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        return listCT;
    }

    public ArrayList<TimeLine> lienKetTimeLine(int id) {
        ArrayList<TimeLine> listTL = new ArrayList<>();
        Connection cn = DBcontext.getConnection();
        String sql = String.format("select * from timeline where timeline.id_hd = %s", id);
        System.out.println(sql);

        try {
            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                listTL.add(new TimeLine(rs.getInt("id"), rs.getInt("id_hd"), rs.getDate("thoigian"), rs.getString("noidung")));
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        System.out.println(listTL);
        return listTL;
    }

    public boolean add(HoaDon hoaDon) {
        int check = 0;
        String sql = "insert into HoaDon(id_nhanvien,id_kh,ngaytao,ngaythanhtoan,tongtien,tennv,tenkh,sdt,pttt,trangthai) values (?,?,?,?,?,?,?,?,?,?)";
        try {Connection con = DBcontext.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setObject(1, hoaDon.getIdnv());
            st.setObject(2, hoaDon.getIdkh());
            st.setObject(3, new Date());
            st.setObject(4, new Date());
            st.setObject(5, hoaDon.getTongTien());
            st.setObject(6, hoaDon.getTennv());
            st.setObject(7, hoaDon.getTenkh());
            st.setObject(8, hoaDon.getSdt());
            st.setObject(9, hoaDon.getPttt());
            st.setObject(10, hoaDon.getTt());
            System.out.println("da tem");
            check = st.executeUpdate();
        } catch (Exception e) {
          e.printStackTrace(System.out);
        }
        return check>0;
    }
    
    public boolean updateHoaDon(int id, HoaDon hoaDon) {
        int check = 0;
        String query = """
                       UPDATE HOADON set id_nhanvien = ?, id_kh = ?, ngaytao = ?, ngaythanhtoan = ?, tongtien = ?, tennv = ?, tenkh = ?, sdt = ?, pttt = ?, trangthai = N'Đã thanh toán' where id = ?
                       """;
        try (Connection con = DBcontext.getConnection();
                PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, hoaDon.getIdnv());
            ps.setObject(2, hoaDon.getIdkh());
            ps.setObject(3, new Date());
            ps.setObject(4, new Date());
            ps.setObject(5, hoaDon.getTongTien());
            ps.setObject(6, hoaDon.getTennv());
            ps.setObject(7, hoaDon.getTenkh());
            ps.setObject(8, hoaDon.getSdt());
            ps.setObject(9, hoaDon.getPttt());
            ps.setObject(10, hoaDon.getId());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
   public ArrayList<HoaDon> getAllInBH() {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection cn = DBcontext.getConnection();
        String sql = "select * from hoadon where trangthai = N'Chờ thanh toán' ORDER BY thoigiantao DESC";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new HoaDon(rs.getInt("id"), rs.getInt("id_nhanvien"), rs.getInt("id_kh"), rs.getDate("ngaytao"), rs.getDate("ngaythanhtoan"), rs.getFloat("tongtien"), rs.getString("tennv"), rs.getString("tenkh"), rs.getString("sdt"), rs.getString("pttt"), rs.getString("trangthai")));
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }
        return list;
    }
   
   public boolean huy(int id) {
        int check = 0;
        String sql = "delete from hoadon where id = ?";
        try {Connection con = DBcontext.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            System.out.println("da tem");

            check = st.executeUpdate();
            
        } catch (Exception e) {
          e.printStackTrace(System.out);
        }
        return check>0;
    }
}
