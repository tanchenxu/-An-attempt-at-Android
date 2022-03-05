package com.akuya.mydear.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.akuya.mydear.R;
import com.akuya.mydear.page.ALL_Adapter.S_Adapter;

import java.util.ArrayList;
import java.util.List;

public class SuggestActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private View root;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        toolbar=findViewById(R.id.tb_min);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(SuggestActivity.this, NewHome.class);
                startActivity(intent);
                SuggestActivity.this.finish();
            }
        });


        LayoutInflater lf = getLayoutInflater().from(this);
        View view = lf.inflate(R.layout.layout1, null);
        View view2 = lf.inflate(R.layout.layout2, null);

        List<View> viewList = new ArrayList<>();
        viewList.add(view);
        viewList.add(view2);

        ViewPager viewPager = findViewById(R.id.vp);
        S_Adapter s_adapter = new S_Adapter(viewList);
        viewPager.setAdapter(s_adapter);

    }

}