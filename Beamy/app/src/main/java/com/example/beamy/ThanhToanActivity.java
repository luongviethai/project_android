package com.example.beamy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThanhToanActivity extends AppCompatActivity {
    TextView txtenhoadon,txttienhoadon;
    EditText edttenkhachhang,edtdiachikhachhang,edtsdtkhachang;
    Button btnok;
    String ten, tenKH, diaChi, sdt, ngayDatHang;
    String tien;
    int maKhachHang, i;
    ArrayList<clsGioHang> dsGioHang;

    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;

    String urlGetGioHang = baseURL + "/CuaHang/get/getDataGioHang.php?maKhachHang=";

    String urlThemDonHang = baseURL + "/CuaHang/insert/themDonHang.php";

    String urlThemCTDonHang = baseURL + "/CuaHang/insert/themCTDonHang.php";

    String urlDeleteGH = baseURL + "/CuaHang/deleteGioHang.php";

    String urlGetKH = baseURL + "/CuaHang/get/getKHbymaKH.php?maKhachHang=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        txttienhoadon = (TextView)findViewById(R.id.txttienhoadon);
        edttenkhachhang = (EditText)findViewById(R.id.edttenkhachhang);
        edtdiachikhachhang = (EditText)findViewById(R.id.edtdiachikhachhang);
        edtsdtkhachang = (EditText)findViewById(R.id.edtsdtkhachhang);

        dsGioHang = new ArrayList<>();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieuhoadon");

        tien = bundle.getString("tongTien");
        //txttienhoadon.setText(ten);
        //txttienhoadon.setText("T???ng thanh to??n: "+tien);
        dsGioHang = (ArrayList<clsGioHang>) bundle.getSerializable("ArrayList");

        txttienhoadon.setText("T???ng thanh to??n: "+ tien + "??");
        btnok = (Button)findViewById(R.id.btnok);

        SharedPreferences sharedPreferences= this.getSharedPreferences("tkSetting", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {
            maKhachHang = sharedPreferences.getInt("maKH", -1);

            String urlGetKH2 = urlGetKH + maKhachHang;
            LoadDataKH(urlGetKH2);
        }
        //Toast.makeText(ThanhToanActivity.this, "Ma KH: " + maKH + "- " + urlGetKH2, Toast.LENGTH_SHORT).show();

        LoadDataGioHang(urlGetGioHang);

        CreateTime();

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtdiachikhachhang.getText().toString().equals("")||edtsdtkhachang.getText().toString().equals("")){
                    Toast.makeText(ThanhToanActivity.this, "H??y nh???p ????? th??ng tin", Toast.LENGTH_SHORT).show();
                }else {

                    btnok.setVisibility(View.GONE);

                    InsertDonHang(urlThemDonHang);
                    //LoadDataGioHang(urlGetGioHang);
                    Toast.makeText(ThanhToanActivity.this, "C???m ??n b???n ???? s??? d???ng d???ch v???", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void CreateTime() {
        Calendar hienTai = Calendar.getInstance();
        int nam = hienTai.get(Calendar.YEAR);
        int thang = hienTai.get(Calendar.MONTH);
        int ngay = hienTai.get(Calendar.DAY_OF_MONTH);
        ngayDatHang = nam + "-" + (thang + 1) + "-" + ngay;
    }

    void InsertDonHang(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(ThanhToanActivity.this, "Nh???n: " + response, Toast.LENGTH_SHORT).show();

                if(response.equals("Done")){
                    //Toast.makeText(ThanhToanActivity.this, "Th??m th??nh c??ng", Toast.LENGTH_SHORT).show();

                    i = 0;

                    //Toast.makeText(ThanhToanActivity.this, "M??: " + String.valueOf(dsGioHang.get(i).getMaSP()) + " - S???: " + String.valueOf(dsGioHang.get(i).getSoLuong()) + " - ti???n: " +  String.valueOf(dsGioHang.get(i).getTien()), Toast.LENGTH_SHORT).show();

                    InsertCTDonHang(urlThemCTDonHang);

                    //Toast.makeText(ThanhToanActivity.this, "C???m ??n b???n ???? s??? d???ng d???ch v???", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(ThanhToanActivity.this, "???? x???y ra l???i", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThanhToanActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("maKhachHang", String.valueOf(maKhachHang));
                params.put("tenKhachHang", edttenkhachhang.getText().toString());
                params.put("diaChi", edtdiachikhachhang.getText().toString());
                params.put("sdt", edtsdtkhachang.getText().toString());
                params.put("tien", String.valueOf(tien));
                params.put("ngayDatHang", ngayDatHang);


                return params;
            }
        };
        queue.add(request);
    }

    public void InsertCTDonHang(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request=new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Done")) {

                    i++;

                    //Toast.makeText(ThanhToanActivity.this, "M??: " + String.valueOf(dsGioHang.get(i).getMaSP()) + " - S???: " + String.valueOf(dsGioHang.get(i).getSoLuong()) + " - ti???n: " +  String.valueOf(dsGioHang.get(i).getTien()), Toast.LENGTH_SHORT).show();
                    if (i < dsGioHang.size()) // ??i???u ki???n d???ng
                    {
                        InsertCTDonHang(urlThemCTDonHang);
                    }
                    else {
                       // Toast.makeText(ThanhToanActivity.this,"???? ?????t h??ng", Toast.LENGTH_LONG).show();


                        XoaGioHang(urlDeleteGH);
                        finish();
                    }
                }
                else
                    Toast.makeText(ThanhToanActivity.this,"Th???t b???iCT", Toast.LENGTH_LONG).show();
                btnok.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*Toast.makeText(ThemPhieuMuonActivity.this,"Ch??a k???t n???i: " + error, Toast.LENGTH_LONG).show();*/
                btnok.setVisibility(View.VISIBLE);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> dulieu=new HashMap<>();


                String sMaSP = String.valueOf(dsGioHang.get(i).getMasanpham());
                String sSoLuong = String.valueOf(dsGioHang.get(i).getSoLuong());
                String sTien = String.valueOf(dsGioHang.get(i).getTien());

                // G???i d??? li???u cho PHP
                dulieu.put("masanpham", sMaSP);
                dulieu.put("soLuong", sSoLuong);
                dulieu.put("tien", sTien);

                return dulieu;
            }
        };
        queue.add(request);
    }

    public void XoaGioHang(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().equals("Done")) {

                }
                else{
                    Toast.makeText(ThanhToanActivity.this, "???? x???y ra l???i" + response.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> dulieu = new HashMap<>();

                String smaKH = String.valueOf(maKhachHang);
                dulieu.put("maKhachHang", smaKH);

                return dulieu;
            }
        };
        queue.add(request);
    }

    void LoadDataKH(String url){

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                //Toast.makeText(ThanhToanActivity.this, "Nhan: " + response.toString(), Toast.LENGTH_SHORT).show();

                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);

                        tenKH = object.getString("tenKhachHang");
                        diaChi = object.getString("diaChi");
                        sdt = object.getString("sdt");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                edttenkhachhang.setText(tenKH);
                edtdiachikhachhang.setText(diaChi);
                edtsdtkhachang.setText(sdt);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    void LoadDataGioHang(String url){
        //Toast.makeText(this.getActivity(),"Load data S??? sp: " + arrClsGioHang.size() ,Toast.LENGTH_LONG).show();


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        dsGioHang.add(new clsGioHang(
                                object.getInt("masanpham"),
                                object.getString("tensanpham"),
                                object.getString("hinhanh"),
                                object.getInt("soLuong"),
                                object.getInt("tien")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                for (int i =0; i<dsGioHang.size(); i++)
                {
                    //Toast.makeText(ThanhToanActivity.this, "M??: " + dsGioHang.get(i).getMaSP() + "- s???: " + dsGioHang.get(i).getSoLuong() + "- gi??: " + dsGioHang.get(i).getTien(), Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }


    void delete(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.toString().equals("done")){
//                    Toast.makeText(ThanhToanActivity.this, "xoas thanfh coong", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ThanhToanActivity.this, "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThanhToanActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}