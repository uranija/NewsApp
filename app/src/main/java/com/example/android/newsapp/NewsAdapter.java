package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.android.newsapp.R.id.WebTitle;

public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * The part of the location string from the GUARDIAN service that we use to extract
     * publication date
     */
    private static final String PUBLICATION_DATE_SEPARATOR = "T";

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param news    A List of News objects to display in a list
     */

    public NewsAdapter(Context context, ArrayList<News> news) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, news);

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
                    R.layout.list_item, parent, false);
        }

        // Get the {@link News} object located at this position in the list
        News currentNews = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView titleTextView = (TextView) listItemView.findViewById(WebTitle);
        // Get the version name from the current News object and
        // set this text on the name TextView
        titleTextView.setText(currentNews.getWebTitle());
        // Display the webTitle of the current news in that TextView

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView publisherTextView = (TextView) listItemView.findViewById(R.id.sectionName);
        // Get the version number from the current News object and
        // set this text on the number TextView
        publisherTextView.setText(currentNews.getSectionName());

        // Get the original date string from the News object,
        // which can be in the format of "2017-04-18T17:20:59Z" or "Pacific-Antarctic Ridge".
        String originalDate = (currentNews.getPublicationDate());

        // If the original date string (i.e. "2017-04-18T17:20:59Z") contains
        // a date (2017-04-18) and a time (17:20:59)
        // then store the date separately from the time in 2 Strings,
        // so they can be displayed in 2 TextViews.
        String date;
        String time;

        // Split the string into different parts (as an array of Strings)
        // based on the "T" text. We expect an array of 2 Strings, where
        // the first String will be "2017-04-18" and the second String will be "17:20:59".
        String[] parts = originalDate.split(PUBLICATION_DATE_SEPARATOR);
        // Date should be "2017-04-18"
        date = parts[0];
        // Time should be "17:20"
        time = parts[1];

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Display the date of the current news in that TextView
        dateView.setText(date);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Display the time of the current news in that TextView
        timeView.setText(time);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(String originalDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(originalDate);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(String originalDate) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(originalDate);
    }
}

