/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

/**
 *
 * @author namtr
 */
public class DangDan {
     private int ID;
    private String dangDan;

    public DangDan() {
    }

    public DangDan(int ID, String dangDan) {
        this.ID = ID;
        this.dangDan = dangDan;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDangDan() {
        return dangDan;
    }

    public void setDangDan(String dangDan) {
        this.dangDan = dangDan;
    }

    
    

    @Override
    public String toString() {
        return dangDan ;
    }
    
    
    
    
}
