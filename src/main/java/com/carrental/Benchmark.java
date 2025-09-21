package com.carrental;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000};
        Random rand = new Random();

        // Create CSV file
        try (FileWriter csv = new FileWriter("metrics.csv")) {
            System.out.println("CSV file path: " + new java.io.File("metrics.csv").getAbsolutePath());
            // Header
            csv.write("Algorithm,n,Time(ms),Comparisons,MaxRecursionDepth\n");

            for (int n : sizes) {
                System.out.println("=== n=" + n + " ===");

                int[] arr = rand.ints(n, -1000, 1000).toArray();

                // --- MergeSort ---
                int[] copy = Arrays.copyOf(arr, arr.length);
                Metrics metrics = new Metrics();
                long start = System.nanoTime();
                MergeSortWithMetrics.sort(copy, metrics);
                long end = System.nanoTime();
                double time = (end - start) / 1e6;
                System.out.printf("MergeSort: time=%.2f ms | %s%n", time, metrics);
                csv.write(String.format("MergeSort,%d,%.2f,%d,%d\n", n, time,
                        metrics.comparisons, metrics.maxDepth));

                // --- QuickSort ---
                copy = Arrays.copyOf(arr, arr.length);
                metrics.reset();
                start = System.nanoTime();
                QuickSortWithMetrics.sort(copy, metrics);
                end = System.nanoTime();
                time = (end - start) / 1e6;
                System.out.printf("QuickSort: time=%.2f ms | %s%n", time, metrics);
                csv.write(String.format("QuickSort,%d,%.2f,%d,%d\n", n, time,
                        metrics.comparisons, metrics.maxDepth));

                // --- Deterministic Select ---
                copy = Arrays.copyOf(arr, arr.length);
                metrics.reset();
                start = System.nanoTime();
                DeterministicSelect.select(copy, copy.length / 2);
                end = System.nanoTime();
                time = (end - start) / 1e6;
                System.out.printf("DeterministicSelect (median): time=%.2f ms%n", time);
                csv.write(String.format("DeterministicSelect,%d,%.2f,N/A,N/A\n", n, time));

                // --- Closest Pair ---
                ClosestPair.Point[] points = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++)
                    points[i] = new ClosestPair.Point(arr[i], rand.nextDouble() * 1000);

                metrics.reset();
                start = System.nanoTime();
                ClosestPair.closestPair(points);
                end = System.nanoTime();
                time = (end - start) / 1e6;
                System.out.printf("ClosestPair: time=%.2f ms%n", time);
                csv.write(String.format("ClosestPair,%d,%.2f,N/A,N/A\n", n, time));

                System.out.println();
            }

            System.out.println("✅ Results also written to metrics.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}