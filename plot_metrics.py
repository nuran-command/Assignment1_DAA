import pandas as pd
import matplotlib.pyplot as plt

# Load your metrics.csv
df = pd.read_csv("metrics.csv")

# --- Time vs n ---
plt.figure(figsize=(8,5))
for algo in df['Algorithm'].unique():
    sub = df[df['Algorithm'] == algo]
    plt.plot(sub['n'], sub['Time(ms)'], marker='o', label=algo)
plt.xlabel("n")
plt.ylabel("Time (ms)")
plt.title("Time vs n")
plt.legend()
plt.grid(True)
plt.show()

# --- Comparisons vs n ---
plt.figure(figsize=(8,5))
for algo in ['MergeSort', 'QuickSort']:  # only these have comparisons
    sub = df[df['Algorithm'] == algo]
    plt.plot(sub['n'], sub['Comparisons'], marker='o', label=algo)
plt.xlabel("n")
plt.ylabel("Comparisons")
plt.title("Comparisons vs n")
plt.legend()
plt.grid(True)
plt.show()

# --- MaxRecursionDepth vs n ---
plt.figure(figsize=(8,5))
for algo in ['MergeSort', 'QuickSort']:  # only sorting algos have depth
    sub = df[df['Algorithm'] == algo]
    plt.plot(sub['n'], sub['MaxRecursionDepth'], marker='o', label=algo)
plt.xlabel("n")
plt.ylabel("Max Recursion Depth")
plt.title("Recursion Depth vs n")
plt.legend()
plt.grid(True)
plt.show()