package com.example.beamy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class SuaKhachHangActivity extends AppCompatActivity {
    ImageView imgSuaKH;
    Button btnSuaKH, btnXoa;
    EditText eedtTenKH, eedtSDTKH, eedtDiaChiKH, eedtTenTaiKhoanKH, eedtMatKhauKH;
    clsDanhSachKhachHang danhSachKhachHang;

    private String tenKhachHang, anhKhachHang, sdt, diaChi,tenTaiKhoan, matKhau;
    private int maKhachHang;

    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlDeleteKH = baseURL+ "/CuaHang/deleteKhachHang.php";
    String urlUpdateKH =baseURL+ "/CuaHang/updateKhachHang.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_khach_hang);

        Init();

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            danhSachKhachHang = (clsDanhSachKhachHang) intent.getSerializableExtra("duLieu");

            /*Toast.makeText(ChiTietSachActivity.this, "Nhận: " + ttSach.getAnhSach(), Toast.LENGTH_SHORT).show();

             */

            maKhachHang = danhSachKhachHang.getMaKhachHang();
            eedtTenKH.setText(danhSachKhachHang.getTenKhachHang());
            eedtSDTKH.setText(danhSachKhachHang.getSdt());
            eedtDiaChiKH.setText(danhSachKhachHang.getDiaChi());
            eedtTenTaiKhoanKH.setText(danhSachKhachHang.getTenTaiKhoan());
            eedtMatKhauKH.setText(danhSachKhachHang.getMatKhau());

            Picasso.get().load(danhSachKhachHang.getAnhKhachHang()).into(imgSuaKH);



        }
        btnSuaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateKhachHang(urlUpdateKH);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DeleteKH(urlDeleteKH);
                MenuXoa();
            }
        });

    }



    public void DeleteKH(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*Toast.makeText(ChiTietDocGiaActivity.this, "Mã nhận: " + response.toString(), Toast.LENGTH_SHORT).show();*/

                if(response.toString().equals("Done")) {
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

                String mdg = String.valueOf(maKhachHang);

                dulieu.put("maKhachHang", mdg);


                return dulieu;
            }
        };
        queue.add(request);
    }


    public void MenuXoa()
    {
        AlertDialog.Builder canhbao = new AlertDialog.Builder(SuaKhachHangActivity.this);
        canhbao.setMessage("Bạn có muốn xóa ko?");
        canhbao.setTitle("Cảnh báo");
        canhbao.setIcon(R.drawable.ic_home);

        canhbao.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        canhbao.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DeleteKH(urlDeleteKH);

                Toast.makeText(SuaKhachHangActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SuaKhachHangActivity.this, DsKhachHangActivity.class);
                startActivity(intent);
                /*Toast.makeText(ChiTietSachActivity.this, "Hủy", Toast.LENGTH_SHORT).show();*/
            }
        });

        canhbao.show();
    }



    public void UpdateKhachHang(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SuaKhachHangActivity.this, "Nhận: " + response, Toast.LENGTH_LONG).show();
                if(response.toString().equals("Done")) {
                    Toast.makeText(SuaKhachHangActivity.this, "Sửa thành công", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SuaKhachHangActivity.this, DsKhachHangActivity.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(SuaKhachHangActivity.this,"Thất bại", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SuaKhachHangActivity.this,"Chưa kết nối: " + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> dulieu=new HashMap<>();


                tenKhachHang = eedtTenKH.getText().toString();
                sdt = eedtSDTKH.getText().toString();
                diaChi = eedtDiaChiKH.getText().toString();
                tenTaiKhoan = eedtTenTaiKhoanKH.getText().toString();
                matKhau = eedtMatKhauKH.getText().toString();


                dulieu.put("maKhachHang", String.valueOf(maKhachHang));
                dulieu.put("tenKhachHang", tenKhachHang);
                dulieu.put("sdt", sdt);
                dulieu.put("diaChi", diaChi);
                dulieu.put("tenTaiKhoan", tenTaiKhoan);
                dulieu.put("matKhau", matKhau);

                dulieu.put("anhDocGia", "Image");



                return dulieu;
            }
        };
        queue.add(request);
    }
    private void Init() {
        eedtTenKH = findViewById(R.id.eedtTenKH);
        eedtDiaChiKH = findViewById(R.id.eedtDiaChiKH);
        eedtTenTaiKhoanKH = findViewById(R.id.eedtTenTaiKhoanKH);
        eedtSDTKH = findViewById(R.id.eedtSDTKH);
        eedtMatKhauKH = findViewById(R.id.eedtMatKhauKH);
        btnSuaKH  = findViewById(R.id.btnSuaKH);
        btnXoa = findViewById(R.id.btnXoa);
        imgSuaKH = findViewById(R.id.imgSuaKH);
    }
}