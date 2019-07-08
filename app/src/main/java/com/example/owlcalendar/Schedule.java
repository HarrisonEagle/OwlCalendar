package com.example.owlcalendar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Schedule extends AppCompatActivity {

    public static int index;
    public int year;
    public int month;
    public int day;


    Schedule(){

    }

    Schedule(int index){
        this.index=index;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        Button exit=findViewById(R.id.exit);

        TextView datetext=findViewById(R.id.scdate);

        year=MainActivity.year;
        month=MainActivity.month;
        day=Integer.valueOf(MainActivity.daydata[index]);

        datetext.setText(year+"年"+month+"月"+day+"日");

        final EditText content=findViewById(R.id.sccontent);

        for(int i=0;i<MainActivity.sclist.size();i++){

            sccontent sc=(sccontent)MainActivity.sclist.get(i);
            if(sc.getDay()==day&&sc.getMonth()==month&&sc.getYear()==year){
                content.setText(sc.getContent());
                break;
            }

        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<MainActivity.sclist.size();i++){

                    sccontent sc=(sccontent)MainActivity.sclist.get(i);
                    if(sc.getDay()==day&&sc.getMonth()==month&&sc.getYear()==year){
                        MainActivity.sclist.remove(sc);
                        break;
                    }

                }
                sccontent sc2=new sccontent();
                sc2.setDay(day);
                sc2.setMonth(month);
                sc2.setYear(year);
                sc2.setContent(content.getText().toString());
                Log.d("tag",content.toString());
                MainActivity.sclist.add(sc2);
                switch (index){
                    case 0:MainActivity.c01.setBackgroundColor(Color.CYAN);break;
                    case 1:MainActivity.c02.setBackgroundColor(Color.CYAN);break;
                    case 2:MainActivity.c03.setBackgroundColor(Color.CYAN);break;
                    case 3:MainActivity.c04.setBackgroundColor(Color.CYAN);break;
                    case 4:MainActivity.c05.setBackgroundColor(Color.CYAN);break;
                    case 5:MainActivity.c06.setBackgroundColor(Color.CYAN);break;
                    case 6:MainActivity.c07.setBackgroundColor(Color.CYAN);break;
                    case 7:MainActivity.c08.setBackgroundColor(Color.CYAN);break;
                    case 8:MainActivity.c09.setBackgroundColor(Color.CYAN);break;
                    case 9:MainActivity.c10.setBackgroundColor(Color.CYAN);break;
                    case 10:MainActivity.c11.setBackgroundColor(Color.CYAN);break;
                    case 11:MainActivity.c12.setBackgroundColor(Color.CYAN);break;
                    case 12:MainActivity.c13.setBackgroundColor(Color.CYAN);break;
                    case 13:MainActivity.c14.setBackgroundColor(Color.CYAN);break;
                    case 14:MainActivity.c15.setBackgroundColor(Color.CYAN);break;
                    case 15:MainActivity.c16.setBackgroundColor(Color.CYAN);break;
                    case 16:MainActivity.c17.setBackgroundColor(Color.CYAN);break;
                    case 17:MainActivity.c18.setBackgroundColor(Color.CYAN);break;
                    case 18:MainActivity.c19.setBackgroundColor(Color.CYAN);break;
                    case 19:MainActivity.c20.setBackgroundColor(Color.CYAN);break;
                    case 20:MainActivity.c21.setBackgroundColor(Color.CYAN);break;
                    case 21:MainActivity.c22.setBackgroundColor(Color.CYAN);break;
                    case 22:MainActivity.c23.setBackgroundColor(Color.CYAN);break;
                    case 23:MainActivity.c24.setBackgroundColor(Color.CYAN);break;
                    case 24:MainActivity.c25.setBackgroundColor(Color.CYAN);break;
                    case 25:MainActivity.c26.setBackgroundColor(Color.CYAN);break;
                    case 26:MainActivity.c27.setBackgroundColor(Color.CYAN);break;
                    case 27:MainActivity.c28.setBackgroundColor(Color.CYAN);break;
                    case 28:MainActivity.c29.setBackgroundColor(Color.CYAN);break;
                    case 29:MainActivity.c30.setBackgroundColor(Color.CYAN);break;
                    case 30:MainActivity.c31.setBackgroundColor(Color.CYAN);break;
                    case 31:MainActivity.c32.setBackgroundColor(Color.CYAN);break;
                    case 32:MainActivity.c33.setBackgroundColor(Color.CYAN);break;
                    case 33:MainActivity.c34.setBackgroundColor(Color.CYAN);break;
                    case 34:MainActivity.c35.setBackgroundColor(Color.CYAN);break;
                    case 35:MainActivity.c36.setBackgroundColor(Color.CYAN);break;
                    case 36:MainActivity.c37.setBackgroundColor(Color.CYAN);break;
                    case 37:MainActivity.c38.setBackgroundColor(Color.CYAN);break;
                    case 38:MainActivity.c39.setBackgroundColor(Color.CYAN);break;
                    case 39:MainActivity.c40.setBackgroundColor(Color.CYAN);break;
                    case 40:MainActivity.c41.setBackgroundColor(Color.CYAN);break;
                    case 41:MainActivity.c42.setBackgroundColor(Color.CYAN);break;
                }
                finish();
            }
        });

        Button clear=findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<MainActivity.sclist.size();i++){

                    sccontent sc=(sccontent)MainActivity.sclist.get(i);
                    if(sc.getDay()==day&&sc.getMonth()==month&&sc.getYear()==year){
                        MainActivity.sclist.remove(sc);
                        break;
                    }


                }
                switch (index){
                    case 0:MainActivity.c01.setBackgroundColor(Color.TRANSPARENT);break;
                    case 1:MainActivity.c02.setBackgroundColor(Color.TRANSPARENT);break;
                    case 2:MainActivity.c03.setBackgroundColor(Color.TRANSPARENT);break;
                    case 3:MainActivity.c04.setBackgroundColor(Color.TRANSPARENT);break;
                    case 4:MainActivity.c05.setBackgroundColor(Color.TRANSPARENT);break;
                    case 5:MainActivity.c06.setBackgroundColor(Color.TRANSPARENT);break;
                    case 6:MainActivity.c07.setBackgroundColor(Color.TRANSPARENT);break;
                    case 7:MainActivity.c08.setBackgroundColor(Color.TRANSPARENT);break;
                    case 8:MainActivity.c09.setBackgroundColor(Color.TRANSPARENT);break;
                    case 9:MainActivity.c10.setBackgroundColor(Color.TRANSPARENT);break;
                    case 10:MainActivity.c11.setBackgroundColor(Color.TRANSPARENT);break;
                    case 11:MainActivity.c12.setBackgroundColor(Color.TRANSPARENT);break;
                    case 12:MainActivity.c13.setBackgroundColor(Color.TRANSPARENT);break;
                    case 13:MainActivity.c14.setBackgroundColor(Color.TRANSPARENT);break;
                    case 14:MainActivity.c15.setBackgroundColor(Color.TRANSPARENT);break;
                    case 15:MainActivity.c16.setBackgroundColor(Color.TRANSPARENT);break;
                    case 16:MainActivity.c17.setBackgroundColor(Color.TRANSPARENT);break;
                    case 17:MainActivity.c18.setBackgroundColor(Color.TRANSPARENT);break;
                    case 18:MainActivity.c19.setBackgroundColor(Color.TRANSPARENT);break;
                    case 19:MainActivity.c20.setBackgroundColor(Color.TRANSPARENT);break;
                    case 20:MainActivity.c21.setBackgroundColor(Color.TRANSPARENT);break;
                    case 21:MainActivity.c22.setBackgroundColor(Color.TRANSPARENT);break;
                    case 22:MainActivity.c23.setBackgroundColor(Color.TRANSPARENT);break;
                    case 23:MainActivity.c24.setBackgroundColor(Color.TRANSPARENT);break;
                    case 24:MainActivity.c25.setBackgroundColor(Color.TRANSPARENT);break;
                    case 25:MainActivity.c26.setBackgroundColor(Color.TRANSPARENT);break;
                    case 26:MainActivity.c27.setBackgroundColor(Color.TRANSPARENT);break;
                    case 27:MainActivity.c28.setBackgroundColor(Color.TRANSPARENT);break;
                    case 28:MainActivity.c29.setBackgroundColor(Color.TRANSPARENT);break;
                    case 29:MainActivity.c30.setBackgroundColor(Color.TRANSPARENT);break;
                    case 30:MainActivity.c31.setBackgroundColor(Color.TRANSPARENT);break;
                    case 31:MainActivity.c32.setBackgroundColor(Color.TRANSPARENT);break;
                    case 32:MainActivity.c33.setBackgroundColor(Color.TRANSPARENT);break;
                    case 33:MainActivity.c34.setBackgroundColor(Color.TRANSPARENT);break;
                    case 34:MainActivity.c35.setBackgroundColor(Color.TRANSPARENT);break;
                    case 35:MainActivity.c36.setBackgroundColor(Color.TRANSPARENT);break;
                    case 36:MainActivity.c37.setBackgroundColor(Color.TRANSPARENT);break;
                    case 37:MainActivity.c38.setBackgroundColor(Color.TRANSPARENT);break;
                    case 38:MainActivity.c39.setBackgroundColor(Color.TRANSPARENT);break;
                    case 39:MainActivity.c40.setBackgroundColor(Color.TRANSPARENT);break;
                    case 40:MainActivity.c41.setBackgroundColor(Color.TRANSPARENT);break;
                    case 41:MainActivity.c42.setBackgroundColor(Color.TRANSPARENT);break;
                }
                finish();

            }
        });


    }
}
