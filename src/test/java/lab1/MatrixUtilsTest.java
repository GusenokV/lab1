package lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatrixUtilsTest {

    @Test
    void constructor_validData_storesCorrectDimensions() {
        short[][] data = {{1, 2}, {3, 4}};
        MatrixUtils m = new MatrixUtils(data);

        assertEquals(2, m.getRows());
        assertEquals(2, m.getCols());
    }

    @Test
    void constructor_validData_storesCorrectValues() {
        short[][] data = {{5, 10}, {15, 20}};
        MatrixUtils m = new MatrixUtils(data);

        assertEquals(5, m.get(0, 0));
        assertEquals(10, m.get(0, 1));
        assertEquals(15, m.get(1, 0));
        assertEquals(20, m.get(1, 1));
    }

    @Test
    void constructor_mutatingOriginalArray_doesNotAffectMatrix() {
        short[][] data = {{1, 2}, {3, 4}};
        MatrixUtils m = new MatrixUtils(data);

        data[0][0] = 99;

        assertEquals(1, m.get(0, 0));
    }


    @Test
    void constructor_emptyData_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new MatrixUtils(new short[0][0]));
    }

    @Test
    void random_validParams_returnsCorrectDimensions() {
        MatrixUtils m = MatrixUtils.random(3, 4, 0, 10);

        assertEquals(3, m.getRows());
        assertEquals(4, m.getCols());
    }

    @Test
    void random_validParams_allValuesInRange() {
        MatrixUtils m = MatrixUtils.random(10, 10, 0, 20);

        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                assertTrue(m.get(i, j) >= 0 && m.get(i, j) <= 20,
                        "Value out of range at [" + i + "][" + j + "]: " + m.get(i, j));
            }
        }
    }

    @Test
    void random_sameMinMax_allValuesEqual() {
        MatrixUtils m = MatrixUtils.random(3, 3, 7, 7);

        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                assertEquals(7, m.get(i, j));
            }
        }
    }

    @Test
    void random_zeroDimensions_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> MatrixUtils.random(0, 3, 0, 10));
        assertThrows(IllegalArgumentException.class, () -> MatrixUtils.random(3, 0, 0, 10));
    }

    @Test
    void random_minGreaterThanMax_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> MatrixUtils.random(3, 3, 10, 5));
    }

    @Test
    void add_twoMatrices_returnsCorrectSum() {
        short[][] dataA = {{1, 2}, {3, 4}};
        short[][] dataB = {{5, 6}, {7, 8}};
        MatrixUtils a = new MatrixUtils(dataA);
        MatrixUtils b = new MatrixUtils(dataB);

        MatrixUtils c = a.add(b);

        assertEquals(6,  c.get(0, 0));
        assertEquals(8,  c.get(0, 1));
        assertEquals(10, c.get(1, 0));
        assertEquals(12, c.get(1, 1));
    }

    @Test
    void add_withZeroMatrix_returnsOriginal() {
        short[][] dataA = {{3, 7}, {1, 5}};
        short[][] zeros = {{0, 0}, {0, 0}};
        MatrixUtils a = new MatrixUtils(dataA);
        MatrixUtils z = new MatrixUtils(zeros);

        MatrixUtils c = a.add(z);

        assertEquals(3, c.get(0, 0));
        assertEquals(7, c.get(0, 1));
        assertEquals(1, c.get(1, 0));
        assertEquals(5, c.get(1, 1));
    }

    @Test
    void add_returnsNewInstance_doesNotMutateOriginals() {
        short[][] dataA = {{1, 2}, {3, 4}};
        short[][] dataB = {{5, 6}, {7, 8}};
        MatrixUtils a = new MatrixUtils(dataA);
        MatrixUtils b = new MatrixUtils(dataB);

        a.add(b);

        assertEquals(1, a.get(0, 0));
        assertEquals(5, b.get(0, 0));
    }

    @Test
    void add_differentDimensions_throwsIllegalArgumentException() {
        short[][] dataA = {{1, 2, 3}};
        short[][] dataB = {{1, 2}, {3, 4}};
        MatrixUtils a = new MatrixUtils(dataA);
        MatrixUtils b = new MatrixUtils(dataB);

        assertThrows(IllegalArgumentException.class, () -> a.add(b));
    }

    @Test
    void add_nullArgument_throwsIllegalArgumentException() {
        short[][] dataA = {{1, 2}, {3, 4}};
        MatrixUtils a = new MatrixUtils(dataA);

        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    void rowAverages_uniformMatrix_returnsCorrectAverages() {
        short[][] data = {{2, 4, 6}, {1, 3, 5}};
        MatrixUtils m = new MatrixUtils(data);

        double[] averages = m.rowAverages();

        assertEquals(4.0, averages[0], 0.001);
        assertEquals(3.0, averages[1], 0.001);
    }

    @Test
    void rowAverages_singleRow_returnsAverageOfRow() {
        short[][] data = {{10, 20, 30}};
        MatrixUtils m = new MatrixUtils(data);

        double[] averages = m.rowAverages();

        assertEquals(1, averages.length);
        assertEquals(20.0, averages[0], 0.001);
    }

    @Test
    void rowAverages_singleElement_returnsItsValue() {
        short[][] data = {{42}};
        MatrixUtils m = new MatrixUtils(data);

        double[] averages = m.rowAverages();

        assertEquals(42.0, averages[0], 0.001);
    }

    @Test
    void rowAverages_returnsArrayWithLengthEqualToRows() {
        short[][] data = {{1, 2}, {3, 4}, {5, 6}};
        MatrixUtils m = new MatrixUtils(data);

        assertEquals(3, m.rowAverages().length);
    }

    @Test
    void rowAverages_nonIntegerAverage_returnsCorrectDecimal() {
        short[][] data = {{1, 2}};
        MatrixUtils m = new MatrixUtils(data);

        assertEquals(1.5, m.rowAverages()[0], 0.001);
    }

    @Test
    void get_outOfBoundsIndex_throwsArrayIndexOutOfBoundsException() {
        short[][] data = {{1, 2}, {3, 4}};
        MatrixUtils m = new MatrixUtils(data);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> m.get(5, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> m.get(0, 5));
    }
}