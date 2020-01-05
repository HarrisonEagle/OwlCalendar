package com.example.owlcalendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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


    public static  void createNotificationChannel(int i){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Channel_id"+i;
            String description = "114514"+i;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Channel_id"+i,name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }



    public static void retrievedata(){
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
                try {
                    JSONArray jarray = new JSONArray(jsonStr);
                    yoteis.clear();

                    for (int i = 0; i < jarray.length(); ++ i) {



                        createNotificationChannel(i);
                        JSONObject json = jarray.getJSONObject(i);

                        String content = json.getString("content");
                        String time = json.getString("timedata");

                        String yotei = "Content:"+content+"Time:"+time;
                        yoteis.add(json.toString());

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.add(Calendar.SECOND, 1);

                        final String time2 = json.getString("timedata");
                        int yeartime = Integer.valueOf(time2.substring(0,4));
                        int monthtime = Integer.valueOf(time2.substring(4,6));
                        int daytime = Integer.valueOf(time2.substring(6,8));
                        int hour = Integer.valueOf(time2.substring(8,10));
                        String minute = time2.substring(10);
                        if(Integer.valueOf(minute)<10&&minute.length()==1){
                            minute = "0" + minute;
                        }

                        long before = System.currentTimeMillis();
                        Calendar cal = Calendar.getInstance();

                        cal.setTimeInMillis(System.currentTimeMillis());
                        cal.clear();
                        cal.set(Calendar.YEAR,yeartime);
                        cal.set(Calendar.MONTH,monthtime-1);
                        cal.set(Calendar.DATE,daytime);
                        cal.set(Calendar.HOUR,hour);
                        cal.set(Calendar.MINUTE,Integer.valueOf(minute));
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);




                        Intent intent = new Intent(context,ReminderBroadcast.class);
                        intent.putExtra("id",String.valueOf(i));
                        intent.putExtra("title",hour+":"+minute+"の予定");
                        intent.putExtra("year",String.valueOf(yeartime));
                        intent.putExtra("month",String.valueOf(monthtime));
                        intent.putExtra("day",String.valueOf(daytime));
                        intent.putExtra("content",content);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,i,intent,PendingIntent.FLAG_ONE_SHOT);





                        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);



                        long after = cal.getTimeInMillis();
                        SimpleDateFormat  object = new SimpleDateFormat("yyyy-MM-dd HH-mm");


                        if(before<after){
                            alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);
                        }

                    }
                }
                catch (Exception e) {

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
                ScheduleAdapter aa = new ScheduleAdapter(context,yoteis);
                lv.setAdapter(aa);



            }
        });
    }


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





        ImageButton newschedule = v.findViewById(R.id.newyoteibutton);
        newschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),NewSchedule.class);
                intent.putExtra("DATE","");
                startActivity(intent);
            }
        });

        final SwipeRefreshLayout swipeRefreshLayout = v.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainContents.retrievedata(0);
                ScheduleList.retrievedata();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 更新が終了したらインジケータ非表示
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, 2000);
            }
        });

        retrievedata();




        return v;
    }

    }
