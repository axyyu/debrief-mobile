package com.kaiamelung.debrief;

/**
 * Created by andrew on 5/30/17.
 */

public class Tag {
    private String mTag;
    private Article[] mArticles;
    private String mColor;
    public Tag(String t, Article[] a, String c){
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

    public Article[] getArticle() {
        return mArticles;
    }

    public void setArticle(Article[] a) {
        mArticles = a;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }
}