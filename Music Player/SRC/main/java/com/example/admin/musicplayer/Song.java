package com.example.admin.musicplayer;

/**
 * Created by admin on 6/24/2017.
 */

public class Song
{

    private long id;
    private String title;
    private String artist;

Song(long songid,String title,String artist)
{
    this.id=songid;
    this.title=title;
    this.artist=artist;

}
    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
}
