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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by witwiki on 9/5/2016.
 *
 * {@link QuakeAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Quake} objects.
 *
 */
public class QuakeAdapter extends ArrayAdapter<Quake> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param quakes         A List of Quake objects to display in a list
     */
    public QuakeAdapter(Activity context, ArrayList<Quake> quakes){
        // We initialize the ArrayAdapter's internal storage for the context and the list.
        // The second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, quakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        // Get the {@link Quake} object located at this position in the list
        Quake currentQuake = getItem(position);

        // Find the TextView in the list_items.xml layout with the ID quake_magnitude
        TextView magTextView = (TextView) listItemView.findViewById(R.id.quake_magnitude);
        // Get the quake magnitude from the current Quake object and
        // set this text on the mag TextView
        magTextView.setText(currentQuake.getQuakeMagnitude());

        // Find the TextView in the list_items.xml layout with the ID quake_location
        TextView locTextView = (TextView) listItemView.findViewById(R.id.quake_location);
        // Get the quake location from the current Quake object and
        // set this text on the loc TextView
        locTextView.setText(currentQuake.getmQuakeLocation());

        // Find the TextView in the list_items.xml layout with the ID quake_date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.quake_date);
        // Get the quake date from the current Quake object and
        // set the text to dateTextView
        dateTextView.setText(currentQuake.getmQuakeDate());

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }


}
