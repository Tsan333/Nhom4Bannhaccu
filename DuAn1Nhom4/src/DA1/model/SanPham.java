package DA1.model;

public class SanPham {

    private String ID;
    private String TenSP;

    public SanPham() {
    }

    public SanPham(String ID, String TenSP) {
        this.ID = ID;
        this.TenSP = TenSP;
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

    @Override
    public String toString() {
        return TenSP;
    }

}
