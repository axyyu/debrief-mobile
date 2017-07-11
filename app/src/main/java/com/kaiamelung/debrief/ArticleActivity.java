package com.kaiamelung.debrief;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity implements ArticleFragment.OnFragmentInteractionListener {

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
    private ViewPager mViewPager;
    private ArticleCollectionPagerAdapter mArticleCollectionPagerAdapter;

    public void setupArticle(){
        uriUrl = Uri.parse(mArticles.get(currentPosition).getLink());
        mTitle.setText(mArticles.get(currentPosition).getHeadline());
        mContent.setText(mArticles.get(currentPosition).getLong());

        screen.setBackgroundColor(Color.parseColor(mArticles.get(currentPosition).getColor()));

        launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        mLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(launchBrowser);
            }
        });
    }
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_article);

        intent = getIntent();
        Bundle bund= intent.getBundleExtra("A");

        size = intent.getIntExtra("S", 0);
        currentPosition = intent.getIntExtra("I",0);

        mArticleCollectionPagerAdapter =
                new ArticleCollectionPagerAdapter(
                        getSupportFragmentManager(),size,bund);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mArticleCollectionPagerAdapter);
        mViewPager.setCurrentItem(currentPosition);
    }
}
