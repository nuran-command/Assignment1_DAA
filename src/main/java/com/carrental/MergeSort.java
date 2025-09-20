package com.carrental;

public class MergeSort {

    // Public entry point
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int[] buffer = new int[arr.length]; // reusable buffer
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    // Recursive mergesort with cutoff to insertion sort
    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        // Small-n cutoff: use insertion sort
        if (right - left < 16) {
            insertionSort(arr, left, right);
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);
    }

    // Insertion sort for small ranges
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Merge step with reusable buffer
    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (int t = left; t <= right; t++) {
            arr[t] = buffer[t];
        }
    }
}