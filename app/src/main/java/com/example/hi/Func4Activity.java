package com.example.hi;

import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Func4Activity extends AppCompatActivity implements Runnable{

    double dollar = 0.1474;
    double euro = 0.1255;
    double won = 171.3606;
    TextView tv2;

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String str = (String) msg.obj;
                TextView tv4 = findViewById(R.id.func4_text_4);
                tv4.setText(str);
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func_4);
        tv2 = findViewById(R.id.func4_text_2);

        Thread t = new Thread(this);
        t.start();
    }

    public void run(){
        Log.i("func4","run:");
        URL url = null;
        try{
            url = new URL("http://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);

            useJsoup(html);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312");
        while (true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz < 0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }

    private void useJsoup(String html){
        Document doc = Jsoup.parse(html);
        Elements trs = doc.select("table").get(0).select("tr");
        //欧元
        Elements tds1 = trs.get(7).select("td");
        String text1 = tds1.get(5).text();
        euro = 100/Double.parseDouble(text1);
        String n1 = String.format("%.4f", euro);
        //韩元
        Elements tds2 = trs.get(13).select("td");
        String text2 = tds2.get(5).text();
        won = 100/Double.parseDouble(text2);
        String n2 = String.format("%.4f", won);
        //美元
        Elements tds3 = trs.get(26).select("td");
        String text3 = tds3.get(5).text();
        dollar = 100/Double.parseDouble(text3);
        String n3 = String.format("%.4f", dollar);

        Log.i("Func4","useJsoup:dollar "+n3+",euro "+n1+",won "+n2);

        Message msg = handler.obtainMessage(1);
        msg.obj = "Current exchange rate: dollar "+n3+" ,euro "+n1+" ,won "+n2;
        handler.sendMessage(msg);
    }

    public void count(View btn){
        TextView tv3 = findViewById(R.id.func4_text_3);
        String tv3str = "Please input RMB!";
        double n2 = 0;
        if(TextUtils.isEmpty(tv2.getText())){
            Toast t = Toast.makeText(this,"Please input RMB!",Toast.LENGTH_SHORT);
            t.show();
            tv3.setText(tv3str);
        }else{
            String str = tv2.getText().toString();
            double n1 =  Double.parseDouble(str);
            if(btn.getId()==R.id.func4_btn_1){
                n2 = n1*dollar;
            } else if(btn.getId()==R.id.func4_btn_2){
                n2 = n1*euro;
            } else if(btn.getId()==R.id.func4_btn_3){
                n2 = n1*won;
            }
            tv3str = String.format("%.4f", n2);
            tv3.setText(tv3str);
        }
    }
}
