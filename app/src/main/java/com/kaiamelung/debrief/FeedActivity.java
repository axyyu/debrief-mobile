package com.kaiamelung.debrief;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {

    private ArrayList<Day> tags;
    private RecyclerView mTag;
    private TagAdapter adapter;

    private TextView mDate;

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mDate = (TextView) findViewById(R.id.date);

        SharedPreferences sharedPref = FeedActivity.this.getPreferences(Context.MODE_PRIVATE);
        int tagNumber = sharedPref.getInt(getString(R.string.saved_tag_num), 0);
        if(tagNumber==0){
            //launch chooser
            Intent intent = new Intent(this, ChooseTagActivity.class);
            startActivity(intent);
        }

        // Lookup the recyclerview in activity layout
        mTag = (RecyclerView) findViewById(R.id.tag_list);

        tags = new ArrayList<Day>();
        // Create adapter passing in the sample user data
        adapter = new TagAdapter(this, tags);

        // Attach the adapter to the recyclerview to populate items
        mTag.setAdapter(adapter);
        // Set layout manager to position the items
        mTag.setLayoutManager(new LinearLayoutManager(this));

        OnSwipeTouchListener onSwipeTouchListener = new OnSwipeTouchListener(FeedActivity.this) {
            @Override
            public void onSwipeLeft() {

            }
            @Override
            public void onSwipeRight() {

            }
        };

    }
}
