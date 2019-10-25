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

import java.util.List;

import id.merahmuda.bcd.R;
import id.merahmuda.bcd.activity.DetailEventActivity;
import id.merahmuda.bcd.activity.DetailTenantActivity;
import id.merahmuda.bcd.model.Event;

/**
 * Created by Ujang Wahyu on 05,Oktober,2018
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private Context mContext;
    private List<Event> eventList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCover;
        private TextView txtNama, txtTanggal, txtDeskripsi, txtKategori;

        public MyViewHolder(View view) {
            super(view);
            imgCover = view.findViewById(R.id.img_cover);
            txtNama = view.findViewById(R.id.txt_nama);
            txtTanggal = view.findViewById(R.id.txt_tanggal);
            txtDeskripsi = view.findViewById(R.id.txt_deskripsi);
            txtKategori = view.findViewById(R.id.txt_kategori);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailEventActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public EventAdapter(Context mContext, List<Event> events) {
        this.mContext = mContext;
        this.eventList = events;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_event, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.txtNama.setText(event.getName());
        holder.txtTanggal.setText(event.getTanggal());
        holder.txtDeskripsi.setText(event.getDeskripsi().substring(0,50));
        holder.txtKategori.setText(event.getKategori());

        Glide.with(mContext)
                .load(event.getCover())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.imgCover);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
