package id.merahmuda.bcd.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import id.merahmuda.bcd.R;
import id.merahmuda.bcd.model.Produk;

/**
 * Created by Ujang Wahyu on 05,Oktober,2018
 */
public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.MyViewHolder> {

    private Context mContext;
    private List<Produk> produkList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama,txtHarga, txtDeskripsi;
        private ImageView imgCover;
        private AVLoadingIndicatorView loading;

        public MyViewHolder(View view) {
            super(view);
            txtNama = view.findViewById(R.id.txt_nama);
            txtHarga = view.findViewById(R.id.txt_harga);
            txtDeskripsi = view.findViewById(R.id.txt_deskripsi);
            imgCover = view.findViewById(R.id.img_cover);
            loading = view.findViewById(R.id.loading);
        }
    }

    public ProdukAdapter(Context mContext, List<Produk> produks) {
        this.mContext = mContext;
        this.produkList = produks;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_produk, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Produk produk = produkList.get(position);
        holder.txtNama.setText(produk.getName());
        holder.txtHarga.setText(produk.getHarga());
        holder.txtDeskripsi.setText(produk.getDeskripsi().substring(0,20));

        Glide.with(mContext)
                .load(produk.getCover())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.loading.hide();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.loading.hide();
                        return false;
                    }
                })
                .into(holder.imgCover);
    }

    @Override
    public int getItemCount() {
        return produkList.size();
    }
}

