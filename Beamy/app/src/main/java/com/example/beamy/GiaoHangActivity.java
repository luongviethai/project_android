package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
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

public class GiaoHangActivity extends AppCompatActivity {
    GridView gvgiaohang;
    ArrayList<clsGiaoHang> arrClsGiaoHang;
    AdapterGiaoHang adapterGiaoHang;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String url =baseURL+ "/CuaHang/getDataGiaoHang.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_hang);
        gvgiaohang = (GridView)findViewById(R.id.gvgiaohang);
        arrClsGiaoHang = new ArrayList<>();
        adapterGiaoHang = new AdapterGiaoHang(GiaoHangActivity.this,R.layout.dong_giao_hang, arrClsGiaoHang);
        gvgiaohang.setAdapter(adapterGiaoHang);
        loadGiaoHang(url);
    }
    void loadGiaoHang(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrClsGiaoHang.add(new clsGiaoHang(
                                object.getString("khuvuc"),
                                object.getInt("tienship")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterGiaoHang.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GiaoHangActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}