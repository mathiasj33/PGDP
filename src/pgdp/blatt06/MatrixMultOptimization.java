package pgdp.blatt06;

public class MatrixMultOptimization {

    public static int f(int[][] mm) {
        return f(mm, 0, mm.length - 1);
    }

    public static int f(int[][] mm, int i, int j) {
        if (i == j)
            return 0;
        else if (i < j) {
            int min = Integer.MAX_VALUE;
            for (int x = i; x < j; x++) {
                int result = f(mm, i, x) + f(mm, x + 1, j) + mm[i][0] * mm[j][1] * mm[x][1];
                if (result < min)
                    min = result;
            }
            return min;
        }
        return -1;
    }
}
