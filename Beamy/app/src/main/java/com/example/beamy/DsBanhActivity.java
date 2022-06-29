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

public class DsBanhActivity extends AppCompatActivity {
    AdapterDanhSachSP adapterDanhSachDoAn;
    ArrayList<clsDanhSachSanPham> arrdsDoAn;
    GridView gvdscom;
    EditText edttimcom;
    ImageButton ibtntimcom,ibtnQL;
    ArrayList<String> arrayList;
    Spinner spcom;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlGetDataCom =baseURL+ "/CuaHang/getDataBanh.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_banh);
        arrdsDoAn = new ArrayList<>();
        gvdscom = (GridView)findViewById(R.id.gvdscom);
        edttimcom = (EditText)findViewById(R.id.edttimcom);
        ibtntimcom = (ImageButton)findViewById(R.id.ibtntimcom);
        spcom = (Spinner)findViewById(R.id.spcom);
        ibtnQL= findViewById(R.id.ibtnQL);
        adapterDanhSachDoAn = new AdapterDanhSachSP(DsBanhActivity.this,R.layout.item_listview_ds_sp,arrdsDoAn);
        gvdscom.setAdapter(adapterDanhSachDoAn);
        arrayList = new ArrayList<>();
        arrayList.add("Sắp xếp tăng dần");
        arrayList.add("Sắp xếp giảm dần");
        ArrayAdapter adapter = new ArrayAdapter(DsBanhActivity.this, android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spcom.setAdapter(adapter);

        ibtnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DsBanhActivity.this,MainActivity.class);
                startActivity(intent3);
            }
        });
        spcom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        LoadData(urlGetDataCom);
        gvdscom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DsBanhActivity.this,ThongTinActivity.class);
                clsDanhSachSanPham clsDanhSachSanPham = new clsDanhSachSanPham(arrdsDoAn.get(position).getMasanpham(),arrdsDoAn.get(position).getTensanpham(),arrdsDoAn.get(position).getGia(),arrdsDoAn.get(position).getLoai(),arrdsDoAn.get(position).getHinhanh(),arrdsDoAn.get(position).getMota(),arrdsDoAn.get(position).getMaLoaiSP(),arrdsDoAn.get(position).getMaShop());
                intent.putExtra("dulieu", clsDanhSachSanPham);
                startActivity(intent);
            }
        });
        ibtntimcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edttimcom.getText().toString();
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
                Toast.makeText( DsBanhActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}