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

public class SuaChua1Activity extends AppCompatActivity {
    GridView gvdssuachua;
    AdapterDanhSachSP adapterDanhSachDoAn;
    ArrayList<clsDanhSachSanPham> arrdsDoAn;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlgetDataSuaChua =baseURL+ "/CuaHang/getDataSuaChua.php";
    EditText edtimsuachua;
    ImageButton ibtntimsuachua;
    Spinner spsuachua;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_chua);
        gvdssuachua = (GridView)findViewById(R.id.gvdssuachua);
        edtimsuachua = (EditText)findViewById(R.id.edttimsuachua);
        ibtntimsuachua = (ImageButton)findViewById(R.id.ibtntimsuachua);
        spsuachua = (Spinner)findViewById(R.id.spsuachua);
        arrayList = new ArrayList<>();
        arrdsDoAn = new ArrayList<>();
        adapterDanhSachDoAn = new AdapterDanhSachSP(SuaChua1Activity.this,R.layout.item_listview_ds_sp,arrdsDoAn);
        gvdssuachua.setAdapter(adapterDanhSachDoAn);
        LoadData(urlgetDataSuaChua);
        arrayList.add("Sắp xếp tăng dần");
        arrayList.add("Sắp xếp giảm dần");
        ArrayAdapter adapter = new ArrayAdapter(SuaChua1Activity.this, android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spsuachua.setAdapter(adapter);
        spsuachua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        adapterDanhSachDoAn.sortLon();
                        break;
                    case 1:
                        adapterDanhSachDoAn.sortNho();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gvdssuachua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SuaChua1Activity.this,ThongTinActivity.class);
                clsDanhSachSanPham clsDanhSachSanPham = new clsDanhSachSanPham(arrdsDoAn.get(position).getMasanpham(),arrdsDoAn.get(position).getTensanpham(),arrdsDoAn.get(position).getGia(),arrdsDoAn.get(position).getLoai(),arrdsDoAn.get(position).getHinhanh(),arrdsDoAn.get(position).getMota(),arrdsDoAn.get(position).getMaLoaiSP(),arrdsDoAn.get(position).getMaShop());
                intent.putExtra("dulieu", clsDanhSachSanPham);
                startActivity(intent);
            }
        });
        ibtntimsuachua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edtimsuachua.getText().toString();
                adapterDanhSachDoAn.sortTruyen(s);
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
                        arrdsDoAn.add(new clsDanhSachSanPham(
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
                adapterDanhSachDoAn.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SuaChua1Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}