/**
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

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by witwiki on 9/5/2016.
 *
 * {@link QuakeAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Quake} objects.
 *
 */
public class QuakeAdapter extends ArrayAdapter<Quake> {

    private static final String LOCATION_SEPARATOR = "of";

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param quakes  A List of Quake objects to display in a list
     */
    public QuakeAdapter(Activity context, ArrayList<Quake> quakes) {
        // We initialize the ArrayAdapter's internal storage for the context and the list.
        // The second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, quakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        // Get the {@link Quake} object located at this position in the list
        Quake currentQuake = getItem(position);

        /**
         * Str Manipulation
         */
        String originalLoc = currentQuake.getQuakeLocation();
        String primaryLoc;
        String locationOffset;
        if (originalLoc.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLoc.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLoc = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLoc = originalLoc;
        }

        // Find the TextView in the list_items.xml layout with the ID quake_magnitude
        // Get the quake magnitude from the current Quake object and
        TextView magTextView = (TextView) listItemView.findViewById(R.id.quake_magnitude);
        // set this text on the mag TextView
        String formattedMag = formatMag(currentQuake.getQuakeMagnitude());
        //  Display formatted magnitude
        magTextView.setText(formattedMag);

        //  Set background color on the magnitude circle.
        //  Get background from TextView - a GradientDrawable object
        GradientDrawable magCircle = (GradientDrawable) magTextView.getBackground();

        //  Get quake background color based on current quake magnitude
        int magColour = getMagnitudeColour(currentQuake.getQuakeMagnitude());

        //  Set magnitude colour on the circle
        magCircle.setColor(magColour);

        // Find the TextView in the list_items.xml layout with the ID quake_location
        TextView locOffTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Get the quake location from the current Quake object and
        // set this text on the loc TextView
        locOffTextView.setText(locationOffset);

        // Find the TextView in the list_items.xml layout with the ID quake_location
        TextView locTextView = (TextView) listItemView.findViewById(R.id.quake_location);
        // Get the quake location from the current Quake object and
        // set this text on the loc TextView
        locTextView.setText(primaryLoc);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentQuake.getQuakeTimeInMillisecs());

        // Find the TextView in the list_items.xml layout with the ID quake_date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.quake_date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.quake_time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeTextView.setText(formattedTime);

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;


    }

    /**
     * Helper method to return the formatted date string (i.e. "Mar 4, 2019") from a
     * Date Object (created from a long data type in milliseconds - UNIX time)
     *
     * @param dateObject is the Date Object input argument
     * @return is the formatted date string
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Helper method to return the formatted time string (i.e. "5:30 PM") from a
     * Date Object (created from a long data type in milliseconds - UNIX time)
     *
     * @param dateObject is the Date Object input argument
     * @return is the formatted time string
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Helper method that converts a double data type {@link Quake} object into {@link String}
     * while changing the decimal format.
     */
    private String formatMag(double mag) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(mag);
    }

    private int getMagnitudeColour(double magnitude) {
        int magColourResourceId;
        int magFloor = (int) Math.floor(magnitude);
        switch (magFloor) {
            case 0:
            case 1:
                magColourResourceId = R.color.magnitude1;
                break;
            case 2:
                magColourResourceId = R.color.magnitude2;
                break;
            case 3:
                magColourResourceId = R.color.magnitude3;
                break;
            case 4:
                magColourResourceId = R.color.magnitude4;
                break;
            case 5:
                magColourResourceId = R.color.magnitude5;
                break;
            case 6:
                magColourResourceId = R.color.magnitude6;
                break;
            case 7:
                magColourResourceId = R.color.magnitude7;
                break;
            case 8:
                magColourResourceId = R.color.magnitude8;
                break;
            case 9:
                magColourResourceId = R.color.magnitude9;
                break;
            default:
                magColourResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magColourResourceId);
    }
}
