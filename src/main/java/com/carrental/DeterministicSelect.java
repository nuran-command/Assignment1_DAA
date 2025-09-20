package com.carrental;

import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] arr, int k) {
        if (arr.length == 1) return arr[0];

        // Split into groups of 5, find medians
        int n = arr.length;
        int numGroups = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int start = i * 5;
            int end = Math.min(start + 5, n);
            int[] group = Arrays.copyOfRange(arr, start, end);
            Arrays.sort(group);
            medians[i] = group[group.length / 2];
        }

        // Median of medians
        int pivot = (numGroups == 1) ? medians[0] : select(medians, medians.length / 2);

        // Partition
        int[] left = Arrays.stream(arr).filter(x -> x < pivot).toArray();
        int[] equal = Arrays.stream(arr).filter(x -> x == pivot).toArray();
        int[] right = Arrays.stream(arr).filter(x -> x > pivot).toArray();

        if (k < left.length) return select(left, k);
        else if (k < left.length + equal.length) return pivot;
        else return select(right, k - left.length - equal.length);
    }
}