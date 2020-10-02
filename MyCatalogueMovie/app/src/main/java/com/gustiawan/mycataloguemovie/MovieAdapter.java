package com.gustiawan.mycataloguemovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Gustiawan on 8/18/2018.
 */

public class MovieAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<MovieList> movieLists = new ArrayList<>();
    private Context context;

    public MovieAdapter (Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieList> movie){
        movieLists = movie;
        notifyDataSetChanged();
    }

    public void addItem(MovieList movie){
        movieLists.add(movie);
        notifyDataSetChanged();
    }

    public void clearData(){
        movieLists.clear();
    }

    public int getItemViewType(int position){
        return 0;
    }

    public int getViewTypeCount(){
        return 1;
    }

    @Override
    public int getCount() {
        if (movieLists == null) return 0;
        return movieLists.size();
    }

    @Override
    public Object getItem(int i) {
        return movieLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.movie_list, null);

            holder.title = (TextView) convertView.findViewById(R.id.tv_judul);
            holder.synopsis = (TextView) convertView.findViewById(R.id.tv_sinopsis);
            holder.posterPath = (ImageView) convertView.findViewById(R.id.poster_movie);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

       // holder.title.setText(movieLists.get(position).get);

        return null;
    }

    private class ViewHolder {
        TextView title, synopsis;
        ImageView posterPath;

    }
}
