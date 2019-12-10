package com.example.owlcalendar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScheduleList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.schedulelist,container, false);
        final ArrayList<String> yoteis = new ArrayList<String>();
        //OkHttpClinet生成
        OkHttpClient client = new OkHttpClient();
        String userid = MainContents.getDefaults("userid",getContext());

        if(userid==null){
            userid="";
        }
        //request生成
        RequestBody formBody = new FormBody.Builder()
                .add("userid",userid)
                .build();

        Request request = new Request.Builder()
                .url("https://owlcalendar.herokuapp.com/schapi/index")
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

                                String content = json.getString("content");
                                String time = json.getString("timedata");
                                String yotei = "Content:"+content+"Time:"+time;
                                yoteis.add(yotei);
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
        ListView lv = v.findViewById(R.id.schlist);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,yoteis);
        lv.setAdapter(aa);

        super.onCreate(savedInstanceState);

        return v;
    }

    }
