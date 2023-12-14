/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.HoaDonChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Welcome
 */
public class HDCTService {

    public HoaDonChiTiet findById(int maHoaDonChiTiet) {
        String sql = "SELECT * FROM HOADONCHITIET WHERE ID = " + maHoaDonChiTiet;
        try {
            Statement statement = DBcontext.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int idct = rs.getInt("id_chitietsanpham");
                int idhd = rs.getInt("id_hd");
                String tensp = rs.getString("tensp");
                String hang = rs.getString("hang");
                String kt = rs.getString("kichthuoc");
                String kd = rs.getString("kieudang");
                String xx = rs.getString("xuatxu");
                String mau = rs.getString("mau");
                int sl = rs.getInt("sl");
                float gia = rs.getFloat("gia");

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(id, idct, idhd, tensp, hang, kt, kd, xx, mau, sl, gia);
                return hoaDonChiTiet;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
     public void HoaDonChiTiet() {
        String sql = "SELECT * FROM HOADONCHITIET ";
        try {
            Statement statement = DBcontext.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int idct = rs.getInt("id_chitietsanpham");
                int idhd = rs.getInt("id_hd");
                String tensp = rs.getString("tensp");
                String hang = rs.getString("hang");
                String kt = rs.getString("kichthuoc");
                String kd = rs.getString("kieudang");
                String xx = rs.getString("xuatxu");
                String mau = rs.getString("mau");
                int sl = rs.getInt("sl");
                float gia = rs.getFloat("gia");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public HoaDonChiTiet findBy_MaHoaDon_MaSPCT(int maHoaDon, int maSPCT) {
        String sql = String.format("SELECT * FROM HOADONCHITIET WHERE id = %s AND id_chitietsanpham = %s", maHoaDon, maSPCT);
        try {
            Statement statement = DBcontext.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int idct = rs.getInt("id_chitietsanpham");
                int idhd = rs.getInt("id_hd");
                String tensp = rs.getString("tensp");
                String hang = rs.getString("hang");
                String kt = rs.getString("kichthuoc");
                String kd = rs.getString("kieudang");
                String xx = rs.getString("xuatxu");
                String mau = rs.getString("mau");
                int sl = rs.getInt("sl");
                float gia = rs.getFloat("gia");

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(id, idct, idhd, tensp, hang, kt, kd, xx, mau, sl, gia);
                return hoaDonChiTiet;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<HoaDonChiTiet> selectAll() {
        List<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM HOADONCHITIET";
        try {
            Statement st = new DBcontext().getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int idct = rs.getInt("id_chitietsanpham");
                int idhd = rs.getInt("id_hd");
                String tensp = rs.getString("tensp");
                String hang = rs.getString("hang");
                String kt = rs.getString("kichthuoc");
                String kd = rs.getString("kieudang");
                String xx = rs.getString("xuatxu");
                String mau = rs.getString("mau");
                int sl = rs.getInt("sl");
                float gia = rs.getFloat("gia");

                listHoaDonChiTiet.add(new HoaDonChiTiet(id, idct, idhd, tensp, hang, kt, kd, xx, mau, sl, gia));
            }
        } catch (Exception e) {
            System.out.println("HOADONCHITIET SERVICE ERROR SELECT ALL:" + e);
        }
        return listHoaDonChiTiet;
    }

    public ArrayList<HoaDonChiTiet> selectAllByMaHoaDon(int maHoaDon) {
        ArrayList<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
        String sql = String.format("SELECT * FROM HOADONCHITIET WHERE id_hd = %s", maHoaDon);
        try {
            Statement st = new DBcontext().getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int idct = rs.getInt("id_chitietsanpham");
                int idhd = rs.getInt("id_hd");
                String tensp = rs.getString("tensp");
                String hang = rs.getString("hang");
                String kt = rs.getString("kichthuoc");
                String kd = rs.getString("kieudang");
                String xx = rs.getString("xuatxu");
                String mau = rs.getString("mau");
                int sl = rs.getInt("sl");
                float gia = rs.getFloat("gia");

                listHoaDonChiTiet.add(new HoaDonChiTiet(id, idct, idhd, tensp, hang, kt, kd, xx, mau, sl, gia));
            }
        } catch (Exception e) {
            System.out.println("HOADONCHITIET SERVICE ERROR SELECT ALL:" + e);
        }
        return listHoaDonChiTiet;
    }

    public boolean addListOrder(int ID, List<HoaDonChiTiet> lists) {
        String query = """
                       insert into hoadonchitiet(id_chitietsanpham,id_hd,tensp,sl,gia) values (?,?,?,?,?)
                       """;

        int[] arr = {};
        try (Connection con = DBcontext.getConnection(); // mở kết nối đến DB
                 PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            if (ps != null) {
                for (HoaDonChiTiet hdct : lists) {
                    ps.setObject(1, hdct.getIdct());
                    ps.setObject(2, hdct.getIdhd());
                    ps.setObject(3, hdct.getTensp());
                    ps.setObject(4, hdct.getSl());
                    ps.setObject(5, hdct.getGia());
                    //batch update
                    ps.addBatch();
                }
                arr = ps.executeBatch();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return arr.length > 0;
    }

    public boolean add(HoaDonChiTiet hdct) {
        int check = 0;
        String sql = "insert into hoadonchitiet(id_chitietsanpham,id_hd,tensp,sl,gia) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = DBcontext.getConnection().prepareCall(sql);
            ps.setInt(1, hdct.getIdct());
            ps.setInt(2, hdct.getIdhd());
            ps.setString(3, hdct.getTensp());
            ps.setInt(4, hdct.getSl());
            ps.setFloat(5, hdct.getGia());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public String delete(int maHoaDonChiTiet) {
        Connection con = new DBcontext().getConnection();
        String sql = "DELETE FROM HOADONCHITIET WHERE id = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, maHoaDonChiTiet);
            int result = st.executeUpdate();
            if (result > 0) {
                return "Xóa Thành Công";
            }
            return "Xóa Thất Bại";
        } catch (Exception e) {
            if (e.toString().contains("FK")) {
                return "Xóa Lỗi: Tồn Tại Khóa Ngoại";
            }
            return "Xóa Lỗi: " + e;
        }
    }

    public String update(HoaDonChiTiet hoaDonChiTiet) {
        Connection con = new DBcontext().getConnection();
        String sql = "update hoadonchitiet set id_chitietsanpham = ?, id_hd = ?, tensp = ?, hang = ?, kichthuoc = ?, kieudang = ?, xuatxu=?, mau = ?, sl=?,gia=? where id = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, hoaDonChiTiet.getIdct());
            st.setInt(2, hoaDonChiTiet.getIdhd());
            st.setString(3, hoaDonChiTiet.getTensp());
            st.setString(4, hoaDonChiTiet.getHang());
            st.setString(5, hoaDonChiTiet.getKt());
            st.setString(6, hoaDonChiTiet.getKd());
            st.setString(7, hoaDonChiTiet.getXx());
            st.setString(8, hoaDonChiTiet.getMau());
            st.setInt(9, hoaDonChiTiet.getSl());
            st.setFloat(10, hoaDonChiTiet.getGia());
            st.setInt(11, hoaDonChiTiet.getId());

            int result = st.executeUpdate();
            if (result > 0) {
                return "Update Thành Công";
            }
            return "Update Thất Bại";
        } catch (Exception e) {
            return "Update Lỗi: " + e;
        }
    }

    public long showTongTien(int idhd) {
        long tong = 0;
        String sql = String.format("select hoadonchitiet.sl,gia from hoadonchitiet where id_hd = %s", idhd);
        try {
            Statement ps = DBcontext.getConnection().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()){
                int sl = rs.getInt("sl");
                float gia = rs.getFloat("gia");
                tong += sl*gia;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return tong;
    }
    
    public boolean updateSL(int masp, int sl){
        int check = 0;
        String sql = "update hoadonchitiet set sl = ? where id_chitietsanpham = ?";
        try {
            PreparedStatement ps = DBcontext.getConnection().prepareStatement(sql);
            ps.setInt(1, sl);
            ps.setInt(2, masp);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
}
