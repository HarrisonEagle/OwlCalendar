package com.example.owlcalendar;

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

public class RegisterActivity extends AppCompatActivity {


    public int userid = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        final EditText usrtxt = findViewById(R.id.usernamebox);
        final EditText emailtxt = findViewById(R.id.emailbox);
        final EditText pwdtxt = findViewById(R.id.passwordbox);




        Button btn = findViewById(R.id.submitbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestParams requestParams = new RequestParams();
                requestParams.add("userid",String.valueOf(userid));
                requestParams.add("username",usrtxt.getText().toString());
                requestParams.add("password",pwdtxt.getText().toString());
                requestParams.add("email",emailtxt.getText().toString());

                MainContents.webclient.post(getApplicationContext(),DataCommunicator.PROTOCOL+"://"+DataCommunicator.host+"/registerapi/index", requestParams,new AsyncHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        Log.d("msg","status:"+response);

                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();

                        finish();


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("msg","status"+statusCode);
                        Toast.makeText(RegisterActivity.this, "Error Code:"+statusCode, Toast.LENGTH_LONG).show();

                    }
                });





                finish();
            }
        });
    }
}
