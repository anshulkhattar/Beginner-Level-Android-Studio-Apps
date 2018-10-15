package com.example.sparsh.phone_book;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LoadImageTask extends AsyncTask<MyTaskParams, Void, Bitmap> {

    ImageButton mImageView ;

     LoadImageTask(ImageButton imageView) {

        mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(MyTaskParams... myTaskParams) {
        Bitmap bitmap = null;
        Bitmap resizedBitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(myTaskParams[0].q.getContentResolver(), myTaskParams[0].w);
            resizedBitmap = Bitmap.createScaledBitmap(bitmap, 90, 90, false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return resizedBitmap;
    }


    protected void onPostExecute(Bitmap result) {
        if (result != null && mImageView != null) {

            mImageView.setImageBitmap(result);
        }
    }
}