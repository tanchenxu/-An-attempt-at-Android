package com.akuya.mydear.page;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.akuya.mydear.R;
import com.akuya.mydear.bean.DailyBean;
import com.akuya.mydear.bean.FoodBean;
import com.akuya.mydear.bean.LoginUserBean;

import org.litepal.LitePal;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private Button Btn_back,Btn_search;
    private TextView Tv_more1,Tv_more2,Tv_more3,Tv_1,Tv_2,Tv_3,Tv_more4,Tv_more5,Tv_more6,Tv_more7,Tv_more8,Tv_more9;
    private EditText Et_search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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

        Btn_back=findViewById(R.id.btn_back2);
        Btn_search=findViewById(R.id.bt_search2);
        Tv_more1=findViewById(R.id.tv_calorie);
        Tv_more2=findViewById(R.id.tv_type);
        Tv_more3=findViewById(R.id.tv_trdt);
        Et_search=findViewById(R.id.et_search);
        Tv_1=findViewById(R.id.tv_1);
        Tv_2=findViewById(R.id.tv_2);
        Tv_3=findViewById(R.id.tv_3);
        Tv_more4=findViewById(R.id.tv_more1);
        Tv_more5=findViewById(R.id.tv_more2);
        Tv_more6=findViewById(R.id.tv_more3);
        Tv_more7=findViewById(R.id.tv_more4);
        Tv_more8=findViewById(R.id.tv_more5);
        Tv_more9=findViewById(R.id.tv_more6);



        Btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchActivity.this,FunActivity.class);
                startActivity(intent);
                SearchActivity.this.finish();
            }
        });



    }

    public void Search(View view) {

        String temp=Et_search.getText().toString();
        List<FoodBean> foodBeanList=LitePal.findAll(FoodBean.class);
        List<DailyBean> dailyBeanList=LitePal.findAll(DailyBean.class);
        DailyBean dailyBean = new DailyBean();
        FoodBean foodBean =new FoodBean();
        for(int i=0;i<foodBeanList.size();i++){
            if(temp.equals(foodBeanList.get(i).getname())){
                foodBean=LitePal.find(FoodBean.class,foodBeanList.get(i).getId());
                String temptype =foodBean.getTypes();
                String tempcalurie =foodBean.getcontent();
                String tempinstrct =foodBean.getFunction();

                Tv_1.setText("卡路里");
                Tv_2.setText("类型");
                Tv_3.setText("食物简介");
                Tv_more1.setText(temptype);
                Tv_more3.setText(tempinstrct);
                Tv_more2.setText(tempcalurie);
                break;
            }
        }

        for(int i=0;i<dailyBeanList.size();i++){
            if(temp.equals(dailyBeanList.get(i).getTime())){
                String arr [] = new String[9];
                dailyBean=LitePal.find(DailyBean.class,dailyBeanList.get(i).getId());
             for(int j=0;j<dailyBean.getFoodBeanList().size();j++){
                 arr[j] = dailyBean.getFoodBeanList().get(i).getname();
                 Log.e("text", "1."+arr[j]);
             }

                Tv_1.setText("当日饮食计划");
                Tv_more1.setText(arr[0]);
                Tv_more2.setText(arr[1]);
                Tv_more3.setText(arr[2]);
                Tv_more4.setText(arr[3]);
                Tv_more5.setText(arr[4]);
                Tv_more6.setText(arr[5]);
                Tv_more7.setText(arr[6]);
                Tv_more8.setText(arr[7]);
                Tv_more9.setText(arr[8]);

                break;
            }
        }

    }
}