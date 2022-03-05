package com.akuya.mydear;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;


public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Handler x = new Handler();//定义一个handle对象

        x.postDelayed(new splashhandler(), 3000);//设置3秒钟延迟执行splashhandler线程。
    }

    class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(),MainActivity.class));
            LaunchActivity.this.finish();
        }
    }


}