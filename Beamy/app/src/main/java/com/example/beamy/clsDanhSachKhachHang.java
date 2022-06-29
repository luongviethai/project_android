package com.example.beamy;

import java.io.Serializable;

public class clsDanhSachKhachHang implements Serializable {

    int maKhachHang;
    String tenKhachHang;
    String anhKhachHang;
    String sdt;
    String diaChi;
    String tenTaiKhoan;
    String matKhau;

    public clsDanhSachKhachHang(){

    }
    public clsDanhSachKhachHang(int maKhachHang, String tenKhachHang, String anhKhachHang, String sdt, String diaChi, String tenTaiKhoan,String matKhau){
        this.maKhachHang=maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.anhKhachHang = anhKhachHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getAnhKhachHang() {
        return anhKhachHang;
    }

    public void setAnhKhachHang(String anhKhachHang) {
        this.anhKhachHang = anhKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
