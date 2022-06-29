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

public class ThemSanPhamCT extends AppCompatActivity {

    EditText edtTenSpCT,edtGia, edtLoai, edtMota, edtLoai1, edtShop;
    Button btnThemSP;
    ImageView imgThemSP;
    int Request_Code_Image = 1235;
    String tensanpham,gia,loai,mota,loai1, shop;
    String realpath = "";
    //String urlInsertSP="http://192.168.0.102/CuaHang/insertSPCT.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham_c_t);
        Init();

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
                gia = edtGia.getText().toString();
                //loai = edtLoai.getText().toString();
                mota = edtMota.getText().toString();
                //loai1 = edtLoai1.getText().toString();
                //shop = edtShop.getText().toString();

                if(tensanpham.length()>0 && gia.length()>0 && mota.length()>0 ){

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
                                    Call<String> callback = insertdata.InsertSP(tensanpham,gia,APIUtils.Base_Url + "image/" + message,mota);
                                    callback.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            String result = response.body();
                                            if(result.equals("Ket")){
                                                Toast.makeText(ThemSanPhamCT.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                                                //finish();
                                            }
                                            else {
                                                Toast.makeText(ThemSanPhamCT.this, "Thất bại", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(ThemSanPhamCT.this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
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
                imgThemSP.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


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
        //edtLoai = findViewById(R.id.edtLoai);
        edtMota = findViewById(R.id.edtMota);
        btnThemSP = findViewById(R.id.btnThemSP);
        imgThemSP = findViewById(R.id.imgThemSP);
        //edtLoai1 = findViewById(R.id.edtLoai1);
        //edtShop = findViewById(R.id.edtShop);
    }
}