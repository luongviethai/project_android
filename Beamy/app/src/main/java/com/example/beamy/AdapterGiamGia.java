package com.example.beamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterGiamGia extends BaseAdapter {
    Context context;
    int layout;
    List<clsGiamgia> arrGiamGia;

    public AdapterGiamGia(Context context, int layout, List<clsGiamgia> arrGiamGia) {
        this.context = context;
        this.layout = layout;
        this.arrGiamGia = arrGiamGia;
    }

    @Override
    public int getCount() {
        return arrGiamGia.size();
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
        convertView = inflater.inflate(layout, null);
        TextView txtgiamgia = (TextView)convertView.findViewById(R.id.txtmagiamgia);
        TextView txtphantram = (TextView)convertView.findViewById(R.id.txtphantram);
        txtgiamgia.setText(arrGiamGia.get(position).getMagiamgia());
        txtphantram.setText(arrGiamGia.get(position).getPhantram());
        return convertView;
    }
}
