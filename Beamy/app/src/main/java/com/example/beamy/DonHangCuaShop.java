package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DonHangCuaShop extends AppCompatActivity {
    GridView gvDonHang;
    ArrayList<clsDonHang> arrClsDonHang;
    AdapterDonHang adapter;
    ImageButton ibtnQL;
    int maKH;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;

    String url = baseURL+ "/CuaHang/get/getDonHangCuaShop.php?maKhachHang=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_cua_shop);

        SharedPreferences sharedPreferences= this.getSharedPreferences("tkSetting", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {


            maKH = sharedPreferences.getInt("maKH", -1);

        }
        String urlGetSPCT2 = url + String.valueOf(maKH);

        loadData(urlGetSPCT2);

        ibtnQL =findViewById(R.id.ibtnQL);
        gvDonHang = (GridView)findViewById(R.id.gvDonHang);
        arrClsDonHang = new ArrayList<>();
        adapter = new AdapterDonHang(DonHangCuaShop.this,R.layout.dong_hoa_don, arrClsDonHang);
        gvDonHang.setAdapter(adapter);
        //loadData(url);

        ibtnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DonHangCuaShop.this,ShopKhachHang.class);
                startActivity(intent3);
            }
        });

        gvDonHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DonHangCuaShop.this,ChiTietDonHang.class);
                clsDonHang clsDonHang = new clsDonHang(arrClsDonHang.get(position).getMaDonHang(), arrClsDonHang.get(position).getMasanpham(), arrClsDonHang.get(position).getTensanpham(),arrClsDonHang.get(position).getHinhanh(), arrClsDonHang.get(position).getSoLuong(), arrClsDonHang.get(position).getGia(), arrClsDonHang.get(position).getMaKhachHang(), arrClsDonHang.get(position).getTenKhachHang(), arrClsDonHang.get(position).getDiaChi(), arrClsDonHang.get(position).getSdt(), arrClsDonHang.get(position).getTien(), arrClsDonHang.get(position).getTrangThai());
                intent.putExtra("dulieuDH", clsDonHang);
                startActivity(intent);
            }
        });
    }
    void loadData(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrClsDonHang.add(new clsDonHang(
                                object.getInt("maDonHang"),
                                object.getInt("masanpham"),
                                object.getString("tensanpham"),
                                object.getString("hinhanh"),
                                object.getInt("soLuong"),
                                object.getInt("gia"),

                                object.getInt("maKhachHang"),
                                object.getString("tenKhachHang"),
                                object.getString("diaChi"),
                                object.getString("sdt"),
                                object.getInt("tien"),
                                object.getString("trangThai")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DonHangCuaShop.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}