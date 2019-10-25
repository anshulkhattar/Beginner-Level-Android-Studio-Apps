package id.merahmuda.bcd.tenant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import id.merahmuda.bcd.R;
import id.merahmuda.bcd.activity.BaseActivity;

public class TambahGalleryActivity extends BaseActivity {
    ImageView imgGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_gallery);
    }

    @Override
    public void findViews() {
        imgGallery = findViewById(R.id.img_upload);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }
}
