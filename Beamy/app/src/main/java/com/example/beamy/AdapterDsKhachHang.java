package com.example.beamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

    public class AdapterDsKhachHang extends ArrayAdapter<clsDanhSachKhachHang> {


        private Context context;
        private int layout;
        private clsDanhSachKhachHang khachHang;
        private List<clsDanhSachKhachHang> mangKhachHang;


        public AdapterDsKhachHang(Context context, int resource, ArrayList<clsDanhSachKhachHang> objects) {
            super(context, resource, objects);

            this.context = context;
            this.layout = resource;
            this.mangKhachHang = objects;
        }

        public View getView(int position, View convertVew, ViewGroup parent) {
            convertVew = LayoutInflater.from(context).inflate(R.layout.item_listview_ds_kh, parent, false);


            TextView txtTenKhachHang = (TextView) convertVew.findViewById(R.id.txtTenKhachHang);
            TextView txtDiaChiKhachhang = (TextView) convertVew.findViewById(R.id.txtDiaChiKhachHang);
            TextView txtsdtKhachHang = (TextView) convertVew.findViewById(R.id.txtsdtKhachHang);
            ImageView imgAnhKhachHang = (ImageView) convertVew.findViewById(R.id.imgKhachHang);

            khachHang = mangKhachHang.get(position);

            txtTenKhachHang.setText(khachHang.getTenKhachHang());
            txtDiaChiKhachhang.setText(khachHang.getDiaChi());
            txtsdtKhachHang.setText(khachHang.getSdt());

            Picasso.get().load(khachHang.getAnhKhachHang()).into(imgAnhKhachHang);



            return convertVew;
        }
    }
