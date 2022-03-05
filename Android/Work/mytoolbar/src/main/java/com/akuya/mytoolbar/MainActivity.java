package com.akuya.mytoolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.RequiresApi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Build;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tb);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("akuya", "onClick: toolbar被点击了" );
            }
        });

        Toolbar toolbar2 = findViewById(R.id.tb2);

        toolbar2.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar2.setTitle("标题2");
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("akuya", "onClick: toolbar2被点击了" );
            }
        });

    }
}