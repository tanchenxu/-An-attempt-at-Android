package com.akuya.mydear.page;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.akuya.mydear.ImageUtil;
import com.akuya.mydear.R;
import com.akuya.mydear.bean.LoginUserBean;
import com.akuya.mydear.bean.SuggestBean;
import com.akuya.mydear.page.ALL_Adapter.TwoAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class FunTwoActivity extends AppCompatActivity {
    private List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private RecyclerView recyclerView;
    private Button Bt_add,Bt_origin,Bt_back;
    private List<SuggestBean> data =new ArrayList<SuggestBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_two);

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

        data=loginUserBean.getSuggestBeans();

        recyclerView = findViewById(R.id.rv2);

        //分割线
        DividerItemDecoration mDivider = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDivider);

        //布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        TwoAdapter twoAdapter = new TwoAdapter(this.loginUserBean.getSuggestBeans(),this);
        recyclerView.setAdapter(twoAdapter);

        //界面按钮
        Bt_add=findViewById(R.id.bt_add2);
        Bt_origin=findViewById(R.id.bt_origin);
        Bt_back=findViewById(R.id.bt_back2);

        Bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunTwoActivity.this,NewHome.class);
                startActivity(intent);
                FunTwoActivity.this.finish();
            }
        });

        Bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunTwoActivity.this,NewTwoListActivity.class);
                startActivity(intent);
                FunTwoActivity.this.finish();
            }
        });

        Bt_origin=findViewById(R.id.bt_origin);

        Bt_origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuggestBean suggestBean =new SuggestBean();
                suggestBean.setFoodName("鸡蛋");
                suggestBean.setNutri("富含蛋白质，脂肪，维生素B、D、E，钙。");
                suggestBean.setBinifit("1、益脑益智类：生鸡蛋对中枢神经系统和人体生长发育有非常大的功效，在其中含的胆碱可改进每个年龄段的记忆能力。\n" +
                        "\n" +
                        "2、保护肝脏：生鸡蛋中的蛋白对肝脏组织损害有修补功效，鸡蛋黄中的大豆卵磷脂可推动肝脏的再造。还可提升身体血浆蛋白量，提高身体的新陈代谢作用和免疫功能。\n" +
                        "\n" +
                        "3、防衰老：许多 最长寿的人的益寿延年工作经验之一，便是每日必食一个鸡蛋。\n" +
                        "\n" +
                        "4、防止癌症：依据对全球人类癌症致死率开展的剖析，大家发觉癌症的致死率与硒的摄取量反比。而生鸡蛋中带有丰富多彩的硒元素。");
                suggestBean.setInto("早餐&午餐");
                suggestBean.setKaluli("114k/100g");
                loginUserBean.getSuggestBeans().add(suggestBean);
                suggestBean.save();
                loginUserBean.save();

                SuggestBean suggestBean2 =new SuggestBean();
                suggestBean2.setFoodName("牛奶");
                suggestBean2.setNutri("蛋白质、钙、磷、维生素A、维生素D和维生素B2\n");
                suggestBean2.setBinifit("1、能够提高机体的免疫力，因为牛奶当中富含有丰富的蛋白质，能够为免疫球蛋白在内的众多免疫物质提供构成的原料，进而抵御一些感染性疾病风险的能力就会得到提高.\n"+
                        "2、能够避免骨质疏松，让我们的骨骼更加强壮。因为牛奶当中也富含有丰富的钙质，并且钙磷比例合适吸收率会比较高，适当的喝牛奶在预防骨质疏松、腰腿痛和腰膝酸软方面的好处非常明显。");
                suggestBean2.setInto("早餐、晚餐");
                suggestBean2.setKaluli("47k/100g");
                loginUserBean.getSuggestBeans().add(suggestBean2);
                suggestBean2.save();
                loginUserBean.save();

                SuggestBean suggestBean3 =new SuggestBean();
                suggestBean3.setFoodName("番茄");
                suggestBean3.setNutri("维生素A、维生素C、维生素B1，维生素B2以及胡萝卜素和钙、磷、钾\n");
                suggestBean3.setBinifit("番茄含的“番茄素”，有抑制细菌的作用；含的苹果酸、柠檬酸和糖类，有助消化的功能。番茄含有丰富的营养，又有多种功用被称为神奇的菜中之果。番茄内的苹果酸和柠檬酸等有机酸，还有增加胃液酸度，帮助消化，调整胃肠功能的作用。番茄中含有果酸，能降低胆固醇的含量，对高血脂症很有益处。");
                suggestBean3.setInto("午餐");
                suggestBean3.setKaluli("20k/100g");
                loginUserBean.getSuggestBeans().add(suggestBean3);
                suggestBean3.save();
                loginUserBean.save();

                SuggestBean suggestBean4 =new SuggestBean();
                suggestBean4.setFoodName("苹果");
                suggestBean4.setNutri("含维生素B、维生素C及钙、磷、钾、铁等构成大脑所必须的营养成分。\n");
                suggestBean4.setBinifit("1、可以降低血脂。苹果中含有非常丰富的维生素、微量元素，特别是含有纤维素，可以帮助人体减少胆固醇的吸收，降低脂肪的含量。\n"+"2、苹果可以缓解和治疗便秘，特别是老年性便秘，活动差，饮食结构不合理，可以通过吃苹果进行调整.\n"+"3、苹果可以帮助降低血压，因为苹果中含有非常丰富的钾，可以导致人体的血压出现平稳和下降。");
                suggestBean4.setInto("零食");
                suggestBean4.setKaluli("54k/100g");
                loginUserBean.getSuggestBeans().add(suggestBean4);
                suggestBean4.save();
                loginUserBean.save();

                SuggestBean suggestBean5 =new SuggestBean();
                suggestBean5.setFoodName("香菇炖鸡");
                suggestBean5.setNutri("热量2544.63大卡、钾3890.19毫克、磷2407.74毫克、胆固醇1590毫克、钠955.98毫克、维生素A727.83微克\n");
                suggestBean5.setBinifit("杀菌、促进消化、降血压、降血脂、防癌抗癌、延缓衰老、补钙、提高机体免疫功能、降胆固醇。");
                suggestBean5.setInto("午餐");
                suggestBean5.setKaluli("2544/100g");
                loginUserBean.getSuggestBeans().add(suggestBean5);
                suggestBean5.save();
                loginUserBean.save();


            }
        });

        twoAdapter.setRecyclierItemClickListener(new TwoAdapter.OnRecyclierItemClickListener() {
            @Override
            public void onRecyclierItemClick(int position) {

            }
        });














    }
}