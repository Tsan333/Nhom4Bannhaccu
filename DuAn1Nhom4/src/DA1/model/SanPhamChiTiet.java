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

    private int ID;
    private int ID_SanPham;
    private int ID_ThuongHieu;
    private int ID_KichThuoc;
    private int ID_MauSac;
    private int ID_XuatXu;
    private int ID_DangDan;
    private String TenSanPham;
    private String HinhAnh;
    private String NXS;
    private String XuatXu;
    private Long GiaBan;
    private String KichCo;
    private String MauSac;
    private String DangDan;
    private int SoLuong;
    private String GhiChu;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int ID, int ID_SanPham, int ID_ThuongHieu, int ID_KichThuoc, int ID_MauSac, int ID_XuatXu, int ID_DangDan, String TenSanPham, String HinhAnh, String NXS, String XuatXu, Long GiaBan, String KichCo, String MauSac, String DangDan, int SoLuong, String GhiChu) {
        this.ID = ID;
        this.ID_SanPham = ID_SanPham;
        this.ID_ThuongHieu = ID_ThuongHieu;
        this.ID_KichThuoc = ID_KichThuoc;
        this.ID_MauSac = ID_MauSac;
        this.ID_XuatXu = ID_XuatXu;
        this.ID_DangDan = ID_DangDan;
        this.TenSanPham = TenSanPham;
        this.HinhAnh = HinhAnh;
        this.NXS = NXS;
        this.XuatXu = XuatXu;
        this.GiaBan = GiaBan;
        this.KichCo = KichCo;
        this.MauSac = MauSac;
        this.DangDan = DangDan;
        this.SoLuong = SoLuong;
        this.GhiChu = GhiChu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_SanPham() {
        return ID_SanPham;
    }

    public void setID_SanPham(int ID_SanPham) {
        this.ID_SanPham = ID_SanPham;
    }

    public int getID_ThuongHieu() {
        return ID_ThuongHieu;
    }

    public void setID_ThuongHieu(int ID_ThuongHieu) {
        this.ID_ThuongHieu = ID_ThuongHieu;
    }

    public int getID_KichThuoc() {
        return ID_KichThuoc;
    }

    public void setID_KichThuoc(int ID_KichThuoc) {
        this.ID_KichThuoc = ID_KichThuoc;
    }

    public int getID_MauSac() {
        return ID_MauSac;
    }

    public void setID_MauSac(int ID_MauSac) {
        this.ID_MauSac = ID_MauSac;
    }

    public int getID_XuatXu() {
        return ID_XuatXu;
    }

    public void setID_XuatXu(int ID_XuatXu) {
        this.ID_XuatXu = ID_XuatXu;
    }

    public int getID_DangDan() {
        return ID_DangDan;
    }

    public void setID_DangDan(int ID_DangDan) {
        this.ID_DangDan = ID_DangDan;
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

    public String getDangDan() {
        return DangDan;
    }

    public void setDangDan(String DangDan) {
        this.DangDan = DangDan;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    
    
    @Override
    public String toString() {
        return String.valueOf(ID_SanPham);
    }

}
