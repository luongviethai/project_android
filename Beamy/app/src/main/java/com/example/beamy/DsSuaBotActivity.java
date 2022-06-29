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

public class DsSuaBotActivity extends AppCompatActivity {
    GridView gvdstrasua;
    ArrayList<clsDanhSachSanPham> arrdsDoAn;
    AdapterDanhSachSP adapterDanhSachDoAn;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlGetDataTraSua = baseURL+ "/CuaHang/getDataSua.php";
    EditText edttimtrasua;
    ImageButton ibtntimtrasua,ibtnQL;
    Spinner sptrasua;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_sua);
        gvdstrasua = (GridView)findViewById(R.id.gvdstrasua);
        edttimtrasua = (EditText)findViewById(R.id.edttimtrasua);
        ibtntimtrasua = (ImageButton)findViewById(R.id.ibtntimtrasua);
        sptrasua = (Spinner)findViewById(R.id.sptrasua);
        ibtnQL =findViewById(R.id.ibtnQL);
        arrayList = new ArrayList<>();
        arrdsDoAn = new ArrayList<>();
        adapterDanhSachDoAn = new AdapterDanhSachSP(DsSuaBotActivity.this,R.layout.item_listview_ds_sp,arrdsDoAn);
        gvdstrasua.setAdapter(adapterDanhSachDoAn);
        LoadData(urlGetDataTraSua);
        arrayList.add("Sắp xếp tăng dần");
        arrayList.add("Sắp xếp giảm dần");
        ArrayAdapter adapter = new ArrayAdapter(DsSuaBotActivity.this, android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sptrasua.setAdapter(adapter);
        sptrasua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        ibtnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DsSuaBotActivity.this,MainActivity.class);
                startActivity(intent3);
            }
        });
        gvdstrasua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DsSuaBotActivity.this,ThongTinActivity.class);
                clsDanhSachSanPham clsDanhSachSanPham = new clsDanhSachSanPham(arrdsDoAn.get(position).getMasanpham(),arrdsDoAn.get(position).getTensanpham(),arrdsDoAn.get(position).getGia(),arrdsDoAn.get(position).getLoai(),arrdsDoAn.get(position).getHinhanh(),arrdsDoAn.get(position).getMota(),arrdsDoAn.get(position).getMaLoaiSP(),arrdsDoAn.get(position).getMaShop());
                intent.putExtra("dulieu", clsDanhSachSanPham);
                startActivity(intent);
            }
        });
        ibtntimtrasua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edttimtrasua.getText().toString();
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
                Toast.makeText(DsSuaBotActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}