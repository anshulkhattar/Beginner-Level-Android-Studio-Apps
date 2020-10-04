package com.gustiawan.mycataloguemovie;

import org.json.JSONObject;

/**
 * Created by Gustiawan on 8/18/2018.
 */

public class MovieList {

    private String judulFilm;
    private String deskripsiFilm;
    private String tahunFilm;
    private String url_poster;
    private String posterBesar;

    public MovieList(JSONObject object){
        try {

            String movieTitle = object.getString("title");
            String sinopsis = object.getString("synopsis");
            String url = object.getString("posterPath");
            String year = object.getString("releaseDate");
            String header = object.getString("header");

            this.judulFilm = movieTitle;
            this.deskripsiFilm = sinopsis;
            this.url_poster = url;

            if (!year.equals("")){
                this.tahunFilm = year.substring(0,4);
            }else {
                tahunFilm = " ";
            }

            this.posterBesar = header;
        }catch (Exception e){

        }
    }


    public String getJudulFilm(){
            return judulFilm;
    }

    public void setJudulFilm(String judulFilm){
        this.judulFilm = judulFilm;
    }

    public String getDeskripsiFilm(){
        return deskripsiFilm;
    }

    public void setDeskripsiFilm(String deskripsiFilm){
        this.deskripsiFilm = deskripsiFilm;
    }

    public String getUrl_poster(){
        return url_poster;
    }

    public void setUrl_poster(String url_poster){
        this.url_poster = url_poster;
    }

    public String getTahunFilm(){
        return tahunFilm;
    }

    public void setTahunFilm(String tahunFilm){
        this.tahunFilm = tahunFilm;
    }

    public String getPosterBesar(){
        return posterBesar;
    }

    public void setPosterBesar(String posterBesar){
        this.posterBesar = posterBesar;
    }

}
