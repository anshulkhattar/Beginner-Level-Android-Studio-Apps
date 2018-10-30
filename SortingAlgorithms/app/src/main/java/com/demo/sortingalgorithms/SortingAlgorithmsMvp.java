package com.demo.sortingalgorithms;

import com.demo.sortingalgorithms.sortingalgorithms.SortingAlgorithms;

public interface SortingAlgorithmsMvp {
    interface Presenter {
        void setView(SortingAlgorithmsMvp.View view);
        void setSort(String method);
        SortingAlgorithms getSort();
        void sortValues(String values);
    }

    interface View {
        void getValues();
        void showResult(String result);
    }
}
