package com.example.android.google.wearable.watchviewstub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;


public class SummaryActivity extends Activity {

    private int mWeekNumber;
    private int mDayNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mWeekNumber = intent.getIntExtra(MainActivity.EXTRA_WEEK_NUMBER, -1);
        mDayNumber = intent.getIntExtra(MainActivity.EXTRA_DAY_NUMBER, -1);

        setContentView(R.layout.summary_layout);

        ((TextView) findViewById(R.id.summaryTitle)).setText("week " + mWeekNumber + " day " + mDayNumber);

        // Prevent the screen timeout from stopping this application
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

}