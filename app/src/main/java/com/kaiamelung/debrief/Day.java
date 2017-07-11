package com.kaiamelung.debrief;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by andrew on 5/25/17.
 */

public class Day {
    private String mDate;
    private ArrayList<Tag> mTag;
    public Day(String d, ArrayList<Tag> t){
        mDate = d;
        mTag = t;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public ArrayList<Tag> getTag() {
        return mTag;
    }

    public void setTag(ArrayList<Tag> t) {
        mTag = t;
    }
}