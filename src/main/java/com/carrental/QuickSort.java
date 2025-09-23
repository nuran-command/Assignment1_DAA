package com.carrental;

import com.carrental.Utils;

public class QuickSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr) {
        // Optional: randomize array to avoid worst-case pivot splits
        Utils.shuffle(arr);
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (high - low < CUTOFF) {
            insertionSort(arr, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = Utils.partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}