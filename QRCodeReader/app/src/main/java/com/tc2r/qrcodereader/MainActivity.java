package com.tc2r.qrcodereader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnScan;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnScan = (Button) findViewById(R.id.btnScan);
        txtResult = (TextView) findViewById(R.id.txtResult);

        final Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().
                getResources(), R.drawable.qrcode);
        imageView.setImageBitmap(myBitmap);

        btnScan.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

                Frame frame = new Frame.Builder()
                        .setBitmap(myBitmap)
                        .build();
                SparseArray<Barcode> barsCode = detector.detect(frame);

                Barcode result = barsCode.valueAt(0);
                txtResult.setText(result.rawValue);
            }
        });
    }
}
