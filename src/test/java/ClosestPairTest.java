import com.carrental.ClosestPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {
    @Test
    void testSimple() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(3,4),
                new ClosestPair.Point(1,1)
        };
        double d = ClosestPair.closestPair(pts);
        assertEquals(Math.sqrt(2), d, 1e-6);
    }
}