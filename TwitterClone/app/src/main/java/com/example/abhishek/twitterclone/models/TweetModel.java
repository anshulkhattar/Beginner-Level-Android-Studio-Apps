package com.example.abhishek.twitterclone.models;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TweetModel {
    private String mName;
    private String mScreenName;
    private String mTweetText;
    private String mTweetTime;
    private String mProfileImageUrl;
    private String mProfileBackgroundImageUrl;
    private String mRetweets;
    private String mFavorites;

    public TweetModel(String name, String screenName, String tweetText, String tweetTime, String profileImageUrl, String profileBackgroundImageUrl, String retweets, String favorites) {
        mName = name;
        mScreenName = screenName;
        mTweetText = tweetText;
        mTweetTime = tweetTime;
        mProfileImageUrl = profileImageUrl;
        mProfileBackgroundImageUrl = profileBackgroundImageUrl;
        mRetweets = retweets;
        mFavorites = favorites;
    }

    public String getProfileBackgroundImageUrl() {
        return mProfileBackgroundImageUrl;
    }

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getScreenName() {
        return mScreenName;
    }

    public String getTweetText() {
        return mTweetText;
    }

    @Override
    public String toString() {
        return "TweetModel{" +
                "mName='" + mName + '\'' +
                ", mScreenName='" + mScreenName + '\'' +
                ", mTweetText='" + mTweetText + '\'' +
                ", mTweetTime='" + getTweetTime() + '\'' +
                ", mRetweets=" + mRetweets +
                ", mFavorites=" + mFavorites +
                '}';
    }

    public String getTweetTime() {
        Date date, curDate = new Date();

        long time;
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");
        StringBuilder formattedDate = new StringBuilder();

        try {
            date = format.parse(mTweetTime);
            time = (curDate.getTime() - date.getTime()) / 1000;

            Log.v(TweetModel.class.getSimpleName(), "time= " + time);

            if (time < 60) {
                formattedDate
                        .append("< 1min");
            } else if (time < 3600) {
                time = time / 60;
                formattedDate
                        .append(time)
                        .append("m");
            } else if (time < 86400) {
                time = time / 3600;
                formattedDate
                        .append(time)
                        .append("h");
            } else {
                time = time / 86400;
                formattedDate
                        .append(time)
                        .append("d");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate.toString();
    }

    public String getRetweets() {
        return mRetweets;
    }

    public String getFavorites() {
        return mFavorites;
    }
}
