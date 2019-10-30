package com.demo.sortingalgorithms;



import com.demo.sortingalgorithms.sortingalgorithms.BubbleSort;
import com.demo.sortingalgorithms.sortingalgorithms.MergeSort;
import com.demo.sortingalgorithms.sortingalgorithms.QuickSort;
import com.demo.sortingalgorithms.sortingalgorithms.SortingAlgorithms;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private SortingAlgorithms method = null;
    private int[] inputValues, outputValues;

    @Before
    public void setup() {
        inputValues = new int[]{3, 52, 12, 94, 83};
    }

    @Test
    public void testBubbleSortMethod() {
        method = new BubbleSort();
        outputValues = method.sort(inputValues);
        Assert.assertTrue(94 == outputValues[4]);
    }

    @Test
    public void testMergeSortMethod() {
        method = new MergeSort();
        outputValues = method.sort(inputValues);
        Assert.assertTrue(94 == outputValues[4]);
    }

    @Test
    public void testQuickSortMethod() {
        method = new QuickSort();
        outputValues = method.sort(inputValues);
        Assert.assertTrue(94 == outputValues[4]);
    }

    @Test
    public void testConvertInputValues() {
        String values = " 3, 52, 12,94, 83,";
        outputValues = convertInputValues(values);
        Assert.assertTrue(94 == outputValues[3]);
    }

    @Test
    public void testRegexToRemoveSpaceBetweenNumbers() {
        String values = " 3, 52, 1 2,94, 8 3,";
        outputValues = convertInputValues(values);
        Assert.assertTrue(12 == outputValues[2]);
    }

    @Test
    public void testValidationDigits() {
        String values = " 3, 5S, 12,9A, 83,";
        outputValues = convertInputValues(values);
        Assert.assertTrue(83 == outputValues[4]);
    }

    @Test
    public void testSortOnlyNumbers() {
        String values = " 3, 5S, 12,9A, 83,";
        method = new QuickSort();
        inputValues = convertInputValues(values);
        outputValues = method.sort(inputValues);
        Assert.assertTrue(83 == outputValues[4]);
    }

    @Test
    public void testConvertOutputValues() {
        String values = " 3, 52, 12,94, 83,";
        method = new QuickSort();
        inputValues = convertInputValues(values);
        outputValues = method.sort(inputValues);
        String result = setFormatOutputValues(outputValues);
        Assert.assertEquals("3, 12, 52, 83, 94", result);
    }

    private int[] convertInputValues(String values) {
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

    private String setFormatOutputValues(int[] values) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            if (i < (values.length - 1))
                result.append(values[i]).append(", ");
            else
                result.append(values[i]);
        }

        return result.toString();
    }
}