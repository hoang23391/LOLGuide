package com.hoangsv.lolguide.model;

/**
 * Created by User on 3/2/2017.
 */

public class News {
    private String tieuDe, anhDaiDien, noiDung, link;

    public News() {
    }

    public News(String tieuDe, String anhDaiDien, String noiDung, String link) {
        this.tieuDe = tieuDe;
        this.anhDaiDien = anhDaiDien;
        this.noiDung = noiDung;
        this.link = link;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}