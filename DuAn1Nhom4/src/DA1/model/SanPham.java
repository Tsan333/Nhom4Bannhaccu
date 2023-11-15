package DA1.model;

public class SanPham {

    private String ID;
    private String TenSP;
    private long GiaSP;

    public SanPham() {
    }

    public SanPham(String ID, String TenSP, long GiaSP) {
        this.ID = ID;
        this.TenSP = TenSP;
        this.GiaSP = GiaSP;
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

    public long getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(long GiaSP) {
        this.GiaSP = GiaSP;
    }

    
    
}
