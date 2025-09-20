package com.carrental;

public class MergeSortWithMetrics {

    // Public entry point
    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;
        int[] buffer = new int[arr.length]; // reusable buffer
        mergeSort(arr, buffer, 0, arr.length - 1, metrics);
    }

    // Recursive mergesort with cutoff to insertion sort
    private static void mergeSort(int[] arr, int[] buffer, int left, int right, Metrics metrics) {
        metrics.enterRecursion();

        if (right - left < 16) {
            insertionSort(arr, left, right, metrics);
            metrics.exitRecursion();
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(arr, buffer, left, mid, metrics);
        mergeSort(arr, buffer, mid + 1, right, metrics);
        merge(arr, buffer, left, mid, right, metrics);

        metrics.exitRecursion();
    }

    // Insertion sort for small ranges
    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    // Merge step with reusable buffer
    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.incComparisons();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (int t = left; t <= right; t++) arr[t] = buffer[t];
    }
}