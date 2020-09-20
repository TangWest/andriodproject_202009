package com.example.hi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Func1Activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func_1);

        MainActivity.activityList.add(this);//添加此activity到表中

        Button button = (Button) findViewById(R.id.func1_btn_1);
        button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();

            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.func1_text_2);
                TextView tv3 = findViewById(R.id.func1_text_3);
                String t1 = tv.getText().toString();
                int a = Integer.parseInt(t1);
                double b = (float) (a - 32) / 1.8;
                String bstr = String.format("%.2f", b);
                tv3.setText(bstr + "℉");
            }
        });
    }

}