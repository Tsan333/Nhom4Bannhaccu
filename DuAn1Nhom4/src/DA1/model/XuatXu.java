/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

/**
 *
 * @author namtr
 */
public class XuatXu {

    private int Id;
    private String TenXuatXu;

    public XuatXu() {
    }

    public XuatXu(int Id, String TenXuatXu) {
        this.Id = Id;
        this.TenXuatXu = TenXuatXu;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTenXuatXu() {
        return TenXuatXu;
    }

    public void setTenXuatXu(String TenXuatXu) {
        this.TenXuatXu = TenXuatXu;
    }

    
    

    @Override
    public String toString() {
        return TenXuatXu;
    }

}
