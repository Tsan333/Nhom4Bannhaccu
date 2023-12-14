/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

/**
 *
 * @author Welcome
 */
public class ThongKe {
    private String ngay;
    private Float dt;

    public ThongKe() {
    }

    public ThongKe(String ngay, Float dt) {
        this.ngay = ngay;
        this.dt = dt;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public Float getDt() {
        return dt;
    }

    public void setDt(Float dt) {
        this.dt = dt;
    }
    
    
}
