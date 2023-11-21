/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

/**
 *
 * @author namtr
 */
public class KichThuoc {

    private String ID;
    private String KichThuoc;

    public KichThuoc() {
    }

    public KichThuoc(String ID, String KichThuoc) {
        this.ID = ID;
        this.KichThuoc = KichThuoc;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKichThuoc() {
        return KichThuoc;
    }

    public void setKichThuoc(String KichThuoc) {
        this.KichThuoc = KichThuoc;
    }

    @Override
    public String toString() {
        return KichThuoc;
    }

}
