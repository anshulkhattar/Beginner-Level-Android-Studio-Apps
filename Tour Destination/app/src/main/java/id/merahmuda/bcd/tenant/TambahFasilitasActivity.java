package id.merahmuda.bcd.tenant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import id.merahmuda.bcd.R;
import id.merahmuda.bcd.activity.BaseActivity;

public class TambahFasilitasActivity extends BaseActivity {
    EditText facilityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_fasilitas);
    }

    @Override
    public void findViews() {
        facilityName = findViewById(R.id.et_facility_name);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }
}
