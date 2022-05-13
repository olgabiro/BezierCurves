package beziercurves;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class BezierCurves {
    Point points[];
    int weight[];
    int degree;
    Color colour;
    
    public BezierCurves(){
        points = new Point[70];
        weight = new int[70];
        degree = 0;
        colour = Color.magenta;
    }
    
    public void elevateDegree(Graphics g){
        // clearing the screen
        g.setColor(new Color(247,241,246));
        for(int i=0; i<degree; i++){
            g.drawOval(points[i].x, points[i].y, 3, 3);
        }
        drawCurve(g);
        
        //creating new points
        Point pom[] = new Point[degree+1];
        pom[0] = points[0];
        for(int k=1; k<degree; k++){
            double factor = (double)k / (double)degree;
            double x = (factor * points[k-1].x) + (1 - factor) * points[k].x;
            double y = (factor * points[k-1].y) + (1 - factor) * points[k].y;
            pom[k] = new Point((int)x,(int)y);
        }
        pom[degree] = new Point(points[degree-1].x, points[degree-1].y);
        
        for(int i=0; i<=degree; i++){
            points[i] = pom[i];
        }
        degree++;
		weight[degree-1] = 1;
        
        // drawing the curve
        g.setColor(colour);
        for(int i=0; i<degree; i++){
            g.drawOval(points[i].x, points[i].y, 3, 3);
        }
        drawCurve(g);
    }
    
    public void lowerDegree(Graphics g, int method){
		if(degree < 2) {return;}
        // clearing the screen
        g.setColor(new Color(247,241,246));
        for(int i=0; i<degree; i++){
            g.drawOval(points[i].x, points[i].y, 3, 3);
        }
        drawCurve(g);
        
        switch(method){
            case 1:
                deelevateStyle();
                break;
            case 2:
                approxStyle();
                break;
            case 3:
                hermiteStyle();
                break;
        }
		
		// drawing the curve
        g.setColor(colour);
        for(int i=0; i<degree; i++){
            g.drawOval(points[i].x, points[i].y, 3, 3);
        }
        drawCurve(g);
		
    }
    
    public void drawCurve(Graphics g){
        for (double i=0; i<=1; i += 0.0015){
            Point p = Tools.horner(points, weight, degree-1, i);
            if(p != null){
                g.drawLine(p.x, p.y, p.x, p.y);
            }
        }  
    }
	
	public void deelevateStyle(){
		double wx[] = new double[degree];
		double wy[] = new double[degree];
		
		int n = degree - 1;
		wx[0] = (double)points[0].x;
		wy[0] = (double)points[0].y;
		for(int i=1; i<= n/2; i++){
			double factor = (double)i / (double)(n-i);
			wx[i] = (1 + factor) * points[i].x - factor * wx[i-1]; 
			wy[i] = (1 + factor) * points[i].y - factor * wy[i-1];
		}
		wx[n] = wy[n] = 0;
		for(int i=n; i > n/2; i--){
			double factor = (double)n / (double)i;
			wx[i-1] = factor * points[i].x + (1 - factor) * wx[i];
			wy[i-1] = factor * points[i].y + (1 - factor) * wy[i];
		}
		double factor = (double)n / (double)(n/2 + 1);
		double middle = factor * points[n/2 + 1].x + (1 - factor) * wx[(n+1)/2];
		wx[n/2] = (middle + wx[n/2])/2;
		middle = factor * points[n/2 + 1].y + (1 - factor) * wy[n/2 + 1];
		wy[n/2] = (middle + wy[n/2])/2;
		
		for(int i=0; i<n; i++){
			points[i] = new Point((int)wx[i], (int)wy[i]);
		}
		degree--;
	}
	
	public void approxStyle(){
		double alfax,alfay;
		alfax = alfay = 0;
		int n = degree-1;
		double betax[] = new double[degree];
		double betay[] = new double[degree];
		double f1 = Tools.Power((double)2, 2*n-1);
		
		for(int i=0; i<=n; i++){
			int f = Tools.NewtonSymbol(n, i);
			if((i+n)%2 != 0){
				alfax -= points[i].x * f;
				alfay -= points[i].y * f;
			}
			else {
				alfax += points[i].x * f;
				alfay += points[i].y * f;
			}
		}
		alfax /= f1;
		alfay /= f1;
		
		for(int i=0; i<=n; i++){
			int f2 = Tools.NewtonSymbol(2*n, 2*i) / Tools.NewtonSymbol(n, i);
			double f = Tools.Power(2, 2*n-1);
			betax[i] = (double)points[i].x * alfax * f2/ f;
			betay[i] = (double)points[i].y * alfay * f2/ f;
			if(((n+i) % 2) != 0){
				betax[i] *= (-1);
				betay[i] *= (-1);
			}
		}
		
		double w [] = new double[degree];
		double wx [] = new double[degree];
		double z [] = new double[degree];
		double wy [] = new double [degree];
		
		for(int i=0; i<degree; i++){
			w[i] = points[i].x - betax[i];
			z[i] = points[i].y - betay[i];
		}
		
		wx[0] = w[0];
		wy[0] = z[0];
		for(int k=1; k<n; k++){
			double factor = (double)k / (double)(n-k);
			wx[k] = (1 + factor) * w[k] - factor * wx[k-1];
			wy[k] = (1 + factor) * z[k] - factor * wy[k-1];
		}
		
		for(int i=0; i<n; i++){
			points[i] = new Point((int)wx[i], (int)wy[i]);
		}
		degree--;
	}
    
	public void hermiteStyle(){
		
		// wciąż (trochę) się rozjeżdża
		int m = degree-2;
		double x [] = new double [m+1];
		double y [] = new double [m+1];
		int k,l;
		k = l = 1;
		for(int i=0; i<k; i++){
			x[i] = 0;
			y[i] = points[0].y;
		}
		for(int i=k; i<= m-l; i++){
			x[i] = points[i].x;
			y[i] = points[i].y;
		}
		for(int i=m-l+1; i<=m; i++){
			x[i] = 1;
			y[i] = points[degree-1].y;
		}

		Tools.diffQuotients(k, l, m, x, y);
		double c [][] = new double [m+1][m+1];
		c[0][m] = Tools.QuotientTable[m];
		for(int j=m-1; j >= 0; j--){
			c[0][j] = Tools.QuotientTable[j];
			for(int i=1; i<=m-j; i++){
				double factor = (double)i / (double)(m-j);
				c[i][j] = (1-x[j]) * factor * c[i-1][j+1] - x[j] * (1 - factor) * c[i][j+1] + Tools.QuotientTable[j];
			}
		}
		
		for(int i=0; i<=m; i++){
			points[i].x = (int)c[i][0];
		}
		degree--;
	}
	
	
    public static void main(String[] args) {
        Tools t = new Tools();
         /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Application().setVisible(true);
            }
        });
    }
}
