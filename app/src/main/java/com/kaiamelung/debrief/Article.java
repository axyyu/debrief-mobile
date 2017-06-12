package com.kaiamelung.debrief;

import java.io.Serializable;

/**
 * Created by andrew on 5/25/17.
 */

public class Article implements Serializable{
    private String mHeadline;
    private String mShort;
    private String mLong;
    private String mLink;
    private String mColor;

    public Article(String h, String ss, String ls,String l, String color) {
        mHeadline = h;
        mShort = ss;
        mLong = ls;
        mLink = l;
        mColor = color;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public void setHeadline(String headline) {
        mHeadline = headline;
    }

    public String getShort() {
        return mShort;
    }

    public void setShort(String aShort) {
        mShort = aShort;
    }

    public String getLong() {
        return mLong;
    }

    public void setLong(String aLong) {
        mLong = aLong;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }
}