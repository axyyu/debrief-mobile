package com.kaiamelung.debrief;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class TagActivity extends AppCompatActivity implements TagFragment.OnFragmentInteractionListener{

    private ArrayList<Article> articles;
    private RecyclerView mArticles;
    private ArticleAdapter adapter;

    private Intent intent;
    private Bundle bund;
    private int size;

    private TextView tag;
    private int currentPosition;

    private GestureDetector detector;
    private ViewPager mViewPager;
    private TagCollectionPagerAdapter mTagCollectionPagerAdapter;
    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
    public void setupArticles(){
        System.out.println(currentPosition+"");
        articles = (ArrayList<Article>) bund.getSerializable("A"+currentPosition);
        System.out.println(articles);
        tag.setText(intent.getStringExtra("T"+currentPosition));

        // Create adapter passing in the sample user data
        adapter = new ArticleAdapter(this, articles);

        // Attach the adapter to the recyclerview to populate items
        mArticles.setAdapter(adapter);
        // Set layout manager to position the items
        mArticles.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tag);

        System.out.println("CREATED ACTIVITY");

        intent = getIntent();
        size = intent.getIntExtra("S",0);
        bund= intent.getBundleExtra("A");
        currentPosition = intent.getIntExtra("I",0);
        //Make tag string array to pass on
        String[] tags = new String[size];
        for(int i = 0;i<size;i++){
            tags[i]=intent.getStringExtra("T"+i);
        }

        mTagCollectionPagerAdapter =
                new TagCollectionPagerAdapter(
                        getSupportFragmentManager(),size,bund,tags);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mTagCollectionPagerAdapter);
        mViewPager.setCurrentItem(currentPosition);
    }
}
