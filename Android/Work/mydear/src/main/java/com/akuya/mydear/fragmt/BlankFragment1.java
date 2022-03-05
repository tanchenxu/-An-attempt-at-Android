package com.akuya.mydear.fragmt;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.akuya.mydear.MainActivity;
import com.akuya.mydear.R;
import com.akuya.mydear.RegisterActivity2;
import com.akuya.mydear.page.HomeActivity;
import com.akuya.mydear.page.UersActivity;
import com.akuya.mydear.page.UserActivity;

public class BlankFragment1 extends Fragment {


    private View root;
    private Button button;
    private  Button button2;
    Intent intent =new Intent();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(root== null) {
            root = inflater.inflate(R.layout.fragment_blank1, container, false);
        }
        button = root.findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                intent.setClass( getActivity(),HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        button2 =root.findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass( getActivity(), UserActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return  root;
    }
}