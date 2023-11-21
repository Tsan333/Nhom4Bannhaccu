/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

/**
 *
 * @author namtr
 */
public class NhaSanXuat {
    private String ID;
    private String TenNSX;

    public NhaSanXuat() {
    }

    public NhaSanXuat(String ID, String TenNSX) {
        this.ID = ID;
        this.TenNSX = TenNSX;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenNSX() {
        return TenNSX;
    }

    public void setTenNSX(String TenNSX) {
        this.TenNSX = TenNSX;
    }
    
}
