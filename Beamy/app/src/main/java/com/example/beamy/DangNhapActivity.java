package com.example.beamy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class DangNhapActivity extends AppCompatActivity {

    EditText edtTenDN,edtMK;
    Button btnDN,btnDK;
    int maDuLieu = 1111;
    CheckBox chksaveacount;

    String tenTaiKhoan, matKhau;
    Integer maKhachHang;

    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;

    String urlCheckLogin = baseURL +"/CuaHang/check_login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        
        Init();

        //loadUserSetting();

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, ThemKhachHangActivity.class);
                startActivityForResult(intent, maDuLieu );
            }
        });

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((edtTenDN.getText().toString().trim().length() > 0) && (edtMK.getText().toString().trim().length() > 0)) {
                    CheckLogin(urlCheckLogin);

                }
                else
                    Toast.makeText(DangNhapActivity.this, "Hãy nhập đủ thông tin", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void CheckLogin(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().equals("false")) {

                    Toast.makeText(DangNhapActivity.this,"Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();


                }
                else{


                    maKhachHang = Integer.parseInt(response.toString());

                    Toast.makeText(DangNhapActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    doSave();

                    Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                    intent.putExtra("maDG", maKhachHang);

                    startActivity(intent);
          }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DangNhapActivity.this,"Chưa kết nối: " + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> dulieu=new HashMap<>();

                tenTaiKhoan = edtTenDN.getText().toString();
                matKhau = edtMK.getText().toString();

                // Gửi dữ liệu cho PHP
                dulieu.put("tenTaiKhoan", tenTaiKhoan);
                dulieu.put("matKhau", matKhau);

                return dulieu;
            }
        };
        queue.add(request);
    }



    private void loadUserSetting()  {

        SharedPreferences sharedPreferences= this.getSharedPreferences("tkSetting", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {


            maKhachHang = sharedPreferences.getInt("maKH", -1);

            if(maKhachHang!=-1) {
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                intent.putExtra("maDG", maKhachHang);

                startActivity(intent);
            }

        } else {
            Toast.makeText(this,"Use the default setting",Toast.LENGTH_LONG).show();
        }

    }




    // Called when user click to Save button.
    public void doSave()  {
        // The created file can only be accessed by the calling application
        // (or all applications sharing the same user ID).
        SharedPreferences sharedPreferences= this.getSharedPreferences("tkSetting", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();



        boolean bchk = chksaveacount.isChecked();
        if(!bchk)
        {
            //xóa mọi lưu trữ trước đó
            editor.clear();

            editor.putInt("maKH", maKhachHang);

            // Save.
            editor.apply();
        }
        else
        {
            //lưu vào editor
            editor.putString("tenTK", edtTenDN.getText().toString());
            editor.putString("MK", edtMK.getText().toString());
            editor.putInt("maKH", maKhachHang);

            editor.putBoolean("checked", bchk);

            // Save.
            editor.apply();

            Toast.makeText(this,"Đã lưu thông tin.",Toast.LENGTH_LONG).show();



        }




    }



    private void Init() {
        edtTenDN = findViewById(R.id.edtTenDN);
        edtMK = findViewById(R.id.edtMK);
        btnDN = findViewById(R.id.btnDN);
        btnDK = findViewById(R.id.btnDK);
        chksaveacount = findViewById(R.id.chksaveacount);
    }
}