package com.akuya.mydear;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.akuya.mydear.bean.FoodBean;
import com.akuya.mydear.bean.LoginUserBean;
import com.akuya.mydear.page.FunActivity;
import com.akuya.mydear.page.NotificationActivity;
import com.akuya.mydear.page.UersActivity;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RegisterActivity2 extends AppCompatActivity {

    private EditText Et_userin,Et_passwordin,Et_agword;
    private Button Btn_register2;
    private Toolbar Tl_return;
    private CheckBox Cb_agreement;
    private  NotificationManager manager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        //获取通知权限
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //判断版本是否需要创建notificationchannel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("akuya", "My calorie", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }


        notification = new NotificationCompat.Builder(this,"akuya")
                .setContentTitle("注册成功")
                .setContentText("恭喜你成功注册，希望在今后的生活中《我的卡路里》能够督促你，让你今后的生活更加健康快乐！！~~~~")
                .setSmallIcon(R.drawable.ic_baseline_sports_handball_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.loge))
                .setAutoCancel(true)
                .build();

        Tl_return = findViewById(R.id.tb_return);
        Et_userin = findViewById(R.id.et_userin);
        Et_agword = findViewById(R.id.et_agword);
        Et_passwordin = findViewById(R.id.et_passwordin);
        Btn_register2 = findViewById(R.id.btn_register2);
        Cb_agreement = findViewById(R.id.cb_agreement);

        //按钮监听
        Tl_return.setNavigationOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();
            @Override
            public void onClick(View view) {
                Log.e("akuya", "onClick:我被点击了 " );
                intent.setClass(RegisterActivity2.this,MainActivity.class);
                startActivity(intent);
                RegisterActivity2.this.finish();
            }
        });

        Btn_register2.setOnClickListener(new View.OnClickListener() {
            Intent intent =new Intent();

            @Override
            public void onClick(View view) {
                LoginUserBean loginUserBean=new LoginUserBean();
                String user = Et_userin.getText().toString();
                String pass  = Et_passwordin.getText().toString();
                String repass =Et_agword.getText().toString();

                if(TextUtils.isEmpty(user)){
                    Toast.makeText(RegisterActivity2.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(RegisterActivity2.this,"密码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }

                if(!TextUtils.equals(pass,repass)){
                    Toast.makeText(RegisterActivity2.this,"前后密码不一致",Toast.LENGTH_LONG).show();
                    return;
                }

                if(Cb_agreement.isChecked()){

                    //判定用户是否已经存在
                List <LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
                for(int i=0;i<loginUserBeans.size();i++){
                    if(user.equals(loginUserBeans.get(i).getUserName())){
                        Toast.makeText(RegisterActivity2.this,"该用户已存在",Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                manager.notify(1,notification);

                loginUserBean.setUserName(user);
                loginUserBean.setPassWord(pass);

                loginUserBean.save();
                    if (loginUserBean.save()) {
                        Toast.makeText(RegisterActivity2.this, "存储成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity2.this, "存储失败", Toast.LENGTH_SHORT).show();
                    }
                Toast.makeText(RegisterActivity2.this,"注册成功",Toast.LENGTH_LONG).show();
                intent.setClass(RegisterActivity2.this, MainActivity.class);
                startActivity(intent);
                RegisterActivity2.this.finish();}else{
                    Toast.makeText(RegisterActivity2.this,"请同意用户协议",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

    }
//用户协议的简单实现

    public void akuyaClick(View view) {
        if(Cb_agreement.isChecked()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher).setTitle("其实没有什么协议")
                    .setMessage("作者：谭晨旭").create().show();
        }
    }
}