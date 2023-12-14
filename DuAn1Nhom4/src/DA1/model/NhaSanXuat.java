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

    private int ID;
    private String TenNSX;

    public NhaSanXuat() {
    }

    public NhaSanXuat(int ID, String TenNSX) {
        this.ID = ID;
        this.TenNSX = TenNSX;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenNSX() {
        return TenNSX;
    }

    public void setTenNSX(String TenNSX) {
        this.TenNSX = TenNSX;
    }

    
    

    @Override
    public String toString() {
        return TenNSX;
    }

}
