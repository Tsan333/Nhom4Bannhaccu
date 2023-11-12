/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien;

/**
 *
 * @author admin
 */
public class NhanVien {
   private String MaNV;
   private String TenNV;
   private int NgaySinh;
   private int SDT;
   private String Email;
   private String SCCCD;
   private int ThoiGianTao;
   private int ThoiGianCapNhat;
   private String TaoBoi;
   private String CapNhatBoi;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String TenNV, int NgaySinh, int SDT, String Email, String SCCCD, int ThoiGianTao, int ThoiGianCapNhat, String TaoBoi, String CapNhatBoi) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.NgaySinh = NgaySinh;
        this.SDT = SDT;
        this.Email = Email;
        this.SCCCD = SCCCD;
        this.ThoiGianTao = ThoiGianTao;
        this.ThoiGianCapNhat = ThoiGianCapNhat;
        this.TaoBoi = TaoBoi;
        this.CapNhatBoi = CapNhatBoi;
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

    public int getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(int NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
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

    public int getThoiGianTao() {
        return ThoiGianTao;
    }

    public void setThoiGianTao(int ThoiGianTao) {
        this.ThoiGianTao = ThoiGianTao;
    }

    public int getThoiGianCapNhat() {
        return ThoiGianCapNhat;
    }

    public void setThoiGianCapNhat(int ThoiGianCapNhat) {
        this.ThoiGianCapNhat = ThoiGianCapNhat;
    }

    public String getTaoBoi() {
        return TaoBoi;
    }

    public void setTaoBoi(String TaoBoi) {
        this.TaoBoi = TaoBoi;
    }

    public String getCapNhatBoi() {
        return CapNhatBoi;
    }

    public void setCapNhatBoi(String CapNhatBoi) {
        this.CapNhatBoi = CapNhatBoi;
    }
   
}
