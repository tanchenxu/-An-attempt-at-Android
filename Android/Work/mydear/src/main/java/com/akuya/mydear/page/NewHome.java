package com.akuya.mydear.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.akuya.mydear.R;
import com.akuya.mydear.fragmt.BlankFragment;
import com.akuya.mydear.fragmt.UserMain;

public class NewHome extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        Button button =findViewById(R.id.btn3);
        button.setOnClickListener(this);
        Button button1 =findViewById(R.id.btn4);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn3:
                replaceFragment(new BlankFragment());
                break;
            case R.id.btn4:
                replaceFragment(new UserMain());
                break;
        }

    }
//fragment替换
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager =getSupportFragmentManager();
         FragmentTransaction transaction = fragmentManager.beginTransaction();
         transaction.replace(R.id.fl_replace,fragment);
         transaction.commit();
    }
}