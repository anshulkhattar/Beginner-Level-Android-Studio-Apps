package com.demo.sortingalgorithms;

import android.os.AsyncTask;

import com.demo.sortingalgorithms.sortingalgorithms.BubbleSort;
import com.demo.sortingalgorithms.sortingalgorithms.MergeSort;
import com.demo.sortingalgorithms.sortingalgorithms.QuickSort;
import com.demo.sortingalgorithms.sortingalgorithms.SortingAlgorithms;

public class PresenterImpl implements SortingAlgorithmsMvp.Presenter {
    private SortingAlgorithmsMvp.View view;
    private SortingAlgorithms sortMethod;

    @Override
    public void setView(SortingAlgorithmsMvp.View view) {
        this.view = view;
    }

    @Override
    public void setSort(String method) {
        // Get sort method
        switch (method) {
            case "BubbleSort":
                this.sortMethod = new BubbleSort();
                break;
            case "MergeSort":
                this.sortMethod = new MergeSort();
                break;
            case "QuickSort":
                this.sortMethod = new QuickSort();
                break;
            default:
                this.sortMethod = null;
                break;
        }
    }

    @Override
    public SortingAlgorithms getSort() {
        return sortMethod;
    }

    @Override
    public void sortValues(String values) {
        // Validate parameters
        if ((view != null)) {
            if ((getSort() != null) && (values != null))
                new SortValuesTask(view).execute(values);
        }
    }

    class SortValuesTask extends AsyncTask<String, Void, String> {
        private int[] inputValues, outputValues;
        private SortingAlgorithmsMvp.View view;

        SortValuesTask(SortingAlgorithmsMvp.View view) {
            this.view = view;
        }

        int[] convertInputValues(String values) {
            String[] inputValues = values.split(",");
            int[] n = new int[inputValues.length];

            for (int i = 0; i < inputValues.length; i++) {
                String val = inputValues[i].trim();
                try {
                    if (!val.equals(""))
                        n[i] = Integer.parseInt(val);
                } catch (NumberFormatException nfe) {
                    val = val.replaceAll("(?<=\\d) +(?=\\d)", "");
                    if (val.matches("\\d+")) {
                        n[i] = Integer.parseInt(val);
                    }
                }
            }

            return n;
        }

        String setFormatOutputValues(int[] values) {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < values.length; i++) {
                if (i < (values.length - 1))
                    result.append(values[i]).append(", ");
                else
                    result.append(values[i]);
            }

            return result.toString();
        }

        @Override
        protected String doInBackground(String... values) {
            // Parse to int input values
            inputValues = convertInputValues(values[0]);
            // Get result
            outputValues = getSort().sort(inputValues);
            return setFormatOutputValues(outputValues);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Return the result
            if (result != null)
                view.showResult(result);
            else
                view.showResult("");
        }
    }
}
