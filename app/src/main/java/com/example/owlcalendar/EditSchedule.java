package com.example.owlcalendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class EditSchedule extends AppCompatActivity implements
        View.OnClickListener{

    public static String id;

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    EditText yoteicontent;
    Button submitbtn;
    private int mYear, mMonth, mDay, mHour, mMinute;
    public static int intentnum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editschedule);
        btnDatePicker = findViewById(R.id.seldate);
        btnTimePicker = findViewById(R.id.seltime);
        txtDate = findViewById(R.id.datetext);
        txtTime = findViewById(R.id.timetext);
        yoteicontent = findViewById(R.id.yoteicontent);

        submitbtn = findViewById(R.id.submit);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        submitbtn.setOnClickListener(this);

        txtDate.setText(getIntent().getStringExtra("YEAR")+"-"+getIntent().getStringExtra("MONTH")+"-"+getIntent().getStringExtra("DAY"));
        txtTime.setText(getIntent().getStringExtra("HOUR")+":"+getIntent().getStringExtra("MINUTE"));

        yoteicontent.setText(getIntent().getStringExtra("CONTENT"));
        id = getIntent().getStringExtra("ID");




    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            String month = String.valueOf(monthOfYear+1);
                            if(monthOfYear+1<10&&month.length()==1){
                                month = "0" + month;
                            }
                            String day = String.valueOf(dayOfMonth);
                            if(dayOfMonth<10&&day.length()==1){
                                day = "0" + day;
                            }


                            txtDate.setText(year+"-"+month+ "-" + dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String hour = String.valueOf(hourOfDay);
                            if(hourOfDay<10&&hour.length()==1){
                                hour = "0" + hour;
                            }
                            String minutes = String.valueOf(minute);
                            if(minute<10&&minutes.length()==1){
                                minutes = "0" + minutes;
                            }

                            txtTime.setText(hour + ":" + minutes);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if( v == submitbtn){
            try{
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("content",yoteicontent.getText().toString());
                String year = txtDate.getText().toString().substring(0,4);
                String month = txtDate.getText().toString().substring(5,7);
                String day = txtDate.getText().toString().substring(8,10);
                String hour = txtTime.getText().toString().substring(0,2);
                String minute = txtTime.getText().toString().substring(3,5);
                jsonObject.put("timedata",year+month+day+hour+minute);
                Log.d("time",year+month+day+hour+minute);
                JSONObject userdata = new JSONObject(MainContents.getDefaults("userdata",getApplicationContext()));
                jsonObject.put("id",id);
                int timenum = (int)(Integer.valueOf(year) * 1000 + Integer.valueOf(month)*100+Integer.valueOf(day)*10+Integer.valueOf(hour)+Integer.valueOf(minute)*0.1);
                jsonObject.put("time",timenum);
                String username = MainContents.getDefaults("username",getApplicationContext());
                String password = MainContents.getDefaults("password",getApplicationContext());
                if(username==null){
                    username="";
                }
                if(password==null){
                    password="";
                }
                MainContents.webclient.setBasicAuth(username,password);
                StringEntity entity = new StringEntity(jsonObject.toString());
                MainContents.webclient.post(getApplicationContext(),DataCommunicator.PROTOCOL+"://"+DataCommunicator.host+"/editschapi/index",entity, "application/json", new AsyncHttpResponseHandler() {


                    public String response;

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        response = new String(responseBody);
                        Log.d("msg","status:"+response);
                        //Integer i = Integer.valueOf(response);
                        if(response.equals("Success")){

                            Intent intent = new Intent(getApplicationContext(),MainContents.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            System.exit(0);



                        }

                        else{
                            Toast.makeText(getApplicationContext(),"The Error Happened!",Toast.LENGTH_LONG).show();
                        }



                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("msg","status"+statusCode);Toast.makeText(getApplicationContext(),"The Error Happened!",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                    }
                });


            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"The Error Happened!",Toast.LENGTH_LONG).show();
            }
        }
    }


}
