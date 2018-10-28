package com.demo.sortingalgorithms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SortingAlgorithmsMvp.View {
    @BindView(R.id.rgSortMethods)
    RadioGroup rgSortMethods;
    @BindView(R.id.txtInputValues)
    EditText txtInputValues;
    @BindView(R.id.tvResult)
    TextView tvResult;
    SortingAlgorithmsMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Bind UI components
        ButterKnife.bind(this);
        // Create a presenter instance
        presenter = new PresenterImpl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        assert presenter != null;
        presenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.setView(null);
    }

    @OnClick(R.id.btnSort)
    @Override
    public void getValues() {
        try {
            // Get sort method selected
            int selectedId = rgSortMethods.getCheckedRadioButtonId();
            RadioButton radioMethod = (RadioButton) findViewById(selectedId);
            // Get input values
            String method = radioMethod.getText().toString();
            presenter.setSort(method);
            String values = txtInputValues.getText().toString();
            presenter.sortValues(values);
        } catch (Exception e) {
            Toast.makeText(this, "Introduce the request parameters with the correct format", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showResult(String result) {
        tvResult.setText("Result: " + result);
    }
}
