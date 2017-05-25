package com.kaiamelung.debrief;

/**
 * Created by andrew on 5/25/17.
 */

public class Article {
    private String mHeadline;
    private String mAuthors;
    private String mShort;
    private String mLong;
    private String mLink;
    private String mTag;

    public Article(String h, String a, String ss, String ls,String l, String t) {
        mHeadline = h;
        mAuthors = a;
        mShort = ss;
        mLong = ls;
        mLink = l;
        mTag = t;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public void setHeadline(String headline) {
        mHeadline = headline;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getAuthor() {
        return mAuthors;
    }

    public void setAuthor(String author) {
        mAuthors = author;
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

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }
}