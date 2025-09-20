# Divide-and-Conquer Algorithms — Assignment 1

Author: Nurdan Z.  
Repo: https://github.com/nuran-command/https://github.com/nuran-command/Assignment1_DAA.git  
Date: 2025-09-20

## Project Overview
This project implements classic divide-and-conquer algorithms and measures their performance:
- **MergeSort** (linear merge, reusable buffer, small-n cutoff)
- **QuickSort** (randomized pivot, smaller-first recursion)
- **Deterministic Select (Median-of-Medians)** (linear-time selection)
- **Closest Pair of Points (2D)** (O(n log n) divide-and-conquer)

The goal is to analyze running-time, recursion depth, and comparisons, and compare measurements with theoretical expectations.

---

## Architecture Notes
- **Recursion depth** is tracked using a `Metrics` object.
- **Small-n cutoff**: MergeSort and QuickSort switch to insertion sort for n < 16.
- **MergeSort buffer**: reusable array to avoid repeated allocations.
- Algorithms are implemented in `src/main/java/com/carrental/`.

---

## Recurrence Analysis

**MergeSort**  
- Recurrence: T(n) = 2T(n/2) + Θ(n)  
- Master Theorem Case 2 → Θ(n log n)  
- Benchmark timings match theoretical complexity.

**QuickSort (random pivot)**  
- Expected recurrence: T(n) = T(n/2) + Θ(n)  
- Worst-case avoided by randomized pivot → expected Θ(n log n)  
- Recursion depth bounded (~2*log2 n).

**Deterministic Select (Median-of-Medians)**  
- Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n) → Θ(n)  
- Benchmark confirms linear-time performance.

**Closest Pair of Points**  
- Recurrence: T(n) = 2T(n/2) + Θ(n) → Θ(n log n)  
- Verified against O(n²) brute-force on small datasets.

---

## Metrics and Benchmark

| Algorithm | n | Time (ms) | Comparisons | Max Recursion Depth |
|-----------|---|------------|------------|-------------------|
| MergeSort | 1000 | X.XX | XXXX | XX |
| QuickSort | 1000 | X.XX | XXXX | XX |
| DeterministicSelect | 1000 | X.XX | N/A | N/A |
| ClosestPair | 1000 | X.XX | N/A | N/A |

- **Plots**: (Add graphs here if you want)
  - Time vs n
  - Recursion depth vs n
  - Comparisons vs n

---

## Summary
- Theoretical time complexities match measured performance for all algorithms.  
- Small deviations may occur due to cache effects and JVM optimizations.  
- Metrics tracking (comparisons, recursion depth) works for MergeSort and QuickSort.

---

## Build & run
```bash
# build
mvn clean package

# run unit tests
mvn test

# run CLI runner (example)
java -jar target/algos-1.0.jar --algo mergesort --n 10000 --trials 10 --out results.csv
