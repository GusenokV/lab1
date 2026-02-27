package lab1;
import java.util.Random;
public class MatrixUtils {

    private final short[][] data;
    private final int rows;
    private final int cols;

    public MatrixUtils(short[][] data) {
        if (data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Matrix data cannot be null or empty.");
        }
        rows = data.length;
        cols = data[0].length;
        this.data = new short[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    public static MatrixUtils random(int rows, int cols, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max.");
        }

        Random random = new Random();
        short[][] data = new short[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = (short) (random.nextInt(max - min + 1) + min);
            }
        }
        return new MatrixUtils(data);
    }

    public MatrixUtils add(MatrixUtils other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix to add cannot be null.");
        }
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException(
                "Matrix dimensions do not match: "
                + this.rows + "x" + this.cols
                + " and " + other.rows + "x" + other.cols
            );
        }

        short[][] result = new short[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = (short) (this.data[i][j] + other.data[i][j]);
            }
        }
        return new MatrixUtils(result);
    }

    public double[] rowAverages() {
        double[] averages = new double[rows];
        for (int i = 0; i < rows; i++) {
            long sum = 0;
            for (short val : data[i]) {
                sum += val;
            }
            averages[i] = (double) sum / cols;
        }
        return averages;
    }

    public void print() {
        for (short[] row : data) {
            StringBuilder sb = new StringBuilder("  [ ");
            for (int j = 0; j < row.length; j++) {
                sb.append(String.format("%4d", row[j]));
                if (j < row.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");
            System.out.println(sb.toString());
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public short get(int row, int col) {
        return data[row][col];
    }
}
