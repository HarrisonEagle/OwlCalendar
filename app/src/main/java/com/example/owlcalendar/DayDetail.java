package com.example.owlcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

public class DayDetail extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daydetail);
        int year = Integer.parseInt(getIntent().getStringExtra("YEAR"));
        int month = Integer.parseInt(getIntent().getStringExtra("MONTH"));
        int day = Integer.parseInt(getIntent().getStringExtra("DAY"));
        TextView textView = findViewById(R.id.daytext);
        textView.setText(year+"年"+month+"月"+day+"日");
        ListView listView = findViewById(R.id.detaillist);
        Button newschedule = findViewById(R.id.newyoteibutton);
        newschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewSchedule.class);
                startActivity(intent);}
        });
        DetailAdapter detailAdapter = new DetailAdapter(getApplicationContext(),year,month,day);
        if(day==1&&month==1){
            detailchild dc =new detailchild("元日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==11&&month==2){
            detailchild dc =new detailchild("建国記念の日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==23&&month==2){
            detailchild dc =new detailchild("天皇誕生日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==29&&month==4){
            detailchild dc =new detailchild("昭和の日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==3&&month==5){
            detailchild dc =new detailchild("憲法記念日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==4&&month==5){
            detailchild dc =new detailchild("みどりの日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==5&&month==5){
            detailchild dc =new detailchild("こどもの日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==11&&month==8){
            detailchild dc =new detailchild("山の日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==3&&month==11){
            detailchild dc =new detailchild("文化の日","",2);
            detailAdapter.detaillist.add(dc);
        }
        if(day==23&&month==11){
            detailchild dc =new detailchild("勤労感謝の日","",2);
            detailAdapter.detaillist.add(dc);
        }
        for(int i=0;i<24;i++){
            detailchild dc =new detailchild("",String.valueOf(i),0);
            detailAdapter.detaillist.add(dc);
            for(int j=0;j<MainContents.yoteis.size();j++){
                try {
                    JSONObject jsonObject = new JSONObject(MainContents.yoteis.get(j));
                    String time = jsonObject.getString("timedata");
                    String content = jsonObject.getString("content");
                    int yeartime = Integer.valueOf(time.substring(0,4));
                    int monthtime = Integer.valueOf(time.substring(4,6));
                    int daytime = Integer.valueOf(time.substring(6,8));
                    int hour = Integer.valueOf(time.substring(8,10));
                    int minute = Integer.valueOf(time.substring(10,12));
                    String time2 = hour+":"+minute;
                    if(yeartime==year&&monthtime==month&&daytime==day&&hour==i){
                        detailchild dcc = new detailchild(jsonObject.toString(),time2,1);
                        detailAdapter.detaillist.add(dcc);
                    }
                    Log.d("DayData",yeartime+"Year"+monthtime+"Month"+daytime+"day");

                }catch (Exception e){
                    Log.d("JSONError",String.valueOf(MainContents.yoteis.size()));
                }

            }
        }
        listView.setAdapter(detailAdapter);
    }
}