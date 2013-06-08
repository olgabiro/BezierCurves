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
        
        // drawing the curve
        g.setColor(colour);
        for(int i=0; i<degree; i++){
            g.drawOval(points[i].x, points[i].y, 3, 3);
        }
        drawCurve(g);
    }
    
    public void drawCurve(Graphics g){
        for (double i=0; i<=1; i += 0.0025){
            Point p = Tools.horner(points, weight, degree-1, i);
            if(p != null){
                g.drawLine(p.x, p.y, p.x, p.y);
            }
        }  
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
