package com.example.beamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterGiaoHang extends BaseAdapter {
    Context context;
    int layout;
    List<clsGiaoHang> arrClsGiaoHang;

    public AdapterGiaoHang(Context context, int layout, List<clsGiaoHang> arrClsGiaoHang) {
        this.context = context;
        this.layout = layout;
        this.arrClsGiaoHang = arrClsGiaoHang;
    }

    @Override
    public int getCount() {
        return arrClsGiaoHang.size();
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
        TextView txttenkhuvuc = (TextView)convertView.findViewById(R.id.txttenkhuvuc);
        TextView txttienship = (TextView)convertView.findViewById(R.id.txttienship);
        txttenkhuvuc.setText(arrClsGiaoHang.get(position).getKhuvuc());
        txttienship.setText(String.valueOf(arrClsGiaoHang.get(position).getTienship()));
        return convertView;
    }
}
