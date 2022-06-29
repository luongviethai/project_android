package com.example.beamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.beamy.clsLoaiSP;

import java.util.ArrayList;
import java.util.List;

public class SpinnerLoaiSPAdapter extends ArrayAdapter<clsLoaiSP> {
    private Context context;
    private int layout;
    private clsLoaiSP loaiSP;
    private List<clsLoaiSP>  mangLoaiSP;


    public SpinnerLoaiSPAdapter(Context context, int resource, ArrayList<clsLoaiSP> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layout = resource;
        this. mangLoaiSP = objects;

    }

    public View getView(int position, View convertVew, ViewGroup parent){
        convertVew = LayoutInflater.from(context).inflate(R.layout.item_spinner_loai_sp, parent, false);

       loaiSP =  mangLoaiSP.get(position);
        TextView txtTentacGia = (TextView) convertVew.findViewById(R.id.txtLoaiSPSpn);

        txtTentacGia.setText(loaiSP.getTenLoaiSP());

        return convertVew;

    }


    public View getDropDownView(int position, View convertVew, ViewGroup parent){
        convertVew = LayoutInflater.from(context).inflate(R.layout.item_spinner_loai_sp, parent, false);

        loaiSP =  mangLoaiSP.get(position);
        TextView txtTentacGia = (TextView) convertVew.findViewById(R.id.txtLoaiSPSpn);

        txtTentacGia.setText(loaiSP.getTenLoaiSP());

        return convertVew;

    }

}
