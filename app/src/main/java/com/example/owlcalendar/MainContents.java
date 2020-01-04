package com.example.owlcalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.impl.client.BasicCookieStore;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
import cz.msebera.android.httpclient.protocol.HttpContext;

public class MainContents extends AppCompatActivity{
    public static DataCommunicator dm =new DataCommunicator();
    public static AsyncHttpClient webclient;
    public static int loginstatus = 0;
    public static int curentindex;
    public static FragmentManager fm;
    public static Context context;
    public static MyPage mp;

    public static MainActivity ma;
    public static ScheduleList sl;

    public static ArrayList<String> yoteis = new ArrayList<String>();

    public static void retrievedata(){
        String username = getDefaults("username",context);
        String password = getDefaults("password",context);
        if(username==null){
            username="";
        }
        if(password==null){
            password="";
        }
        webclient.setBasicAuth(username,password);




        webclient.post(DataCommunicator.PROTOCOL+"://"+DataCommunicator.host+"/loginapi/index", new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Log.d("response",response);
                if(response.equals("Password not Correct!")||response.equals("User not exist!")){
                    loginstatus = 0;
                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                }else{
                    loginstatus = 1;
                    MainContents.setDefaults("userdata",response,context);


                }
                MyPage.setup();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("msg","status"+statusCode);
                Toast.makeText(context, "Error Code:"+statusCode, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFinish() {
                super.onFinish();
                String username = MainContents.getDefaults("username",context);
                String password = MainContents.getDefaults("password",context);
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
                        yoteis.clear();
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
                        ma.retrievedata();


                    }
                });

            }
        });
    }



    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static void delDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().remove(key).apply();
    }
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            if(!isTaskRoot()){
                //finish();
            }
            setContentView(R.layout.maincontent);

            context = getApplicationContext();
            fm = getSupportFragmentManager();

            webclient = new AsyncHttpClient();
            HttpContext httpContext = webclient.getHttpContext();
            DataCommunicator.myCookieStore = (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
            webclient.setCookieStore(DataCommunicator.myCookieStore);
            Toast.makeText(getApplicationContext(),getDefaults("username",getApplicationContext()),Toast.LENGTH_LONG);


            ma = new MainActivity();
            sl=new ScheduleList();
            mp=new MyPage();
            fm.beginTransaction().add(R.id.maincontainer,sl).add(R.id.maincontainer,mp).add(R.id.maincontainer,ma).hide(sl).hide(mp).show(ma).commit();
            final ImageButton calendar = findViewById(R.id.calendarbtn);
            final ImageButton schedule = findViewById(R.id.schedulebtn);
            final ImageButton mypage = findViewById(R.id.mypagebtn);
            calendar.setImageResource(R.mipmap.calendaricon1);
            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction().hide(sl).hide(mp).show(ma).commit();
                    calendar.setImageResource(R.mipmap.calendarsmallicon);
                    schedule.setImageResource(R.mipmap.schedulelistgray);
                    mypage.setImageResource(R.mipmap.settingsgray);

                }
            });

            schedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction().hide(ma).hide(mp).show(sl).commit();
                    calendar.setImageResource(R.mipmap.calendarsmallgray);
                    schedule.setImageResource(R.mipmap.schedulelist);
                    mypage.setImageResource(R.mipmap.settingsgray);
                }
            });

            mypage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction().hide(sl).hide(ma).show(mp).commit();
                    calendar.setImageResource(R.mipmap.calendarsmallgray);
                    schedule.setImageResource(R.mipmap.schedulelistgray);
                    mypage.setImageResource(R.mipmap.settinggreen);
                }
            });

            retrievedata();





        }
}
