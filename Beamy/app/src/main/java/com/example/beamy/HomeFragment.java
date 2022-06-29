package com.example.beamy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GridView gvdsgiohang;
    TextView txttamtinh,txttongcong;
    Button btnthanhtoan;
    ArrayList<clsGioHang> arrClsGioHang;
    AdapterGioHang adapterGioHang;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String url =baseURL+ "/CuaHang/get/getDataGioHang.php?maKhachHang=";
    int tien,maKhachHang;
    int ship = 20000;
    int masanpham;
    String tendoan;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        gvdsgiohang = (GridView)view.findViewById(R.id.gvdsgiohang);
        arrClsGioHang =new ArrayList<>();
        adapterGioHang = new AdapterGioHang(getContext(),R.layout.dong_gio_hang, arrClsGioHang);
        gvdsgiohang.setAdapter(adapterGioHang);
        txttamtinh = (TextView)view.findViewById(R.id.txttamtinh);
        txttongcong = (TextView)view.findViewById(R.id.txttongcong);

        btnthanhtoan = (Button)view.findViewById(R.id.btndathang);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("tkSetting", Context.MODE_PRIVATE);

        if(sharedPreferences!= null) {
            maKhachHang = sharedPreferences.getInt("maKH", -1);
        }

        String url2 = url + maKhachHang;

        LoadData(url2);

        gvdsgiohang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),SuaSoLuongActivity.class);
                clsGioHang clsGioHang = new clsGioHang(arrClsGioHang.get(position).getMasanpham(), arrClsGioHang.get(position).getTensanpham(), arrClsGioHang.get(position).getHinhanh(), arrClsGioHang.get(position).getSoLuong(), arrClsGioHang.get(position).getTien());
                intent.putExtra("donhang", clsGioHang);
                startActivity(intent);
            }
        });
       /* txttongcong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i< arrClsDonHang.size(); i++){
                    tien += arrClsDonHang.get(i).getTien();
                    txttamtinh.setText(String.valueOf(tien)+"đ");

                    txttongcong.setText(String.valueOf(tien+ship)+"đ");

                    tendoan += arrClsDonHang.get(i).getTensanpham();
                }
            }
        });*/
        //LoadData(url);

        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ThanhToanActivity.class);
                Bundle bundle = new Bundle();

                String tongTien = txttongcong.getText().toString();
                String result = tongTien.substring(0, tongTien.length() - 1);

                bundle.putString("tongTien", result);

                bundle.putSerializable("ArrayList",(Serializable) arrClsGioHang);

                intent.putExtra("dulieuhoadon",bundle);
                startActivity(intent);
            }
        });
        return view;
    }
    void LoadPrices(){

        tendoan = "";

        for (int i = 0; i< arrClsGioHang.size(); i++){
            tien += arrClsGioHang.get(i).getTien();
            tendoan += arrClsGioHang.get(i).getTensanpham();

        }

        txttamtinh.setText(tien +"đ");
        txttongcong.setText((tien + ship) +"đ");

        //Toast.makeText(this.getActivity(),"Tổng: " + tendoan ,Toast.LENGTH_LONG).show();


    }


    void LoadData(String url){
        arrClsGioHang.clear();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(this.getActivity(),"Nhận: " + response , Toast.LENGTH_LONG).show();

                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrClsGioHang.add(new clsGioHang(
                                object.getInt("masanpham"),
                                object.getString("tensanpham"),
                                object.getString("hinhanh"),
                                object.getInt("soLuong"),
                                object.getInt("tien")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterGioHang.notifyDataSetChanged();

                LoadPrices();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
    @Override
    public void onResume() {

        //Toast.makeText(this.getActivity(),"Resume Số sp: " + arrClsGioHang.size() ,Toast.LENGTH_LONG).show();


        arrClsGioHang.clear();
        String url2 = url + maKhachHang;

        //LoadData(url2);
        super.onResume();
    }
}