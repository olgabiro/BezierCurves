package beziercurves;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Tools {
    private static int NewtonTable [][];
    
    public Tools(){
        NewtonTable = new int[70][70];
        for (int i=0; i<70; i++){
            NewtonTable[i][0] = 1;
            NewtonTable[i][i] = 1;
        }
    }
    public static void evalConvexHull(BezierCurves c, Graphics g, boolean x){
        if(!x) {
            g.setColor(new Color(247,241,246));
        }
        else {
            g.setColor(Color.BLACK);
        }
        for (int i=1; i<c.degree; i++){
            g.drawLine(c.points[i-1].x, c.points[i-1].y, c.points[i].x, c.points[i].y);
        }
    }
    
    public static int factorial(int n){
        int value = 1;
        while(n-- > 0) {value *= n;}
        return value;
    }
    
    public static int pochhammer(int n){
        int value = 1;
        for(int i=0; i<n; i++){
            value *= n+i;
        }
        return value;
    }
    
    public static Point horner(Point[] data, int[] weight, int n, double t){
        if(n < 1){
            return null;
        }
        double factor;
        double f1;
        double wx, wy, w;
        if(t < 0.5){
            factor = t/(1-t);
            f1 = Power((1-t), n);
            wx = data[n].x;
            wy = data[n].y;
            w = weight[n];
            for(int i=n-1; i>=0; i--){
                int s = NewtonSymbol(n,i);
                wx = wx * factor + data[i].x * s;
                wy = wy * factor + data[i].y * s;
                w = w * factor + weight[i] * s;
            }
        }
        else{
            factor = (1-t)/t;
            f1 = Power(t, n);
            wx = data[0].x;
            wy = data[0].y;
            w = weight[0];
            for(int i=n-1; i>=0; i--){
                int s = NewtonSymbol(n,i);
                wx = wx * factor + data[n-i].x * s;
                wy = wy * factor + data[n-i].y * s;
                w = w * factor + weight[n-i] * s;
            }
        }
        wx = wx / w;
        wy = wy / w;
        return new Point((int) wx, (int) wy);
    }
    
    public static int NewtonSymbol(int n, int k){
        if (NewtonTable[n][k] > 0){
            return NewtonTable[n][k];
        }
        return NewtonSymbol(n-1, k) + NewtonSymbol(n-1, k-1);
    }
    
    public static double Power (double x, int n){
        double wynik = 1;
        while(n-- > 0){
            wynik *= x;
        }
        return wynik;
    }
}
