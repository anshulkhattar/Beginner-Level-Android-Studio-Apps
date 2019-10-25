package ay3524.com.popularmovies.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ay3524.com.popularmovies.R;
import ay3524.com.popularmovies.Utils.BitmapUtility;
import ay3524.com.popularmovies.model.MovieFav;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 22-10-2016.
 */

public class RecyclerAdapterFav extends RecyclerView.Adapter<RecyclerAdapterFav.ViewHolder> {

    private List<MovieFav> itemsModels = null;
    private Context context;
    private ClickListener clickListener;

    public RecyclerAdapterFav(List<MovieFav> itemsModels, Context context) {
        this.itemsModels = itemsModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_girdlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        byte[] posterImage = itemsModels.get(position).getPosterImage();
        holder.image.setImageBitmap(BitmapUtility.getImage(posterImage));
        holder.title.setText(itemsModels.get(position).getName());
        holder.genre.setText(itemsModels.get(position).getGenre());

        //int vibrant = BitmapUtility.getPaletteColor(holder.image);
        //holder.linearLayout.setBackgroundColor(vibrant);

        BitmapDrawable drawable = (BitmapDrawable) holder.image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Palette palette = Palette.generate(bitmap);
        int defaultColor = 0xFF333333;
        int color = palette.getDarkMutedColor(defaultColor);
        holder.linearLayout.setBackgroundColor(color);

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
