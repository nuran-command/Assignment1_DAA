package com.carrental;

import java.util.Arrays;
import java.util.Random;

public class Benchmark {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000}; // You can adjust sizes
        Random rand = new Random();

        for (int n : sizes) {
            System.out.println("=== n=" + n + " ===");

            // Generate random array for sorting
            int[] arr = rand.ints(n, -1000, 1000).toArray();

            // --- MergeSort ---
            int[] copy = Arrays.copyOf(arr, arr.length);
            Metrics metrics = new Metrics();
            long start = System.nanoTime();
            MergeSortWithMetrics.sort(copy, metrics); // Use your instrumented MergeSort
            long end = System.nanoTime();
            System.out.printf("MergeSort: time=%.2f ms | %s%n", (end - start)/1e6, metrics);

            // --- QuickSort ---
            copy = Arrays.copyOf(arr, arr.length);
            metrics.reset();
            start = System.nanoTime();
            QuickSortWithMetrics.sort(copy, metrics); // You can create QuickSortWithMetrics like MergeSort
            end = System.nanoTime();
            System.out.printf("QuickSort: time=%.2f ms | %s%n", (end - start)/1e6, metrics);

            // --- Deterministic Select (median) ---
            copy = Arrays.copyOf(arr, arr.length);
            metrics.reset();
            start = System.nanoTime();
            DeterministicSelect.select(copy, copy.length / 2); // median
            end = System.nanoTime();
            System.out.printf("DeterministicSelect (median): time=%.2f ms%n", (end - start)/1e6);

            // --- Closest Pair ---
            ClosestPair.Point[] points = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) points[i] = new ClosestPair.Point(arr[i], rand.nextDouble()*1000);
            metrics.reset();
            start = System.nanoTime();
            ClosestPair.closestPair(points);
            end = System.nanoTime();
            System.out.printf("ClosestPair: time=%.2f ms%n", (end - start)/1e6);

            System.out.println();
        }
    }
}