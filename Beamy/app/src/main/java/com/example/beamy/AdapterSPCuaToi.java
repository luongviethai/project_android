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

public class AdapterSPCuaToi extends ArrayAdapter<clsDanhSachSanPham> {

    private Context context;
    private int layout;
    private clsDanhSachSanPham spCuaToi;
    private List<clsDanhSachSanPham> mangSanSpham;


    public AdapterSPCuaToi(Context context, int resource, ArrayList<clsDanhSachSanPham> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layout = resource;
        this.mangSanSpham = objects;
    }

    public View getView(int position, View convertVew, ViewGroup parent) {
        convertVew = LayoutInflater.from(context).inflate(R.layout.item_grivew_ds_sp, parent, false);


        TextView txtTenSanPham = (TextView) convertVew.findViewById(R.id.txtTenSanPham);
        TextView txtGiaSPCT = (TextView) convertVew.findViewById(R.id.txtGiaSPCT);

        ImageView imgSPCT = (ImageView) convertVew.findViewById(R.id.imgSPCT);

        spCuaToi= mangSanSpham.get(position);

        txtTenSanPham.setText(spCuaToi.getTensanpham());
        txtGiaSPCT.setText("Giá: "+spCuaToi.getGia()+"đ");
        //txtsdtKhachHang.setText(khachHang.getSdt());

        Picasso.get().load(spCuaToi.getHinhanh()).into(imgSPCT);



        return convertVew;
    }
}
