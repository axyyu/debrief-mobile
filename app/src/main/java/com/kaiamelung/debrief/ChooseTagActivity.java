package com.kaiamelung.debrief;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ChooseTagActivity extends AppCompatActivity {
    private FlowLayout selected;
    private FlowLayout unselected;

    private Thread[] threadList = new Thread[]{
            new Thread("tech","#CCffae00"),
            new Thread("science","#CC11d10a"),
            new Thread("world","#CCce7509"),
            new Thread("politics","#CC09a6ce"),
            new Thread("music","#CCff09ce"),
            new Thread("movies","#CCce098c"),
            new Thread("tv","#CC09ce8f"),
            new Thread("money","#CC7fce09"),
            new Thread("sports","#CCce0909")
    };
    private ArrayList<Thread> unselectedThreads = new ArrayList<Thread>(Arrays.asList(threadList));
    private ArrayList<Thread> selectedThreads = new ArrayList<Thread>();

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.saved_threads), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_tag_num), 2);
        for(int a =0; a<selectedThreads.size(); a++){
            editor.putString(""+a, selectedThreads.get(a).getValue());
            editor.putString(""+a+"c", selectedThreads.get(a).getColor());
        }
        for(int a =selectedThreads.size();a<threadList.length;a++){
            editor.remove(""+a);
            editor.remove(""+a+"c");
        }
        editor.commit();
        setResult(Activity.RESULT_OK);
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
        for(final Thread a : selectedThreads){
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
            selected.addView(t);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_choose_tag);
        //unselectedThreads.clear();

        selectedThreads.clear();
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.saved_threads), Context.MODE_PRIVATE);
        int a =0;
        while(true){
            String name = sharedPref.getString("" + a, "none");
            String color= sharedPref.getString("" + a + "c", "none");
            if(name.equals("none") || color.equals("none")){
                break;
            }
            else{
                selectedThreads.add(new Thread(name, color));
            }
            a++;
        }
        for(int i =0;i<selectedThreads.size();i++){
            Thread t = selectedThreads.get(i);
            boolean run = true;
            int j = 0;
            while(j<unselectedThreads.size() && run){
                if(t.getValue().equals(unselectedThreads.get(j).getValue())){
                    unselectedThreads.remove(j);
                    run = false;
                }
                j++;
            }
        }
        selected = (FlowLayout) findViewById(R.id.selected);
        unselected = (FlowLayout) findViewById(R.id.unselected);

        setupThreads();
    }
}
