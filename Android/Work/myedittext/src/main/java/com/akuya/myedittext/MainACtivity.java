package com.akuya.myedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainACtivity extends AppCompatActivity {

    private EditText et;
    private EditText ps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myeditext);


       Button btn=findViewById(R.id.btn);
       et =findViewById(R.id.et);
       ps=findViewById(R.id.ps);

       btn.setOnClickListener(new View.OnClickListener(){


           @Override
           public void onClick(View view) {
        String  text= et.getText().toString();
               Log.e("akuya", "输入的内容: "+text );
        String Text= ps.getText().toString();
             Log.e("akuya", "输入的内容: "+Text );
           }
       });
    }
}