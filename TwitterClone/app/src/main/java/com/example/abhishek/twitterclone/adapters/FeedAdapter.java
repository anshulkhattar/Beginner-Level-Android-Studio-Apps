package com.example.abhishek.twitterclone.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishek.twitterclone.R;
import com.example.abhishek.twitterclone.models.TweetModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedItemViewHolder> {

    private Context mContext;
    private ArrayList<TweetModel> mTweets;


    /**
     * Creates a {@link FeedAdapter} Object
     *
     * @param context For UI and resources
     * @param tweets  Data set to display in {@link FeedAdapter}
     */
    public FeedAdapter(@NonNull Context context, ArrayList<TweetModel> tweets) {
        mContext = context;
        mTweets = tweets;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent   The ViewGroup which holds all views inside it
     * @param viewType If more than one time [Not needed currently]
     * @return A new {@link FeedItemViewHolder} that holds the view for single item
     */
    @NonNull
    @Override
    public FeedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedItemViewHolder(
                LayoutInflater
                        .from(mContext)
                        .inflate(R.layout.row_tweet, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FeedItemViewHolder holder, int position) {
        holder.nameView.setText(mTweets.get(position).getName());
        holder.screenNameView.setText(mContext.getString(R.string.scree_name, mTweets.get(position).getScreenName()));
        holder.tweetTimeView.setText(mTweets.get(position).getTweetTime());
        holder.textView.setText(mTweets.get(position).getTweetText());
        holder.retweetView.setText(mTweets.get(position).getRetweets());
        holder.favView.setText(mTweets.get(position).getFavorites());

        Picasso.get()
                .load(mTweets.get(position).getProfileImageUrl())
                .transform(new CropCircleTransformation())
                .into(holder.mProfileImage);
        Picasso.get()
                .load(mTweets.get(position).getProfileBackgroundImageUrl())
                .resize(800,460)
                .centerCrop()
                .transform(new RoundedCornersTransformation(10, 0))
                .into(holder.mProfileBackgroundImage);
    }

    @Override
    public int getItemCount() {
        if (mTweets == null || mTweets.size() == 0) return 0;
        return mTweets.size();
    }

    class FeedItemViewHolder extends RecyclerView.ViewHolder {

        final TextView nameView;
        final TextView screenNameView;
        final TextView tweetTimeView;
        final TextView textView;
        final TextView retweetView;
        final TextView favView;

        final ImageView mProfileImage;
        final ImageView mProfileBackgroundImage;

        FeedItemViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.tv_name);
            screenNameView = itemView.findViewById(R.id.tv_screen_name);
            tweetTimeView = itemView.findViewById(R.id.tv_tweet_time);
            textView = itemView.findViewById(R.id.tv_tweet_text);
            mProfileImage = itemView.findViewById(R.id.iv_profile_image);
            mProfileBackgroundImage = itemView.findViewById(R.id.iv_tweet_image);
            retweetView = itemView.findViewById(R.id.tv_retweets);
            favView = itemView.findViewById(R.id.tv_fav);
        }
    }
}
