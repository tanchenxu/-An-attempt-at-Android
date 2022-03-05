package com.akuya.mydear.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.akuya.mydear.R;
import com.akuya.mydear.bean.LoginUserBean;
import com.akuya.mydear.bean.ReduceFoodBean;
import com.akuya.mydear.bean.SuggestBean;
import com.akuya.mydear.page.ALL_Adapter.ThirAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class FunThirActivity extends AppCompatActivity {

    private List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private RecyclerView recyclerView;
    private Button Bt_add2,Bt_origin,Bt_back;
    private List<ReduceFoodBean> data =new ArrayList<ReduceFoodBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_thir);

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

        data=loginUserBean.getReduceFoodBeans();

        recyclerView = findViewById(R.id.rv3);

        //分割线
        DividerItemDecoration mDivider = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDivider);

        //布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ThirAdapter thirAdapter = new ThirAdapter(this.loginUserBean.getReduceFoodBeans(),this);
        recyclerView.setAdapter(thirAdapter);

        Bt_back=findViewById(R.id.bt_back3);
        Bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunThirActivity.this,NewHome.class);
                startActivity(intent);
                FunThirActivity.this.finish();
            }
        });


        Bt_add2=findViewById(R.id.bt_add2);

        Bt_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunThirActivity.this,NewThirListActivity.class);
                startActivity(intent);
                FunThirActivity.this.finish();
            }
        });

    }
}