package com.example.owlcalendar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CalendarFragment extends Fragment {
    public static String position;
    public static CalendarAdapter calendarAdapter;
    public static GridView gridView;
    public static CalendarFragment newInstance(int monthnum) {
        CalendarFragment frag = new CalendarFragment();
        Bundle b = new Bundle();
        b.putInt(position, monthnum);
        frag.setArguments(b);
        return frag;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendarlayout, null);
        TextView textView = view.findViewById(R.id.text);
        int year = getArguments().getInt(position)/12+1970;
        int month = getArguments().getInt(position)%12+1;
        textView.setText(String.valueOf(year)+"年"+String.valueOf(month)+"月");
        gridView = view.findViewById(R.id.calendargrid);
        calendarAdapter = new CalendarAdapter(getContext(),year,month);
        gridView.setAdapter(calendarAdapter);




        return view;
    }
}
