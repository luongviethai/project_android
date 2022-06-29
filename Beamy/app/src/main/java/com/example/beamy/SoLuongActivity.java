package com.example.beamy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class SoLuongActivity extends AppCompatActivity {
    ImageButton ibtntru,ibtncong,ibtnhuy;
    TextView txttongtien,txttendodat,txtgiadodat,txtsoluong;
    Button btnthem;
    ImageView imgnhananh;
    int soluong;
    int tien;
    int tongtien;
    int masanpham;
    int maKhachHang;
    String hinhanh;

    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlAdd = baseURL+"/CuaHang/insert/themGioHang.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_luong);
        imgnhananh = (ImageView)findViewById(R.id.imgnhananh);
        ibtncong = (ImageButton)findViewById(R.id.ibtncong);
        ibtnhuy = (ImageButton)findViewById(R.id.ibtnhuy);
        ibtntru = (ImageButton)findViewById(R.id.ibtntru);
        txtgiadodat = (TextView)findViewById(R.id.txtgiadodat);
        txttongtien = (TextView)findViewById(R.id.txttongtien);
        txttendodat = (TextView)findViewById(R.id.txxtendodat);
        txtsoluong = (TextView)findViewById(R.id.txtsoluong);
        btnthem = (Button)findViewById(R.id.btnthem);
        soluong = Integer.parseInt(txtsoluong.getText().toString());

        Intent intent = getIntent();
        clsDanhSachSanPham clsDanhSachSanPham = (clsDanhSachSanPham)intent.getSerializableExtra("dulieudoan");
        Picasso.get().load(clsDanhSachSanPham.getHinhanh()).into(imgnhananh);
        txttendodat.setText(clsDanhSachSanPham.getTensanpham());
        txtgiadodat.setText(String.valueOf(clsDanhSachSanPham.getGia())+"đ");
        tien = clsDanhSachSanPham.getGia();
        masanpham = clsDanhSachSanPham.getMasanpham();
        hinhanh = clsDanhSachSanPham.getHinhanh();

        SharedPreferences sharedPreferences= this.getSharedPreferences("tkSetting", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {
            maKhachHang = sharedPreferences.getInt("maKH", -1);
        }

        //Toast.makeText(this,"Mã kh: " + maKH ,Toast.LENGTH_LONG).show();



        tongtien = tien;
        txttongtien.setText("Tổng tiền: "+String.valueOf(tien)+"đ");

        ibtncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong+=1;
                txtsoluong.setText(String.valueOf(soluong));
                tongtien = soluong * tien;
                txttongtien.setText("Tổng tiền: "+String.valueOf(tongtien)+"đ");
            }
        });
        ibtntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong-=1;
                txtsoluong.setText(String.valueOf(soluong));
                tongtien = soluong * tien;
                txttongtien.setText("Tổng tiền: "+String.valueOf(tongtien)+"đ");
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(SoLuongActivity.this, "maxkh: " + maKhachHang + " masp: " + masanpham + "-số: "+txtsoluong.getText().toString()+"-tiền: "+String.valueOf(tongtien), Toast.LENGTH_LONG).show();

                AddGioHang(urlAdd);
                finish();
            }
        });
        ibtnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void AddGioHang(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(SoLuongActivity.this, "Nhận: " + response, Toast.LENGTH_LONG).show();


                if (response.toString().equals("done")){
                    Toast.makeText(SoLuongActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SoLuongActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SoLuongActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("maKhachHang",String.valueOf(maKhachHang));
                params.put("masanpham",String.valueOf(masanpham));
                params.put("soLuong",txtsoluong.getText().toString());
                params.put("tien",String.valueOf(tongtien));
                return params;
            }
        };
        queue.add(request);
    }
}