package com.kaiamelung.debrief;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity {

    private Switch mSaveSetting;
    private Switch mNotificationOn;

    private TextView mNotificationTime;

    private Button mChangeTime;
    private Button mChooseThreads;

    private ImageButton mSaveSettings;

    private SharedPreferences sharedPref;
    private DateFormat dateFormat;

    @Override
    public void onBackPressed() {
        saveSettings();
    }

    public void saveSettings(){
        if(!sharedPref.getBoolean(getString(R.string.save_articles), true)){
            File dir = getFilesDir();
            try {
                FileUtils.deleteDirectory(dir);
            }
            catch(Exception e){
                System.out.print("ERROR");
            }
        }
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dateFormat = new SimpleDateFormat("H:mm");

        sharedPref = getSharedPreferences(getString(R.string.saved_threads),Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        mSaveSetting = (Switch) findViewById(R.id.save_articles);
        mSaveSetting.setChecked(sharedPref.getBoolean(getString(R.string.save_articles), true));
        mSaveSetting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(getString(R.string.save_articles), isChecked);
                editor.apply();
            }
        });

        mNotificationOn = (Switch) findViewById(R.id.notifs_on);
        mNotificationOn.setChecked(sharedPref.getBoolean(getString(R.string.notif_on), true));
        mNotificationOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(getString(R.string.notif_on), isChecked);
                editor.apply();
            }
        });

        int min = sharedPref.getInt(getString(R.string.notif_minute), 0);
        int hour = sharedPref.getInt(getString(R.string.notif_hour), 9);
        mNotificationTime = (TextView) findViewById(R.id.notif_time);
        try {
            Date date = dateFormat.parse(hour+":"+min);
            mNotificationTime.setText(dateFormat.format(date));
        }
        catch( Exception e){
            System.out.print("ERROR");
        }
        mNotificationTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        try {
                            Date date = dateFormat.parse(selectedHour+":"+selectedMinute);
                            mNotificationTime.setText(dateFormat.format(date));
                        }
                        catch( Exception e){
                            System.out.print("ERROR");
                        }
                        editor.putInt(getString(R.string.notif_hour), selectedHour);
                        editor.putInt(getString(R.string.notif_minute), selectedMinute);
                        editor.apply();
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        final Intent intent = new Intent(this, ChooseTagActivity.class);
        mChooseThreads = (Button) findViewById(R.id.change_threads);
        mChooseThreads.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        mSaveSettings = (ImageButton) findViewById(R.id.save_settings);
        mSaveSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveSettings();
            }
        });
    }
}
