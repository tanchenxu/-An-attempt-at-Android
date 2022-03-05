package com.akuya.mydear.fragmt;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.akuya.mydear.R;
import com.akuya.mydear.page.FunActivity;
import com.akuya.mydear.page.FunThirActivity;
import com.akuya.mydear.page.FunTwoActivity;
import com.akuya.mydear.page.HomeActivity;
import com.akuya.mydear.page.SuggestActivity;
import com.akuya.mydear.page.UserActivity;

public class BlankFragment extends Fragment {
    private Button button_sug,button_mylist,button_binifit,button_harm;
    private View root;
    private TextView textview;
    Intent intent =new Intent();

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(root==null) {

            root = inflater.inflate(R.layout.fragment_blank, container, false);
        }

        button_binifit=root.findViewById(R.id.btn_menu2);
        button_mylist=root.findViewById(R.id.btn_menu4);
        button_sug=root.findViewById(R.id.btn_menu1);
        button_harm=root.findViewById(R.id.btn_menu3);

        button_harm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass( getActivity(), FunThirActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        button_binifit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass( getActivity(), FunTwoActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        button_sug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass( getActivity(), SuggestActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        button_mylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass( getActivity(), FunActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }
}