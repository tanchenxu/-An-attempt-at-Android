package com.akuya.mylitepal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import org.litepal.LitePal;
import org.litepal.tablemanager.callback.DatabaseListener;

public class MainActivity extends AppCompatActivity {

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
    }
}