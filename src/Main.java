public class Main {

    public static void main(String[] args) {
	// write your code here
        // Example matrices
        float[][] A = {
                {1, 2},
                {3, 4}
        };

        float[][] B = {
                {5, 6},
                {7, 8}
        };

        int n = A.length;        // rows of A
        int p = B[0].length;     // columns of B

        Monitor monitor = new Monitor();

        Thread[] threads = new Thread[n * p];

        int id = 1;
        int index = 0;

        // Create n × p threads (one per cell)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {

                Runnable task =
                        new CellMultiplier(A, B, i, j, id++, monitor);

                threads[index] = new Thread(task);
                threads[index].start();

                index++;
            }
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("All threads finished." + e.getMessage());
            }
        }

        System.out.println("All threads finished.");
    }
}
