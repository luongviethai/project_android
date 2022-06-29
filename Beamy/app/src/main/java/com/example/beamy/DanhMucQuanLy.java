package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DanhMucQuanLy extends AppCompatActivity {
    Button btnQLSP, btnQLKH, btnQLDH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_quan_ly);

        btnQLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhMucQuanLy.this, ShopKhachHang.class);
                startActivity(intent);
            }
        });

        btnQLSP = findViewById(R.id.btnQLSP);
        btnQLKH = findViewById(R.id.btnQLKH);
        btnQLDH = findViewById(R.id.btnQLDH);
    }
}