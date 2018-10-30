package ay3524.com.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import ay3524.com.popularmovies.R;
import ay3524.com.popularmovies.Utils.Constants;
import ay3524.com.popularmovies.model.Casts;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 04-12-2016.
 */

public class RecyclerAdapterCasts extends RecyclerView.Adapter<RecyclerAdapterCasts.ViewHolder> {

    private List<Casts> itemsModels = null;
    private Context context;

    public RecyclerAdapterCasts(List<Casts> itemsModels, Context context) {
        this.itemsModels = itemsModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            Glide.with(context)
                    .load(Constants.FINAL_POSTER_URI.concat(itemsModels.get(position).getProfilePath()))
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
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    }).into(holder.castImage);
            /*Picasso.with(context).load(Constants.FINAL_POSTER_URI.concat(itemsModels.get(position).getProfilePath()))
                    .error(R.drawable.icon).placeholder(R.drawable.icon)
                    .into(holder.castImage);*/
        } catch (NullPointerException ignored) {

            Log.e("RecyclerAdapter", "Error while handling image");
        }

        holder.name.setText(itemsModels.get(position).getName());
        holder.title.setText(itemsModels.get(position).getCharacterName());
    }

    @Override
    public int getItemCount() {
        return itemsModels == null ? 0 : itemsModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.castImage)
        ImageView castImage;
        @BindView(R.id.castName)
        TextView name;
        @BindView(R.id.castTitle)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
