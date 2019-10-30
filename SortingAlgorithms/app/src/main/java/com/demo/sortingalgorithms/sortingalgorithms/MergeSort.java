package com.demo.sortingalgorithms.sortingalgorithms;

public class MergeSort implements SortingAlgorithms {

    /**
     * This method sort the input array using top-down
     * merge sort algorithm.
     * @param numbers the array of integers to sort.
     * @return sorted array of integers.
     */
    @Override
    public int[] sort(int[] numbers) {
        if(numbers.length == 1){
            return numbers;
        }

        int middle = (int) Math.ceil((double)numbers.length / 2);
        int[] left = new int[middle];

        int rightLength = 0;

        if (numbers.length % 2 == 0) {
            rightLength = middle;
        } else {
            rightLength = middle - 1;
        }

        int[] right = new int[rightLength];

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = 0; i < numbers.length; i++) {
            if(i < middle) {
                left[leftIndex] = numbers[i];
                leftIndex++;
            } else {
                right[rightIndex] = numbers[i];
                rightIndex++;
            }
        }

        left = sort(left);
        right = sort(right);

        return merge(left, right);
    }

    /**
     * This method merge two integer arrays into a sorted integer array.
     * @param left first array.
     * @param right second array.
     * @return a sorted integer array.
     */
    private int[] merge(int[] left, int[] right){
        int[] result = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while(leftIndex < left.length || rightIndex < right.length){
            if(leftIndex < left.length && rightIndex < right.length){
                if(left[leftIndex] < right[rightIndex]){
                    result[resultIndex] = left[leftIndex];
                    leftIndex++;
                    resultIndex++;
                }
                else{
                    result[resultIndex] = right[rightIndex];
                    rightIndex++;
                    resultIndex++;
                }
            }
            else if(leftIndex < left.length){
                for (int i = resultIndex; i < result.length; i++) {
                    result[i] = left[leftIndex];
                    leftIndex++;
                }
            }
            else if(rightIndex < right.length){
                for (int i = resultIndex; i < result.length; i++) {
                    result[i] = right[rightIndex];
                    rightIndex++;
                }
            }
        }

        return result;
    }
}
