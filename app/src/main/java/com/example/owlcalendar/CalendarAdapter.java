package com.example.owlcalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Integer> calendarlist = new ArrayList<Integer>();
    public int year;
    public int month;
    public Context context;

    public void initialize(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DATE,1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

        int[] monthlength = {31,28,31,30,31,30,31,31,30,31,30,31};
        if((year%400==0)||(year%4==0&&year%100!=0)){
            monthlength[1] = 29;
        }


        int youbi = calendar.get(Calendar.DAY_OF_WEEK)-1;
        Log.d("Calendar",String.valueOf(year)+String.valueOf(month));
        int init = 1;
        for(int i=0;i<35;i++){

            if(i<youbi||i>youbi+monthlength[month-1]-1){
                calendarlist.add(0);
                continue;
            }
            calendarlist.add(init);
            init++;
        }


    }

    CalendarAdapter(Context context,int year,int month){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.year = year;
        this.month = month;
        initialize();
    }

    @Override
    public int getCount() {
        return calendarlist.size();
    }

    @Override
    public Object getItem(int i) {
        return calendarlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final int yyear = year;
        final int ymonth = month;
        view = inflater.inflate(R.layout.calendarchild,null);
        TextView day = view.findViewById(R.id.childtext);
        LinearLayout childview = view.findViewById(R.id.childview);

        Calendar today = Calendar.getInstance();
        if(calendarlist.get(i)==Integer.valueOf(today.get(Calendar.DATE))&&year==Integer.valueOf(today.get(Calendar.YEAR))&&month==Integer.valueOf(today.get(Calendar.MONTH))+1){
            day.setTextColor(Color.WHITE);
            day.setBackgroundColor(Color.BLUE);
        }
        ViewGroup.LayoutParams params = childview.getLayoutParams();
        params.height = viewGroup.getHeight()/6 ;
        childview.setLayoutParams(params);
        ListView listView = view.findViewById(R.id.childlist);
        ChildAdapter childAdapter = new ChildAdapter(context,month,calendarlist.get(i));


        if(calendarlist.get(i)==1&&month==1){
            listchild lc = new listchild("元日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==11&&month==2){
            listchild lc = new listchild("建国記念の日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==23&&month==2){
            listchild lc = new listchild("天皇誕生日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==29&&month==4){
            listchild lc = new listchild("昭和の日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==3&&month==5){
            listchild lc = new listchild("憲法記念日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==4&&month==5){
            listchild lc = new listchild("みどりの日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==5&&month==5){
            listchild lc = new listchild("こどもの日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==11&&month==8){
            listchild lc = new listchild("山の日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==3&&month==11){
            listchild lc = new listchild("文化の日",0);
            childAdapter.childlistlist.add(lc);
        }
        if(calendarlist.get(i)==23&&month==11){
            listchild lc = new listchild("勤労感謝の日",0);
            childAdapter.childlistlist.add(lc);
        }

        for(int j=0;j<MainContents.yoteis.size();j++){
            try {
                JSONObject jsonObject = new JSONObject(MainContents.yoteis.get(j));
                String time = jsonObject.getString("timedata");
                String content = jsonObject.getString("content");
                int yeartime = Integer.valueOf(time.substring(0,4));
                int monthtime = Integer.valueOf(time.substring(4,6));
                int daytime = Integer.valueOf(time.substring(6,8));
                if(yeartime==year&&monthtime==month&&daytime==calendarlist.get(i)){
                    listchild lc = new listchild(content,1);
                    childAdapter.childlistlist.add(lc);
                }
            }catch (Exception e){
                Log.d("JSONError",String.valueOf(MainContents.yoteis.size()));
            }

        }
        if(calendarlist.get(i)!=0){
            day.setText(String.valueOf(calendarlist.get(i)));
            childview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context,DayDetail.class);
                    intent.putExtra("YEAR", String.valueOf(year));
                    intent.putExtra("MONTH", String.valueOf(month));
                    intent.putExtra("DAY",String.valueOf( calendarlist.get(i)));
                    context.startActivity(intent);

                }
            });
            if(childAdapter.getCount()==0){
                listView.setVisibility(View.GONE);
            }else{
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int k, long l) {
                        Intent intent =new Intent(context,DayDetail.class);
                        intent.putExtra("YEAR", String.valueOf(year));
                        intent.putExtra("MONTH", String.valueOf(month));
                        intent.putExtra("DAY",String.valueOf( calendarlist.get(i)));
                        context.startActivity(intent);
                    }
                });
            }
        }

        listView.setAdapter(childAdapter);
        return view;
    }
}
