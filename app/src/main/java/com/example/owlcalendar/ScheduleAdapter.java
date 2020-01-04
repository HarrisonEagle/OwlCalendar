package com.example.owlcalendar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class ScheduleAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<String> yoteis;
    Context context;



    ScheduleAdapter(Context context, ArrayList<String> yoteis){
        inflater = LayoutInflater.from(context);
        this.yoteis = yoteis;
        this.context = context;
    }

    @Override
    public int getCount() {
        return yoteis.size();
    }

    @Override
    public Object getItem(int i) {
        return yoteis.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.schedulechild,null);
        try {
            final JSONObject jsonObject = new JSONObject(yoteis.get(i));
            final String time = jsonObject.getString("timedata");
            final String content = jsonObject.getString("content");
            final String id = jsonObject.getString("id");
            int yeartime = Integer.valueOf(time.substring(0,4));
            int monthtime = Integer.valueOf(time.substring(4,6));
            int daytime = Integer.valueOf(time.substring(6,8));
            int hour = Integer.valueOf(time.substring(8,10));
            String minute = time.substring(10);
            TextView textView = view.findViewById(R.id.childtextchild);
            textView.setText(yeartime+"年"+monthtime+"月"+daytime+"日  "+hour+":"+minute);
            TextView contentt = view.findViewById(R.id.contentchild);
            contentt.setText(content);
            SwipeLayout swipeLayout =  (SwipeLayout)view.findViewById(R.id.swipelayout);
            swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, view.findViewById(R.id.bottom_wrapper));
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right,null);
            Button editbtn = view.findViewById(R.id.editbtn);
            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context,EditSchedule.class);
                    intent.putExtra("YEAR", String.valueOf(time.substring(0,4)));
                    intent.putExtra("MONTH", String.valueOf(time.substring(4,6)));
                    intent.putExtra("DAY",String.valueOf(time.substring(6,8)));
                    intent.putExtra("HOUR",String.valueOf(time.substring(8,10)));
                    intent.putExtra("MINUTE",String.valueOf(time.substring(10,12)));
                    intent.putExtra("CONTENT",content);
                    intent.putExtra("ID",id);
                    context.startActivity(intent);
                }
            });

            Button delbtn = view.findViewById(R.id.delbtn);
            delbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = MainContents.getDefaults("username",context);
                    String password = MainContents.getDefaults("password",context);
                    if(username==null){
                        username="";
                    }
                    if(password==null){
                        password="";
                    }
                    MainContents.webclient.setBasicAuth(username,password);
                    RequestParams params = new RequestParams();
                    params.put("deleteindex", id);

                    MainContents.webclient.post(context,DataCommunicator.PROTOCOL+"://"+DataCommunicator.host+"/delschapi/index", params,new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            ScheduleList.retrievedata();
                            MainContents.retrievedata(0);


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


                        }
                    });
                }
            });

        }catch (Exception e){
            Log.d("JSONError",String.valueOf(yoteis.size()));
        }



        return view;
    }
}
