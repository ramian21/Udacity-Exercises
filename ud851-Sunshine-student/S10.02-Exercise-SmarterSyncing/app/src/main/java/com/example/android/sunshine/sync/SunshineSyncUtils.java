/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.sync;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.sunshine.data.WeatherContract;


public class SunshineSyncUtils {

    private static boolean sInitialized;

    public static synchronized void initialize(final Context context) {
        if (sInitialized) return;
        else {
            sInitialized = true;
            AsyncTask task = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    ContentResolver contentResolver = context.getContentResolver();
                    Cursor cursor = contentResolver.query(
                            WeatherContract.WeatherEntry.CONTENT_URI,
                            new String[]{WeatherContract.WeatherEntry.COLUMN_WEATHER_ID}, //only _id column required
                            WeatherContract.WeatherEntry.getSqlSelectForTodayOnwards(), //only entries from today onward are relevant
                            null,
                            null);
                    if (cursor == null || cursor.getCount() == 0) {
                        startImmediateSync(context);
                    }
                    cursor.close();
                    return null;
                }
            };
            task.execute();
        }
    }

//  COMPLETED (1) Declare a private static boolean field called sInitialized

    //  COMPLETED (2) Create a synchronized public static void method called initialize
    //  COMPLETED (3) Only execute this method body if sInitialized is false
    //  COMPLETED (4) If the method body is executed, set sInitialized to true
    //  COMPLETED (5) Check to see if our weather ContentProvider is empty
    //  COMPLETED (6) If it is empty or we have a null Cursor, sync the weather now!

    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution.
     *
     * @param context The Context used to start the IntentService for the sync.
     */
    public static void startImmediateSync(@NonNull final Context context) {
        Intent intentToSyncImmediately = new Intent(context, SunshineSyncIntentService.class);
        context.startService(intentToSyncImmediately);
    }
}