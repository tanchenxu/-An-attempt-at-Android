package com.akuya.mydear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akuya.mydear.bean.LoginUserBean;
import com.akuya.mydear.page.FunActivity;
import com.akuya.mydear.page.HomeActivity;
import com.akuya.mydear.page.NewHome;
import com.akuya.mydear.page.UersActivity;
import com.akuya.mydear.page.UserActivity;

import org.litepal.LitePal;
import org.litepal.tablemanager.callback.DatabaseListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button Btn_register,Btn_login;
    private EditText Et_user,Et_password;
    private CheckBox Cb_remword,Cb_autologin;
    private TextView Tv_regist;
    SQLiteDatabase db = LitePal.getDatabase();

    Intent intent =new Intent();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LitePal.registerDatabaseListener(new DatabaseListener() {
            @Override
            public void onCreate() {
            }

            @Override
            public void onUpgrade(int oldVersion, int newVersion) {
            }
        });

        initView();

        initData();

        //登录注册按钮的监听

        Tv_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(MainActivity.this,RegisterActivity2.class);
                startActivity(intent);
            }
        });


        Btn_login.setOnClickListener(new View.OnClickListener() {
            LoginUserBean loginUserBean=new LoginUserBean();

            @Override
            public void onClick(View view) {
                String User=Et_user.getText().toString();
                String Key=Et_password.getText().toString();


                //判断用户名是否为空
                if(TextUtils.isEmpty(User)) {
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                //密码是否为空
                    if(TextUtils.isEmpty(Key)) {
                        Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Boolean flag=false;
                    //再数据库中寻找用户
                    List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
                for(int i=0;i<loginUserBeans.size();i++){
                    if(User.equals(loginUserBeans.get(i).getUserName())){
                        if(Key.equals(loginUserBeans.get(i).getPassWord())){
                            flag=true;
                            break;
                        }else{
                        }
                    }else {
                    }
                }
                //记住密码的实现
                    if(Cb_remword.isChecked()){

                        //SharedPreferrences临时储存密码与用户
                        SharedPreferences spf = getSharedPreferences("spfRecorid",MODE_PRIVATE);
                      SharedPreferences.Editor edit =spf.edit();
                        edit.putString("account",User);
                        edit.putString("password",Key);
                        edit.putBoolean("isRemember",true);
                        edit.apply();
                    }else{
                        SharedPreferences spf = getSharedPreferences("spfRecorid",MODE_PRIVATE);
                        SharedPreferences.Editor edit =spf.edit();
                        edit.putBoolean("isRemember",false);
                        edit.apply();
                    }
                if(flag){

                    //记住登录用户名方便以后使用
                    SharedPreferences spf1 = getSharedPreferences("spfRecorid1",MODE_PRIVATE);
                    SharedPreferences.Editor edit =spf1.edit();
                    edit.putString("account1",User);
                    edit.apply();
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    intent.setClass(MainActivity.this,NewHome.class);
                    intent.putExtra("account",User);
                    startActivity(intent);
                    MainActivity.this.finish();

                }else{
                    Toast.makeText(MainActivity.this, "用户名或者密码错误，请尝试重新输入", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void initView() {

        Btn_login =findViewById(R.id.btn_login);
        Et_user = findViewById(R.id.et_user);
        Et_password = findViewById(R.id.et_password);
        Cb_remword = findViewById(R.id.cb_remword);
        Tv_regist=findViewById(R.id.loginsign);
    }

    public void initData() {
        SharedPreferences spf = getSharedPreferences("spfRecorid",MODE_PRIVATE);
        boolean isRemember =  spf.getBoolean("isRemember",false);
        String account =  spf.getString("account","");
        String password =  spf.getString("password","");

        if(isRemember){
            Et_user.setText(account);
            Et_password.setText(password);
            Cb_remword.setChecked(true);
        }

    }

}