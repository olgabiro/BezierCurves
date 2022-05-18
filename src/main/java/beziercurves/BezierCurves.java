package beziercurves;

import java.awt.*;
import java.util.Arrays;

public class BezierCurves {

    Point[] points;
    int[] weight;
    int degree;
    Color colour;

    public BezierCurves() {
        this.points = new Point[70];
        this.weight = new int[70];
        this.degree = 0;
        this.colour = Color.magenta;
    }

    public void elevateDegree(final Graphics g) {
        // clearing the screen
        g.setColor(new Color(247,
                             241,
                             246));
        for (int i = 0; i < this.degree; i++) {
            g.drawOval(this.points[i].x,
                       this.points[i].y,
                       3,
                       3);
        }
        drawCurve(g);

        //creating new points
        final Point[] pom = new Point[this.degree + 1];
        pom[0] = this.points[0];
        for (int k = 1; k < this.degree; k++) {
            final double factor = (double) k / (double) this.degree;
            final double x = (factor * this.points[k - 1].x) + (1 - factor) * this.points[k].x;
            final double y = (factor * this.points[k - 1].y) + (1 - factor) * this.points[k].y;
            pom[k] = new Point((int) x,
                               (int) y);
        }
        pom[this.degree] = new Point(this.points[this.degree - 1].x,
                                     this.points[this.degree - 1].y);
        
        this.points = Arrays.copyOf(pom,
                                    this.degree + 1);
        this.degree++;
        this.weight[this.degree - 1] = 1;

        // drawing the curve
        g.setColor(this.colour);
        for (int i = 0; i < this.degree; i++) {
            g.drawOval(this.points[i].x,
                       this.points[i].y,
                       3,
                       3);
        }
        drawCurve(g);
    }

    public void lowerDegree(final Graphics g,
                            final int method) {
        if (this.degree < 2) {
            return;
        }
        // clearing the screen
        g.setColor(new Color(247,
                             241,
                             246));
        for (int i = 0; i < this.degree; i++) {
            g.drawOval(this.points[i].x,
                       this.points[i].y,
                       3,
                       3);
        }
        drawCurve(g);

        switch (method) {
            case 1 -> deelevateStyle();
            case 2 -> approxStyle();
            case 3 -> hermiteStyle();
            default -> throw new IllegalStateException("Unexpected value: " + method);
        }

        // drawing the curve
        g.setColor(this.colour);
        for (int i = 0; i < this.degree; i++) {
            g.drawOval(this.points[i].x,
                       this.points[i].y,
                       3,
                       3);
        }
        drawCurve(g);

    }

    public void drawCurve(final Graphics g) {
        for (double i = 0; i <= 1; i += 0.0015) {
            final Point p = Tools.horner(this.points,
                                         this.weight,
                                         this.degree - 1,
                                         i);
            if (p != null) {
                g.drawLine(p.x,
                           p.y,
                           p.x,
                           p.y);
            }
        }
    }

    public void deelevateStyle() {
        final double[] wx = new double[this.degree];
        final double[] wy = new double[this.degree];

        final int n = this.degree - 1;
        wx[0] = this.points[0].x;
        wy[0] = this.points[0].y;
        for (int i = 1; i <= n / 2; i++) {
            final double factor = (double) i / (double) (n - i);
            wx[i] = (1 + factor) * this.points[i].x - factor * wx[i - 1];
            wy[i] = (1 + factor) * this.points[i].y - factor * wy[i - 1];
        }
        wx[n] = wy[n] = 0;
        for (int i = n; i > n / 2; i--) {
            final double factor = (double) n / (double) i;
            wx[i - 1] = factor * this.points[i].x + (1 - factor) * wx[i];
            wy[i - 1] = factor * this.points[i].y + (1 - factor) * wy[i];
        }
        final double factor = n / ((double) n / 2 + 1);
        double middle = factor * this.points[n / 2 + 1].x + (1 - factor) * wx[(n + 1) / 2];
        wx[n / 2] = (middle + wx[n / 2]) / 2;
        middle = factor * this.points[n / 2 + 1].y + (1 - factor) * wy[n / 2 + 1];
        wy[n / 2] = (middle + wy[n / 2]) / 2;

        for (int i = 0; i < n; i++) {
            this.points[i] = new Point((int) wx[i],
                                       (int) wy[i]);
        }
        this.degree--;
    }

    public void approxStyle() {
        double alfax;
        double alfay;
        alfax = alfay = 0;
        final int n = this.degree - 1;
        final double[] betax = new double[this.degree];
        final double[] betay = new double[this.degree];
        final double f1 = Tools.power(2,
                                      2 * n - 1);

        for (int i = 0; i <= n; i++) {
            final int f = Tools.newtonSymbol(n,
                                             i);
            if ((i + n) % 2 != 0) {
                alfax -= this.points[i].x * f;
                alfay -= this.points[i].y * f;
            } else {
                alfax += this.points[i].x * f;
                alfay += this.points[i].y * f;
            }
        }
        alfax /= f1;
        alfay /= f1;

        for (int i = 0; i <= n; i++) {
            final int f2 = Tools.newtonSymbol(2 * n,
                                              2 * i) / Tools.newtonSymbol(n,
                                                                          i);
            final double f = Tools.power(2,
                                         2 * n - 1);
            betax[i] = this.points[i].x * alfax * f2 / f;
            betay[i] = this.points[i].y * alfay * f2 / f;
            if (((n + i) % 2) != 0) {
                betax[i] *= (-1);
                betay[i] *= (-1);
            }
        }

        final double[] w = new double[this.degree];
        final double[] wx = new double[this.degree];
        final double[] z = new double[this.degree];
        final double[] wy = new double[this.degree];

        for (int i = 0; i < this.degree; i++) {
            w[i] = this.points[i].x - betax[i];
            z[i] = this.points[i].y - betay[i];
        }

        wx[0] = w[0];
        wy[0] = z[0];
        for (int k = 1; k < n; k++) {
            final double factor = (double) k / (double) (n - k);
            wx[k] = (1 + factor) * w[k] - factor * wx[k - 1];
            wy[k] = (1 + factor) * z[k] - factor * wy[k - 1];
        }

        for (int i = 0; i < n; i++) {
            this.points[i] = new Point((int) wx[i],
                                       (int) wy[i]);
        }
        this.degree--;
    }

    public void hermiteStyle() {

        // wciąż (trochę) się rozjeżdża
        final int m = this.degree - 2;
        final double[] x = new double[m + 1];
        final double[] y = new double[m + 1];
        final int k;
        final int l;
        k = l = 1;
        for (int i = 0; i < k; i++) {
            x[i] = 0;
            y[i] = this.points[0].y;
        }
        for (int i = k; i <= m - l; i++) {
            x[i] = this.points[i].x;
            y[i] = this.points[i].y;
        }
        for (int i = m - l + 1; i <= m; i++) {
            x[i] = 1;
            y[i] = this.points[this.degree - 1].y;
        }

        Tools.diffQuotients(k,
                            l,
                            m,
                            x,
                            y);
        final double[][] c = new double[m + 1][m + 1];
        c[0][m] = Tools.QUOTIENTS_TABLE[m];
        for (int j = m - 1; j >= 0; j--) {
            c[0][j] = Tools.QUOTIENTS_TABLE[j];
            for (int i = 1; i <= m - j; i++) {
                final double factor = (double) i / (double) (m - j);
                c[i][j] = (1 - x[j]) * factor * c[i - 1][j + 1] - x[j] * (1 - factor) * c[i][j + 1]
                          + Tools.QUOTIENTS_TABLE[j];
            }
        }

        for (int i = 0; i <= m; i++) {
            this.points[i].x = (int) c[i][0];
        }
        this.degree--;
    }

    public static void main(final String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (final javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName())
                                    .log(java.util.logging.Level.SEVERE,
                                         null,
                                         ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Application().setVisible(true));
    }
}
