package com.example.owlcalendar;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookiePolicy;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.impl.client.BasicCookieStore;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
import cz.msebera.android.httpclient.protocol.HttpContext;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScheduleList extends Fragment {


    public static Context context;
    public static ListView lv;
    public static ArrayList<String> yoteis = new ArrayList<>();



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final View v = inflater.inflate(R.layout.schedulelist,container, false);
        context = getContext();
        //
        //request生成

        if(lv == null){
            lv = v.findViewById(R.id.schlist);
        }
        String username = MainContents.getDefaults("username",getContext());
        String password = MainContents.getDefaults("password",getContext());
        if(username==null){
            username="";
        }
        if(password==null){
            password="";
        }
        MainContents.webclient.setBasicAuth(username,password);
        MainContents.webclient.get(DataCommunicator.PROTOCOL + "://" + DataCommunicator.host + "/schapi/index", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                //response取り出し
                final String jsonStr = new String(responseBody);
                Log.d("Tagarray","jsonStr=" + jsonStr);
                try {
                    JSONArray jarray = new JSONArray(jsonStr);
                    for (int i = 0; i < jarray.length(); ++ i) {
                        JSONObject json = jarray.getJSONObject(i);

                        String content = json.getString("content");
                        String time = json.getString("timedata");
                        String yotei = "Content:"+content+"Time:"+time;
                        yoteis.add(json.toString());
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


                }catch(Exception e){
                    Log.e("Hoge",e.getMessage());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                ScheduleAdapter aa = new ScheduleAdapter(getContext(),yoteis);
                lv.setAdapter(aa);

                Button newschedule = v.findViewById(R.id.newyoteibutton);
                newschedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(),NewSchedule.class);
                        startActivity(intent);
                    }
                });

            }
                });











        return v;
    }

    }
