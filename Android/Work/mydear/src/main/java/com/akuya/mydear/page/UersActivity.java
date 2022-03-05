package com.akuya.mydear.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akuya.mydear.MainActivity;
import com.akuya.mydear.R;

public class UersActivity extends AppCompatActivity {

  private TextView tvNickName,tvAccount,tvAge,tvGender,tvCity,tvHome,tvSchool,tvSign,tvBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uers);



        initView();
        initData();
        inEvent();

    }

    private void inEvent() {
    }

    private void initData() {
        getDataFromspf();
    }

    private void getDataFromspf() {
        SharedPreferences spfRecord = getSharedPreferences("spfRecordnew",MODE_PRIVATE);
        String accountnew = spfRecord.getString("accountnew", "");
        String nick_name = spfRecord.getString("nick_name", "");
        String age = spfRecord.getString("age", "");
        String city = spfRecord.getString("city", "");
        String gender = spfRecord.getString("gender", "");
        String school = spfRecord.getString("school", "");
        String birthtime = spfRecord.getString("birthtime", "");
        String home = spfRecord.getString("Home","");

        tvAccount.setText(accountnew);
        tvNickName.setText(nick_name);
        tvAge.setText(age);
        tvCity.setText(city);
        tvSchool.setText(school);
        tvHome.setText(home);
        tvGender.setText(gender);
        tvBirthday.setText(birthtime);

    }

    private void initView() {
        tvAccount=findViewById(R.id.tv_account_text);
        tvNickName=findViewById(R.id.tv_nick_name);
        tvAge=findViewById(R.id.tv_age);
        tvCity=findViewById(R.id.tv_city);
        tvSchool=findViewById(R.id.tv_school_text);
        tvHome=findViewById(R.id.tv_city_text);
        tvGender=findViewById(R.id.tv_gender);
        tvBirthday=findViewById(R.id.tv_birthtime_text);


    }


    public void toEdit(View view) {
        Intent intent=new Intent(this,EidtActivity.class);

        startActivity(intent);

    }

        public void logout(View view) {
            Intent intent =new Intent(this, MainActivity.class);
            startActivity(intent);
            UersActivity.this.finish();
        }
}