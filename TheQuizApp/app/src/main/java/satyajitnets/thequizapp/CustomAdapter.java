package satyajitnets.thequizapp;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{
    Typeface fn;

    private ArrayList<DataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtQue;
        ImageView select;
        TextView txtAns;
        TextView txtEntr;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.res_list, data);
        this.dataSet = data;
        this.mContext=context;
        fn = Typeface.createFromAsset(context.getAssets(), "fonts/fnt.ttf");

    }

    @Override
    public void onClick(View v) {




        switch (v.getId())
        {

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.res_list, parent, false);

            viewHolder.txtQue =  convertView.findViewById(R.id.Que);

            viewHolder.txtAns =  convertView.findViewById(R.id.ans);

            viewHolder.txtEntr =  convertView.findViewById(R.id.entr);

viewHolder.select=convertView.findViewById(R.id.imageV);



            viewHolder.txtAns.setTypeface(fn);
            viewHolder.txtQue.setTypeface(fn);
            viewHolder.txtEntr.setTypeface(fn);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.anim_translate_out : R.anim.anim_translate_in);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtQue.setText(dataModel.getQ());

        viewHolder.txtAns.setText("Answer : "+dataModel.getAns());
        viewHolder.txtEntr.setText("Your Response : "+dataModel.getEntrd());



        if((dataModel.getAns()).contains(dataModel.getEntrd())){
            viewHolder.txtEntr.setTextColor(mContext.getResources().getColor(R.color.green));
        viewHolder.select.setBackgroundResource(R.drawable.tick);
        }
else {
            viewHolder.txtEntr.setTextColor(mContext.getResources().getColor(R.color.red));
            viewHolder.select.setBackgroundResource(R.drawable.cross); }


        // Return the completed view to render on screen
        return convertView;
    }
}
