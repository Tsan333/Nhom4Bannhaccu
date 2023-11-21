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
    private String ID_ThuongHieu;
    private String ID_KichThuoc;
    private String ID_MauSac;
    private String ID_XuatXu;
    private String TenSanPham;
    private String HinhAnh;
    private String NXS;
    private String XuatXu;
    private Long GiaBan;
    private String KichCo;
    private String MauSac;
    private int SoLuong;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(String ID, String ID_SanPham, String ID_ThuongHieu, String ID_KichThuoc, String ID_MauSac, String ID_XuatXu, String TenSanPham, String HinhAnh, String NXS, String XuatXu, Long GiaBan, String KichCo, String MauSac, int SoLuong) {
        this.ID = ID;
        this.ID_SanPham = ID_SanPham;
        this.ID_ThuongHieu = ID_ThuongHieu;
        this.ID_KichThuoc = ID_KichThuoc;
        this.ID_MauSac = ID_MauSac;
        this.ID_XuatXu = ID_XuatXu;
        this.TenSanPham = TenSanPham;
        this.HinhAnh = HinhAnh;
        this.NXS = NXS;
        this.XuatXu = XuatXu;
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

    public String getID_SanPham() {
        return ID_SanPham;
    }

    public void setID_SanPham(String ID_SanPham) {
        this.ID_SanPham = ID_SanPham;
    }

    public String getID_ThuongHieu() {
        return ID_ThuongHieu;
    }

    public void setID_ThuongHieu(String ID_ThuongHieu) {
        this.ID_ThuongHieu = ID_ThuongHieu;
    }

    public String getID_KichThuoc() {
        return ID_KichThuoc;
    }

    public void setID_KichThuoc(String ID_KichThuoc) {
        this.ID_KichThuoc = ID_KichThuoc;
    }

    public String getID_MauSac() {
        return ID_MauSac;
    }

    public void setID_MauSac(String ID_MauSac) {
        this.ID_MauSac = ID_MauSac;
    }

    public String getID_XuatXu() {
        return ID_XuatXu;
    }

    public void setID_XuatXu(String ID_XuatXu) {
        this.ID_XuatXu = ID_XuatXu;
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

    public String getXuatXu() {
        return XuatXu;
    }

    public void setXuatXu(String XuatXu) {
        this.XuatXu = XuatXu;
    }

    public Long getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Long GiaBan) {
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
