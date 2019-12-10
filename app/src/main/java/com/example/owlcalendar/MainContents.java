package com.example.owlcalendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainContents extends AppCompatActivity{
        public static DataCommunicator dm =new DataCommunicator();


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
            setContentView(R.layout.maincontent);
            final MainActivity ma=new MainActivity();
            final ScheduleList sl=new ScheduleList();
            final MyPage mp=new MyPage();
            getSupportFragmentManager().beginTransaction().add(R.id.maincontainer,sl).add(R.id.maincontainer,mp).add(R.id.maincontainer,ma).hide(sl).hide(mp).show(ma).commit();
            Button calendar = findViewById(R.id.calendarbtn);
            Button schedule = findViewById(R.id.schedulebtn);
            Button mypage = findViewById(R.id.mypagebtn);
            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction().hide(sl).hide(mp).show(ma).commit();
                }
            });

            schedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction().hide(ma).hide(mp).show(sl).commit();
                }
            });

            mypage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSupportFragmentManager().beginTransaction().hide(sl).hide(ma).show(mp).commit();
                }
            });
        }
}
