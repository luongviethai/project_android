package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ChiTietDonHang extends AppCompatActivity {

    TextView edtTenSPDH, edtTenKHDH, edtDiaChiDH,edtSDTDH, txtSuaTTDH;
    ImageButton btnQL;
    clsHoaDon clsHoaDon;
    int id,tien,sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        Init();


        Intent intent = getIntent();

            clsHoaDon = (clsHoaDon) intent.getSerializableExtra("dulieuDH");

            id = clsHoaDon.getId();
            edtTenSPDH.setText(clsHoaDon.getTensanpham());
            //edtTenKHDH.setText(clsHoaDon.getTenkhachhang());
            edtDiaChiDH.setText(clsHoaDon.getDiachi());
            edtSDTDH.setText("(+84) " + String.valueOf(clsHoaDon.getSdt()));
            tien = clsHoaDon.getTien();

          txtSuaTTDH.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intentToEdit = new Intent();
                  intentToEdit.setClass(ChiTietDonHang.this, SuaDonHangActivity.class);

                  Bundle goidl = new Bundle();
                  goidl.putSerializable("duLieuDH", clsHoaDon);

                  intentToEdit.putExtras(goidl);

                  startActivity(intentToEdit);
                  return ;
              }
          });


    }

    private void Init() {
        edtTenSPDH = findViewById(R.id.edtTenSPDH);
        edtTenKHDH = findViewById(R.id.edtTenKHDH);
        edtDiaChiDH = findViewById(R.id.edtDiaChiDH);
        edtSDTDH = findViewById(R.id.edtSDTDH);
        txtSuaTTDH = findViewById(R.id.txtSuaTTDH);
        btnQL = findViewById(R.id.ibtnQL);
    }

}