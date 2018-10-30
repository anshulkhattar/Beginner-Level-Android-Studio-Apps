package id.merahmuda.bcd.tenant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import id.merahmuda.bcd.R;
import id.merahmuda.bcd.activity.BaseActivity;

public class TambahEventActivity extends BaseActivity {
    EditText namaEvent, kategoriEvent, waktuEvent, deskripsiEvent;
    ImageView imgEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_event);

    }

    @Override
    public void findViews() {
        imgEvent = findViewById(R.id.img_upload);
        namaEvent = findViewById(R.id.et_nama_event);
        kategoriEvent = findViewById(R.id.et_kategori_event);
        waktuEvent = findViewById(R.id.et_waktu_event);
        deskripsiEvent = findViewById(R.id.et_deskripsi_event);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }
}
