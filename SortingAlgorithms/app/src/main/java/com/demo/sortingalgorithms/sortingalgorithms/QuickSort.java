package com.demo.sortingalgorithms.sortingalgorithms;

public class QuickSort implements SortingAlgorithms {
    private int array[];

    @Override
    public int[] sort(int[] numbers) {
        if(numbers == null || numbers.length == 0){
            return numbers;
        }
        this.array = numbers;
        int length = numbers.length;
        quickSort(0, length - 1);
        return numbers;
    }

    private void quickSort(int lowerIndex, int higherIndex){
        int i = lowerIndex;
        int j = higherIndex;
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        while(i <= j){
            while (array[i] < pivot){
                i++;
            }
            while (array[j] > pivot){
                j--;
            }
            if(i <= j){
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }
        if(lowerIndex < j)
            quickSort(lowerIndex, j);
        if(i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
