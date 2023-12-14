/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA1.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Welcome
 */
public class TimeLine {
    private int id;
    private int idhd;
    private Date tg;
    private String nd;

    public TimeLine() {
    }

    public TimeLine(int id, int idhd, Date tg, String nd) {
        this.id = id;
        this.idhd = idhd;
        this.tg = tg;
        this.nd = nd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdhd() {
        return idhd;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public Date getTg() {
        return tg;
    }

    public void setTg(Date tg) {
        this.tg = tg;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

}