package com.example.owlcalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import static android.content.Context.ALARM_SERVICE;

public class MyPage extends Fragment {

    public static Button login;
    public static Button mypage;
    public static Context context;
    public static TextView usertext;

    public static void setup(){
        String username = "";

        try {

            final JSONObject jsonObject = new JSONObject(MainContents.getDefaults("userdata",context));
            username = jsonObject.getString("name");
        }catch (Exception e){
            e.printStackTrace();
        }

        if (MainContents.loginstatus == 1) {
            login.setText("ログアウト");
            usertext.setText("ようこそ！"+username);
            mypage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ChangePWDActivity.class);
                    context.startActivity(intent);

                }
            });

        } else if(MainContents.loginstatus == 0){
            usertext.setText("ログインしていません");
            mypage.setVisibility(View.GONE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainContents.loginstatus != 1) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("EXIT", "no");
                    context.startActivity(intent);
                } else {
                    MainContents.setDefaults("username", "", context);
                    MainContents.setDefaults("password", "", context);
                    MainContents.setDefaults("userdata", "", context);
                    Toast.makeText(context, "ログアウト", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MainContents.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    System.exit(0);

                }
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.mypage,container, false);

        login = v.findViewById(R.id.login);
        mypage = v.findViewById(R.id.changepwd);
        context = getContext();
        usertext = v.findViewById(R.id.usernametext);
        Log.d("logstatus",String.valueOf(MainContents.loginstatus));






        super.onCreate(savedInstanceState);

        return v;
    }

}