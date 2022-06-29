package com.example.beamy;

import java.io.Serializable;

public class clsDanhSachSanPham implements Serializable {
    private int masanpham;
    private String tensanpham;
    private int gia;
    private String loai;
    private String hinhanh;
    private String mota;
    private int maLoaiSP;
    private int maShop;

    public clsDanhSachSanPham(int masanpham, String tensanpham, int gia, String loai, String hinhanh, String mota, int maLoaiSP, int maShop) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.gia = gia;
        this.loai = loai;
        this.hinhanh = hinhanh;
        this.mota = mota;
        this.maLoaiSP = maLoaiSP;
        this.maShop = maShop;
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
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

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public int getMaShop() {
        return maShop;
    }

    public void setMaShop(int maShop) {
        this.maShop = maShop;
    }
}
