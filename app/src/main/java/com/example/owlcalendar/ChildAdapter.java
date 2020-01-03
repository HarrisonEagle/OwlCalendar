package com.example.owlcalendar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;



public class ChildAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    public ArrayList<listchild> childlistlist = new ArrayList<listchild>();





    ChildAdapter(Context context, int year, int month){
        inflater = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return childlistlist.size();
    }

    @Override
    public Object getItem(int i) {
        return childlistlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.childlistchild,null);
        TextView content = view.findViewById(R.id.childtextchild);
        if(childlistlist.get(i).status==0){
            content.setBackgroundColor(Color.GREEN);
        }else{
            content.setBackgroundColor(Color.CYAN);
        }
        content.setText(childlistlist.get(i).content);

        return view;
    }
}
