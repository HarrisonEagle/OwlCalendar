package com.example.owlcalendar;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ExampleFragmentPagerAdapter extends FragmentPagerAdapter {
    public ExampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return CalendarFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return 1560;
    }


}