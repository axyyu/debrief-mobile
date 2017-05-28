package com.kaiamelung.debrief;

/**
 * Created by andrew on 5/25/17.
 */

public class Day {
    private String mDate;
    private Article[] mArticles;
    public Day(String d, Article[] a){
        mDate = d;
        mArticles = a;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Article[] getArticle() {
        return mArticles;
    }

    public void setArticle(Article[] article) {
        mArticles = article;
    }
}