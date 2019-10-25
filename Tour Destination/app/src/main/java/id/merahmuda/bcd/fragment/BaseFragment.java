package id.merahmuda.bcd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Ujang Wahyu on 03,Oktober,2018
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void findViews(View view);

    public abstract void initViews(View view);

    public abstract void initListeners(View view);
}
