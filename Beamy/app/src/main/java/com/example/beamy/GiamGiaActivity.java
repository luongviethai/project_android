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

public class GiamGiaActivity extends AppCompatActivity {
    GridView gvgiamgia;
    ArrayList<clsGiamgia> arGiamGia;
    AdapterGiamGia adapterGiamGia;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String url = baseURL+"/CuaHang/getGiamGia.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giam_gia);
        gvgiamgia = (GridView)findViewById(R.id.gvgiamgia);
        arGiamGia = new ArrayList<>();
        adapterGiamGia = new AdapterGiamGia(GiamGiaActivity.this,R.layout.dong_giam_gia,arGiamGia);
        gvgiamgia.setAdapter(adapterGiamGia);
        LoadGiamGia(url);
    }
    void LoadGiamGia(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arGiamGia.add(new clsGiamgia(
                                object.getString("magiamgia"),
                                object.getString("phantram")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterGiamGia.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GiamGiaActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}