package com.carrental;

import java.util.Random;

public class QuickSortWithMetrics {

    private static final Random rand = new Random();

    // Public entry point
    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int left, int right, Metrics metrics) {
        if (left >= right) return;

        metrics.enterRecursion();

        // Randomized pivot
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);

        int partitionIndex = partition(arr, left, right, pivot, metrics);

        // Recurse on smaller partition first
        if (partitionIndex - 1 - left < right - (partitionIndex + 1)) {
            quickSort(arr, left, partitionIndex - 1, metrics);
            quickSort(arr, partitionIndex + 1, right, metrics);
        } else {
            quickSort(arr, partitionIndex + 1, right, metrics);
            quickSort(arr, left, partitionIndex - 1, metrics);
        }

        metrics.exitRecursion();
    }

    private static int partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
        int i = left;
        for (int j = left; j < right; j++) {
            metrics.incComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}