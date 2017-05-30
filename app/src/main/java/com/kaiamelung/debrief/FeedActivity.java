package com.kaiamelung.debrief;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {

    private ArrayList<Day> tags;
    private RecyclerView mTag;
    private TagAdapter adapter;

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);



        // Lookup the recyclerview in activity layout
        mTag = (RecyclerView) findViewById(R.id.day_list);

        tags = new ArrayList<Day>();
        // Create adapter passing in the sample user data
        adapter = new TagAdapter(this, tags);

        // Attach the adapter to the recyclerview to populate items
        mTag.setAdapter(adapter);
        // Set layout manager to position the items
        mTag.setLayoutManager(new LinearLayoutManager(this));
    }
}
