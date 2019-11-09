package com.example.voicerecorder;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class List_Adapter extends ArrayAdapter
{
    Context context;
    File f_list[];

    public List_Adapter(Context context , File[] objects)
    {
        super(context , R.layout.list_each_item , objects);

        this.context = context;
        this.f_list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        list_holder holder;

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_each_item , null , false);

            holder = new list_holder(convertView);
        }
        else
        {
            holder = new list_holder(convertView);
        }

        holder.fill(f_list[position]);

        return convertView;
    }

    //CLASS VIEW HOLDER ----------------------------------------------------------------------------
    class list_holder
    {
        TextView tv_name;
        TextView tv_date;
        TextView tv_duration;

        MediaPlayer mp;

        public list_holder(View v)
        {
            tv_name = v.findViewById(R.id.tv_name);
            tv_date = v.findViewById(R.id.tv_date);
            tv_duration = v.findViewById(R.id.tv_duration);
        }

        public void fill(File f)
        {
            MediaPlayer mp = new MediaPlayer();
            try
            {
                mp.setDataSource(f.getPath());
                mp.prepare();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            tv_name.setText(f.getName());
            tv_duration.setText(String.valueOf(mp.getDuration()/1000)+" : "+String.valueOf(mp.getDuration()%100));

        }
    }
}
