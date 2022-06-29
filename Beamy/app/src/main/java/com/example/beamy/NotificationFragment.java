package com.example.beamy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int maKhachHang;
    ViewFlipper viewFlipper;
    AdapterSanPham adapter;
    ArrayList<clsSanpham> arrClsSanpham;
    GridView gvdoan;
    ImageButton ibtntim;
    GridView gvdsdoan;
    AdapterDanhSachSP adapterDanhSachSanPham;
    ArrayList<clsDanhSachSanPham> arrdsSanPham;
    EditText edttimkiem;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;

    //String urlGetData = "http://192.168.0.102/CuaHang/getData.php";
    String urlGetData = baseURL+"/CuaHang/get/getSanPham.php";
    String urlSearch = baseURL +"/CuaHang/search.php";
    Animation in, out;
    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        viewFlipper = (ViewFlipper)view.findViewById(R.id.viewFlipper);
        in = AnimationUtils.loadAnimation(getContext(),R.anim.face);
        out = AnimationUtils.loadAnimation(getContext(),R.anim.face_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        gvdoan = (GridView)view.findViewById(R.id.gvloaidoan);
        gvdsdoan = (GridView)view.findViewById(R.id.gvdanhsahcdoan);
        edttimkiem = (EditText)view.findViewById(R.id.edttimkiem);
        ibtntim = (ImageButton)view.findViewById(R.id.ibtntim);
        arrdsSanPham = new ArrayList<>();
        arrClsSanpham = new ArrayList<>();
        adapterDanhSachSanPham = new AdapterDanhSachSP(getContext(),R.layout.item_listview_ds_sp, arrdsSanPham);
        adapter = new AdapterSanPham(getContext(),R.layout.item_ds_danh_muc, arrClsSanpham);


        arrClsSanpham.add(new clsSanpham("Bánh",R.drawable.banh));
        arrClsSanpham.add(new clsSanpham("Bia",R.drawable.tiger));
        arrClsSanpham.add(new clsSanpham("Sữa",R.drawable.similac));
        arrClsSanpham.add(new clsSanpham("Nước ngọt",R.drawable.nuoc));

        //arrDoAn.add(new DoAn("Healthy",R.drawable.healthyjpg));

        arrClsSanpham.add(new clsSanpham("Sữa tươi",R.drawable.suatuoi));
        gvdoan.setAdapter(adapter);
        gvdsdoan.setAdapter(adapterDanhSachSanPham);

        LoadData(urlGetData);



        gvdsdoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),ThongTinActivity.class);
                clsDanhSachSanPham clsDanhSachSanPham = new clsDanhSachSanPham(arrdsSanPham.get(position).getMasanpham(), arrdsSanPham.get(position).getTensanpham(), arrdsSanPham.get(position).getGia(), arrdsSanPham.get(position).getLoai(), arrdsSanPham.get(position).getHinhanh(), arrdsSanPham.get(position).getMota(),arrdsSanPham.get(position).getMaLoaiSP(),arrdsSanPham.get(position).getMaShop());
                intent.putExtra("dulieu", clsDanhSachSanPham);
                startActivity(intent);
            }
        });
        gvdoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getContext(), DsBanhActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getContext(), DsBiaActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getContext(), DsSuaBotActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getContext(), DsNuocNgotActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getContext(), DsSuaTuoiActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(getContext(),SuaChua1Activity.class);
                        startActivity(intent5);
                        break;
                }
            }
        });
        ibtntim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edttimkiem.getText().toString();
                adapterDanhSachSanPham.sortTruyen(s);
            }
        });
        return view;
    }
    void LoadData(String url){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0 ;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrdsSanPham.add(new clsDanhSachSanPham(
                                object.getInt("masanpham"),
                                object.getString("tensanpham"),
                                object.getInt("gia"),
                                object.getString("loai"),
                                object.getString("hinhanh"),
                                object.getString("mota"),
                                object.getInt("maLoaiSP"),
                                object.getInt("maShop")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterDanhSachSanPham.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                edttimkiem.setText(error.toString());
            }
        });
        queue.add(request);
    }
}