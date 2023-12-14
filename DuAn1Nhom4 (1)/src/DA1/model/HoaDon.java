
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Welcome
 */
public class HoaDon {
    private int id;
    private int idnv;
    private int idkh;
    private Date nt;
    private Date ngd;
    private float tongTien;
    private String tennv;
    private String tenkh;
    private String sdt;
   
    private String pttt;
    private String tt;

    public HoaDon() {
    }
    
    

    
    public HoaDon(int id, int idnv, int idkh, Date nt, Date ngd, float tongTien, String tennv, String tenkh, String sdt, String pttt, String tt) {
        this.id = id;
        this.idnv = idnv;
        this.idkh = idkh;
        this.nt = nt;
        this.ngd = ngd;
        this.tongTien = tongTien;
        this.tennv = tennv;
        this.tenkh = tenkh;
        this.sdt = sdt;
        this.pttt = pttt;
        this.tt = tt;
    }

    
    public int getIdkh() {
        return idkh;
    }

    public void setIdkh(int idkh) {
        this.idkh = idkh;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNt() {
        SimpleDateFormat ntf = new SimpleDateFormat("dd-MM-yyyy");
        return ntf.format(nt);
    }

    public void setNt(Date nt) {
        this.nt = nt;
    }

    public String getNgd() {
        SimpleDateFormat ngdf = new SimpleDateFormat("dd-MM-yyyy");
        return ngdf.format(ngd);
    }

    public void setNgd(Date ngd) {
        this.ngd = ngd;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPttt() {
        return pttt;
    }

    public void setPttt(String pttt) {
        this.pttt = pttt;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }
    
}
