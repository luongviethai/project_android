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

public class SuaSanPhamCT extends AppCompatActivity {
    ImageView imgSuaSP;
    TextView eedtLoaiSP;
    Button btnSuaSP, btnXoa;
    EditText eedtTenSP, eedtGiaSP, eedtMotaSP;
    clsDanhSachSanPham spCuaToi;

    private String masanpham, tensanpham, gia, loai, hinhanh, mota;

    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlDeleteSPCT= baseURL+"/CuaHang/deleteSPCT.php";
    String urlUpdateSPCT =baseURL+ "/CuaHang/updateSanPhamCT.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham_c_t);

        Init();
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            spCuaToi = (clsDanhSachSanPham) intent.getSerializableExtra("duLieuS");

            /*Toast.makeText(ChiTietSachActivity.this, "Nhận: " + ttSach.getAnhSach(), Toast.LENGTH_SHORT).show();

             */

            masanpham =String.valueOf(spCuaToi.getMasanpham());
            eedtTenSP.setText(spCuaToi.getTensanpham());
            eedtGiaSP.setText(String.valueOf(spCuaToi.getGia()));
            loai=spCuaToi.getLoai();
            eedtMotaSP.setText(spCuaToi.getMota());

            Picasso.get().load(spCuaToi.getHinhanh()).into(imgSuaSP);



        }
        btnSuaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSPCT(urlUpdateSPCT);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuXoa();
            }
        });
    }


    public void DeleteSPCT(String path)
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

                String mdg = String.valueOf(masanpham);

                dulieu.put("masanpham", mdg);


                return dulieu;
            }
        };
        queue.add(request);
    }


    public void MenuXoa()
    {
        AlertDialog.Builder canhbao = new AlertDialog.Builder(SuaSanPhamCT.this);
        canhbao.setMessage("Bạn có muốn xóa không?");
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

                DeleteSPCT(urlDeleteSPCT);

                Toast.makeText(SuaSanPhamCT.this, "Đã xóa", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SuaSanPhamCT.this, ShopKhachHang.class);
                startActivity(intent);
                /*Toast.makeText(ChiTietSachActivity.this, "Hủy", Toast.LENGTH_SHORT).show();*/
            }
        });

        canhbao.show();
    }
    public void UpdateSPCT(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(SuaSanPhamCT.this, "Nhận: " + response, Toast.LENGTH_LONG).show();
                if(response.toString().equals("Done")) {
                    Toast.makeText(SuaSanPhamCT.this, "Đã sửa", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SuaSanPhamCT.this, ShopKhachHang.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(SuaSanPhamCT.this,"Thất bại", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SuaSanPhamCT.this,"Chưa kết nối: " + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> dulieu=new HashMap<>();


                tensanpham = eedtTenSP.getText().toString();
                gia = eedtGiaSP.getText().toString();
                //loai = eedtLoaiSP.getText().toString();
                mota = eedtMotaSP.getText().toString();


                dulieu.put("masanpham", String.valueOf(masanpham));
                dulieu.put("tensanpham", tensanpham);
                dulieu.put("gia", gia);
                dulieu.put("loai", loai);
                dulieu.put("mota", mota);

                dulieu.put("hinhanh", "Image");



                return dulieu;
            }
        };
        queue.add(request);
    }
    private void Init() {
        imgSuaSP = findViewById(R.id.imgSuaSP);
        btnSuaSP = findViewById(R.id.btnSuaSP);
        btnXoa = findViewById(R.id.btnXoa);
        eedtTenSP = findViewById(R.id.eedtTenSP);
        eedtGiaSP = findViewById(R.id.eedtGiaSP);
        //eedtLoaiSP = findViewById(R.id.eedtLoaiSP);
        eedtMotaSP = findViewById(R.id.eedtMotaSP);
    }
}