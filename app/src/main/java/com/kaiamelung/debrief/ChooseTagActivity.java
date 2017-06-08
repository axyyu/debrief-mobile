package com.kaiamelung.debrief;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ChooseTagActivity extends AppCompatActivity {
    private FlowLayout selected;
    private FlowLayout unselected;

    private Thread[] threadList = new Thread[]{
            new Thread("general","#CC4286f4"),
            new Thread("tech","#CCffae00"),
            new Thread("science","#CC11d10a"),
            new Thread("math","#CCce7509"),
            new Thread("literature","#CC6f09ce"),
            new Thread("politics","#CC09a6ce"),
            new Thread("music","#CCff09ce"),
            new Thread("history","#CC0961ce"),
            new Thread("movies","#CCce098c"),
            new Thread("space","#CC09ce8f"),
            new Thread("money","#CC7fce09"),
            new Thread("sports","#CCce0909")
    };
    private ArrayList<Thread> unselectedThreads = new ArrayList<Thread>(Arrays.asList(threadList));
    private ArrayList<Thread> selectedThreads = new ArrayList<Thread>();

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.saved_threads), Context.MODE_PRIVATE);;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_tag_num), 1);
        for(int a =0; a<selectedThreads.size(); a++){
            editor.putString(""+a, selectedThreads.get(a).getValue());
            editor.putString(""+a+"c", selectedThreads.get(a).getColor());
        }
        editor.commit();
        finish();
    }

    private void setupThreads(){
        for(final Thread a : unselectedThreads){
            TextView t = new TextView(this);
            t.setText(a.getValue());

            LinearLayout.LayoutParams temp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            temp.setMargins(20, 20, 20, 20);
            t.setLayoutParams(temp);
            t.setTextSize(20);
            t.setTextColor(Color.parseColor("#FFFFFF"));
            t.setPadding(40, 20, 40, 20);
            t.setBackgroundColor(Color.parseColor(a.getColor()));
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getParent() == unselected){
                        unselected.removeView(v);
                        selected.addView(v);
                        unselectedThreads.remove(a);
                        selectedThreads.add(a);
                    }
                    else {
                        selected.removeView(v);
                        unselected.addView(v);
                        selectedThreads.remove(a);
                        unselectedThreads.add(a);
                    }

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
