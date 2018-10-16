package com.example.admin.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 6/24/2017.
 */

public class SongAdapter extends BaseAdapter {

    private ArrayList<Song> songs;
    private LayoutInflater songinf;
    SongAdapter(Context c,ArrayList<Song> songs)
    {
    this.songs=songs;
        songinf=LayoutInflater.from(c);

    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LinearLayout songlay=(LinearLayout)songinf.inflate(R.layout.song,parent,false);
        TextView songView = (TextView)songlay.findViewById(R.id.song_title);
        TextView artistView = (TextView)songlay.findViewById(R.id.song_artist);

        Song currSong = songs.get(i);

        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
        //set position as tag
        songlay.setTag(i);
        return songlay;



    }
}
