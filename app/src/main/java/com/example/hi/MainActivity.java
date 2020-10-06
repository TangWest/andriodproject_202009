package com.example.hi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
     *一键彻底退出程序的实现：
     * 将后来onCreate的每个activity记录到表中，点击exitButton调用exit()，使表中所有的activity被finish，退出程序。
     */
    public static List<Activity> activityList = new LinkedList();//创建acticity表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//显示布局，activity_main.xml

        activityList.add(this);//在表中添加此activity

        Button func1Button = (Button) findViewById(R.id.main_btn_1);//点击第一个按钮
        func1Button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, Func1Activity.class);
                startActivity(intent);
            }
        });

        Button func2Button = (Button) findViewById(R.id.main_btn_2);
        func2Button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, Func2Activity.class);
                startActivity(intent);
            }
        });

        Button func3Button = (Button) findViewById(R.id.main_btn_3);
        func3Button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, Func3Activity.class);
                startActivity(intent);
            }
        });

        Button func4Button = (Button) findViewById(R.id.main_btn_4);
        func4Button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, Func4Activity.class);
                startActivity(intent);
            }
        });

    }

}
