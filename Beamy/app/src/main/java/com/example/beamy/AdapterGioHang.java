package com.example.beamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterGioHang extends BaseAdapter {
    Context context;
    int layout;
    List<clsGioHang> dsGioHang;

    public AdapterGioHang(Context context, int layout, List<clsGioHang> dsGioHang) {
        this.context = context;
        this.layout = layout;
        this.dsGioHang = dsGioHang;
    }

    @Override
    public int getCount() {
        return dsGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView txtten = (TextView)convertView.findViewById(R.id.txttendonhang);
        TextView txtsoluong = (TextView)convertView.findViewById(R.id.txtsoluongdonhang);
        TextView txtgia = (TextView)convertView.findViewById(R.id.txtgiadonhang);

        ImageView imgGH = (ImageView)convertView.findViewById(R.id.imgGH);
        Picasso.get().load(dsGioHang.get(position).getHinhanh()).into(imgGH);

        txtten.setText(dsGioHang.get(position).getTensanpham());
        txtsoluong.setText(String.valueOf(dsGioHang.get(position).getSoLuong())+"x");
        txtgia.setText(String.valueOf(dsGioHang.get(position).getTien())+"đ");
        return convertView;
    }
}
