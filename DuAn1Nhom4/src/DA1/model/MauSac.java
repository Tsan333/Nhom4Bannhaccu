/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

/**
 *
 * @author namtr
 */
public class MauSac {

    private int id;
    private String TenMau;

    public MauSac() {
    }

    public MauSac(int id, String TenMau) {
        this.id = id;
        this.TenMau = TenMau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

   

    @Override
    public String toString() {
        return TenMau;
    }

}
