package com.example.hi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Func2Activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func_2);

        MainActivity.activityList.add(this);//添加此activity到表中

        //Team A
        Button button1 = (Button) findViewById(R.id.func2_btn_1);//btn_1
        button1.setOnClickListener(new View.OnClickListener() {//btn_1的点击事件

            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.func2_text_2);  //btn_1要修改text_2
                String sumstr = tv.getText().toString();
                int sum = Integer.parseInt(sumstr); //初始text_2的值为"0"，String转为int
                sum += 1;   //btn_1是加一分
                tv.setText(""+sum); //更新text_2
            }
        });
        Button button2 = (Button) findViewById(R.id.func2_btn_2);
        button2.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.func2_text_2);
                String sumstr = tv.getText().toString();
                int sum = Integer.parseInt(sumstr);
                sum += 2;
                tv.setText(""+sum);
            }
        });
        Button button3 = (Button) findViewById(R.id.func2_btn_3);
        button3.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.func2_text_2);
                String sumstr = tv.getText().toString();
                int sum = Integer.parseInt(sumstr);
                sum += 3;
                tv.setText(""+sum);
            }
        });

        //Team B
        Button button4 = (Button) findViewById(R.id.func2_btn_4);
        button4.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.func2_text_3);
                String sumstr = tv.getText().toString();
                int sum = Integer.parseInt(sumstr);
                sum += 1;
                tv.setText(""+sum);
            }
        });
        Button button5 = (Button) findViewById(R.id.func2_btn_5);
        button5.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.func2_text_3);
                String sumstr = tv.getText().toString();
                int sum = Integer.parseInt(sumstr);
                sum += 2;
                tv.setText(""+sum);
            }
        });
        Button button6 = (Button) findViewById(R.id.func2_btn_6);
        button6.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.func2_text_3);
                String sumstr = tv.getText().toString();
                int sum = Integer.parseInt(sumstr);
                sum += 3;
                tv.setText(""+sum);
            }
        });

        //RESET
        Button button7 = (Button) findViewById(R.id.func2_btn_7);
        button7.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                TextView tv1 = findViewById(R.id.func2_text_2);
                TextView tv2 = findViewById(R.id.func2_text_3);
                tv1.setText("0");
                tv2.setText("0");
            }
        });
    }

}