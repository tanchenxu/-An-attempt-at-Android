package com.akuya.mydear.page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akuya.mydear.ImageUtil;
import com.akuya.mydear.R;
import com.akuya.mydear.bean.LoginUserBean;
import com.akuya.mydear.bean.ReduceFoodBean;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class NewThirListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_TAKE = 1;
    public static final int REQUEST_CODE_CHOOSE = 0;
    private List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private EditText Et_foodname,Et_harm,Et_time;
    private TextView Tv_time;
    private ImageView Iv_foodpictrue;
    private Uri imageUri;
    private String imageBase64;
    private Button Return;
    private String signDay,signDaytime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_thir_list);

        SharedPreferences spf1 = getSharedPreferences("spfRecorid1",MODE_PRIVATE);
        String account =  spf1.getString("account1","");

        for (int i=0;i<loginUserBeans.size();i++)
        {
            Log.e("akuya", "onCreate:1111 ");
            if (account.equals(loginUserBeans.get(i).getUserName())){
                loginUserBean=LitePal.find(LoginUserBean.class,loginUserBeans.get(i).getId(),true);
                Log.e("akuya", "onCreate: i find it"+loginUserBean.getUserName()+loginUserBean.getId());
                break;
            }
        }


        initView();
        initData();
        initEvent();

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(NewThirListActivity.this,FunThirActivity.class);
                startActivity(intent);
                NewThirListActivity.this.finish();
            }
        });

    }

    private void initView() {
        Et_foodname=findViewById(R.id.et_food_name3);
        Et_harm=findViewById(R.id.et_warming_text);
        Tv_time=findViewById(R.id.tv_time_text2);
        Iv_foodpictrue=findViewById(R.id.iv_foodpic3);
        Return=findViewById(R.id.btn_return4);
    }

    private void initData() {
    }

    private void initEvent() {

    Tv_time.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new DatePickerDialog(NewThirListActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMouth) {
                    int realMouth =month+1;
                    signDay = year+"年"+realMouth+"月"+dayOfMouth+"日";
                }
            },2021,12,16).show();
        }
    });

    }

    public void save3(View view) {
        String Foodname = Et_foodname.getText().toString();
        String Warming =Et_harm.getText().toString();


        ReduceFoodBean reduceFoodBean =new ReduceFoodBean();
        reduceFoodBean.setUserName(Foodname);
        reduceFoodBean.setHarm(Warming);
        reduceFoodBean.setLasttime(signDay);
        reduceFoodBean.setPicture(imageBase64);

        loginUserBean.getReduceFoodBeans().add(reduceFoodBean);
        reduceFoodBean.save();
        loginUserBean.save();

        Intent intent =new Intent(this,NewHome.class);
        startActivity(intent);
        this.finish();

    }

    public void takePhoto(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            //拍照
            doTake();
        }else {
            //去申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                doTake();
            }else {
                Toast.makeText(this, "你没有获得拍照的权限", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode==0){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openAlbum();
            }else {
                Toast.makeText(this, "你没有获得相册的权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void doTake() {
        File imageTemp = new File(getExternalCacheDir(),"imageOut.jpeg");
        if(imageTemp.exists()){
            imageTemp.delete();
        }
        try {
            imageTemp.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Build.VERSION.SDK_INT>24){
            //contentprovider
            imageUri = FileProvider.getUriForFile(this,"com.akuya.mydear.fileprovider",imageTemp);
        }else {
            imageUri = Uri.fromFile(imageTemp);
        }

        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,REQUEST_CODE_TAKE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_TAKE){
            if(resultCode == RESULT_OK){
                // 获取拍摄的照片
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Iv_foodpictrue.setImageBitmap(bitmap);
                    String imageToBase64 = ImageUtil.imageToBase64(bitmap);
                    imageBase64 =imageToBase64;


                } catch (FileNotFoundException e) {

                }
            }
        }else if(requestCode ==REQUEST_CODE_CHOOSE){
            if(Build.VERSION.SDK_INT<19) {
                handleImageBeforeApi19(data);
            }else{
                handleImageOnApi19(data);
            }
        }
    }

    private void handleImageBeforeApi19(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @TargetApi(19)
    private void handleImageOnApi19(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);

            if (TextUtils.equals(uri.getAuthority(), "com.android.providers.media.documents")) {
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

            } else if (TextUtils.equals(uri.getAuthority(), "com.android.providers.downloads.documents")) {
                if (documentId != null && documentId.startsWith("msf:")) {
                    resolveMSFContent(uri, documentId);
                    return;
                }
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath = getImagePath(contentUri, null);
            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }

        displayImage(imagePath);
    }

    private void resolveMSFContent(Uri uri, String documentId) {

        File file = new File(getCacheDir(), "temp_file" + getContentResolver().getType(uri).split("/")[1]);

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);

            OutputStream outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[4 * 1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            Iv_foodpictrue.setImageBitmap(bitmap);
            imageBase64 = ImageUtil.imageToBase64(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            Iv_foodpictrue.setImageBitmap(bitmap);
            String imageToBase64 = ImageUtil.imageToBase64(bitmap);
            imageBase64 = imageToBase64;
        }
    }

    public void choosePhoto(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            //打开相册
            openAlbum();
        }else {
            //去申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }

    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CODE_CHOOSE);
    }
}
