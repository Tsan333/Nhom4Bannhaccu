/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

/**
 *
 * @author namtr
 */
public class SanPhamChiTiet {
    private String ID;
    private String ID_SanPham;
    private String TenSanPham;
    private String HinhAnh;
    private String NXS;
    private String GiaBan;
    private String KichCo;
    private String MauSac;
    private int SoLuong;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(String ID, String TenSanPham, String HinhAnh, String NXS, String GiaBan, String KichCo, String MauSac, int SoLuong) {
        this.ID = ID;
        this.TenSanPham = TenSanPham;
        this.HinhAnh = HinhAnh;
        this.NXS = NXS;
        this.GiaBan = GiaBan;
        this.KichCo = KichCo;
        this.MauSac = MauSac;
        this.SoLuong = SoLuong;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getNXS() {
        return NXS;
    }

    public void setNXS(String NXS) {
        this.NXS = NXS;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(String GiaBan) {
        this.GiaBan = GiaBan;
    }

    public String getKichCo() {
        return KichCo;
    }

    public void setKichCo(String KichCo) {
        this.KichCo = KichCo;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    
}
