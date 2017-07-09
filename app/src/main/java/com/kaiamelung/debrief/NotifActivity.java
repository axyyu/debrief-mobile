package com.kaiamelung.debrief;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class NotifActivity extends AppCompatActivity {
    private ToggleButton mToggle;
    private TimePicker mTimePicker;
    private boolean notif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        mToggle = (ToggleButton) findViewById(R.id.toggleButton);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        mToggle.setTextOn("Notifications On");
        mToggle.setTextOff("Notifications Off");
        mToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notif = isChecked;
            }
        });
    }
    @Override
    public void onBackPressed() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.saved_threads), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.notif_on),notif);
        editor.putInt(getString(R.string.notif_hour),mTimePicker.getHour());
        editor.putInt(getString(R.string.notif_minute),mTimePicker.getMinute());
        editor.apply();
        setResult(Activity.RESULT_OK);
        finish();
    }
}
