/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author admin
 */
public class NhanVien {

    private String MaNV;
    private String TenNV;
    private String NgaySinh;
    private String SDT;
    private String Email;
    private String SCCCD;
    private String IDVaiTro;
    private String TrangThai;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String TenNV, String NgaySinh, String SDT, String Email, String SCCCD, String IDVaiTro, String TrangThai) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.NgaySinh = NgaySinh;
        this.SDT = SDT;
        this.Email = Email;
        this.SCCCD = SCCCD;
        this.IDVaiTro = IDVaiTro;
        this.TrangThai = TrangThai;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSCCCD() {
        return SCCCD;
    }

    public void setSCCCD(String SCCCD) {
        this.SCCCD = SCCCD;
    }

    public String getIDVaiTro() {
        return IDVaiTro;
    }

    public void setIDVaiTro(String IDVaiTro) {
        this.IDVaiTro = IDVaiTro;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
