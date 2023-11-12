/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.KhachHang;
import utility.DBContext;

/**
 *
 * @author khanh
 */
public class KhachHangService {
    
    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> lst = new ArrayList<>();
        String sql = "select * from [dbo].[KHACHHANG] ";
        Connection cn = DBContext.getConnection();
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setID(rs.getString("ID"));
                kh.setTen(rs.getString("TEN"));
                kh.setDiaChi(rs.getString("DIACHI"));
                kh.setEmail(rs.getString("EMAIL"));
                kh.setGioiTinh(rs.getBoolean("GIOITINH"));
                kh.setSDT(rs.getString("SDT"));
                lst.add(kh);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return lst;
    }
}
