package com.amary.app.data.moviecat.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.data.networking.model.ResultMovie;
import com.amary.app.data.moviecat.utils.DateConvert;
import com.amary.app.data.moviecat.utils.ImgDownload;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.CategoryViewHolder>{
    private ArrayList<ResultMovie> movieData;
    private static ClickListener clickListener;

    public MovieListAdapter(ArrayList<ResultMovie> movieData) {
        this.movieData = movieData;
    }

    public void refill(ArrayList<ResultMovie> movieData){
        this.movieData =new ArrayList<>();
        this.movieData.addAll(movieData);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_list, viewGroup, false);
        return new CategoryViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.onBind(movieData.get(i));
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
        @BindView(R.id.btn_delete_favorite)
        Button btnDeleteFavorite;


        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            btnDeleteFavorite.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }

        @SuppressLint("SetTextI18n")
        void onBind(ResultMovie resultMovie) {
            txtJudul.setText(resultMovie.getTitle());
            txtTglRilis.setText(DateConvert.convert(resultMovie.getReleaseDate()));
            txtRating.setText(resultMovie.getVoteAverage().toString());
            ImgDownload.imgPoster(resultMovie.getPosterPath(),imgPoster);
            ImgDownload.imgPoster(resultMovie.getBackdropPath(), imgBackdrops);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MovieListAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }

    public void setFilter(ArrayList<ResultMovie> filter){
        this.movieData = new ArrayList<>();
        this.movieData.addAll(filter);
        notifyDataSetChanged();
    }
}

