package com.example.beamy;

import java.io.Serializable;

public class clsSPCuaToi implements Serializable {
    private String masanpham;
    private String tensanpham;
    private String gia;
    private String loai;
    private String hinhanh;
    private String mota;

    public clsSPCuaToi(String masanpham, String tensanpham, String gia, String loai, String hinhanh, String mota) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.loai = loai;
        this.hinhanh = hinhanh;
        this.mota = mota;

    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
