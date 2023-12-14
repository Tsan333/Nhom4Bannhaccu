package DA1.model;

public class SanPham {

    private String ID;
    private String TenSP;
    private int id_NhanVien;

    public SanPham() {
    }

    public SanPham(String ID, String TenSP, int id_NhanVien) {
        this.ID = ID;
        this.TenSP = TenSP;
        this.id_NhanVien = id_NhanVien;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getId_NhanVien() {
        return id_NhanVien;
    }

    public void setId_NhanVien(int id_NhanVien) {
        this.id_NhanVien = id_NhanVien;
    }
    
    @Override
    public String toString() {
        return TenSP;
    }

}
