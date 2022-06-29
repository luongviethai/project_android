package com.example.beamy;

import java.io.Serializable;

public class clsLoaiSP implements Serializable {
    int maLoaiSP;
    String tenLoaiSP;

    public clsLoaiSP(int maLoaiSP, String tenLoaiSP) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }
}
