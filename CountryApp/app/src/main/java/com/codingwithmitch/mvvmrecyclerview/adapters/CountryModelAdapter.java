package com.codingwithmitch.mvvmrecyclerview.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codingwithmitch.mvvmrecyclerview.R;
import com.codingwithmitch.mvvmrecyclerview.models.CountryModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountryModelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CountryModel> countryModels = new ArrayList<>();
    private Context context;

    public CountryModelAdapter(Context context, List<CountryModel> cCountry) {
        countryModels = cCountry;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolderClass vh = new ViewHolderClass(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        // Set the name of the 'NicePlace'
        ((ViewHolderClass)viewHolder).cName.setText(countryModels.get(i).getcName());
        ((ViewHolderClass)viewHolder).cPresident.setText(countryModels.get(i).getcPresident());
        ((ViewHolderClass)viewHolder).cPopulation.setText(countryModels.get(i).getcPopulartion());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(countryModels.get(i).getcImageUrl())
                .into(((ViewHolderClass)viewHolder).mImage);
    }

    @Override
    public int getItemCount() {
        return countryModels.size();
    }

    private class ViewHolderClass extends RecyclerView.ViewHolder{

        private CircleImageView mImage;
        private TextView cName;
        private TextView cPresident;
        private TextView cPopulation;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            cName = itemView.findViewById(R.id.cNameTxt);
            cPresident = itemView.findViewById(R.id.presidentNameTxt);
            cPopulation = itemView.findViewById(R.id.populationTxt);
        }
    }
}
