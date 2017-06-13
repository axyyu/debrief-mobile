package com.kaiamelung.debrief;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Date;

/**
 * Created by 2018nsubrama on 6/8/2017.
 */

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class DayCollectionPagerAdapter extends FragmentStatePagerAdapter {
    private int count=10;
    public DayCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DayFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(DayFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public int getCount() {
        DateFormat dateFormat = new SimpleDateFormat("M-d");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR,-1);
        Date dateBefore = cal.getTime();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("M-d");

        try{
            Date a = dateFormat.parse("6-7");
            long diff = dateBefore.getTime() - a.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            count = ((int)days);

        }
        catch (Exception e){

        }



        return count;
   }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
