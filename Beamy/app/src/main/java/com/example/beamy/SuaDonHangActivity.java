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

public class SuaDonHangActivity extends AppCompatActivity {
    EditText edtTenKHDH, edtDiaChiDH, edtSDTDH;
    TextView txtLưuTTDH,txtTenSPDH;
    Button btnXoaDH;
    clsHoaDon clsHoaDon;

     String tenkhachang, diachi, tensanpham;
     int  id,sdt, tien;

    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlDeleteDH= baseURL+"/CuaHang/deleteHoaDon.php";
    String urlUpdateDH = baseURL+"/CuaHang/updateDonHang.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_don_hang);

        edtTenKHDH = findViewById(R.id.edtTenKHDH);
        edtDiaChiDH = findViewById(R.id.edtDiaChiDH);
        edtSDTDH = findViewById(R.id.edtSDTDH);
        txtLưuTTDH = findViewById(R.id.txtLưuTTDH);
        btnXoaDH = findViewById(R.id.btnXoaDH);
        txtTenSPDH = findViewById(R.id.txtTenSPDH);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            clsHoaDon = (clsHoaDon) intent.getSerializableExtra("duLieuDH");

            /*Toast.makeText(ChiTietSachActivity.this, "Nhận: " + ttSach.getAnhSach(), Toast.LENGTH_SHORT).show();
             */
            txtTenSPDH.setText(clsHoaDon.getTensanpham());
            //edtTenKHDH.setText(clsHoaDon.getTenkhachhang());
            edtDiaChiDH.setText(clsHoaDon.getDiachi());
            edtSDTDH.setText(String.valueOf(clsHoaDon.getSdt()));


        }
        txtLưuTTDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDonhang(urlUpdateDH);

            }
        });

        btnXoaDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuXoa();
            }
        });
    }


    public void DeleteDH(String path)
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

                String mdg = String.valueOf(id);

                dulieu.put("id", mdg);


                return dulieu;
            }
        };
        queue.add(request);
    }


    public void MenuXoa()
    {
        AlertDialog.Builder canhbao = new AlertDialog.Builder(SuaDonHangActivity.this);
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

                DeleteDH(urlDeleteDH);

                Toast.makeText(SuaDonHangActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SuaDonHangActivity.this, DonHangCuaToiActivity.class);
                startActivity(intent);
                /*Toast.makeText(ChiTietSachActivity.this, "Hủy", Toast.LENGTH_SHORT).show();*/
            }
        });

        canhbao.show();
    }
    public void UpdateDonhang(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(SuaDonHangActivity.this, "Nhận: " + response, Toast.LENGTH_LONG).show();
                if(response.toString().equals("Done")) {
                    Toast.makeText(SuaDonHangActivity.this, "Đã sửa", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SuaDonHangActivity.this, DonHangCuaToiActivity.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(SuaDonHangActivity.this,"Thất bại", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SuaDonHangActivity.this,"Chưa kết nối: " + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> dulieu=new HashMap<>();


                tensanpham = txtTenSPDH.getText().toString();
                tenkhachang = edtTenKHDH.getText().toString();
                diachi = edtDiaChiDH.getText().toString();
                sdt =  Integer.parseInt(edtSDTDH.getText().toString());



                dulieu.put("id", String.valueOf(id));
                dulieu.put("tensanpham", tensanpham);
                dulieu.put("tenkhachhang", tenkhachang);
                dulieu.put("diachi", diachi);
                dulieu.put("sdt", String.valueOf(sdt));
                dulieu.put("tien",String.valueOf(tien) );






                return dulieu;
            }
        };
        queue.add(request);
    }
}