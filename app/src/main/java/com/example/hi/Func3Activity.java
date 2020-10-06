package com.example.hi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Func3Activity extends Activity {

    TextView tv2;
    double dollar = 0.1474;
    double euro = 0.1255;
    double won = 171.3606;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func_3);

        MainActivity.activityList.add(this);//添加此activity到表中
        tv2 = findViewById(R.id.func3_text_2);
    }

    public void count(View btn){
        TextView tv3 = findViewById(R.id.func3_text_3);
        String tv3str = "Please input RMB!";
        double n2 = 0;
        if(TextUtils.isEmpty(tv2.getText())){
            Toast t = Toast.makeText(this,"Please input RMB!",Toast.LENGTH_SHORT);
            t.show();
            tv3.setText(tv3str);
        }else{
            String str = tv2.getText().toString();
            double n1 =  Double.parseDouble(str);
            if(btn.getId()==R.id.func3_btn_1){
                n2 = n1*dollar;
            } else if(btn.getId()==R.id.func3_btn_2){
                n2 = n1*euro;
            } else if(btn.getId()==R.id.func3_btn_3){
                n2 = n1*won;
            }
            tv3str = String.format("%.2f", n2);
            tv3.setText(tv3str);
        }
    }

    public void open(View btn){
        Intent intent = new Intent(this,Func32Activity.class);

        Bundle bundle = new Bundle();
        bundle.putDouble("dollar",dollar);
        bundle.putDouble("euro",euro);
        bundle.putDouble("won",won);
        intent.putExtras(bundle);
        Log.i("Func3","open");

        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==1){
            Bundle bundle = data.getExtras();

            dollar = bundle.getDouble("new_dollar");
            euro = bundle.getDouble("new_euro");
            won = bundle.getDouble("new_won");

            Log.i("Func3","onActivityResult resultCode==1:dollar "+dollar+",euro "+euro+",won "+won);
        }else if(requestCode==1&&resultCode==2){
            SharedPreferences sp = getSharedPreferences("rate", Activity.MODE_PRIVATE);
            PreferenceManager.getDefaultSharedPreferences(this);

            dollar = sp.getFloat("dollar_rate",0.0000f);
            euro = sp.getFloat("euro_rate",0.0000f);
            won = sp.getFloat("won_rate",0.0000f);

            Log.i("Func3","onActivityResult resultCode==2:dollar "+dollar+",euro "+euro+",won "+won);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}