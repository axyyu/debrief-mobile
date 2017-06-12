package com.kaiamelung.debrief;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class TagActivity extends AppCompatActivity {

    private ArrayList<Article> articles;
    private RecyclerView mArticles;
    private ArticleAdapter adapter;

    private TextView tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        Intent intent = getIntent();
        String val = intent.getStringExtra("T");
        tag = (TextView) findViewById(R.id.tag);
        tag.setText(val);

        articles = new ArrayList<Article>();

        Bundle args = intent.getBundleExtra("A");
        articles = (ArrayList<Article>) args.getSerializable("ARRAYLIST");
        System.out.print(articles);

        mArticles = (RecyclerView) findViewById(R.id.article_list_view);

        // Create adapter passing in the sample user data
        adapter = new ArticleAdapter(this, articles);

        // Attach the adapter to the recyclerview to populate items
        mArticles.setAdapter(adapter);
        // Set layout manager to position the items
        mArticles.setLayoutManager(new LinearLayoutManager(this));
    }
}
