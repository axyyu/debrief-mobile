package com.kaiamelung.debrief;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 2018nsubrama on 6/8/2017.
 */

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class TagCollectionPagerAdapter extends FragmentStatePagerAdapter {
    private int count;
    private Bundle b;
    private String[] tags;

    public TagCollectionPagerAdapter(FragmentManager fm,int size, Bundle a, String[] t) {
        super(fm);
        count = size;
        b=a;
        tags=t;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new TagFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(TagFragment.ARG_OBJECT, i);
        args.putBundle(TagFragment.ARG_OBJECT2, b);
        args.putString(TagFragment.ARG_OBJECT3,tags[i]);
        //Read these and use setUpArticles in TagFragment

        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public int getCount() {

        return count;
    }

}
