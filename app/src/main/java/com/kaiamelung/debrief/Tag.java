package com.kaiamelung.debrief;

/**
 * Created by andrew on 5/30/17.
 */

public class Tag {
    private String mTag;
    private Article[] mArticles;
    public Tag(String t, Article[] a){
        mTag = t;
        mArticles = a;
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
}