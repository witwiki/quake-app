/**
 * Created by witwiki on 9/5/2016.
 *
 * Copyright (C) 2016 Vikasa Studios
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
package com.example.android.quakereport;

/**
 * {@link Quake} represents information of Earthquakes.
 * Each object has 3 properties: Quake Magnitude, Quake Location, and Quake Date.
 */

public class Quake {

    // Quake magnitude on the Richter scale (e.g. 4.2, 4.4, 7.2, etc.)
    private double mQuakeMagnitude;

    // Place of quake (e.g. San Francisco, Auckland, etc.)
    private String mQuakeLocation;

    // Date of quake (e.g. Feb 2, 2013; Jan 1, 2014; etc.)
    private long mQuakeTimeInMillisecs;

    /**
    * Create a new Quake object (Constructor).
    *
    * @param vQuakeMagnitude is the quake magnitude on the Richter scale (e.g. 4.2, 4.4, 7.2, etc.)
    * @param vQuakeLocation is the place of quake (e.g. San Francisco, Auckland, etc.)
    * @param vQuakeTimeInMillisecs is the date of quake (e.g. Feb 2, 2013; Jan 1, 2014; etc.)
    * */
    public Quake(double vQuakeMagnitude, String vQuakeLocation, long vQuakeTimeInMillisecs){
        mQuakeMagnitude = vQuakeMagnitude;
        mQuakeLocation = vQuakeLocation;
        mQuakeTimeInMillisecs = vQuakeTimeInMillisecs;
    }

    /**
     * Get the quake magnitude
     * @return mQuakeMagnitude
     */
    public double getQuakeMagnitude(){
        return mQuakeMagnitude;
    }

    /**
     * Get the quake location
     * @return mQuakeLocation
     */
    public String getQuakeLocation() {
        return mQuakeLocation;
    }

    /**
     * Get the quake date
     * @return mQuakeDate
     */
    public long getQuakeTimeInMillisecs() {
        return mQuakeTimeInMillisecs;
    }
}
