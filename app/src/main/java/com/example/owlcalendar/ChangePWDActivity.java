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
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ChangePWDActivity extends AppCompatActivity {


    public int userid = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwdpage);
        final EditText usrtxt = findViewById(R.id.usernamebox);
        final EditText emailtxt = findViewById(R.id.emailbox);
        final EditText pwdtxt = findViewById(R.id.passwordbox);


        try{
            JSONObject jsonObject = new JSONObject(MainContents.getDefaults("userdata",getApplicationContext()));
            usrtxt.setText(jsonObject.getString("name"));
            pwdtxt.setText(MainContents.getDefaults("password",getApplicationContext()));
            emailtxt.setText(jsonObject.getString("email"));
            userid = jsonObject.getInt("id");
        }catch (Exception e){
            e.printStackTrace();
        }



        Button btn = findViewById(R.id.submitbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestParams requestParams = new RequestParams();
                requestParams.add("userid",String.valueOf(userid));
                requestParams.add("username",usrtxt.getText().toString());
                requestParams.add("password",pwdtxt.getText().toString());
                requestParams.add("email",emailtxt.getText().toString());

                MainContents.webclient.setBasicAuth(MainContents.getDefaults("username",getApplicationContext()),MainContents.getDefaults("password",getApplicationContext()));
                MainContents.webclient.post(getApplicationContext(),DataCommunicator.PROTOCOL+"://"+DataCommunicator.host+"/changepwdapi/index", requestParams,new AsyncHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        Log.d("msg","status:"+response);
                        if(response.equals("Success")) {
                            //Integer i = Integer.valueOf(response);
                            MainContents.setDefaults("username",usrtxt.getText().toString(),getApplicationContext());
                            MainContents.setDefaults("password",pwdtxt.getText().toString(),getApplicationContext());
                            Toast.makeText(ChangePWDActivity.this, response, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainContents.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            System.exit(0);


                        }else{
                            Toast.makeText(ChangePWDActivity.this, response, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("msg","status"+statusCode);
                        Toast.makeText(ChangePWDActivity.this, "Error Code:"+statusCode, Toast.LENGTH_LONG).show();

                    }
                });





                finish();
            }
        });
    }
}
