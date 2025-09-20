import com.carrental.DeterministicSelect;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class DeterministicSelectTest {
    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 9, 4, 1};
        assertEquals(1, DeterministicSelect.select(arr, 0)); // smallest
        assertEquals(4, DeterministicSelect.select(arr, 2)); // 3rd smallest
    }

    @Test
    void testRandom() {
        Random rnd = new Random();
        int[] arr = rnd.ints(20, 0, 100).toArray();
        int k = 5;
        int expected = Arrays.stream(arr).sorted().toArray()[k];
        assertEquals(expected, DeterministicSelect.select(arr, k));
    }
}