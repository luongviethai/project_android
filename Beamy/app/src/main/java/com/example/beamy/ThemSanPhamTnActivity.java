package com.example.beamy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemSanPhamTnActivity extends AppCompatActivity {

    EditText edtTenSpCT,edtGia, edtMota, edtLoai1;
    TextView  edtShop, edtLoai;
    Button btnThemSP;
    ImageView imgThemSP;
    Spinner spnLoaiSP;

    int maLoaiSP, maShop, maKhachHang, maLoaif;
    String tenLoaiSP;
    clsLoaiSP loaiSP;

    private ArrayList<clsLoaiSP> dsLoaiSP;


    int Request_Code_Image = 1235;
    String tensanpham, mota, loai;
    int gia;
    String realpath = "";
    //String urlInsertSP="http://192.168.0.102/CuaHang/insertSPCT.php";

    clsConfig b = new clsConfig();
    String baseURL = b.baseURL;

    String urlGetLoaiSP = baseURL + "/CuaHang/get/getLoaiSP.php";

    String urlGetMaShop = baseURL + "/CuaHang/get/getMaShopByMaKH.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham_tn);
        Init();

        dsLoaiSP = new ArrayList<>();


        ReadDataLoaiSP(urlGetLoaiSP);

        SharedPreferences sharedPreferences= this.getSharedPreferences("tkSetting", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {
            maKhachHang = sharedPreferences.getInt("maKH", -1);
        }
        //Toast.makeText(this,"Mã kh: " + maKH ,Toast.LENGTH_LONG).show();


        LoadMaShop(urlGetMaShop);




        imgThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_Code_Image);
            }
        });

        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tensanpham = edtTenSpCT.getText().toString();
                gia = Integer.parseInt(edtGia.getText().toString());
                loai = edtLoai.getText().toString();
                mota = edtMota.getText().toString();
                //loai1 = edtLoai1.getText().toString();
                //maShop = Integer.parseInt(edtShop.getText().toString()) ;

                if( tensanpham.length()>0 && gia>0 && mota.length()>0 ){

                    File file = new File(realpath);
                    String file_path = file.getAbsolutePath();

                    String[] mangtenfile = file_path.split("\\.");
                    file_path = mangtenfile[0] + System.currentTimeMillis() + "." + mangtenfile[1];

                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", file_path, requestBody);

                    DataClient dataClient = APIUtils.getData();
                    Call<String> callback = dataClient.UploadPhoto(body);

                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                            if (response != null) {
                                String message = response.body();
                                if(message.length()>0){
                                    DataClient insertdata = APIUtils.getData();
                                    Call<String> callback = insertdata.InsertSP1( tensanpham, gia,loai,APIUtils.Base_Url + "image/" + message, mota, maLoaiSP,maShop);
                                    callback.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String result = response.body();
                                            if(result.equals("Ket")){
                                                Toast.makeText(ThemSanPhamTnActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                            else {
                                                Toast.makeText(ThemSanPhamTnActivity.this, "Thất bại", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Log.d("BBB",t.getMessage());
                                        }
                                    });
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
//                            Log.d("BBB", t.getMessage());
                        }
                    });
                }else {
                    Toast.makeText(ThemSanPhamTnActivity.this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void LoadMaShop(String path)
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, path, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Toast.makeText(ThemSanPhamCT.this, "Mã: " + response.toString(), Toast.LENGTH_LONG).show();

                edtShop.setText(response.toString());
                maShop = Integer.parseInt(response.toString());
                 edtLoai.setText(response.toString());
                 maLoaif= Integer.parseInt(response.toString());


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemSanPhamTnActivity.this,"Chưa kết nối: " + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> dulieu=new HashMap<>();

                // Gửi dữ liệu cho PHP
                dulieu.put("maKH", String.valueOf(maKhachHang));

                return dulieu;
            }
        };

        /*Toast.makeText(DsPhieuMuonActivity.this,"Ngày trả: " + ngayTraThucTe + "/n " + maPhieuMuon, Toast.LENGTH_LONG).show();*/

        queue.add(request);
    }



    // Đọc dữ liệu tblTácGiả
    void ReadDataLoaiSP(String path){

        dsLoaiSP.clear();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, path, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject svJSON = response.getJSONObject(i);

                        /*Toast.makeText(DanhSachSanPhamActivity.this, svJSON.toString(),Toast.LENGTH_LONG).show();
                         */

                        //lấy giá trị từ JSON
                        maLoaiSP = svJSON.getInt("maLoaiSP");
                        tenLoaiSP = svJSON.getString("tenLoaiSP");

                        // Khởi tạo đối tượng sách
                        loaiSP = new clsLoaiSP(maLoaiSP, tenLoaiSP);

                        // add Thêm vào danh sách sách
                        dsLoaiSP.add(loaiSP);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                nhapSpinnerLoaiSP();


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemSanPhamTnActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }

        });
        queue.add(request);
    }








    public void nhapSpinnerLoaiSP(){
        /*Khai báo Bộ kết nối layout item_layout_custom với mảng*/
        SpinnerLoaiSPAdapter adapter = new SpinnerLoaiSPAdapter(ThemSanPhamTnActivity.this, R.layout.item_spinner_loai_sp, dsLoaiSP);

        spnLoaiSP.setAdapter(adapter);

        /* Đặt Spinner chế độ lắng nghe click*/
        spnLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*Đối tượng nhận được giá trị tại vị trí vừa chọn*/
                loaiSP = dsLoaiSP.get(position);
                maLoaiSP = loaiSP.getMaLoaiSP();

                //Toast.makeText(ThemSanPhamCT.this, "Mã loại: " + maLoaiSP, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Request_Code_Image && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            realpath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgThemSP.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /* public void InserSanPhamCT(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().equals("Done")) {

                    Toast.makeText(ThemSanPhamCT.this, "Thêm dữ liệu thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ThemSanPhamCT.this, ShopKhachHang.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(ThemSanPhamCT.this,"Thất bại", Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemSanPhamCT.this,"chưa kết nối: " + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws
                    AuthFailureError {
                Map<String, String> dulieu = new HashMap<>();


                 tenSP = edtTenSpCT.getText().toString();
                gia = edtGia.getText().toString();
                loai = edtLoai.getText().toString();

                moTa = edtMota.getText().toString();


                dulieu.put(" tenSP",  tenSP);
                dulieu.put("gia", gia);
                dulieu.put("loai", loai);

                dulieu.put("moTa", moTa);

                dulieu.put("hinhanh", "Image");




                return dulieu;
            }

        };
        queue.add(request);
    }*/

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }


    private void Init() {
        edtTenSpCT = findViewById(R.id.edtTenSpCT);
        edtGia = findViewById(R.id.edtGia);
        edtLoai = findViewById(R.id.edtLoai);
        edtMota = findViewById(R.id.edtMota);
        btnThemSP = findViewById(R.id.btnThemSP);
        imgThemSP = findViewById(R.id.imgThemSP);
        //edtLoai1 = findViewById(R.id.edtLoai1);
        edtShop = findViewById(R.id.edtShop);
        spnLoaiSP = findViewById(R.id.spnLoaiSP);
    }

}