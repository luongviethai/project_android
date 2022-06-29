package com.example.beamy;

public class clsSanpham {
    String tenDoAn;
    int hinhAnhDoAn;

    public clsSanpham(String tenDoAn, int hinhAnhDoAn) {
        this.tenDoAn = tenDoAn;
        this.hinhAnhDoAn = hinhAnhDoAn;
    }

    public String getTenDoAn() {
        return tenDoAn;
    }

    public void setTenDoAn(String tenDoAn) {
        this.tenDoAn = tenDoAn;
    }

    public int getHinhAnhDoAn() {
        return hinhAnhDoAn;
    }

    public void setHinhAnhDoAn(int hinhAnhDoAn) {
        this.hinhAnhDoAn = hinhAnhDoAn;
    }
}
