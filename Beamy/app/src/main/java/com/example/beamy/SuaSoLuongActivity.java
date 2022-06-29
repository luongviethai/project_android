package com.example.beamy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SuaSoLuongActivity extends AppCompatActivity {
    TextView txtsusoluong,txtsuatien,txtsuaten;
    ImageButton ibtncongsoluong,ibtntrusoluong;
    int soluong;
    Button btnsuasoluong, btnxoasoluong;
    int masanpham, maKhachHang;
    int tien = 0;
    int tienmoisoluong = 0;
    int moisoluong = 0;
    int tongtien= 0;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlUpdate = baseURL + "/CuaHang/updateSoLuong.php";
    String urlDelete = baseURL + "/CuaHang/deleteDonHang.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_so_luong);

        txtsusoluong = (TextView)findViewById(R.id.txtsuasoluong);
        ibtncongsoluong = (ImageButton)findViewById(R.id.ibtncongsoluong);
        ibtntrusoluong = (ImageButton)findViewById(R.id.ibtntrusoluong);
        txtsuatien = (TextView)findViewById(R.id.txtsuatien);
        txtsuaten = (TextView)findViewById(R.id.txtsuaten);

        SharedPreferences sharedPreferences = this.getSharedPreferences("tkSetting", Context.MODE_PRIVATE);

        if(sharedPreferences!= null) {
            maKhachHang = sharedPreferences.getInt("maKH", -1);
        }


        Intent intent = getIntent();
        clsGioHang clsGioHang = (clsGioHang) intent.getSerializableExtra("donhang");
        txtsusoluong = (TextView)findViewById(R.id.txtsuasoluong);
        masanpham =clsGioHang.getMasanpham();
        txtsusoluong.setText(String.valueOf(clsGioHang.getSoLuong()));
        moisoluong = clsGioHang.getSoLuong();
        tien = clsGioHang.getTien();
        tienmoisoluong = tien/moisoluong;
        soluong = Integer.parseInt(txtsusoluong.getText().toString());
        btnsuasoluong = (Button)findViewById(R.id.btnsuasoluong);
        btnxoasoluong = (Button)findViewById(R.id.btnxoasoluong);
        tongtien = tienmoisoluong * soluong;
        txtsuatien.setText(String.valueOf(tongtien));
        txtsuaten.setText(clsGioHang.getTensanpham());

        ibtncongsoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibtntrusoluong.setEnabled(true);
                soluong += 1;
                txtsusoluong.setText(String.valueOf(soluong));
                tongtien = tienmoisoluong * soluong;
                txtsuatien.setText(String.valueOf(tongtien));
            }
        });

        ibtntrusoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soluong>1)
                {
                    soluong -= 1;
                    txtsusoluong.setText(String.valueOf(soluong));
                    tongtien = tienmoisoluong * soluong;
                    txtsuatien.setText(String.valueOf(tongtien));
                    ibtntrusoluong.setEnabled(true);

                }
                else {
                    ibtntrusoluong.setEnabled(false);
                }

            }
        });
        btnsuasoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSoluong(urlUpdate);
            }
        });
        btnxoasoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SuaSoLuongActivity.this);
                dialog.setMessage("Bạn có muốn xóa không");
                dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            DeleteSoLuong(urlDelete);
                    }
                });
                dialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }
    void DeleteSoLuong(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.toString().equals("done")){
                    Toast.makeText(SuaSoLuongActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(SuaSoLuongActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SuaSoLuongActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maKhachHang", String.valueOf(maKhachHang));

                params.put("masanpham", String.valueOf(masanpham));
                return params;
            }
        };
        queue.add(request);
    }
    void UpdateSoluong(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.toString().equals("done")){
                    Toast.makeText(SuaSoLuongActivity.this, "Đã sửa", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(SuaSoLuongActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SuaSoLuongActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maKhachHang", String.valueOf(maKhachHang));
                params.put("masanpham", String.valueOf(masanpham));
                params.put("soLuong",txtsusoluong.getText().toString());
                params.put("tien",String.valueOf(tongtien));
                return params;
            }
        };
        queue.add(request);
    }
}