package beziercurves;

import java.awt.Point;

public class Tools {
    private static int NewtonTable [][];
    
    public Tools(){
        NewtonTable = new int[70][70];
    }
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
    
    public static double horner(Point[] data, int n, double x){
        
        return 0;
    }
    
    public static int NewtonSymbol(int n, int k){
        if (NewtonTable[n][k] > 0){
            return NewtonTable[n][k];
        }
        
        return NewtonSymbol(n-1, k) + NewtonSymbol(n-1, k-1);
    }
}
