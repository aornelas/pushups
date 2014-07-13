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

    private final int LEVEL_COUNT = 3;
    private final int SET_COUNT = 5;

    private final int[/* week */][/* day */][/* level */][/* set */] PUSHUPS = {
            { /* week 1 */
                    { /* day 1 */
                            { /* level 1 */
                                    2,3,2,2,3
                            },
                            { /* level 2 */
                                    6,6,4,4,5
                            },
                            { /* level 3 */
                                    10,12,7,7,9
                            }
                    },
                    { /* day 2 */
                            { /* level 1 */
                                    3,4,2,3,4
                            },
                            { /* level 2 */
                                    6,8,6,6,7
                            },
                            { /* level 3 */
                                    10,12,8,8,12
                            }
                    },
                    { /* day 3 */
                            { /* level 1 */
                                    4,5,4,4,5
                            },
                            { /* level 2 */
                                    8,10,7,7,10
                            },
                            { /* level 3 */
                                    11,15,9,9,13
                            }
                    }
            }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Prevent the screen timeout from stopping this application
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent intent = getIntent();
        mWeekNumber = intent.getIntExtra(MainActivity.EXTRA_WEEK_NUMBER, -1);
        mDayNumber = intent.getIntExtra(MainActivity.EXTRA_DAY_NUMBER, -1);

        setContentView(R.layout.summary_layout);

        ((TextView) findViewById(R.id.summaryTitle)).setText("week " + mWeekNumber + " day " + mDayNumber);

        int weekIndex = mWeekNumber - 1;
        int dayIndex = mDayNumber - 1;
        int[] levelIds = { R.id.rookieSets, R.id.veteranSets, R.id.eliteSets };
        for (int levelIndex = 0 ; levelIndex < LEVEL_COUNT; levelIndex++) {
            for (int setIndex = 0; setIndex < SET_COUNT; setIndex++) {
                String setLabel = setIndex == SET_COUNT - 1 ? ">" : "";
                setLabel += String.valueOf(PUSHUPS[weekIndex][dayIndex][levelIndex][setIndex]);
                ((TextView) findViewById(levelIds[levelIndex] + setIndex + 1)).setText(setLabel);
            }
        }
    }

}
