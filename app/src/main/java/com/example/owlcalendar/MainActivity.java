package com.example.owlcalendar;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.loopingviewpager.LoopingViewPager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Fragment {

    public static ViewPager viewPager;
    public static ExampleFragmentPagerAdapter adapter;
    public static FragmentManager manager;

    public static void retrievedata(){
        adapter = new ExampleFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue()-1;
        int day = now.getDayOfMonth();
        MainContents.curentindex = (year-1970)*12+month;
        viewPager.setCurrentItem(MainContents.curentindex);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.calendarmain, container, false);

        super.onCreate(savedInstanceState);

        manager = getFragmentManager();
        viewPager =  v.findViewById(R.id.calendarview);



       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int i, float v, int i1) {

           }

           @Override
           public void onPageSelected(int i) {

               if (MainContents.curentindex > i) {
                   Log.d("scroll","left");

               }else if (MainContents.curentindex <i) {
                   Log.d("scroll","right");
               }
               MainContents.curentindex = i;

           }

           @Override
           public void onPageScrollStateChanged(int i) {

           }
       });


        return v;
        


    }
}
