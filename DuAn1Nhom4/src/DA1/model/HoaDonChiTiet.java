/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

/**
 *
 * @author Welcome
 */
public class HoaDonChiTiet {
    private int id;
    private int idct;
    private int idhd;
    private String tensp;
    private String hang;
    private String kt;
    private String kd;
    private String xx;
    private String mau;
    private int sl;
    private float gia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id, int idct, int idhd, String tensp, String hang, String kt, String kd, String xx, String mau, int sl, float gia) {
        this.id = id;
        this.idct = idct;
        this.idhd = idhd;
        this.tensp = tensp;
        this.hang = hang;
        this.kt = kt;
        this.kd = kd;
        this.xx = xx;
        this.mau = mau;
        this.sl = sl;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdct() {
        return idct;
    }

    public void setIdct(int idct) {
        this.idct = idct;
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }

    public String getKd() {
        return kd;
    }

    public void setKd(String kd) {
        this.kd = kd;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
    
    
}