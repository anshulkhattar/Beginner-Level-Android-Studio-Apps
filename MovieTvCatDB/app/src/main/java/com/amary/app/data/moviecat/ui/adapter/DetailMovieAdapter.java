package com.amary.app.data.moviecat.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amary.app.data.moviecat.R;
import com.amary.app.data.moviecat.data.networking.model.ImageMovieItem;
import com.amary.app.data.moviecat.utils.ImgDownload;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieAdapter extends RecyclerView.Adapter<DetailMovieAdapter.CategoryViewHolder> {
    private ArrayList<ImageMovieItem> backdropList;

    public DetailMovieAdapter(ArrayList<ImageMovieItem> backdropList) {
        this.backdropList = backdropList;
    }

    public void refillImage(ArrayList<ImageMovieItem> backdropList){
        this.backdropList = new ArrayList<>();
        this.backdropList.addAll(backdropList);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_screenshot, parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.onBind(backdropList.get(position));
    }

    @Override
    public int getItemCount() {
        return backdropList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_screenshot)
        ImageView imgScreenshot;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void onBind(ImageMovieItem imageMovieItem) {
            ImgDownload.imgPoster(imageMovieItem.getFilePath(),imgScreenshot);
        }
    }


}
