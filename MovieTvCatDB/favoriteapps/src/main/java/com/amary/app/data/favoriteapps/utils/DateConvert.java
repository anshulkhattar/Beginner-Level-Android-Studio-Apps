package com.amary.app.data.favoriteapps.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConvert {
    @SuppressLint("SimpleDateFormat")
    public static String convert(String date){

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date convertDate = simpleDateFormat.parse(date);
            simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy", new Locale(""));
            date = simpleDateFormat.format(convertDate);
        }catch (Exception e){
            e.printStackTrace();
        }

        return date;
    }
}
