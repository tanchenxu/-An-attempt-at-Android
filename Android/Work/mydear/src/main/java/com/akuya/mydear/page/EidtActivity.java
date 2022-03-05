package com.akuya.mydear.page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.TargetApi;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.akuya.mydear.ImageUtil;
import com.akuya.mydear.R;
import com.akuya.mydear.bean.LoginUserBean;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

public class EidtActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_TAKE = 1;
    public static final int REQUEST_CODE_CHOOSE = 0;
    private List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private EditText etNickName,etSchool,etage,etgoal;
    private RadioButton Rb_male,Rb_female;
    private AppCompatSpinner spinnerCity;
    private ImageView ivAvatar;
    private Button Bt_return;

    private String[] cities;
    private int selectedCityPosition;
    private String selectedCity;
    private Uri imageUri;
    private String imageBase64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eidt);

        //通过再登录界面临时读取的用户名，再Litepal中找到用户
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



        Bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(EidtActivity.this,NewHome.class);
                startActivity(intent);
                EidtActivity.this.finish();
            }
        });
    }


    private void initView() {
        etNickName=findViewById(R.id.et_nick_name_text);
        etSchool=findViewById(R.id.et_school_text);
        Rb_male=findViewById(R.id.rb_male);
        Rb_female=findViewById(R.id.rb_female);
        spinnerCity=findViewById(R.id.sp_city);
        etage=findViewById(R.id.et_Age_text);
        etgoal=findViewById(R.id.et_goal_text);
        ivAvatar=findViewById(R.id.iv_avater);
        Bt_return=findViewById(R.id.btn_return);
    }

    private void initData() {
        cities=getResources().getStringArray(R.array.cities);
    }

    private void initEvent() {
//spinner的监听
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCityPosition =position;
                selectedCity=cities[position];
                Log.e("EditActivity", "onItemSelected:--------------------------- "+position );
                Log.e("EditActivity", "onItemSelected:--------------------------- "+selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void save(View view) {

        //用户信息的贮存(litepal)
        String Nick_name = etNickName.getText().toString();
        String School = etSchool.getText().toString();
        String Age = etage.getText().toString();
        String Goal =etgoal.getText().toString();

        String gender="请设置";
        if(Rb_male.isChecked()){
            gender="男";
        }
        if(Rb_female.isChecked()){
            gender="女";
        }

        loginUserBean.setAge(Age);
        loginUserBean.setCity(selectedCity);
        loginUserBean.setGender(gender);
        loginUserBean.setSchool(School);
        loginUserBean.setGoal(Goal);
        loginUserBean.setNickName(Nick_name);
        loginUserBean.setHead(imageBase64);
        loginUserBean.save();


        Intent intent=new Intent(this,NewHome.class);
        startActivity(intent);
        this.finish();
    }

    public void takePhoto(View view) {
        //询问权限权限进行拍照
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            //拍照
            doTake();
        }else {
            //去申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
        }

    }

    //对权限获取结果的响应
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
//拍照
    private void doTake() {
        //创建文件临时存储路径
        File imageTemp = new File(getExternalCacheDir(),"imageOut.jpeg");
        //存在则删除，保证每次为空
        if(imageTemp.exists()){
            imageTemp.delete();
        }
        try {
            imageTemp.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断版本是否需要contentprovider
        if(Build.VERSION.SDK_INT>24){
            //contentprovider
            imageUri = FileProvider.getUriForFile(this,"com.akuya.mydear.fileprovider",imageTemp);
        }else {
            imageUri = Uri.fromFile(imageTemp);
        }

        //拍照跳转
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
                    //转为Base4
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    ivAvatar.setImageBitmap(bitmap);
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
            ivAvatar.setImageBitmap(bitmap);
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
            ivAvatar.setImageBitmap(bitmap);
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