package lab1;
public class Matrix {

    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final int RAND_MIN = 0;
    private static final int RAND_MAX = 20;

    public static void main(String[] args) {
        try {
            MatrixUtils a = MatrixUtils.random(ROWS, COLS, RAND_MIN, RAND_MAX);
            MatrixUtils b = MatrixUtils.random(ROWS, COLS, RAND_MIN, RAND_MAX);

            System.out.println("Matrix A:");
            a.print();

            System.out.println("\nMatrix B:");
            b.print();

            MatrixUtils c = a.add(b);

            System.out.println("\nMatrix C = A + B:");
            c.print();

            System.out.println("\nAverage value of each row in matrix C:");
            double[] averages = c.rowAverages();
            for (int i = 0; i < averages.length; i++) {
                System.out.printf("  Row %d: %.2f%n", i, averages[i]);
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Arithmetic error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
