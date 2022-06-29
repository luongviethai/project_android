package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ChiTIetKhachHangActivity extends AppCompatActivity {

    TextView edtTenCT, edtSDTCT, edtDiaChiCT, edtTenTaiKhoanCT,edtMatKhauCT,txtSuaTTKH ;
    ImageView imgKhachHangCT;
    ImageButton ibtnQL;
    int maKhachHang;
    clsDanhSachKhachHang danhSachKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_t_iet_khach_hang);

        Init();

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null ){
        danhSachKhachHang = (clsDanhSachKhachHang) intent.getSerializableExtra("dulieuKH");

            maKhachHang = danhSachKhachHang.getMaKhachHang();
            edtTenCT.setText(danhSachKhachHang.getTenKhachHang());
            edtSDTCT.setText(danhSachKhachHang.getSdt());
            edtDiaChiCT.setText(danhSachKhachHang.getDiaChi());
            edtTenTaiKhoanCT.setText(danhSachKhachHang.getTenTaiKhoan());
            edtMatKhauCT.setText(danhSachKhachHang.getMatKhau());
            Picasso.get().load(danhSachKhachHang.getAnhKhachHang()).into(imgKhachHangCT);

        }
        ibtnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtSuaTTKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToEdit = new Intent();
                intentToEdit.setClass(ChiTIetKhachHangActivity.this, SuaKhachHangActivity.class);

                Bundle goidl = new Bundle();
                goidl.putSerializable("duLieu", danhSachKhachHang);

                intentToEdit.putExtras(goidl);

                startActivity(intentToEdit);
                return ;
            }
        });
    }

    private void Init() {
        edtTenCT = findViewById(R.id.edtTenCT);
        edtSDTCT = findViewById(R.id.edtSDTCT);
        edtDiaChiCT = findViewById(R.id.edtDiaChiCT);
        edtTenTaiKhoanCT = findViewById(R.id.edtTenTaiKhoanCT);
        edtMatKhauCT = findViewById(R.id.edtMatKhauCT);
        ibtnQL = findViewById(R.id.ibtnQL);
        txtSuaTTKH = findViewById(R.id.txtSuaTTKH);
        imgKhachHangCT = findViewById(R.id.imgKhachHangCT);
    }
}