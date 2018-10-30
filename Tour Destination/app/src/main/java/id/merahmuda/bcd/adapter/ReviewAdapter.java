package id.merahmuda.bcd.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
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
import id.merahmuda.bcd.activity.ReviewActivity;
import id.merahmuda.bcd.model.Review;

/**
 * Created by Herdhiantoko Fathani on 10/05/2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Review> reviewList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtContent, txtDate;
        private ImageView imgCover;

        public MyViewHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txt_name);
            txtContent = view.findViewById(R.id.txt_content);
            txtDate = view.findViewById(R.id.txt_date);
            imgCover = view.findViewById(R.id.img_cover);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ReviewActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }
    }

    public ReviewAdapter(Context context, List<Review> reviews){
        this.mContext = context;
        this.reviewList = reviews;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_review, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.txtName.setText(review.getName());
        holder.txtContent.setText(review.getContent().length() > 40 ? review.getContent().substring(0,40) : review.getContent());
        holder.txtDate.setText(review.getDate());

        Glide.with(mContext)
            .load(review.getCover())
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
        return reviewList.size();
    }
}
