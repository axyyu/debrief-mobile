package com.kaiamelung.debrief;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ChooseTagActivity extends AppCompatActivity {
    private ArrayList<String> tagArrayAvailable;
    private ArrayList<String> tagArrayChosen;
    private String[] tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_tag);
        tagArrayAvailable = new ArrayList<String>(getResources().getInteger(R.integer.tagNums));
        tagArrayChosen = new ArrayList<String>(getResources().getInteger(R.integer.tagNums));
        tags = getResources().getStringArray(R.array.tags);


    }
}
