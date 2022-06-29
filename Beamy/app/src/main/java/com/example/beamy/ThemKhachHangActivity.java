package com.example.beamy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemKhachHangActivity extends AppCompatActivity {
    EditText edtTenKH, edtSDT, edtDiaChi, edtTenTaiKhoan, edtMK;
    Button btnXacNhanKhachHang;
    ImageView imgThemKhachHang;
    int Request_Code_Image = 1234;
    String realpath = "";

    String tenKhachHang, sdt, diaChi, tenTaiKhoan, matKhau;
   // private int maKhachHang;

    //String urlThemKhachHang ="http://192.168.0.102/CuaHang/insertKhachHang.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khach_hang);

        Init();
        imgThemKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_Code_Image);
            }
        });


        btnXacNhanKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenKhachHang = edtTenKH.getText().toString();
                sdt = edtSDT.getText().toString();
                diaChi = edtDiaChi.getText().toString();
                tenTaiKhoan = edtTenTaiKhoan.getText().toString();
                matKhau = edtMK.getText().toString();

                if(tenKhachHang.length()>0 && sdt.length()>0 && diaChi.length()>0 && tenTaiKhoan.length()>0 && matKhau.length()>0){

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
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response != null) {
                                String message = response.body();
                                if(message.length()>0){
                                    DataClient insertdata = APIUtils.getData();
                                    Call<String> callback = insertdata.InsertData(tenKhachHang,APIUtils.Base_Url + "image/" + message,sdt,diaChi,tenTaiKhoan,matKhau);
                                    callback.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String result = response.body();
                                            if(result.equals("Ket")){
                                                Toast.makeText(ThemKhachHangActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                            else {
                                                Toast.makeText(ThemKhachHangActivity.this, "Thất bại", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(ThemKhachHangActivity.this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }



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
                imgThemKhachHang.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /*public void InserKhachHang(String path)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().equals("Done")) {

                    Toast.makeText(ThemKhachHangActivity.this, "Thêm dữ liệu thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ThemKhachHangActivity.this, DsKhachHangActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(ThemKhachHangActivity.this,"Thất bại", Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemKhachHangActivity.this,"chưa kết nối: " + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws
                    AuthFailureError {
                Map<String, String> dulieu = new HashMap<>();


                tenKhachHang = edtTenKH.getText().toString();
                sdt = edtSDT.getText().toString();
                diaChi = edtDiaChi.getText().toString();
                tenTaiKhoan = edtTenTaiKhoan.getText().toString();
                matKhau = edtMK.getText().toString();


                dulieu.put("tenKhachHang", tenKhachHang);
                dulieu.put("sdt", sdt);
                dulieu.put("diaChi", diaChi);
                dulieu.put("tenTaiKhoan", tenTaiKhoan);
                dulieu.put("matKhau", matKhau);

                dulieu.put("anhKhachHang", "Image");




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
        edtTenKH = findViewById(R.id.edtTenKH);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtTenTaiKhoan = findViewById(R.id.edtTenTaiKhoan);
        edtMK = findViewById(R.id.edtMK);
        edtSDT = findViewById(R.id.edtSDT);

        imgThemKhachHang = findViewById(R.id.imgThemKhachHang);
        btnXacNhanKhachHang = findViewById(R.id.btnXacNhanKhachHang);
    }
}