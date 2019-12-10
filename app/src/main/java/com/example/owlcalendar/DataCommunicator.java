package com.example.owlcalendar;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.loopj.android.http.*;

public class DataCommunicator {

    public static String message="";

    String login(String username,String password){

        return message;
    }

    void getschedule(String url) throws IOException {

        //OkHttpClinet生成
        OkHttpClient client = new OkHttpClient();

        //request生成
        RequestBody formBody = new FormBody.Builder()
                .add("userid","1")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //非同期リクエスト
        client.newCall(request)
                .enqueue(new Callback() {

                    //エラーのとき
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("Hoge",e.getMessage());
                    }

                    //正常のとき
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        //response取り出し
                        final String jsonStr = response.body().string();
                        //Log.d("Tagarray","jsonStr=" + jsonStr);
                        try {
                            JSONArray jarray = new JSONArray(jsonStr);
                            for (int i = 0; i < jarray.length(); ++ i) {
                                JSONObject json = jarray.getJSONObject(i);
                                String id   = json.getString("id");
                                String userid   = json.getString("userid");
                                String content = json.getString("content");
                                String time = json.getString("time");
                                Log.d("Schedule","id:"+id+"content:"+content+"time:"+time+"Userid:"+userid);
                            }
                        }
                        catch (org.json.JSONException e) {

                        }


                        //JSON処理
                        try{
                            //jsonパース
                            JSONObject json = new JSONObject(jsonStr);
                            final String status = json.getString("status");

                            //親スレッドUI更新
                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });


                        }catch(Exception e){
                            Log.e("Hoge",e.getMessage());
                        }

                    }
                });
    }

    void postschedule(String urls,final String content,final int time,final int userid,final String timedata) throws Exception{
        final String posturl = urls;
        Thread thread = new Thread(new Runnable()

        {
            @Override
            public void run() {
                try {
                    JSONObject json=new JSONObject();
                    json.put("content",content);
                    json.put("time",time);
                    json.put("userid",userid);
                    json.put("timedata",timedata);
                    String buffer = "";
                    HttpURLConnection con = null;
                    URL url = new URL(posturl);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setInstanceFollowRedirects(false);
                    con.setRequestProperty("Accept-Language", "jp");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    con.connect();
                    DataOutputStream os = new DataOutputStream(con.getOutputStream());
                    os.writeBytes(json.toString());

                    os.flush();
                    os.close();

                    Log.d("post","ResponseCode:"+con.getResponseCode());

                    con.disconnect();
                }catch (Exception e) {
                    e.printStackTrace();
                    Log.d("errtag",e.toString());
                }

            }
        });

        thread.start();
    }
}
