/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sviest;

import NhanVien.NhanVien;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author admin
 */
public class Svietst {
    public ArrayList<NhanVien> GETALL(){
     ArrayList<NhanVien> list = new ArrayList<>();
     String sql = "select * from NHANVIEN";
     Connection cn = DBconect.DBconect.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("ID"));
                nv.setTenNV(rs.getString("TenNhanVien"));
                nv.setNgaySinh(rs.getInt("NgaySinh"));
                nv.setSDT(rs.getInt("SoDienThoai"));
                nv.setSCCCD(rs.getString("SoCCCD"));
                nv.setTaoBoi(rs.getString("TaoBoi"));
                list.add(nv);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
