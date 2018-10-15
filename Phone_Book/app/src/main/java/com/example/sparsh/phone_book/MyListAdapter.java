package com.example.sparsh.phone_book;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MyListAdapter extends ArrayAdapter<String> {

    int REQUEST_CALL=1;
    Activity activity;
    DbHelper obj = new DbHelper(getContext());
    FloatingActionButton fab,fab1,fab2,fab3;
    LayoutInflater inflater;
    ImageButton button;
    TextView t,t2;
    String str[]={};
    int resource;
    Context context;
    Cursor cursor;
    String names[];
    String phone[];
    String images[];
    Uri u;


    public MyListAdapter(@NonNull Context context, String names[], String phone[],String images[], FloatingActionButton fab, Activity activity, FloatingActionButton fab1, FloatingActionButton fab2,FloatingActionButton fab3) {
        super(context, R.layout.imageswithtext, R.id.textv, names);
        this.resource=resource;
        this.context=context;
        this.names = names;
        this.phone=phone;
        this.fab = fab;
        this.activity=activity;
        this.fab1 = fab1;
        this.fab2 = fab2;
        this.images= images;
        this.fab3=fab3;
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent)
    {

        View view = convertView;
        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.imageswithtext,parent,false);
        }

        t2 = view.findViewById(R.id.textv2);
        t = view.findViewById(R.id.textv);
        button = view.findViewById(R.id.bt1);

        t.setText(names[position]);
        t2.setText(phone[position]);

            if (images[position] != null)
            {
                u = Uri.parse(images[position]);
                new LoadImageTask(button).execute(new MyTaskParams(context,u));
            }

            button.setOnTouchListener(new View.OnTouchListener()
            {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    ClipData data = ClipData.newPlainText("hello", "this");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);

                    fab.setVisibility(View.VISIBLE);

                    fab.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                int i = dragEvent.getAction();
                switch (i) {
                    case DragEvent.ACTION_DROP: {
                        int j = obj.delete(names[position]);
                        Toast.makeText(getContext(), j + " contact deleted", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(getContext(), MainActivity.class));
                        activity.finish();
                        break;
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {
                        fab.setVisibility(View.INVISIBLE);
                    }
                }

                return true;
            }
        });

        fab1.setVisibility(View.VISIBLE);
        fab1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                int i = dragEvent.getAction();
                switch (i) {
                    case DragEvent.ACTION_DROP: {
                        ((MainActivity) activity).dial(phone[position]);
                        fab1.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {
                        fab1.setVisibility(View.INVISIBLE);
                    }

                }

                return true;
            }
        });

                    fab3.setVisibility(View.VISIBLE);
                    fab3.setOnDragListener(new View.OnDragListener() {
                        @Override
                        public boolean onDrag(View view, DragEvent dragEvent) {
                            int i = dragEvent.getAction();
                            switch (i) {
                                case DragEvent.ACTION_DROP: {
                                    ((MainActivity) activity).textMessage(phone[position]);
                                    fab3.setVisibility(View.INVISIBLE);
                                    break;
                                }
                                case DragEvent.ACTION_DRAG_ENDED: {
                                    fab3.setVisibility(View.INVISIBLE);
                                }

                            }

                            return true;
                        }
                    });

        return true;
    }
});

        return view;
    }

}



