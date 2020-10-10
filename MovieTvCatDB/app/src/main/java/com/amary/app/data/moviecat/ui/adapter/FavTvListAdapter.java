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
import com.amary.app.data.moviecat.data.database.model_db.Tv;
import com.amary.app.data.moviecat.utils.DateConvert;
import com.amary.app.data.moviecat.utils.ImgDownload;
import com.amary.app.data.moviecat.utils.LocalData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavTvListAdapter extends RecyclerView.Adapter<FavTvListAdapter.FavoriteViewHolder> {
    private List<Tv> tvList;

    public FavTvListAdapter(List<Tv> tvList) {
        this.tvList = tvList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_list, parent, false);
        return new FavoriteViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.onBind(tvList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
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

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        void onBind(Tv tv) {
            txtJudul.setText(tv.getTitleTv());
            txtTglRilis.setText(DateConvert.convert(tv.getDateTv()));
            txtRating.setText(tv.getRateTv().toString());
            ImgDownload.imgPoster(tv.getPosterTv(), imgPoster);
            ImgDownload.imgPoster(tv.getBackdropsTv(), imgBackdrops);
            btnDeleteFavorite.setOnClickListener(v -> LocalData.tvRepository.deleteItemTv(tv));
        }
    }
}
