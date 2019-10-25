package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

import android.graphics.drawable.GradientDrawable;


public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeArrayAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formatMagnitude(currentEarthquake.getMagnitude()));
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView with view ID location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView offsetLocationView = (TextView) listItemView.findViewById(R.id.offset_location);

        String location = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;
        if (location.contains(LOCATION_SEPARATOR)) {
            String[] parts = location.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = location;
        }

        primaryLocationView.setText(primaryLocation);
        offsetLocationView.setText(locationOffset);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate dat
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int mag = (int) Math.floor(magnitude);
        int color;
        switch (mag) {
            case 1:
                color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                color = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                color = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                color = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                color = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                color = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                color = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                color = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                color = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return color;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

}
