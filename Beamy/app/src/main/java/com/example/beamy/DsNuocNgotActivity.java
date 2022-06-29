package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

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

public class DsNuocNgotActivity extends AppCompatActivity {
    GridView gvdsdouong;
    AdapterDanhSachSP adapterDanhSachSP;
    ArrayList<clsDanhSachSanPham> arrdsSanPham;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlgetDataDoUong = baseURL+ "/CuaHang/getDataNuocNgot.php";
    EditText edttimdouong;
    ImageButton ibntimdouong,ibtnQL;
    Spinner spdouong;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_nuoc_ngot);
        gvdsdouong = (GridView)findViewById(R.id.gvdsdouong);
        edttimdouong = (EditText)findViewById(R.id.edttimdouong);
        ibntimdouong = (ImageButton)findViewById(R.id.ibtntimdouong);
        spdouong = (Spinner)findViewById(R.id.spdouong);
        ibtnQL = findViewById(R.id.ibtnQL);
        arrayList = new ArrayList<>();
        arrdsSanPham = new ArrayList<>();
        adapterDanhSachSP = new AdapterDanhSachSP(DsNuocNgotActivity.this,R.layout.item_listview_ds_sp, arrdsSanPham);
        gvdsdouong.setAdapter(adapterDanhSachSP);
        LoadData(urlgetDataDoUong);
        arrayList.add("Sắp xếp tăng dần");
        arrayList.add("Sắp xếp giảm dần");
        ArrayAdapter adapter = new ArrayAdapter(DsNuocNgotActivity.this, android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdouong.setAdapter(adapter);
        spdouong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        adapterDanhSachSP.sortLon();
                        break;
                    case 1:
                        adapterDanhSachSP.sortNho();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ibtnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DsNuocNgotActivity.this,MainActivity.class);
                startActivity(intent3);
            }
        });
        gvdsdouong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DsNuocNgotActivity.this,ThongTinActivity.class);
                clsDanhSachSanPham clsDanhSachSanPham = new clsDanhSachSanPham(arrdsSanPham.get(position).getMasanpham(), arrdsSanPham.get(position).getTensanpham(), arrdsSanPham.get(position).getGia(), arrdsSanPham.get(position).getLoai(), arrdsSanPham.get(position).getHinhanh(), arrdsSanPham.get(position).getMota(),arrdsSanPham.get(position).getMaLoaiSP(),arrdsSanPham.get(position).getMaShop());
                intent.putExtra("dulieu", clsDanhSachSanPham);
                startActivity(intent);
            }
        });
        ibntimdouong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edttimdouong.getText().toString();
                adapterDanhSachSP.sortTruyen(s);
            }
        });
    }
    void LoadData(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
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
                adapterDanhSachSP.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DsNuocNgotActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}