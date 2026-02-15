import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Read matrix sizes
        System.out.print("Enter rows of matrix A: ");
        int rowsA = scanner.nextInt();

        System.out.print("Enter columns of matrix A: ");
        int colsA = scanner.nextInt();

        System.out.print("Enter rows of matrix B: ");
        int rowsB = scanner.nextInt();

        System.out.print("Enter columns of matrix B: ");
        int colsB = scanner.nextInt();

        // Create matrices
        float[][] A = new float[rowsA][colsA];
        float[][] B = new float[rowsB][colsB];

        // Fill matrix A with random floats between 0 and 10
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                A[i][j] = random.nextFloat() * 10.0f;
            }
        }

        // Fill matrix B with random floats between 0 and 10
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                B[i][j] = random.nextFloat() * 10.0f;
            }
        }

        printMatrix("Matrix A", A);
        printMatrix("Matrix B", B);

        int n = rowsA;     // rows of result
        int p = colsB;     // columns of result

        Monitor monitor = new Monitor(p);

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

    private static void printMatrix(String name, float[][] matrix) {
        System.out.println(name + ":");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.2f ", matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }

}
