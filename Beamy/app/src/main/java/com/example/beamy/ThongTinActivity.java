package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ThongTinActivity extends AppCompatActivity {
    TextView txtnhanten,txtnhangia,txtnhanmota,txtgiamgia,txtkhuvucship;
    ImageView imgnhanhinh;
    Button btnmuangay, btnTtMuaHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        txtnhanten = (TextView)findViewById(R.id.txtnhanten);
        txtnhangia = (TextView)findViewById(R.id.txtnhangia);
        txtnhanmota = (TextView)findViewById(R.id.txtnhanmota);
        imgnhanhinh = (ImageView)findViewById(R.id.imgnhanhinh);
        txtgiamgia = (TextView)findViewById(R.id.txtgiamgia);
        btnmuangay = (Button)findViewById(R.id.btnmuangay);
        txtkhuvucship = (TextView)findViewById(R.id.txtkhuvucship);
        btnTtMuaHang = (Button) findViewById(R.id.btnTtMuaHang);

        Intent intent = getIntent();
        clsDanhSachSanPham clsDanhSachSanPham = (clsDanhSachSanPham)intent.getSerializableExtra("dulieu");
        txtnhanten.setText(clsDanhSachSanPham.getTensanpham());
        Picasso.get().load(clsDanhSachSanPham.getHinhanh()).into(imgnhanhinh);
        txtnhangia.setText("Giá: " + clsDanhSachSanPham.getGia()+"đ");
        txtnhanmota.setText("Mô tả: " + clsDanhSachSanPham.getMota());

        btnmuangay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ThongTinActivity.this,SoLuongActivity.class);
                clsDanhSachSanPham clsDanhSachSanPham1 = new clsDanhSachSanPham(clsDanhSachSanPham.getMasanpham(), clsDanhSachSanPham.getTensanpham(), clsDanhSachSanPham.getGia(), clsDanhSachSanPham.getLoai(), clsDanhSachSanPham.getHinhanh(), clsDanhSachSanPham.getMota(),clsDanhSachSanPham.getMaLoaiSP(),clsDanhSachSanPham.getMaShop());
                intent1.putExtra("dulieudoan", clsDanhSachSanPham1);
                startActivity(intent1);
            }
        });
        txtgiamgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ThongTinActivity.this,GiamGiaActivity.class);
                startActivity(intent2);
            }
        });
        btnTtMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ThongTinActivity.this,MainActivity.class);
                startActivity(intent3);
            }
        });
        txtkhuvucship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ThongTinActivity.this,GiaoHangActivity.class);
                startActivity(intent1);
            }
        });
    }
}