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

    private String id;
    private String TenMau;

    public MauSac() {
    }

    public MauSac(String id, String TenMau) {
        this.id = id;
        this.TenMau = TenMau;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
