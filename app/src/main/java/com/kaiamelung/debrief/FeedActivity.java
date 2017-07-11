package com.kaiamelung.debrief;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.GestureOverlayView;
import android.net.Uri;
import android.net.sip.SipAudioCall;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

public class FeedActivity extends AppCompatActivity implements /*GestureDetector.OnGestureListener,*/ DayFragment.OnFragmentInteractionListener {

    private ArrayList<Tag> tags;
    private RecyclerView mTag;
    private TagAdapter adapter;

    private TextView mDate;
    private SharedPreferences sharedPref;

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyMgr;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DateFormat mDateFormat = new SimpleDateFormat("M-d");
    private Date date = new Date();
    private Calendar cal = Calendar.getInstance();

    DayCollectionPagerAdapter mDayCollectionPagerAdapter;
    ViewPager mViewPager;

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                mDayCollectionPagerAdapter =
                        new DayCollectionPagerAdapter(
                                getSupportFragmentManager());
                mViewPager = (ViewPager) findViewById(R.id.pager);
                mViewPager.setAdapter(mDayCollectionPagerAdapter);
                mViewPager.setCurrentItem(mDayCollectionPagerAdapter.getCount()-1);
                System.out.println("OK VERY GOOD AWESOME");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feed);

        sharedPref = this.getSharedPreferences(getString(R.string.saved_threads),Context.MODE_PRIVATE);

        Boolean notifOn = sharedPref.getBoolean(getString(R.string.notif_on),false);
        int hour = sharedPref.getInt(getString(R.string.notif_hour),0);
        int min = sharedPref.getInt(getString(R.string.notif_minute),0);

        Calendar updateTime = Calendar.getInstance();
        updateTime.setTimeInMillis(System.currentTimeMillis());
        updateTime.set(Calendar.HOUR_OF_DAY, hour);
        updateTime.set(Calendar.MINUTE, min);
        updateTime.set(Calendar.SECOND, 0);
        updateTime.set(Calendar.MILLISECOND, 0);

        Intent downloader = new Intent(this, Notification.class);
        PendingIntent recurringDownload = PendingIntent.getBroadcast(this,
                0, downloader, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarms = (AlarmManager) getSystemService(
                Context.ALARM_SERVICE);
        if(notifOn) {
            alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    updateTime.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, recurringDownload);
        }
        else{
            alarms.cancel(recurringDownload);
        }

        int tagNumber = sharedPref.getInt(getString(R.string.saved_tag_num), 0);
        if(tagNumber!=2){
            //launch chooser
            Intent intent = new Intent(this, ChooseTagActivity.class);
            startActivityForResult(intent, 1);
        }
        else{
            mDayCollectionPagerAdapter =
                    new DayCollectionPagerAdapter(
                            getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.pager);
            mViewPager.setAdapter(mDayCollectionPagerAdapter);
            mViewPager.setCurrentItem(mDayCollectionPagerAdapter.getCount()-1);
        }
    }
}