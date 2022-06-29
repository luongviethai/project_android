package com.example.beamy;

import java.io.Serializable;

public class clsGioHang implements Serializable {

    int masanpham;
    String tensanpham;
    String hinhanh;
    int soLuong;
    int tien;

    public clsGioHang(int masanpham, String tensanpham, String hinhanh, int soLuong, int tien) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.hinhanh = hinhanh;
        this.soLuong = soLuong;
        this.tien = tien;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }
}
