/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;
import DA1.model.NhanVien;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.locks.StampedLock;
import java.sql.Statement;
import java.text.SimpleDateFormat;
/**
 *
 * @author admin
 */

   
public class Svietst {
    public ArrayList<NhanVien> GETALL() {
    ArrayList<NhanVien> list = new ArrayList<>();
    String sql = "select * from NHANVIEN JOIN VAITRO ON NHANVIEN.ID_VaiTro = VAITRO.ID ORDER BY \n" +
"    nhanvien.thoigiancapnhat DESC";
    
    try (Connection cn = DBcontext.getConnection();
         PreparedStatement pstm = cn.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery()) {

        while (rs.next()) {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getString("ID"));
            nv.setTenNV(rs.getString("TenNhanVien"));
            nv.setNgaySinh(rs.getString("NgaySinh"));
            nv.setSDT(rs.getString("SoDienThoai"));
            nv.setSCCCD(rs.getString("SoCCCD"));
            nv.setEmail(rs.getString("Email")); 
            nv.setIDVaiTro(rs.getString("VaiTro"));
            nv.setTrangThai(rs.getString("Trangthai"));
            list.add(nv);
        }
    } catch (SQLException e) {
        
    }
    return list;
    }
      public Integer ADD(NhanVien nv) {
        Integer row = null;
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "INSERT INTO NHANVIEN(ID_VaiTro,TenNhanVien,NgaySinh,SoDienThoai,Email,SoCCCD,Trangthai) VALUES (?,?,?,?,?,?,?)";
        Connection cn = DBcontext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, nv.getIDVaiTro());
            pstm.setString(2, nv.getTenNV());
             pstm.setString(3,nv.getNgaySinh());
            pstm.setString(4, nv.getSDT());
            pstm.setString(5, nv.getEmail());
            pstm.setString(6,nv.getSCCCD());
            pstm.setString(7,nv.getTrangThai());
            row = pstm.executeUpdate();
        } catch (Exception e){
        }
        return row;
    }
public Integer KhoiNghi(NhanVien nv){
     Integer row = null;
    String sql = "UPDATE NHANVIEN SET Trangthai = ? WHERE ID = ?;";
    Connection cn = DBcontext.getConnection();
    try {
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setString(1, nv.getTrangThai());     
        pstm.setString(2, nv.getMaNV());
        row = pstm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); 
//    } finally {
//       
//        try {
//            if (cn != null && !cn.isClosed()) {
//                cn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    return row;
}
      public Integer Update(NhanVien nv) {
    Integer row = null;
    String sql = "UPDATE NHANVIEN SET TenNhanVien = ?, NgaySinh = ?, SoCCCD = ?, SoDienThoai = ?, Email = ? , Trangthai = ? , id_vaitro = ? WHERE ID = ?;";
    Connection cn = DBcontext.getConnection();

    try {
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setString(1, nv.getTenNV());
        pstm.setString(2, nv.getNgaySinh());
        pstm.setString(3, nv.getSCCCD());
        pstm.setString(4, nv.getSDT());
        pstm.setString(5, nv.getEmail());
        pstm.setString(6, nv.getTrangThai());
        pstm.setString(8, nv.getMaNV());
        pstm.setString(7,nv.getIDVaiTro());
        pstm.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); 
//    } finally {
//       
//        try {
//            if (cn != null && !cn.isClosed()) {
//                cn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    }
    return row;
}
      
      public NhanVien timByMA(int ma){
          String sql = "select * from nhanvien where id =  " + ma;

        try {
            Statement statement = new DBcontext().getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
               int id = rs.getInt("id");
               int idvt = rs.getInt("id_vaitro");
               String ten = rs.getString("tennhanvien");
               Date ns = rs.getDate("ngaysinh");
               String sdt = rs.getString("sodienthoai");
               String email = rs.getString("email");
               String socccd = rs.getString("socccd");
               NhanVien nv = new NhanVien(String.valueOf(id), ten, String.valueOf((Date) ns), sdt, email, socccd, String.valueOf(idvt), ten);
                return nv;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
      }

//         public Integer TimKiemMA(String id){
//             Integer row = null;
//             ArrayList<NhanVien> list = new ArrayList<>();
//    String sql = "select * from NHANVIEN where ID = ? ";
//        Connection cn = DBconect.DBconect.getConnection();
//        try {
//            PreparedStatement pstm = cn.prepareStatement(sql);
//              pstm.setString(1, id);
//          row =  pstm.executeUpdate();
//    } catch (SQLException e) {
//        
//    }
//    return row;
//    
//    }
//                 public Integer TimKiem(String id){
//             Integer row = null;
//             ArrayList<NhanVien> list = new ArrayList<>();
//    String sql = "select * from NHANVIEN where V = ? ";
//        Connection cn = DBconect.DBconect.getConnection();
//        try {
//            PreparedStatement pstm = cn.prepareStatement(sql);
//              pstm.setString(1, id);
//          row =  pstm.executeUpdate();
//    } catch (SQLException e) {
//        
//    }
//    return row;
//    
//    }
         
         public int Xoa(String id) {
    int rowsAffected = 0;
    String sql = "DELETE FROM NHANVIEN WHERE ID = ?";
    try (Connection cn = DBcontext.getConnection();
         PreparedStatement pstm = cn.prepareStatement(sql)) {
        pstm.setString(1, id);
        rowsAffected = pstm.executeUpdate();

    } catch (SQLException e) {    
        e.printStackTrace(); 
    }
    return rowsAffected;
}

}     

