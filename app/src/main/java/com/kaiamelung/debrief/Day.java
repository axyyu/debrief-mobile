package com.kaiamelung.debrief;

/**
 * Created by andrew on 5/25/17.
 */

public class Day {
    private String mDate;
    private Tag[] mTag;
    public Day(String d, Tag[] t){
        mDate = d;
        mTag = t;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Tag[] getTag() {
        return mTag;
    }

    public void setTag(Tag[] t) {
        mTag = t;
    }
}