package com.kaiamelung.debrief;

import android.graphics.Color;


public class Thread {
    private String mValue;
    private String mColor;

    public Thread(String v, String c) {
        mValue = v;
        mColor = c;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }
}
