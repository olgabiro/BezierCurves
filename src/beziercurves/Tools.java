package beziercurves;

import java.awt.Point;

public class Tools {
    public static Point[] evalConvexHull(Point[] data){
        return data;
    }
    
    public static double deCasteljau(Point[] data){
        return 0;
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
}
