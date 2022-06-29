package com.example.beamy;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {
    @Multipart
    @POST("uploadhinhanh.php")
    Call<String> UploadPhoto(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("chenKH.php")
    Call<String> InsertData(@Field("tenKhachHang")String tenKhachHang
                            ,@Field("anhKhachHang")String anhKhachHang
                            ,@Field("sdt")String sdt
                            ,@Field("diaChi")String diaChi
                            ,@Field("tenTaiKhoan")String tenTaiKhoan
                            ,@Field("matKhau")String matKhau);

    @FormUrlEncoded
    @POST("chenSPCT.php")
    Call<String> InsertSP(@Field("tensanpham")String tensanpham
                            ,@Field("gia") String gia
                            ,@Field("hinhanh") String hinhanh
                            ,@Field("mota") String mota);
    @FormUrlEncoded
    @POST("chenSP.php")
    Call<String> InsertSP1(@Field("tensanpham")String tensanpham
            ,@Field("gia") int gia
            ,@Field("loai") String loai
            ,@Field("hinhanh") String hinhanh
            ,@Field("mota") String mota
            ,@Field("maLoaiSP") int maLoaiSP
            ,@Field("maShop") int maShop);
}
