package com.example.myapplication.DTO;

import java.io.Serializable;

public class NguPhap implements Serializable {
    private String cauHoi;
    private String luaChon1;
    private String luaChon2;
    private String luaChon3;
    private String luaChon4;
    private String phanLoai;
    private String dapAn;
    private String nguoiDungChonDapAn;

    public NguPhap() {
    }

    public NguPhap(String cauHoi, String luaChon1, String luaChon2, String luaChon3, String luaChon4, String phanLoai, String dapAn) {
        this.cauHoi = cauHoi;
        this.luaChon1 = luaChon1;
        this.luaChon2 = luaChon2;
        this.luaChon3 = luaChon3;
        this.luaChon4 = luaChon4;
        this.phanLoai = phanLoai;
        this.dapAn = dapAn;
        this.nguoiDungChonDapAn = "";
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getLuaChon1() {
        return luaChon1;
    }

    public void setLuaChon1(String luaChon1) {
        this.luaChon1 = luaChon1;
    }

    public String getLuaChon2() {
        return luaChon2;
    }

    public void setLuaChon2(String luaChon2) {
        this.luaChon2 = luaChon2;
    }

    public String getLuaChon3() {
        return luaChon3;
    }

    public void setLuaChon3(String luaChon3) {
        this.luaChon3 = luaChon3;
    }

    public String getLuaChon4() {
        return luaChon4;
    }

    public void setLuaChon4(String luaChon4) {
        this.luaChon4 = luaChon4;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String getNguoiDungChonDapAn() {
        return nguoiDungChonDapAn;
    }

    public void setNguoiDungChonDapAn(String nguoiDungChonDapAn) {
        this.nguoiDungChonDapAn = nguoiDungChonDapAn;
    }
}
