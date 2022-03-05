package com.akuya.mydear.fragmt;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akuya.mydear.ImageUtil;
import com.akuya.mydear.MainActivity;
import com.akuya.mydear.R;
import com.akuya.mydear.bean.LoginUserBean;
import com.akuya.mydear.page.EidtActivity;
import com.akuya.mydear.page.UersActivity;

import org.litepal.LitePal;

import java.util.List;


public class UserMain extends Fragment {
    private List<LoginUserBean> loginUserBeans = LitePal.findAll(LoginUserBean.class);
    private LoginUserBean loginUserBean;
    private View root;
    private Button Rt_return,Bt_Edit;
    private TextView tvNickName,tvAccount,tvAge,tvGender,tvCity,tvSchool,tvGoal;
    private ImageView Iv_avater;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        if (root == null) {
            root = inflater.inflate(R.layout.fragment_user_main, container, false);
        }


        tvAccount =root.findViewById(R.id.tv_account_text);
        tvNickName =root.findViewById(R.id.tv_nick_name);
        tvAge =root.findViewById(R.id.tv_age);
        tvSchool =root.findViewById(R.id.tv_school_text);
        tvCity =root.findViewById(R.id.tv_city_text);
        tvGender =root.findViewById(R.id.tv_gender);
        Bt_Edit=root.findViewById(R.id.bt_edit);
        Rt_return = root.findViewById(R.id.BT_return);
        tvGoal=root.findViewById(R.id.tv_Goal_text);
        Iv_avater=root.findViewById(R.id.iv_avaters);

        SharedPreferences spf1 = getActivity().getSharedPreferences("spfRecorid1",MODE_PRIVATE);
        String accountnew = spf1.getString("account1","");
        for (int i=0;i<loginUserBeans.size();i++) {
            Log.e("akuya", "onCreate:1111 ");
            if (accountnew.equals(loginUserBeans.get(i).getUserName())) {
                loginUserBean = LitePal.find(LoginUserBean.class, loginUserBeans.get(i).getId(), true);
                Log.e("akuya", "onCreate: i find it" + loginUserBean.getUserName() + loginUserBean.getId());
                break;
            }
        }



        tvGoal.setText(loginUserBean.getGoal());
        tvAccount.setText(accountnew);
        tvNickName.setText(loginUserBean.getNickName());
        tvAge.setText(loginUserBean.getAge());
        tvCity.setText(loginUserBean.getCity());
        tvSchool.setText(loginUserBean.getSchool());
        tvGender.setText(loginUserBean.getGender());
        if(loginUserBean.getHead()!=null) {
            Iv_avater.setImageBitmap(ImageUtil.base64ToImage(loginUserBean.getHead()));
        }

        Bt_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EidtActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        Rt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences spf = getActivity().getSharedPreferences("spfRecorid",MODE_PRIVATE);
                SharedPreferences.Editor edit =spf.edit();
                edit.putBoolean("isRemember",false);
                edit.apply();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return root;
    }
}
