package com.kaiamelung.debrief;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 2018nsubrama on 6/8/2017.
 */

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class ArticleCollectionPagerAdapter extends FragmentStatePagerAdapter {
    private int count;
    private Bundle b;
    private ArrayList<Article> a;

    public ArticleCollectionPagerAdapter(FragmentManager fm,int size, Bundle b) {
        super(fm);
        count = size;
        //b=a;
        a=(ArrayList<Article>)b.getSerializable("ARRAY");
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(ArticleFragment.ARG_OBJECT, i);
        args.putSerializable(ArticleFragment.ARG_OBJECT2, a);


        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public int getCount() {

        return count;
    }

}
