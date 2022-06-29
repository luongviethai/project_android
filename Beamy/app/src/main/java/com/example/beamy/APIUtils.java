package com.example.beamy;

public class APIUtils {
    public static final String Base_Url="http://192.168.36.174/CuaHang/";
    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}

