package com.aornelas.wearable.pullups;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;


public class SummaryActivity extends Activity {

    private int mWeekNumber;
    private int mDayNumber;

    private final int LEVEL_COUNT = 3;
    private final int SET_COUNT = 5;

    // Source: http://twentypullups.com/the-twenty-pull-ups-challenge/
    private final int[/* week */][/* day */][/* level */][/* set */] PULLUPS = {
        { /* week 1 */
            { /* day 1 */
                { 2,1,1,2,3 }, { 4,3,2,4,5 }, { 6,6,4,7,9 }
            },
            { /* day 2 */
                { 2,2,1,2,3 }, { 4,3,3,4,5 }, { 7,6,6,8,9 }
            },
            { /* day 3 */
                { 2,2,2,2,3 }, { 4,4,4,3,4 }, { 8,8,8,6,8 }
            }
        },
        { /* week 2 */
            { /* day 1 */
                { 3,2,3,2,3 }, { 5,4,3,4,5 }, { 8,7,6,7,8 }
            },
            { /* day 2 */
                { 3,3,2,3,3 }, { 4,4,3,4,6 }, { 8,7,7,6,10 }
            },
            { /* day 3 */
                { 3,3,3,2,4 }, { 4,4,4,4,7 }, { 8,8,7,7,10 }
            }
        },
        { /* week 3 */
            { /* day 1 */
                { 4,3,4,3,4 }, { 6,5,4,5,6 }, { 9,7,6,7,9 }
            },
            { /* day 2 */
                { 5,3,4,3,5 }, { 6,5,6,5,6 }, { 9,7,8,7,9 }
            },
            { /* day 3 */
                { 6,5,4,3,6 }, { 7,6,5,4,7 }, { 10,8,7,6,10 }
            }
        },
        { /* week 4 */
            { /* day 1 */
                { 5,4,5,6,7 }, { 5,6,7,8,9 }, { 6,7,8,9,11 }
            },
            { /* day 2 */
                { 7,6,5,6,7 }, { 7,6,7,8,9 }, { 7,8,9,10,11 }
            },
            { /* day 3 */
                { 8,6,5,4,7 }, { 10,8,7,6,9 }, { 12,10,8,7,11 }
            }
        },
        { /* week 5 */
            { /* day 1 */
                { 4,5,6,6,7 }, { 5,6,7,8,9 }, { 6,7,8,9,11 }
            },
            { /* day 2 */
                { 8,7,8,7,8 }, { 10,8,10,8,10 }, { 12,10,12,10,12 }
            },
            { /* day 3 */
                { 5,6,8,9,10 }, { 7,8,9,11,12 }, { 10,11,12,13,15 }
            }
        },
        { /* week 6 */
            { /* day 1 */
                { 5,6,7,8,9 }, { 6,7,8,9,11 }, { 7,8,9,10,12 }
            },
            { /* day 2 */
                { 8,9,10,11,12 }, { 10,11,12,13,14 }, { 11,12,13,14,15 }
            },
            { /* day 3 */
                { 13,11,9,7,12 }, { 14,12,11,9,14 }, { 15,13,12,10,15 }
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Prevent the screen timeout from stopping this application
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent intent = getIntent();
        mWeekNumber = intent.getIntExtra(MainActivity.EXTRA_WEEK_NUMBER, 1);
        mDayNumber = intent.getIntExtra(MainActivity.EXTRA_DAY_NUMBER, 1);

        SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_file_key),
            Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(getString(R.string.saved_week), mWeekNumber);
        editor.putInt(getString(R.string.saved_day), mDayNumber);
        editor.commit();

        setContentView(R.layout.summary_layout);

        ((TextView) findViewById(R.id.summaryTitle)).setText("week " + mWeekNumber + " day " + mDayNumber);

        int weekIndex = mWeekNumber - 1;
        int dayIndex = mDayNumber - 1;
        // TODO: handle dupe sets in weeks 5 and 6
        int[] levelIds = { R.id.rookieSets, R.id.veteranSets, R.id.eliteSets };
        for (int levelIndex = 0 ; levelIndex < LEVEL_COUNT; levelIndex++) {
            for (int setIndex = 0; setIndex < SET_COUNT; setIndex++) {
                String setLabel = String.valueOf(PULLUPS[weekIndex][dayIndex][levelIndex][setIndex]);
                ((TextView) findViewById(levelIds[levelIndex] + setIndex + 1)).setText(setLabel);
            }
        }
    }

}
