package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ChiTietSanPhamCT extends AppCompatActivity {
    TextView edtTenSPCT, edtGiaCT, edtLoaiCT, edtMotaCT,txtSuaTTSP ;
    ImageView imgSanPhamCT;
    ImageButton ibtnQL;
    String masanpham,loai;
    clsDanhSachSanPham spCuaToi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham_c_t);

        Init();

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null ){
            spCuaToi = (clsDanhSachSanPham) intent.getSerializableExtra("dulieuSP");

            masanpham =String.valueOf(spCuaToi.getMasanpham()) ;
            edtTenSPCT.setText(spCuaToi.getTensanpham());
            edtGiaCT.setText(String.valueOf(spCuaToi.getGia()));
            loai=spCuaToi.getLoai();
            edtMotaCT.setText(spCuaToi.getMota());

            Picasso.get().load(spCuaToi.getHinhanh()).into(imgSanPhamCT);

        }

        ibtnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSanPhamCT.this,ShopKhachHang.class);
                startActivity(intent);

            }
        });
        txtSuaTTSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToEdit = new Intent();
                intentToEdit.setClass(ChiTietSanPhamCT.this, SuaSanPhamCT.class);

                Bundle goidl = new Bundle();
                goidl.putSerializable("duLieuS", spCuaToi);

                intentToEdit.putExtras(goidl);

                startActivity(intentToEdit);
                return ;
            }
        });
    }





    private void Init() {
        edtTenSPCT = findViewById(R.id.edtTenSPCT);
        edtGiaCT = findViewById(R.id.edtGiaCT);
       // edtLoaiCT = findViewById(R.id.edtLoaiCT);
        edtMotaCT = findViewById(R.id.edtMotaCT);
        txtSuaTTSP = findViewById(R.id.txtSuaTTSP);
        ibtnQL = findViewById(R.id.ibtnQL);
        imgSanPhamCT = findViewById(R.id.imgSanPhamCT);
    }
}