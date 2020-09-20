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

        Button button1 = (Button) findViewById(R.id.func2_btn_1);
        button1.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.func2_text_2);
                String sumstr = tv.getText().toString();
                System.out.println("xxxxxxxxxxxxxxxxx"+sumstr);
                int sum = Integer.parseInt(sumstr);
                sum += 1;
                tv.setText(""+sum);
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
    }

}