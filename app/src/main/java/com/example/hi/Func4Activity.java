package com.example.hi;

import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Func4Activity extends AppCompatActivity implements Runnable{

    double dollar = 0.1474;
    double euro = 0.1255;
    double won = 171.3606;
    TextView tv2;
    int oneday = 86400;
    int testtime = 5000;
    int sum = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.func_4);
        tv2 = findViewById(R.id.func4_text_2);

        Thread thread = new Thread(this);
        thread.start();


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String str = (String) msg.obj;
                TextView tv4 = findViewById(R.id.func4_text_4);
                tv4.setText(str);
                Log.i("Func4","handleMessage: mag.what==0 Thread:"+Thread.currentThread().getName());
            }else if(msg.what == 1){
                sum++;
                String str = (String) msg.obj;
                TextView tv5 = findViewById(R.id.func4_text_5);
                tv5.setText(str+" and already updated for "+sum+" time(s).");
                Log.i("Func4","handleMessage: mag.what==1 Thread:"+Thread.currentThread().getName());
            }
        }
    };

    public void run(){
        // 不能在主线程中请求HTTP请求 不能在主线程中延时
        new Thread(new Runnable(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        new Thread(new Runnable(){
                            @Override
                            public void run() {
                                showRate();
                                showUpdateTime();
                            }
                        }).start();

                        Log.i("Func4","run: postDelayed() Thread:"+Thread.currentThread().getName());
                        //handler.postDelayed(this,oneday);
                        handler.postDelayed(this,testtime);
                    }
                });
            }
        }).start();
    }

    private  void  showRate(){
        Log.i("Func4","run showRate(): Thread:"+Thread.currentThread().getName());
        URL url = null;
        InputStream in = null;
        try{
            url = new URL("http://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            in = http.getInputStream();
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
        inputStream.close();
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

        Message msg = handler.obtainMessage(0);
        msg.obj = "Current exchange rate: dollar "+n3+" ,euro "+n1+" ,won "+n2;
        handler.sendMessage(msg);
    }

    private void showUpdateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        Message msg = handler.obtainMessage(1);
        msg.obj = "Last update time: "+simpleDateFormat.format(date);
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
