package com.kaiamelung.debrief;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooseTagActivity extends AppCompatActivity {
    //private String tagArrrayAvailable[] = new String[R.integer.tagNums];
    //private String tagArrrayChosen[] = new String[R.integer.tagNums];

    private FlowLayout selected;
    private FlowLayout unselected;

    private Thread[] threadList = new Thread[]{
            new Thread("Test","#FFFF0000"),
            new Thread("Test","#FFFF0000"),
            new Thread("Test","#FFFF0000"),
            new Thread("Test","#FFFF0000"),
            new Thread("Test","#FFFF0000"),
            new Thread("Test","#FFFF0000")
    };

    private void setupThreads(){
        for(Thread a : threadList){
            TextView t = new TextView(this);
            t.setText(a.getValue());

            LinearLayout.LayoutParams temp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            temp.setMargins(20, 20, 20, 20);
            t.setLayoutParams(temp);
            t.setTextSize(25);
            t.setPadding(30, 30, 30, 30);
            t.setBackgroundColor(Color.parseColor(a.getColor()));
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do you work here
                }
            });
            unselected.addView(t);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_tag);

        selected = (FlowLayout) findViewById(R.id.selected);
        unselected = (FlowLayout) findViewById(R.id.unselected);

        setupThreads();
    }
}
