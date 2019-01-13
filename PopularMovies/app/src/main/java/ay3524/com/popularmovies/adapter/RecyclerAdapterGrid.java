package ay3524.com.popularmovies.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import ay3524.com.popularmovies.R;
import ay3524.com.popularmovies.Utils.Constants;
import ay3524.com.popularmovies.model.Movies;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 22-10-2016.
 */

public class RecyclerAdapterGrid extends RecyclerView.Adapter<RecyclerAdapterGrid.ViewHolder> {

    private ArrayList<Movies> itemsModels = null;
    private Context context;
    private ClickListener clickListener;

    public RecyclerAdapterGrid(ArrayList<Movies> itemsModels, Context context) {
        this.itemsModels = itemsModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_girdlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            String posterShortUrl = itemsModels.get(position).getPosterURL();
            String posterUrl = Constants.FINAL_POSTER_URI + posterShortUrl;
            Glide.clear(holder.image);
            Glide.with(context)
                    .load(posterUrl)
                    .placeholder(R.drawable.icon)
                    .error(R.drawable.icon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model,
                                                       Target<GlideDrawable> target,
                                                       boolean isFromMemoryCache, boolean isFirstResource) {
                            Bitmap bitmap = ((GlideBitmapDrawable) resource.getCurrent()).getBitmap();
                            Palette palette = Palette.generate(bitmap);
                            int defaultColor = 0xFF333333;
                            int color = palette.getDarkMutedColor(defaultColor);
                            holder.linearLayout.setBackgroundColor(color);
                            return false;
                        }
                    })
                    .into(holder.image);
            //Picasso.with(context).load(posterUrl).error(R.drawable.icon).placeholder(R.drawable.icon).into(holder.image);

            holder.title.setText(itemsModels.get(position).getTitle());
            List<Integer> genreIds = itemsModels.get(position).getGenreIds();
            String genreString = Movies.changeGenreToString(genreIds);
            holder.genre.setText(genreString);

            //int x = BitmapUtility.getPaletteColor(holder.image);
            //holder.linearLayout.setBackgroundColor(x);
        } catch (Exception e) {
            Log.e("RecyclerAdapterGrid", "Error while assigning values");
        }
    }

    @Override
    public int getItemCount() {
        return itemsModels == null ? 0 : itemsModels.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.movie_item_title)
        TextView title;
        @BindView(R.id.movie_item_genres)
        TextView genre;
        @BindView(R.id.movie_item_footer)
        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {

            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void onItemClick(View v, int pos);
    }
}
