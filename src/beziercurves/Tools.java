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
	
	static int det(Point a, Point b){
		return a.x * b.y - a.y * b.x;
	}
	
	static boolean turnsRight(Point a, Point b, Point c){
		Point x = new Point(b.x - a.x, b.y - a.y);
		Point y = new Point(c.x - a.x, c.y - a.y);
		int n = det(x,y);
		if (n < 0) {
			return true;
		}
		return false;
	}
	
    public static void evalConvexHull(BezierCurves c, Graphics g, boolean x){
		if(c.degree < 2) {return;}
		
		if(!x) {	// clear convex hull
            g.setColor(new Color(247,241,246));
        }
        else {		// draw black convex hull
            g.setColor(Color.BLACK);
        }
		
		Point pMin = c.points[0];
		for(int i=1; i<c.degree; i++){
			if(c.points[i].x < pMin.x){ pMin = c.points[i]; }
			else if(c.points[i].x == pMin.x && c.points[i].y < pMin.y) {pMin = c.points[i];}
		}
		
		Point p, last;
		last = pMin;
		do {
				if(c.points[0] == last){
					p = c.points[1];
				}
				else {p = c.points[0];}
				
				for(int j=0; j < c.degree; j++){
					if((last.x != c.points[j].x || last.y != c.points[j].y) && turnsRight(last, p, c.points[j])){
						p = c.points[j];
					}
				}
				g.drawLine(last.x, last.y, p.x, p.y);
				last = p;
		} while(p != pMin);
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
        double wx, wy, w;
        if(t < 0.5){
            factor = t/(1-t);
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
		wx /= w;
		wy /= w;
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
	
	public static int abs (int x){
		if(x < 0) {return -x;}
		return x;
	}
	
	public static int findPoint(Point x, BezierCurves c){
		for(int i=0; i<c.degree; i++){
			if(abs(x.x - c.points[i].x) <= 5 && abs(x.y - c.points[i].y) <= 5) {
				return i;}
		}
		return c.degree;
	}
}
