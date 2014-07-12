package com.example.android.google.wearable.watchviewstub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivity extends Activity implements WearableListView.ClickListener {

    private static final String DEBUG_TAG = "DEBUG.MainActivity";

    public static final String EXTRA_WEEK_NUMBER = "com.andres.pushups.WEEK_NUMBER";
    public static final String EXTRA_DAY_NUMBER = "com.andres.pushups.DAY_NUMBER";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WearableListView listView = (WearableListView) findViewById(R.id.list);
        listView.setAdapter(new Adapter(this));
        listView.setClickListener(this);
    }

    @Override
    public void onClick(WearableListView.ViewHolder holder) {
        Intent intent = new Intent(this, DayActivity.class);
        int weekNumber = holder.getPosition() + 1;
        intent.putExtra(EXTRA_WEEK_NUMBER, weekNumber);
        startActivity(intent);
    }

    @Override
    public void onTopEmptyRegionClick() { }

    private static final class Adapter extends WearableListView.Adapter {
        private final Context mContext;
        private final LayoutInflater mInflater;

        private Adapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new WearableListView.ViewHolder(
                    mInflater.inflate(R.layout.list_item, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            TextView view = (TextView) holder.itemView.findViewById(R.id.name);
            view.setText("week " + (position + 1));
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return 6;
        }
    }
}