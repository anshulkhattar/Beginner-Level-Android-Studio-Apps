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
import com.amary.app.data.moviecat.data.networking.model.ResultTv;
import com.amary.app.data.moviecat.utils.DateConvert;
import com.amary.app.data.moviecat.utils.ImgDownload;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowListAdapter extends RecyclerView.Adapter<TvShowListAdapter.CategoryViewHolder> {

    private ArrayList<ResultTv> tvData;
    private static ClickListener clickListener;

    public TvShowListAdapter(ArrayList<ResultTv> tvData) {
        this.tvData = tvData;
    }

    public void refill(ArrayList<ResultTv> tvData){
        this.tvData = new ArrayList<>();
        this.tvData.addAll(tvData);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_list, viewGroup, false);
        return new CategoryViewHolder(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.onBind(tvData.get(i));
    }

    @Override
    public int getItemCount() {
        return tvData.size();
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
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
            btnDeleteFavorite.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }

        @SuppressLint("SetTextI18n")
        void onBind(ResultTv resultTv) {
            txtJudul.setText(resultTv.getName());
            txtTglRilis.setText(DateConvert.convert(resultTv.getFirstAirDate()));
            txtRating.setText(resultTv.getVoteAverage().toString());
            ImgDownload.imgPoster(resultTv.getPosterPath(),imgPoster);
            ImgDownload.imgPoster(resultTv.getBackdropPath(),imgBackdrops);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        TvShowListAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }

    public void setFilter(ArrayList<ResultTv> filter){
        this.tvData = new ArrayList<>();
        this.tvData.addAll(filter);
        notifyDataSetChanged();
    }
}
