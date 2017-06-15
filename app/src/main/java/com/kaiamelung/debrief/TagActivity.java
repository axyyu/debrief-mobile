package com.kaiamelung.debrief;

import android.content.Intent;
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

public class TagActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private ArrayList<Article> articles;
    private RecyclerView mArticles;
    private ArticleAdapter adapter;

    private Intent intent;
    private Bundle bund;
    private int size;

    private TextView tag;
    private int currentPosition;

    private GestureDetector detector;

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

//        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if( Math.abs(velocityX)> Math.abs(2*velocityY)){
            if(velocityX > 0){
                currentPosition-=1;
                if(currentPosition<0){
                    currentPosition = size-1;
                }
            }
            else if (velocityX < 0){
                currentPosition+=1;
                if(currentPosition>=size){
                    currentPosition = 0;
                }
            }
            setupArticles();
        }
        System.out.println("X:"+velocityX);
        System.out.println("Y:"+velocityY);
        return true;
    }
    @Override
    public void onLongPress(MotionEvent e) {
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
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

        tag = (TextView) findViewById(R.id.tag);

        detector=new GestureDetector(getApplicationContext(), this);

        articles = new ArrayList<Article>();

        mArticles = (RecyclerView) findViewById(R.id.article_list_view);

        // Create adapter passing in the sample user data
        adapter = new ArticleAdapter(this, articles);

        // Attach the adapter to the recyclerview to populate items
        mArticles.setAdapter(adapter);
        // Set layout manager to position the items
        mArticles.setLayoutManager(new LinearLayoutManager(this));

        setupArticles();
    }
}
