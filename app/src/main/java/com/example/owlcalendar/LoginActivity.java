package com.example.owlcalendar;

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

import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        final EditText usrtxt = findViewById(R.id.usernamebox);
        final EditText pwdtxt = findViewById(R.id.passwordbox);



        Button btn = findViewById(R.id.loginbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient webclient = new AsyncHttpClient();
                webclient.setBasicAuth(usrtxt.getText().toString(),pwdtxt.getText().toString());

                webclient.post("https://owlcalendar.herokuapp.com/loginapi/index", new AsyncHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        Log.d("msg","status:"+response);
                        try {
                            Integer i = Integer.valueOf(response);
                            Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_LONG).show();
                            MainContents.setDefaults("userid",response,getApplicationContext());
                            MainContents.setDefaults("username",usrtxt.getText().toString(),getApplicationContext());

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


                Intent intent = new Intent(LoginActivity.this,MainContents.class);
                startActivity(intent);


                finish();
            }
        });
    }
}
