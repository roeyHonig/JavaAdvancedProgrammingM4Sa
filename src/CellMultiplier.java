public class CellMultiplier implements Runnable {
    private final float[][] A;
    private final float[][] B;
    private final int row;
    private final int col;
    private final int id;
    private final Monitor monitor;

    public CellMultiplier(float[][] A, float[][] B,
                          int row, int col,
                          int id,
                          Monitor monitor) {
        this.A = A;
        this.B = B;
        this.row = row;
        this.col = col;
        this.id = id;
        this.monitor = monitor;
    }

    @Override
    public void run() {

        float sum = 0f;

        for (int k = 0; k < B.length; k++) {
            sum += A[row][k] * B[k][col];
        }

        monitor.reportResult(id, sum);
    }
}
