package com.kaiamelung.debrief;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private TextView mTitle;
    private TextView mContent;
    private Button mLink;

    private View screen;

    private Intent intent;
    private int size;
    private int currentPosition;
    private ArrayList<Article> mArticles;

    private GestureDetector detector;

    private Uri uriUrl;
    private Intent launchBrowser;

    public void setupArticle(){
        uriUrl = Uri.parse(mArticles.get(currentPosition).getLink());
        mTitle.setText(mArticles.get(currentPosition).getHeadline());
        mContent.setText(mArticles.get(currentPosition).getLong());

        screen.setBackgroundColor(Color.parseColor("#ffffff"));

        launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        mLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(launchBrowser);
            }
        });
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
            setupArticle();
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
        setContentView(R.layout.activity_article);

        intent = getIntent();
        Bundle bund= intent.getBundleExtra("A");
        mArticles = (ArrayList<Article>) bund.getSerializable("ARRAY");

        screen = findViewById(R.id.activity_article);

        size = intent.getIntExtra("S", 0);
        currentPosition = intent.getIntExtra("I",0);

        detector=new GestureDetector(getApplicationContext(), this);

        mTitle = (TextView) findViewById(R.id.article_name);

        mContent = (TextView) findViewById(R.id.article_content);
        mContent.setMovementMethod(new ScrollingMovementMethod());

        mLink = (Button) findViewById(R.id.article_link);

        setupArticle();
    }
}
