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

    private ArrayList<Tag> tags;
    private RecyclerView mTag;
    private TagAdapter adapter;

    private TextView mDate;
    private SharedPreferences sharedPref;

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyMgr;

    private void fetchData(){
        tags = new ArrayList<Tag>();
        adapter.notifyDataSetChanged();
        int a =0;
        while(true){
            String name = sharedPref.getString("" + a, "none");
            String color= sharedPref.getString("" + a + "c", "none");
            if(name.equals("none") || color.equals("none")){
                break;
            }
            else{

            }
            a++;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                fetchData();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mDate = (TextView) findViewById(R.id.date);

        sharedPref = this.getSharedPreferences(getString(R.string.saved_threads),Context.MODE_PRIVATE);
        int tagNumber = sharedPref.getInt(getString(R.string.saved_tag_num), 0);
        if(tagNumber==0){
            //launch chooser
            Intent intent = new Intent(this, ChooseTagActivity.class);
            startActivityForResult(intent, 1);
        }
        else{
            fetchData();
        }

        // Lookup the recyclerview in activity layout
        mTag = (RecyclerView) findViewById(R.id.tag_list);

        tags = new ArrayList<Tag>();
        // Create adapter passing in the sample user data
        adapter = new TagAdapter(this, tags);

        // Attach the adapter to the recyclerview to populate items
        mTag.setAdapter(adapter);
        // Set layout manager to position the items
        mTag.setLayoutManager(new LinearLayoutManager(this));

        OnSwipeTouchListener onSwipeTouchListener = new OnSwipeTouchListener(FeedActivity.this) {
            @Override
            public void onSwipeLeft() {
                mDate.setText("6/10/2017");
            }
            @Override
            public void onSwipeRight() {
                mDate.setText("6/12/2017");
            }
        };

    }
}
