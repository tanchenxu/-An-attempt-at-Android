package com.akuya.mypopuwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "akuya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void akuyaclick(View view) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_view, null);

        Button btn1 = popupView.findViewById(R.id.btn1);
        Button btn2 = popupView.findViewById(R.id.btn2);

        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT,true);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.wall));
        popupWindow.showAsDropDown(view);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: 我想去上海" );
                popupWindow.dismiss();
    }
});

        btn2.setOnClickListener(new View.OnClickListener() {
         @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: 我想去北京" );
                popupWindow.dismiss();
    }
});




    }
}