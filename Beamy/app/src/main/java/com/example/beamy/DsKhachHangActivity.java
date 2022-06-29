package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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

public class DsKhachHangActivity extends AppCompatActivity {

    ListView lvwDsKhachHang;
    EditText edttimkhachhang;
    ImageButton ibtntimkhachhang, ibtnThemKH;
    AdapterDsKhachHang adapterDsKhachHang;
    ArrayList <clsDanhSachKhachHang> arrdsKhachHang;
    ArrayList<String> arrayList;
    clsDanhSachKhachHang khachHang;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlGetKhachHang = baseURL +"/CuaHang/getKhachHang.php";
    int maDuLieu = 528;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_khach_hang);

        LoadData(urlGetKhachHang);
        Init();
        arrayList = new ArrayList<>();
        arrdsKhachHang = new ArrayList<>();
        adapterDsKhachHang = new AdapterDsKhachHang(DsKhachHangActivity.this,R.layout.item_listview_ds_kh, arrdsKhachHang);
        lvwDsKhachHang.setAdapter(adapterDsKhachHang);

        lvwDsKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DsKhachHangActivity.this,ChiTIetKhachHangActivity.class);
                clsDanhSachKhachHang clsDanhSachKhachHang = new clsDanhSachKhachHang(arrdsKhachHang.get(position).getMaKhachHang(), arrdsKhachHang.get(position).getTenKhachHang(), arrdsKhachHang.get(position).getAnhKhachHang(), arrdsKhachHang.get(position).getSdt(), arrdsKhachHang.get(position).getDiaChi(), arrdsKhachHang.get(position).getTenTaiKhoan(),arrdsKhachHang.get(position).getMatKhau());
                intent.putExtra("dulieuKH", clsDanhSachKhachHang);
                startActivity(intent);
            }
        });

        /*ibtnThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DsKhachHangActivity.this, ThemKhachHangActivity.class);
                startActivityForResult(intent, maDuLieu );
            }
        });*/
    }
    void LoadData(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0 ;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrdsKhachHang.add(new clsDanhSachKhachHang(
                                object.getInt("maKhachHang"),
                                object.getString("tenKhachHang"),
                                object.getString("anhKhachHang"),
                                object.getString("sdt"),
                                object.getString("diaChi"),
                                object.getString("tenTaiKhoan"),
                                object.getString("matKhau")
                        ));


                        //Toast.makeText(DsKhachHangActivity.this, "Ten kH: " + response.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterDsKhachHang.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DsKhachHangActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void Init() {
    lvwDsKhachHang = findViewById(R.id.lvwDsKhachHang);
    edttimkhachhang = findViewById(R.id.edttimkhachhang);
    ibtntimkhachhang = findViewById(R.id.ibtntimkhachhang);
    ibtnThemKH = findViewById(R.id.ibtnThemKH);

    }
}