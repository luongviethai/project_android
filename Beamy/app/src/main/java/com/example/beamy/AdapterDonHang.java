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

public class AdapterDonHang extends BaseAdapter {
    Context context;
    int layout;
    List<clsDonHang> arrClsDonHang;

    public AdapterDonHang(Context context, int layout, List<clsDonHang> arrClsDonHang) {
        this.context = context;
        this.layout = layout;
        this.arrClsDonHang = arrClsDonHang;
    }
    @Override
    public int getCount() {
        return arrClsDonHang.size();
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
        TextView txtmahoadon = (TextView)convertView.findViewById(R.id.txtmahoadon);
        TextView txttenhoadon = (TextView)convertView.findViewById(R.id.txttenhoadon);
        ImageView imgAnhDH = (ImageView)convertView.findViewById(R.id.imgAnhDH) ;
        TextView txtSoLuong = (TextView)convertView.findViewById(R.id.txtSoLuong);
        TextView txtGia = (TextView)convertView.findViewById(R.id.txtGia);

        TextView txttenkhachhang = (TextView)convertView.findViewById(R.id.txttenkhachhang);
        TextView txttendiachi =(TextView)convertView.findViewById(R.id.txttendiachi);
        TextView txtsdt = (TextView)convertView.findViewById(R.id.txtsdt);
        TextView txtnhantienhoadon = (TextView)convertView.findViewById(R.id.txtnhantienhoadon);
        TextView txtTrangThai = (TextView)convertView.findViewById(R.id.txtTrangThai);


        txtmahoadon.setText("Mã đơn hàng: "+ arrClsDonHang.get(position).getMaDonHang());
        txttenhoadon.setText("Tên sản phẩm: "+ arrClsDonHang.get(position).getTensanpham());
        Picasso.get().load(arrClsDonHang.get(position).getHinhanh()).into(imgAnhDH);
        txtSoLuong.setText("Số lượng: "+ arrClsDonHang.get(position).getSoLuong());
        txtGia.setText("Tổng: "+ arrClsDonHang.get(position).getGia() + "đ");

        txttenkhachhang.setText("Tên khách hàng: "+ arrClsDonHang.get(position).getTenKhachHang());
        txttendiachi.setText("Địa chỉ giao hàng: "+ arrClsDonHang.get(position).getDiaChi());
        txtsdt.setText("Số điện thoại: "+ arrClsDonHang.get(position).getSdt());
        txtnhantienhoadon.setText("Tổng thanh toán: "+  arrClsDonHang.get(position).getTien()+"đ");
        txtTrangThai.setText("Trạng thái: "+  arrClsDonHang.get(position).getTrangThai());
        return convertView;
    }
}
