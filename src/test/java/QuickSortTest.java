import com.carrental.QuickSort;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};
        QuickSort.sort(arr);
        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}