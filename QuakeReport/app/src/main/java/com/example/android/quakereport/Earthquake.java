package com.example.android.quakereport;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /**
     * Sets an Earthquake Object
     *
     * @param magnitude          magnitude of the earthquake
     * @param location           location of the earthquake
     * @param timeInMilliseconds Timestamp the earthquake occurred
     * @param url                URL for the website that shows the event
     */
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }
}
