package com.example.beamy;

public class clsGiaoHang {
    String khuvuc;
    int tienship;

    public clsGiaoHang(String khuvuc, int tienship) {
        this.khuvuc = khuvuc;
        this.tienship = tienship;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }

    public int getTienship() {
        return tienship;
    }

    public void setTienship(int tienship) {
        this.tienship = tienship;
    }
}
