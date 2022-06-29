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

public class AdapterDanhSachSP extends BaseAdapter {
    Context context;
    int layout;
    List<clsDanhSachSanPham> arrdsSanPham;

    public AdapterDanhSachSP(Context context, int layout, List<clsDanhSachSanPham> arrdsSanPham) {
        this.context = context;
        this.layout = layout;
        this.arrdsSanPham = arrdsSanPham;
    }
    @Override
    public int getCount() {
        return arrdsSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void sortTruyen(String s){
        s = s.toUpperCase();
        int k =0;
        for(int i = 0; i< arrdsSanPham.size(); i++){
            clsDanhSachSanPham clsDanhSachSanPham = arrdsSanPham.get(i);
            String ten = clsDanhSachSanPham.getTensanpham().toUpperCase();
            if(ten.indexOf(s)>=0){
                arrdsSanPham.set(i, arrdsSanPham.get(k));
                arrdsSanPham.set(k, clsDanhSachSanPham);
                k++;
            }
        }
        notifyDataSetChanged();
    }
    public void sortLon(){
        for(int i = 0; i< arrdsSanPham.size(); i++){
            for (int j = i+1; j< arrdsSanPham.size(); j++){
                clsDanhSachSanPham clsDanhSachSanPham = arrdsSanPham.get(i);
                clsDanhSachSanPham clsDanhSachSanPham1 = arrdsSanPham.get(j);
                int gia = clsDanhSachSanPham.getGia();
                int gia2 = clsDanhSachSanPham1.getGia();
                if (gia>gia2){
                    arrdsSanPham.set(i, clsDanhSachSanPham1);
                    arrdsSanPham.set(j, clsDanhSachSanPham);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void sortNho(){
        for(int i = 0; i< arrdsSanPham.size(); i++){
            for (int j = i+1; j< arrdsSanPham.size(); j++){
                clsDanhSachSanPham clsDanhSachSanPham = arrdsSanPham.get(i);
                clsDanhSachSanPham clsDanhSachSanPham1 = arrdsSanPham.get(j);
                int gia = clsDanhSachSanPham.getGia();
                int gia2 = clsDanhSachSanPham1.getGia();
                if (gia<gia2){
                    arrdsSanPham.set(i, clsDanhSachSanPham1);
                    arrdsSanPham.set(j, clsDanhSachSanPham);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        //TextView txtmadoan = (TextView)convertView.findViewById(R.id.txtmadoan);
        TextView txtten = (TextView)convertView.findViewById(R.id.txtten);
        ImageView ibtndshinhanh = (ImageView)convertView.findViewById(R.id.ibtndshinhanh);
        TextView txtgiadoan = (TextView)convertView.findViewById(R.id.txtgiadoan);
        //txtmadoan.setText("Mã sản phẩm: "+arrdsDoAn.get(position).getMadoan());
        txtten.setText(arrdsSanPham.get(position).getTensanpham());
        txtgiadoan.setText("Giá: "+String.valueOf(arrdsSanPham.get(position).getGia())+"đ");
        Picasso.get().load(arrdsSanPham.get(position).getHinhanh()).into(ibtndshinhanh);
        return convertView;
    }
}
