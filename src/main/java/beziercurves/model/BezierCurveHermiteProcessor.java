package beziercurves.model;

import static beziercurves.common.ParamValidationHelper.assertNotNull;

class BezierCurveHermiteProcessor {

    public BezierCurve decreaseDegree(final BezierCurve curve) {
        assertNotNull(curve);
        //int m = degree-2;
        //double x [] = new double [m+1];
        //double y [] = new double [m+1];
        //int k,l;
        //k = l = 1;
        //for(int i=0; i<k; i++){
        //    x[i] = 0;
        //    y[i] = points[0].y;
        //}
        //for(int i=k; i<= m-l; i++){
        //    x[i] = points[i].x;
        //    y[i] = points[i].y;
        //}
        //for(int i=m-l+1; i<=m; i++){
        //    x[i] = 1;
        //    y[i] = points[degree-1].y;
        //}
        //
        //Tools.diffQuotients(k, l, m, x, y);
        //double c [][] = new double [m+1][m+1];
        //c[0][m] = Tools.QuotientTable[m];
        //for(int j=m-1; j >= 0; j--){
        //    c[0][j] = Tools.QuotientTable[j];
        //    for(int i=1; i<=m-j; i++){
        //        double factor = (double)i / (double)(m-j);
        //        c[i][j] = (1-x[j]) * factor * c[i-1][j+1] - x[j] * (1 - factor) * c[i][j+1] + Tools.QuotientTable[j];
        //    }
        //}
        //
        //for(int i=0; i<=m; i++){
        //    points[i].x = (int)c[i][0];
        //}
        //degree--;
        return curve;
    }
}