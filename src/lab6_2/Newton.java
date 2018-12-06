package lab6_2;

/**
 * Created by ArtemBulkhak on 28.04.18.
 */
public class Newton {
    private final double eps = 0.00001;
    private double[] xPrev = {1, 1};
    private double[] g;
    private double[][] G;
    private double[] xNext;

    public Newton(){
        g = new double[]{derivFx1(xPrev), derivFx2(xPrev)};
        G = new double[][]{{derivFxx1(xPrev), derivFx1x2(xPrev)}, {derivFx1x2(xPrev), derivFxx2()}};
        xNext = sub(xPrev, mul(invertMatrix(G), g));
    }


    public double derivFx1(double[] x) {
        return 2 * x[0] - 20 * (x[1] - Math.sin(x[0])) * Math.cos(x[0]);
    }
    public double derivFx2(double[] x) {
        return 20 * (x[1] - Math.sin(x[0]));
    }
    public double derivFxx1(double[] x) {
        return 2 - 20 * (- Math.cos(x[0]) * Math.cos(x[0]) - Math.sin(x[0]) * (x[1] - Math.sin(x[0])));
    }
    public double derivFx1x2(double[] x) {
        return - 20 * Math.cos(x[0]);
    }
    public double derivFxx2() {
        return 20;
    }

    public double[][] invertMatrix(double[][] M) {
        double[][] res = new double[2][2];
        double det = M[0][0] * M[1][1] - M[0][1] * M[1][0];

        res[0][0] = M[1][1];
        res[0][1] = - M[0][1];
        res[1][0] = - M[1][0];
        res[1][1] = M[0][0];

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                res[i][j] /= det;
        return res;
    }

    public double[] mul(double[][] M, double[] v) {
        double[] res = new double[2];
        res[0] = M[0][0] * v[0] + M[0][1] * v[1];
        res[1] = M[1][0] * v[0] + M[1][1] * v[1];
        return res;
    }

    public double[] sub(double[] a, double[] b) {
        double[] res = new double[2];
        res[0] = a[0] - b[0];
        res[1] = a[1] - b[1];
        return res;
    }

    public void calcRes(){
        int count = 1;
        while (Math.max(Math.abs(xNext[0] - xPrev[0]), Math.abs(xNext[1] - xPrev[0])) > eps) {
            xPrev = xNext;
            xNext = sub(xPrev, mul(invertMatrix(G), g));

            G[0][0] = derivFxx1(xNext);
            G[1][0] = G[0][1] = derivFx1x2(xNext);
            G[1][1] = derivFxx2();

            g[0] = derivFx1(xNext);
            g[1] = derivFx2(xNext);

            count++;
        }
        System.out.println("x1 = " + Math.abs(0 - xNext[0]) + ", x2 = " + Math.abs(0 - xNext[1]));
        System.out.println("count = " + count);
    }
}
