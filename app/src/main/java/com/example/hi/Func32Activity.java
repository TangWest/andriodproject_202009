package com.example.hi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Func32Activity extends AppCompatActivity {
    double dollar;
    double euro;
    double won;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func_32);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        double dollar = bundle.getDouble("dollar");
        double euro = bundle.getDouble("euro");
        double won = bundle.getDouble("won");


        TextView tv1 = findViewById(R.id.func32_text_1);
        TextView tv2 = findViewById(R.id.func32_text_2);
        TextView tv3 = findViewById(R.id.func32_text_3);
        tv1.setText(""+dollar);
        tv2.setText(""+euro);
        tv3.setText(""+won);

    }
    public void save(View btn){
        TextView tv1 = findViewById(R.id.func32_text_1);
        TextView tv2 = findViewById(R.id.func32_text_2);
        TextView tv3 = findViewById(R.id.func32_text_3);
        dollar =  Double.parseDouble(tv1.getText().toString());
        euro = Double.parseDouble(tv2.getText().toString());
        won = Double.parseDouble(tv3.getText().toString());

        Intent data = new Intent(this,Func3Activity.class);

        Bundle bundle = new Bundle();
        bundle.putDouble("new_dollar",dollar);
        bundle.putDouble("new_euro",euro);
        bundle.putDouble("new_won",won);
        data.putExtras(bundle);

        setResult(1,data);
        Log.i("Func32","save:setResult");
        finish();
    }
    public void saveSP(View btn) {
        Intent spdata = new Intent(this,Func3Activity.class);

        TextView tv1 = findViewById(R.id.func32_text_1);
        TextView tv2 = findViewById(R.id.func32_text_2);
        TextView tv3 = findViewById(R.id.func32_text_3);
        dollar =  Double.parseDouble(tv1.getText().toString());
        euro = Double.parseDouble(tv2.getText().toString());
        won = Double.parseDouble(tv3.getText().toString());

        SharedPreferences sp = getSharedPreferences("rate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollar_rate",(float) dollar);
        editor.putFloat("euro_rate",(float) euro);
        editor.putFloat("won_rate",(float) won);
        editor.apply();

        setResult(2,spdata);
        Log.i("Func32","saveSP:setResult");
        finish();
    }
}