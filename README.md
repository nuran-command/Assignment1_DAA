# Divide-and-Conquer Algorithms — Assignment 1

Author: Nurdan Z.  
Repo: https://github.com/nuran-command/https://github.com/nuran-command/Assignment1_DAA.git  
Date: 2025-09-20

## Contents
- `src/main/java/...` implementations
- `src/test/java/...` tests
- `docs/plots/` generated plots (time vs n, depth vs n)
- `results.csv` sample benchmarking results

---

## Build & run
```bash
# build
mvn clean package

# run unit tests
mvn test

# run CLI runner (example)
java -jar target/algos-1.0.jar --algo mergesort --n 10000 --trials 10 --out results.csv