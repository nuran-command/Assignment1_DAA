package com.carrental;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java -cp <classpath> com.carrental.Main <algorithm> <n>");
            System.out.println("Algorithms: mergesort | quicksort | select | closest");
            return;
        }

        String algo = args[0].toLowerCase();
        int n = Integer.parseInt(args[1]);
        Random rand = new Random();

        Path out = Paths.get("metrics_cli.csv");
        boolean needHeader = !Files.exists(out);

        try (FileWriter fw = new FileWriter(out.toFile(), true)) {
            if (needHeader) fw.write("Algorithm,n,Time(ms),Comparisons,MaxRecursionDepth\n");

            long start, end;
            double timeMs;
            Metrics metrics = new Metrics();

            switch (algo) {
                case "mergesort": {
                    int[] arr = rand.ints(n, -1_000_000, 1_000_000).toArray();
                    metrics.reset();
                    start = System.nanoTime();
                    MergeSortWithMetrics.sort(arr, metrics);
                    end = System.nanoTime();
                    timeMs = (end - start) / 1e6;
                    fw.write(String.format("MergeSort,%d,%.3f,%d,%d\n",
                            n, timeMs, metrics.comparisons, metrics.maxDepth));
                    break;
                }

                case "quicksort": {
                    int[] arr = rand.ints(n, -1_000_000, 1_000_000).toArray();
                    metrics.reset();
                    start = System.nanoTime();
                    QuickSortWithMetrics.sort(arr, metrics);
                    end = System.nanoTime();
                    timeMs = (end - start) / 1e6;
                    fw.write(String.format("QuickSort,%d,%.3f,%d,%d\n",
                            n, timeMs, metrics.comparisons, metrics.maxDepth));
                    break;
                }

                case "select": {
                    int[] arr = rand.ints(n, -1_000_000, 1_000_000).toArray();
                    start = System.nanoTime();
                    DeterministicSelect.select(arr, arr.length / 2);
                    end = System.nanoTime();
                    timeMs = (end - start) / 1e6;
                    fw.write(String.format("DeterministicSelect,%d,%.3f,N/A,N/A\n", n, timeMs));
                    break;
                }

                case "closest": {
                    ClosestPair.Point[] points = new ClosestPair.Point[n];
                    for (int i = 0; i < n; i++) {
                        points[i] = new ClosestPair.Point(rand.nextDouble() * 10000, rand.nextDouble() * 10000);
                    }
                    start = System.nanoTime();
                    ClosestPair.closestPair(points);
                    end = System.nanoTime();
                    timeMs = (end - start) / 1e6;
                    fw.write(String.format("ClosestPair,%d,%.3f,N/A,N/A\n", n, timeMs));
                    break;
                }

                default:
                    System.out.println("Unknown algorithm: " + algo);
                    return;
            }

            System.out.println("Done: " + algo + " n=" + n + " (appended to " + out.toAbsolutePath() + ")");
        }
    }
}