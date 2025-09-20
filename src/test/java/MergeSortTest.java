import com.carrental.MergeSort;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeSortTest {

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    @Test
    public void testRandomArray() {
        int[] arr = new Random().ints(1000, -1000, 1000).toArray();
        MergeSort.sort(arr);
        assertTrue(isSorted(arr));
    }

    @Test
    public void testAlreadySorted() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = i;
        MergeSort.sort(arr);
        assertTrue(isSorted(arr));
    }

    @Test
    public void testReversedArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = arr.length - i;
        MergeSort.sort(arr);
        assertTrue(isSorted(arr));
    }
}