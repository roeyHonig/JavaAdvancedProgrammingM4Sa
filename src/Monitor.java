import java.util.HashMap;
import java.util.Map;

public class Monitor {
    private int nextExpectedId = 1;
    private final Map<Integer, Float> results = new HashMap<>();

    public synchronized void reportResult(int id, float value) {

        results.put(id, value);

        // Print in order when possible
        while (results.containsKey(nextExpectedId)) {
            float result = results.remove(nextExpectedId);
            System.out.println("Result " + nextExpectedId + " = " + result);
            nextExpectedId++;
        }

        notifyAll(); // wake any waiting threads (safe even if none waiting)
    }

}
