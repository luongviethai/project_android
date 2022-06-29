package com.example.beamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterSanPham extends BaseAdapter {
    Context context;
    int layout;
    List<clsSanpham> arrClsSanpham;

    public AdapterSanPham(Context context, int layout, List<clsSanpham> arrClsSanpham) {
        this.context = context;
        this.layout = layout;
        this.arrClsSanpham = arrClsSanpham;
    }

    @Override
    public int getCount() {
        return arrClsSanpham.size();
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
        TextView txtdoan = (TextView)convertView.findViewById(R.id.txttendoan);
        ImageView ibtnhinh = (ImageView) convertView.findViewById(R.id.ibtnhinhdoan);
        txtdoan.setText(arrClsSanpham.get(position).getTenDoAn());
        ibtnhinh.setImageResource(arrClsSanpham.get(position).getHinhAnhDoAn());
        return convertView;
    }
}
