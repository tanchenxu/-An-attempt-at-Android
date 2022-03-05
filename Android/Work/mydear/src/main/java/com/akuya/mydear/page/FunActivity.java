package com.akuya.mydear.page;

import com.akuya.mydear.bean.DailyBean;
import com.akuya.mydear.bean.FoodBean;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.akuya.mydear.page.ALL_Adapter.MyAdapter;
import com.akuya.mydear.R;
import com.akuya.mydear.bean.LoginUserBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FunActivity extends AppCompatActivity {
    private List<LoginUserBean> loginUserBeans =LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private RecyclerView recyclerView;
    private Button Bt_add,Bt_back,Bt_search,Bt_calorie;
    private List<FoodBean> data=new ArrayList<FoodBean>() ;
    private int Calorie;
    private NotificationManager manager;
    private Notification notification1,notification2,notification3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);


        //通知
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("akuya", "My calorie", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }



        SharedPreferences spf1 = getSharedPreferences("spfRecorid1",MODE_PRIVATE);
        String account =  spf1.getString("account1","");
        Log.e("akuya", "onCreate: "+account );
        for (int i=0;i<loginUserBeans.size();i++)
        {
            Log.e("akuya", "onCreate:1111 ");
            if (account.equals(loginUserBeans.get(i).getUserName())){
                loginUserBean=LitePal.find(LoginUserBean.class,loginUserBeans.get(i).getId(),true);
                Log.e("akuya", "onCreate: i find it"+loginUserBean.getUserName()+loginUserBean.getId());
                break;
            }
         }

        for (int i = 0; i < loginUserBean.getFoodBeans().size(); i++) {
            Calorie =Calorie+loginUserBean.getFoodBeans().get(i).getCalorie();

        }


        notification1 = new NotificationCompat.Builder(this,"akuya")
                .setContentTitle("审查结果")
                .setContentText("亲，您摄取的卡路里为"+Calorie+"k,请适当减少卡路里摄取~")
                .setSmallIcon(R.drawable.ic_baseline_sports_handball_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.loge))
                .setAutoCancel(true)
                .build();

        notification2 = new NotificationCompat.Builder(this,"akuya")
                .setContentTitle("审查结果")
                .setContentText("亲，您摄取的卡路里为"+Calorie+"k,请适当增加卡路里摄取~")
                .setSmallIcon(R.drawable.ic_baseline_sports_handball_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.loge))
                .setAutoCancel(true)
                .build();

        notification3 = new NotificationCompat.Builder(this,"akuya")
                .setContentTitle("审查结果")
                .setContentText("亲，您摄取的卡路里为"+Calorie+"k,在控制饮食的同时不要忘了每日锻炼哦~~")
                .setSmallIcon(R.drawable.ic_baseline_sports_handball_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.loge))
                .setAutoCancel(true)
                .build();




        data=loginUserBean.getFoodBeans();

        recyclerView = findViewById(R.id.recyclerview);
//分割线
        DividerItemDecoration mDivider = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDivider);


//布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyAdapter myAdapter = new MyAdapter(this.loginUserBean.getFoodBeans(), this);
        recyclerView.setAdapter(myAdapter);

        //按钮的监听
        Bt_back=findViewById(R.id.btn_back);
        Bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunActivity.this,NewHome.class);
                startActivity(intent);
                FunActivity.this.finish();
            }
        });

        Bt_add=findViewById(R.id.bt_add);
        Bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FunActivity.this,NewListActivity.class);
                startActivity(intent);
                FunActivity.this.finish();
            }
        });

        Bt_search=findViewById(R.id.bt_search);
        Bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunActivity.this,SearchActivity.class);
                startActivity(intent);
                FunActivity.this.finish();
            }
        });

        Bt_calorie=findViewById(R.id.btn_calorle);
        Bt_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                DailyBean dailyBean = new DailyBean();
                dailyBean.setTime(Calendar.YEAR+"."+(Calendar.MONTH+1)+"."+Calendar.DAY_OF_MONTH);
                Log.e("text", ""+Calendar.YEAR+"."+(Calendar.MONTH+1)+"."+Calendar.DAY_OF_MONTH);
                dailyBean.getFoodBeanList().addAll(loginUserBean.getFoodBeans());
                loginUserBean.getDailyBeans().add(dailyBean);

                dailyBean.save();
                loginUserBean.save();

                if(Calorie>2000){
                    manager.notify(1,notification1);
                }
                if(Calorie<500){
                    manager.notify(2,notification2);
                }
                if (Calorie>=500&&Calorie<=2000){
                    manager.notify(3,notification3);
                }

            }
        });
        myAdapter.setRecyclerItemClickListener(new MyAdapter.OnRecyclierItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {

            }
        });
    }
}