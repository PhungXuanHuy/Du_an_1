package com.example.myapplication.DTO;

import java.io.Serializable;

public class TuVung implements Serializable {
    private String tenTuVung;
    private String nghiaTuVung;
    private String dapAn;
    private String anh;
    private String phanLoai;

    public TuVung() {
    }

    public TuVung(String tenTuVung, String nghiaTuVung, String anh, String phanLoai) {
        this.tenTuVung = tenTuVung;
        this.nghiaTuVung = nghiaTuVung;
        this.anh = anh;
        this.phanLoai = phanLoai;
        this.dapAn = "";
    }

    public String getTenTuVung() {
        return tenTuVung;
    }

    public void setTenTuVung(String tenTuVung) {
        this.tenTuVung = tenTuVung;
    }

    public String getNghiaTuVung() {
        return nghiaTuVung;
    }

    public void setNghiaTuVung(String nghiaTuVung) {
        this.nghiaTuVung = nghiaTuVung;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }
}
