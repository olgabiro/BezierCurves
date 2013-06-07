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
        
        /**
         * NIE WIEDZIEĆ CZEMU ZUPEŁNIE NIE DZIAŁA.
         */
        Point pom[] = new Point[degree+1];
        pom[0] = points[0];
        for(int k=1; k<degree; k++){
            double factor = k / (degree);
            int x = (int)(factor * points[k-1].x) + points[k].x - (int)(factor * points[k].x);
            int y = (int)(factor * points[k-1].y) + points[k].y - (int)(factor * points[k].y);
            pom[k] = new Point(x,y);
        }
        pom[this.degree] = new Point(points[this.degree-1].x, points[this.degree-1].y);
        points = pom;
        
        degree++;
        g.setColor(colour);
        drawCurve(g);
    }
    
    public void drawCurve(Graphics g){
        for (double i=0; i<=1; i += 0.0025){
            Point p = Tools.horner(points, degree-1, i);
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
