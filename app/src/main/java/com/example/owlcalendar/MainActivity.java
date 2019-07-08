package com.example.owlcalendar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String[] daydata=new String[42];

    public static int today;
    public static int todaymonth;
    public static int todayyear;
    public static int todayindex;


    public static int year;
    public static int month;

    public static Button c01;
    public static Button c02;
    public static Button c03;
    public static Button c04;
    public static Button c05;
    public static Button c06;
    public static Button c07;
    public static Button c08;
    public static Button c09;
    public static Button c10;
    public static Button c11;
    public static Button c12;
    public static Button c13;
    public static Button c14;
    public static Button c15;
    public static Button c16;
    public static Button c17;
    public static Button c18;
    public static Button c19;

    public static Button c20;
    public static Button c21;
    public static Button c22;
    public static Button c23;
    public static Button c24;
    public static Button c25;
    public static Button c26;
    public static Button c27;
    public static Button c28;
    public static Button c29;
    public static Button c30;
    public static Button c31;
    public static Button c32;
    public static Button c33;
    public static Button c34;
    public static Button c35;
    public static Button c36;
    public static Button c37;
    public static Button c38;
    public static Button c39;
    public static Button c40;
    public static Button c41;
    public static Button c42;
    public static List<sccontent> sclist=new ArrayList<>();

    public static TextView title;

    public static int[] daynum={31,28,31,30,31,30,31,31,30,31,30,31};

    public void settoday(){
        LocalDateTime ldt = LocalDateTime.now();
        todayyear=Integer.valueOf(ldt.toString().substring(0,4));
        todaymonth=Integer.valueOf(ldt.toString().substring(5,7));
        today=Integer.valueOf(ldt.toString().substring(8,10));
        year=todayyear;
        month=todaymonth;
        Calendar cal = Calendar.getInstance();
        int tempmonth=month-1;
        int tempyear=year;

        cal.set(tempyear,tempmonth,1);
        todayindex=cal.get(Calendar.DAY_OF_WEEK)+today-1;

    }

    public void init(){
        for(int i=0;i<42;i++){
            daydata[i]="";
            setbtn(i,daydata[i]);
        }

    }

    public void setbtn(int num,String content){

        boolean istoday=false;

        if(todaymonth==month&&todayyear==year){
            istoday=true;

            Log.d("todayindex",String.valueOf(todayindex));
        }

        switch(num){
            case 0:c01.setText(content);if(istoday&&todayindex==1){c01.setTextColor(Color.GREEN);}else{c01.setTextColor(Color.RED);}break;
            case 1:c02.setText(content);if(istoday&&todayindex==2){c02.setTextColor(Color.GREEN);}else{c02.setTextColor(Color.BLACK);}break;
            case 2:c03.setText(content);if(istoday&&todayindex==3){c03.setTextColor(Color.GREEN);}else{c03.setTextColor(Color.BLACK);}break;
            case 3:c04.setText(content);if(istoday&&todayindex==4){c04.setTextColor(Color.GREEN);}else{c04.setTextColor(Color.BLACK);}break;
            case 4:c05.setText(content);if(istoday&&todayindex==5){c05.setTextColor(Color.GREEN);}else{c05.setTextColor(Color.BLACK);}break;
            case 5:c06.setText(content);if(istoday&&todayindex==6){c06.setTextColor(Color.GREEN);}else{c06.setTextColor(Color.BLACK);}break;
            case 6:c07.setText(content);if(istoday&&todayindex==7){c07.setTextColor(Color.GREEN);}else{c07.setTextColor(Color.CYAN);}break;
            case 7:c08.setText(content);if(istoday&&todayindex==8){c08.setTextColor(Color.GREEN);}else{c08.setTextColor(Color.RED);}break;
            case 8:c09.setText(content);if(istoday&&todayindex==9){c09.setTextColor(Color.GREEN);}else{c09.setTextColor(Color.BLACK);}break;
            case 9:c10.setText(content);if(istoday&&todayindex==10){c10.setTextColor(Color.GREEN);}else{c10.setTextColor(Color.BLACK);}break;
            case 10:c11.setText(content);if(istoday&&todayindex==11){c11.setTextColor(Color.GREEN);}else{c11.setTextColor(Color.BLACK);}break;
            case 11:c12.setText(content);if(istoday&&todayindex==12){c12.setTextColor(Color.GREEN);}else{c12.setTextColor(Color.BLACK);}break;
            case 12:c13.setText(content);if(istoday&&todayindex==13){c13.setTextColor(Color.GREEN);}else{c13.setTextColor(Color.BLACK);}break;
            case 13:c14.setText(content);if(istoday&&todayindex==14){c14.setTextColor(Color.GREEN);}else{c14.setTextColor(Color.CYAN);}break;
            case 14:c15.setText(content);if(istoday&&todayindex==15){c15.setTextColor(Color.GREEN);}else{c15.setTextColor(Color.RED);}break;
            case 15:c16.setText(content);if(istoday&&todayindex==16){c16.setTextColor(Color.GREEN);}else{c16.setTextColor(Color.BLACK);}break;
            case 16:c17.setText(content);if(istoday&&todayindex==17){c17.setTextColor(Color.GREEN);}else{c17.setTextColor(Color.BLACK);}break;
            case 17:c18.setText(content);if(istoday&&todayindex==18){c18.setTextColor(Color.GREEN);}else{c18.setTextColor(Color.BLACK);}break;
            case 18:c19.setText(content);if(istoday&&todayindex==19){c19.setTextColor(Color.GREEN);}else{c19.setTextColor(Color.BLACK);}break;
            case 19:c20.setText(content);if(istoday&&todayindex==20){c20.setTextColor(Color.GREEN);}else{c20.setTextColor(Color.BLACK);}break;
            case 20:c21.setText(content);if(istoday&&todayindex==21){c21.setTextColor(Color.GREEN);}else{c21.setTextColor(Color.CYAN);}break;
            case 21:c22.setText(content);if(istoday&&todayindex==22){c22.setTextColor(Color.GREEN);}else{c22.setTextColor(Color.RED);}break;
            case 22:c23.setText(content);if(istoday&&todayindex==23){c23.setTextColor(Color.GREEN);}else{c23.setTextColor(Color.BLACK);}break;
            case 23:c24.setText(content);if(istoday&&todayindex==24){c24.setTextColor(Color.GREEN);}else{c24.setTextColor(Color.BLACK);}break;
            case 24:c25.setText(content);if(istoday&&todayindex==25){c25.setTextColor(Color.GREEN);}else{c25.setTextColor(Color.BLACK);}break;
            case 25:c26.setText(content);if(istoday&&todayindex==26){c26.setTextColor(Color.GREEN);}else{c26.setTextColor(Color.BLACK);}break;
            case 26:c27.setText(content);if(istoday&&todayindex==27){c27.setTextColor(Color.GREEN);}else{c27.setTextColor(Color.BLACK);}break;
            case 27:c28.setText(content);if(istoday&&todayindex==28){c28.setTextColor(Color.GREEN);}else{c28.setTextColor(Color.CYAN);}break;
            case 28:c29.setText(content);if(istoday&&todayindex==29){c29.setTextColor(Color.GREEN);}else{c29.setTextColor(Color.RED);}break;
            case 29:c30.setText(content);if(istoday&&todayindex==30){c30.setTextColor(Color.GREEN);}else{c30.setTextColor(Color.BLACK);}break;
            case 30:c31.setText(content);if(istoday&&todayindex==31){c31.setTextColor(Color.GREEN);}else{c31.setTextColor(Color.BLACK);}break;
            case 31:c32.setText(content);if(istoday&&todayindex==32){c32.setTextColor(Color.GREEN);}else{c32.setTextColor(Color.BLACK);}break;
            case 32:c33.setText(content);if(istoday&&todayindex==33){c33.setTextColor(Color.GREEN);}else{c33.setTextColor(Color.BLACK);}break;
            case 33:c34.setText(content);if(istoday&&todayindex==34){c34.setTextColor(Color.GREEN);}else{c34.setTextColor(Color.BLACK);}break;
            case 34:c35.setText(content);if(istoday&&todayindex==35){c35.setTextColor(Color.GREEN);}else{c35.setTextColor(Color.CYAN);}break;
            case 35:c36.setText(content);if(istoday&&todayindex==36){c36.setTextColor(Color.GREEN);}else{c36.setTextColor(Color.RED);}break;
            case 36:c37.setText(content);if(istoday&&todayindex==37){c37.setTextColor(Color.GREEN);}else{c37.setTextColor(Color.BLACK);}break;
            case 37:c38.setText(content);if(istoday&&todayindex==38){c38.setTextColor(Color.GREEN);}else{c38.setTextColor(Color.BLACK);}break;
            case 38:c39.setText(content);if(istoday&&todayindex==39){c39.setTextColor(Color.GREEN);}else{c39.setTextColor(Color.BLACK);}break;
            case 39:c40.setText(content);if(istoday&&todayindex==40){c40.setTextColor(Color.GREEN);}else{c40.setTextColor(Color.BLACK);}break;
            case 40:c41.setText(content);if(istoday&&todayindex==41){c41.setTextColor(Color.GREEN);}else{c41.setTextColor(Color.BLACK);}break;
            case 41:c42.setText(content);if(istoday&&todayindex==42){c42.setTextColor(Color.GREEN);}else{c42.setTextColor(Color.CYAN);}break;
        }

    }


    public void setcalendar(){
        Calendar cal = Calendar.getInstance();
        int tempmonth=month-1;
        int tempyear=year;

        cal.set(tempyear,tempmonth,1);
        int initday=0;
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:     // Calendar.SUNDAY:1 （値。意味はない）
                initday=0;//日曜日
                break;
            case Calendar.MONDAY:     // Calendar.MONDAY:2
                initday=1;//月曜日
                break;
            case Calendar.TUESDAY:    // Calendar.TUESDAY:3
                initday=2;//火曜日
                break;
            case Calendar.WEDNESDAY:  // Calendar.WEDNESDAY:4
                initday=3;//水曜日
                break;
            case Calendar.THURSDAY:   // Calendar.THURSDAY:5
                initday=4;//木曜日
                break;
            case Calendar.FRIDAY:     // Calendar.FRIDAY:6
                initday=5;//金曜日
                break;
            case Calendar.SATURDAY:   // Calendar.SATURDAY:7
                initday=6;//土曜日
                break;
        }
        Log.d("tag",String.valueOf(initday));
        int day=1;
        for(int i=initday;i<initday+daynum[tempmonth];i++){
            daydata[i]=String.valueOf(day);
            setbtn(i,daydata[i]);
            day++;

        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c01 = findViewById(R.id.c01);
        c02 = findViewById(R.id.c02);
        c03 = findViewById(R.id.c03);
        c04 = findViewById(R.id.c04);
        c05 = findViewById(R.id.c05);
        c06 = findViewById(R.id.c06);
        c07 = findViewById(R.id.c07);
        c08 = findViewById(R.id.c08);
        c09 = findViewById(R.id.c09);
        c10 = findViewById(R.id.c10);
        c11 = findViewById(R.id.c11);
        c12 = findViewById(R.id.c12);
        c13 = findViewById(R.id.c13);
        c14 = findViewById(R.id.c14);
        c15 = findViewById(R.id.c15);
        c16 = findViewById(R.id.c16);
        c17 = findViewById(R.id.c17);
        c18 = findViewById(R.id.c18);
        c19 = findViewById(R.id.c19);
        c20 = findViewById(R.id.c20);
        c21 = findViewById(R.id.c21);
        c22 = findViewById(R.id.c22);
        c23 = findViewById(R.id.c23);
        c24 = findViewById(R.id.c24);
        c25 = findViewById(R.id.c25);
        c26 = findViewById(R.id.c26);
        c27 = findViewById(R.id.c27);
        c28 = findViewById(R.id.c28);
        c29 = findViewById(R.id.c29);
        c30 = findViewById(R.id.c30);
        c31 = findViewById(R.id.c31);
        c32 = findViewById(R.id.c32);
        c33 = findViewById(R.id.c33);
        c34 = findViewById(R.id.c34);
        c35 = findViewById(R.id.c35);
        c36 = findViewById(R.id.c36);
        c37 = findViewById(R.id.c37);
        c38 = findViewById(R.id.c38);
        c39 = findViewById(R.id.c39);
        c40 = findViewById(R.id.c40);
        c41 = findViewById(R.id.c41);
        c42 = findViewById(R.id.c42);
        title =findViewById(R.id.title);

        Button forward=findViewById(R.id.forward);
        Button back=findViewById(R.id.back);

        init();

        settoday();
        title.setText(year+"年"+month+"月");
        setcalendar();

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month==12){
                    month=1;
                    year++;
                }else{
                    month++;
                }

                init();


                title.setText(year+"年"+month+"月");
                setcalendar();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month==1){
                    month=12;
                    year--;
                }else{
                    month--;
                }

                init();


                title.setText(year+"年"+month+"月");
                setcalendar();


            }
        });

        c01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[0]!=""){
                    Schedule sc=new Schedule(0);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[1]!=""){
                    Schedule sc=new Schedule(1);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[2]!=""){
                    Schedule sc=new Schedule(2);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[3]!=""){
                    Schedule sc=new Schedule(3);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[4]!=""){
                    Schedule sc=new Schedule(4);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[5]!=""){
                    Schedule sc=new Schedule(5);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[6]!=""){
                    Schedule sc=new Schedule(6);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[7]!=""){
                    Schedule sc=new Schedule(7);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[8]!=""){
                    Schedule sc=new Schedule(8);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[9]!=""){
                    Schedule sc=new Schedule(9);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[10]!=""){
                    Schedule sc=new Schedule(10);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[11]!=""){
                    Schedule sc=new Schedule(11);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[12]!=""){
                    Schedule sc=new Schedule(12);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[13]!=""){
                    Schedule sc=new Schedule(13);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[14]!=""){
                    Schedule sc=new Schedule(14);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[15]!=""){
                    Schedule sc=new Schedule(15);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[16]!=""){
                    Schedule sc=new Schedule(16);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[17]!=""){
                    Schedule sc=new Schedule(17);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[18]!=""){
                    Schedule sc=new Schedule(18);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[19]!=""){
                    Schedule sc=new Schedule(19);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[20]!=""){
                    Schedule sc=new Schedule(20);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });


        c22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[21]!=""){
                    Schedule sc=new Schedule(21);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[22]!=""){
                    Schedule sc=new Schedule(22);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });


        c24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[23]!=""){
                    Schedule sc=new Schedule(23);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[24]!=""){
                    Schedule sc=new Schedule(24);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[25]!=""){
                    Schedule sc=new Schedule(25);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[26]!=""){
                    Schedule sc=new Schedule(26);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[27]!=""){
                    Schedule sc=new Schedule(27);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[28]!=""){
                    Schedule sc=new Schedule(28);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[29]!=""){
                    Schedule sc=new Schedule(29);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[30]!=""){
                    Schedule sc=new Schedule(30);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[31]!=""){
                    Schedule sc=new Schedule(31);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[32]!=""){
                    Schedule sc=new Schedule(32);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[33]!=""){
                    Schedule sc=new Schedule(33);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[34]!=""){
                    Schedule sc=new Schedule(34);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[35]!=""){
                    Schedule sc=new Schedule(35);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });


        c37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[36]!=""){
                    Schedule sc=new Schedule(36);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[37]!=""){
                    Schedule sc=new Schedule(37);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[38]!=""){
                    Schedule sc=new Schedule(38);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[39]!=""){
                    Schedule sc=new Schedule(39);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[40]!=""){
                    Schedule sc=new Schedule(40);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });

        c42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daydata[41]!=""){
                    Schedule sc=new Schedule(41);
                    Intent intent=new Intent(MainActivity.this,sc.getClass());
                    startActivity(intent);
                }
            }
        });





        


    }
}
