package com.example.beamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
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

public class ShopKhachHang extends AppCompatActivity {

    EditText edtTimSP;
    ImageButton ibtnTimSP, ibtnThemSPCT , ibtnQL, ibtnDonHang;
    GridView grvDsSPCT;
    AdapterSPCuaToi spCuaToi;
    ArrayList<clsDanhSachSanPham> arrSPCuaToi;
    int maKH;
    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;
    String urlGetSPCT =baseURL+ "/CuaHang/get/getSPCuaToi.php?maKhachHang=";
    int maDuLieu = 1110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_khach_hang);

        Init();
        //maKH = 2;

        SharedPreferences sharedPreferences= this.getSharedPreferences("tkSetting", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {


            maKH = sharedPreferences.getInt("maKH", -1);

        }

        //Toast.makeText(ShopKhachHang.this, "Nhan kH: " + String.valueOf(maKH), Toast.LENGTH_SHORT).show();


        String urlGetSPCT2 = urlGetSPCT + String.valueOf(maKH);

        LoadData(urlGetSPCT2);

        arrSPCuaToi = new ArrayList<>();
        spCuaToi = new AdapterSPCuaToi(ShopKhachHang.this,R.layout.item_grivew_ds_sp, arrSPCuaToi);
        grvDsSPCT.setAdapter(spCuaToi);
        grvDsSPCT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShopKhachHang.this,ChiTietSanPhamCT.class);
                clsDanhSachSanPham clsSPCuaToi = new clsDanhSachSanPham(arrSPCuaToi.get(position).getMasanpham(), arrSPCuaToi.get(position).getTensanpham(), arrSPCuaToi.get(position).getGia(), arrSPCuaToi.get(position).getLoai(), arrSPCuaToi.get(position).getHinhanh(), arrSPCuaToi.get(position).getMota(),arrSPCuaToi.get(position).getMaLoaiSP(),arrSPCuaToi.get(position).getMaShop());
                intent.putExtra("dulieuSP", clsSPCuaToi);
                startActivity(intent);
            }

        });
        ibtnThemSPCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopKhachHang.this, ThemSanPhamTnActivity.class);
                startActivityForResult(intent, maDuLieu );
            }
        });

        ibtnDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopKhachHang.this,DonHangCuaShop.class);
                startActivity(intent);
            }
        });
        ibtnQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //finish();
               Intent intent = new Intent(ShopKhachHang.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    void LoadData(String url){


        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                //Toast.makeText(ShopKhachHang.this, "Nhan kH: " + response.toString(), Toast.LENGTH_SHORT).show();

                for (int i =0 ;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrSPCuaToi.add(new clsDanhSachSanPham(
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
                spCuaToi.notifyDataSetChanged();

               // Toast.makeText(ShopKhachHang.this, "so: " + arrSPCuaToi.size(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShopKhachHang.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }


    @Override
    protected void onRestart() {
        arrSPCuaToi.clear();
        String urlGetSPCT2 = urlGetSPCT + String.valueOf(maKH);

        LoadData(urlGetSPCT2);
        super.onRestart();
    }

    private void Init() {
        edtTimSP = findViewById(R.id.edtTimSP);
        ibtnThemSPCT = findViewById(R.id.ibtnThemSPCT);
        ibtnTimSP = findViewById(R.id.ibtnTimSP);
        grvDsSPCT = findViewById(R.id.grvDsSPCT);
        ibtnQL= findViewById(R.id.ibtnQL);
        ibtnDonHang = findViewById(R.id.ibtnDonHang);
    }
}