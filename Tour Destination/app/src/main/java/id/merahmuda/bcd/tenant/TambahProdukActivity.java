package id.merahmuda.bcd.tenant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import id.merahmuda.bcd.R;
import id.merahmuda.bcd.activity.BaseActivity;

public class TambahProdukActivity extends BaseActivity {
    ImageView imgProduct;
    EditText productName, productPrice, productDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);
    }

    @Override
    public void findViews() {
        imgProduct = findViewById(R.id.img_upload);
        productName = findViewById(R.id.et_product_name);
        productPrice = findViewById(R.id.et_product_price);
        productDescription = findViewById(R.id.et_product_description);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }
}
