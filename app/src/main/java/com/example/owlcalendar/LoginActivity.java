package com.example.owlcalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.w3c.dom.Text;

import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.impl.client.BasicCookieStore;
import cz.msebera.android.httpclient.protocol.HttpContext;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("EXIT").equals("yes")){
            Intent mStartActivity = new Intent(getApplicationContext(), MainContents.class);
            int mPendingIntentId = 123456;
            PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager)getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
            System.exit(0);
        }
        setContentView(R.layout.loginpage);
        final EditText usrtxt = findViewById(R.id.usernamebox);
        final EditText pwdtxt = findViewById(R.id.passwordbox);


        Button newuser = findViewById(R.id.newuserbtn);

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.loginbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                MainContents.webclient.setBasicAuth(usrtxt.getText().toString(),pwdtxt.getText().toString());
                MainContents.webclient.post(DataCommunicator.PROTOCOL+"://"+DataCommunicator.host+"/loginapi/index", new AsyncHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        Log.d("msg","status:"+response);
                        try {
                            //Integer i = Integer.valueOf(response);

                            if(!response.equals("Password not Correct!")||!response.equals("User not exist!")){
                                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG).show();
                                MainContents.setDefaults("username",usrtxt.getText().toString(),getApplicationContext());
                                MainContents.setDefaults("password",pwdtxt.getText().toString(),getApplicationContext());
                                MainContents.setDefaults("userdata",response,getApplicationContext());
                                Log.d("Response",response);
                                MainContents.loginstatus = 1;
                                ScheduleList.retrievedata();
                                MainContents.retrievedata();
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                            }


                        } catch (Exception e) {

                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("msg","status"+statusCode);
                        Toast.makeText(LoginActivity.this, "Error Code:"+statusCode, Toast.LENGTH_LONG).show();

                    }
                });





                finish();
            }
        });
    }
}
