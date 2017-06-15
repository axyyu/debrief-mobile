package com.kaiamelung.debrief;

import java.util.ArrayList;

/**
 * Created by andrew on 5/30/17.
 */

public class Tag {
    private String mTag;
    private ArrayList<Article> mArticles;
    private String mColor;
    public Tag(String t, ArrayList<Article> a, String c){
        mTag = t;
        mArticles = a;
        mColor = c;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String t) {
        mTag = t;
    }

    public ArrayList<Article> getArticle() {
        return mArticles;
    }

    public void setArticle(ArrayList<Article> a) {
        mArticles = a;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }
}