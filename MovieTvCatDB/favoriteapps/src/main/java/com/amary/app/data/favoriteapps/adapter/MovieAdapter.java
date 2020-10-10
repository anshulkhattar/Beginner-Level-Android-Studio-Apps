package com.amary.app.data.favoriteapps.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amary.app.data.favoriteapps.R;
import com.amary.app.data.favoriteapps.utils.DateConvert;
import com.amary.app.data.favoriteapps.utils.ImgDownload;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Cursor cursor;
    private Context context;

    public void refill(Cursor cursor){
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        if (cursor.moveToPosition(position)){
            holder.txtJudul.setText(cursor.getString(cursor.getColumnIndexOrThrow("title_movie")));
            holder.txtTglRilis.setText(DateConvert.convert(cursor.getString(cursor.getColumnIndexOrThrow("date_movie"))));
            holder.txtRating.setText(cursor.getString(cursor.getColumnIndexOrThrow("rate_movie")));
            ImgDownload.imgPoster(cursor.getString(cursor.getColumnIndexOrThrow("poster_movie")),holder.imgPoster);
            ImgDownload.imgPoster(cursor.getString(cursor.getColumnIndexOrThrow("backdrops_movie")), holder.imgBackdrops);
        }
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_poster)
        ImageView imgPoster;
        @BindView(R.id.img_backdrops)
        ImageView imgBackdrops;
        @BindView(R.id.txt_judul)
        TextView txtJudul;
        @BindView(R.id.txt_tgl_rilis)
        TextView txtTglRilis;
        @BindView(R.id.txt_rating)
        TextView txtRating;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
