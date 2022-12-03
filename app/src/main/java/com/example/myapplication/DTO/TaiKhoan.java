package com.example.myapplication.DTO;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private int tongDiem;
    private String linkAnh;
    private String phanLoai;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String hoTen, int tongDiem, String linkAnh, String phanLoai) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.tongDiem = tongDiem;
        this.linkAnh = linkAnh;
        this.phanLoai = phanLoai;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTongDiem() {
        return tongDiem;
    }

    public void setTongDiem(int tongDiem) {
        this.tongDiem = tongDiem;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }
}
