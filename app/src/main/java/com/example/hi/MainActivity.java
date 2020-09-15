package com.example.hi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_1);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        TextView tv = findViewById(R.id.text_2);
        TextView tv3 = findViewById(R.id.text_3);
        String t1 = tv.getText().toString();
        int a = Integer.parseInt(t1);
        double b = (float) (a-32)/1.8;
        String bstr = String.format("%.2f", b);
        tv3.setText(bstr+"℉");
    }

}