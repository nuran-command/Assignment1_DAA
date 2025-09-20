package com.carrental;

public class Metrics {
    public long comparisons = 0;   // Only used where instrumented
    public int maxDepth = 0;       // For recursive algorithms
    private int currentDepth = 0;

    public void incComparisons() { comparisons++; }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exitRecursion() { currentDepth--; }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
        currentDepth = 0;
    }

    @Override
    public String toString() {
        return "comparisons=" + comparisons + ", maxDepth=" + maxDepth;
    }
}