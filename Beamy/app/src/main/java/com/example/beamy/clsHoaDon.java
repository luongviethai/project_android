package com.example.beamy;

import java.io.Serializable;

public class clsHoaDon implements Serializable {
    int id;
    int masanpham;
    //int maKhachHang;
    String tensanpham;
    String diachi;
    int sdt;
    int tien;

    public clsHoaDon(int id, int masanpham, String tensanpham, String diachi, int sdt, int tien) {
        this.id = id;
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tien = tien;
    }

   /*public clsHoaDon(int id, String tensanpham, String tenkhachhang, String diachi, int sdt, int tien) {
        this.id = id;
        this.tensanpham = tensanpham;
        this.tenkhachhang = tenkhachhang;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tien = tien;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }
}
