package com.kaiamelung.debrief;

/**
 * Created by andrew on 5/25/17.
 */

public class Article {
    private String mHeadline;
    private String mShort;
    private String mLong;
    private String mLink;

    public Article(String h, String ss, String ls,String l) {
        mHeadline = h;
        mShort = ss;
        mLong = ls;
        mLink = l;
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
}