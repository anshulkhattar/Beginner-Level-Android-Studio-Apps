package com.amary.app.data.moviecat.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConvert {
    private static String bhsData;

    public static void setBhsData(String bhsData) {
        DateConvert.bhsData = bhsData;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convert(String date){

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date convertDate = simpleDateFormat.parse(date);
            simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy", new Locale("" + bhsData));
            date = simpleDateFormat.format(convertDate);
        }catch (Exception e){
            e.printStackTrace();
        }

        return date;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertMonth(String date){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date convertDate = simpleDateFormat.parse(date);
            simpleDateFormat = new SimpleDateFormat("MM");
            date = simpleDateFormat.format(convertDate);
        }catch (Exception e){
            e.printStackTrace();
        }

        return date;
    }
}
