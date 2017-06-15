package com.kaiamelung.debrief;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ArticleActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mContent;
    private Button mLink;

    private Uri uriUrl;
    private Intent launchBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent = getIntent();
        String val = intent.getStringExtra("A");
        String val2 = intent.getStringExtra("C");
        String val3 = intent.getStringExtra("L");

        uriUrl = Uri.parse(val3);
        launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);


        mTitle = (TextView) findViewById(R.id.article_name);
        mTitle.setText(val);

        mContent = (TextView) findViewById(R.id.article_content);
        mContent.setText(val2);
        mContent.setMovementMethod(new ScrollingMovementMethod());

        mLink = (Button) findViewById(R.id.article_link);
        mLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(launchBrowser);
            }
        });
    }
}
