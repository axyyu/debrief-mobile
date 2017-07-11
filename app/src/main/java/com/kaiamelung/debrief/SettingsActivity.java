package com.kaiamelung.debrief;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    private Switch mSaveSetting;
    private Switch mNotificationOn;

    private TextView mNotificationTime;

    private Button mChangeTime;
    private Button mChooseThreads;

    private ImageButton mSaveSettings;

    @Override
    public void onBackPressed() {
        saveSettings();
    }

    public void saveSettings(){
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.saved_threads), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.commit();
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSaveSetting = (Switch) findViewById(R.id.save_articles);
        mNotificationOn = (Switch) findViewById(R.id.notifs_on);

        mNotificationTime = (TextView) findViewById(R.id.notif_time);

        mChangeTime = (Button) findViewById(R.id.change_time);

        final Intent intent = new Intent(this, ChooseTagActivity.class);
        mChooseThreads = (Button) findViewById(R.id.change_threads);
        mChooseThreads.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
