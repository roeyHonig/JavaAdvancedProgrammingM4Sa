import java.util.HashMap;
import java.util.Map;

public class Monitor {
    private int nextExpectedId = 1;
    private final Map<Integer, Float> results = new HashMap<>();
    private final int columns;

    public Monitor(int columns) {
        this.columns = columns;
    }

    public synchronized void reportResult(int id, float value) {

        results.put(id, value);

        // Print in order when possible
        while (results.containsKey(nextExpectedId)) {
            float result = results.remove(nextExpectedId);
            int zeroIndex = nextExpectedId - 1;
            int row = zeroIndex / columns;
            int col = zeroIndex % columns;
            System.out.println("Result of cell in row " + (row + 1) + " and column " + (col + 1) + " = " + result);
            nextExpectedId++;
        }

        notifyAll(); // wake any waiting threads (safe even if none waiting)
    }

}
