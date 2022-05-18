package beziercurves;

import java.awt.*;

public class Tools {

    private static final int[][] NEWTON_TABLE = new int[70][70];
    static final double[] QUOTIENTS_TABLE = new double[70];

    private Tools() {
    }

    static int det(final Point a,
                   final Point b) {
        return a.x * b.y - a.y * b.x;
    }

    static boolean turnsRight(final Point a,
                              final Point b,
                              final Point c) {
        final Point x = new Point(b.x - a.x,
                                  b.y - a.y);
        final Point y = new Point(c.x - a.x,
                                  c.y - a.y);
        final int n = det(x,
                          y);
        return n < 0;
    }

    public static void evalConvexHull(final BezierCurves c,
                                      final Graphics g,
                                      final boolean x) {
        if (c.degree < 2) {
            return;
        }

        if (!x) {    // clear convex hull
            g.setColor(new Color(247,
                                 241,
                                 246));
        } else {        // draw black convex hull
            g.setColor(Color.BLACK);
        }

        Point pMin = c.points[0];
        for (int i = 1; i < c.degree; i++) {
            if (c.points[i].x < pMin.x) {
                pMin = c.points[i];
            } else if (c.points[i].x == pMin.x && c.points[i].y < pMin.y) {
                pMin = c.points[i];
            }
        }

        Point p;
        Point last;
        last = pMin;
        do {
            if (c.points[0] == last) {
                p = c.points[1];
            } else {
                p = c.points[0];
            }

            for (int j = 0; j < c.degree; j++) {
                if ((last.x != c.points[j].x || last.y != c.points[j].y) && turnsRight(last,
                                                                                       p,
                                                                                       c.points[j])) {
                    p = c.points[j];
                }
            }
            g.drawLine(last.x,
                       last.y,
                       p.x,
                       p.y);
            last = p;
        } while (p != pMin);
    }

    public static int factorial(int n) {
        int value = 1;
        while (n-- > 0) {
            value *= n;
        }
        return value;
    }

    public static int pochhammer(final int n) {
        int value = 1;
        for (int i = 0; i < n; i++) {
            value *= n + i;
        }
        return value;
    }

    public static Point horner(final Point[] data,
                               final int[] weight,
                               final int n,
                               final double t) {
        if (n < 1) {
            return null;
        }
        final double factor;
        double wx;
        double wy;
        double w;
        if (t < 0.5) {
            factor = t / (1 - t);
            wx = data[n].x;
            wy = data[n].y;
            w = weight[n];
            for (int i = n - 1; i >= 0; i--) {
                final int s = newtonSymbol(n,
                                           i);
                wx = wx * factor + data[i].x * s;
                wy = wy * factor + data[i].y * s;
                w = w * factor + weight[i] * s;
            }
        } else {
            factor = (1 - t) / t;
            wx = data[0].x;
            wy = data[0].y;
            w = weight[0];
            for (int i = n - 1; i >= 0; i--) {
                final int s = newtonSymbol(n,
                                           i);
                wx = wx * factor + data[n - i].x * s;
                wy = wy * factor + data[n - i].y * s;
                w = w * factor + weight[n - i] * s;
            }
        }
        wx /= w;
        wy /= w;
        return new Point((int) wx,
                         (int) wy);
    }

    public static int newtonSymbol(final int n,
                                   final int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        if (NEWTON_TABLE[n][k] > 0) {
            return NEWTON_TABLE[n][k];
        }
        return newtonSymbol(n - 1,
                            k) + newtonSymbol(n - 1,
                                              k - 1);
    }

    public static double power(final double x,
                               int n) {
        double wynik = 1;
        while (n-- > 0) {
            wynik *= x;
        }
        return wynik;
    }

    public static int abs(final int x) {
        if (x < 0) {
            return -x;
        }
        return x;
    }

    public static int findPoint(final Point x,
                                final BezierCurves c) {
        for (int i = 0; i < c.degree; i++) {
            if (abs(x.x - c.points[i].x) <= 5 && abs(x.y - c.points[i].y) <= 5) {
                return i;
            }
        }
        return c.degree;
    }

    public static void diffQuotients(final int k,
                                     final int l,
                                     final int m,
                                     final double[] x,
                                     final double[] y) {
        final double[][] f = new double[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            f[i][i] = y[i];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= m - i; j++) {
                f[j][j + i] = f[j + 1][i] - f[j][i - 1];
                f[j][j + i] /= (x[j + i] - x[j]);
            }
        }

        for (int i = 0; i <= m; i++) {
            QUOTIENTS_TABLE[i] = f[0][i];
        }
    }
}
