/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.service;

import DA1.model.KichThuoc;
import DA1.model.MauSac;
import DA1.model.NhaSanXuat;
import DA1.model.ThuocTinhSanPham;
import DA1.model.XuatXu;
import DBconnext.DBconect;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author namtr
 */
public class ThuocTinhSanPhamService {

    // lấy db NSX
    public static List<ThuocTinhSanPham> selectAllNSX() {
        List<ThuocTinhSanPham> listNSX = new ArrayList<>();
        String sql = "SELECT * FROM Thuonghieu";

        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenNSX = rs.getString("TenThuongHieu");
                listNSX.add(new ThuocTinhSanPham(ID, TenNSX));
            }
        } catch (Exception e) {
            System.out.println("Lỗi phần NSX đẩy lên combobox" + e);
        }
        return listNSX;
    }

    // lấy db XUATXU
    public static List<ThuocTinhSanPham> selectAllXuatXu() {
        List<ThuocTinhSanPham> listXuatXu = new ArrayList<>();
        String sql = "SELECT * FROM XuatXu";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String XuatXu = rs.getString("TenXuatXu");

                listXuatXu.add(new ThuocTinhSanPham(ID, XuatXu));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy db XUATXU" + e);
        }
        return listXuatXu;
    }

    // lấy db kichco
    public static List<ThuocTinhSanPham> selectAllKichThuoc() {
        List<ThuocTinhSanPham> listKt = new ArrayList<>();
        String sql = "SELECT * FROM KichThuoc";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String KichThuoc = rs.getString("KichThuoc");

                listKt.add(new ThuocTinhSanPham(ID, KichThuoc));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listKt;
    }

    // lấy db thuocTinh
    public static List<ThuocTinhSanPham> selectAllThuocTinh() {
        List<ThuocTinhSanPham> listThuocTinh = new ArrayList<>();
        String sql = "SELECT ID, TenXuatXu  as N'ThuocTinh' FROM XuatXu\n"
                + "UNION \n"
                + "SELECT ID, TenThuongHieu FROM THUONGHIEU\n"
                + "UNION \n"
                + "SELECT ID, TenMau FROM MauSac\n"
                + "UNION \n"
                + "SELECT ID, KichThuoc FROM KichThuoc;";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String ThuocTinh = rs.getString("ThuocTinh");

                listThuocTinh.add(new ThuocTinhSanPham(ID, ThuocTinh));
            }
        } catch (Exception e) {
            System.out.println("Lỗi đẩy db thuộc tính lên:" + e);
        }
        return listThuocTinh;
    }
    
    // lấy db MauSac
    public static List<ThuocTinhSanPham> selectAllMauSac() {
        List<ThuocTinhSanPham> listMauSac = new ArrayList<>();
        String sql = "SELECT * FROM mausac";
        try {
            Statement st = DBconect.getConnnetion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("ID");
                String TenMau = rs.getString("TenMau");

                listMauSac.add(new ThuocTinhSanPham(ID,TenMau));
            }
        } catch (Exception e) {
            System.out.println("Lỗi:" + e);
        }
        return listMauSac;
    }

}
