package beziercurves;
import java.awt.Color;
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
    
    public void setColour(Color colour){
        this.colour = colour;
    }
    
    public void elevateDegree(){    //problem z utrata dokladnosci, co jesli trzeba bedzie to robic na doublach?
        Point pom[] = new Point[degree + 2];
        pom[0] = new Point(points[0].x, points[0].y);
        for(int k=1; k<=degree; k++){
            double factor = k / (degree + 1);
            int x = (int)(factor * points[k-1].x) + points[k].x - (int)(factor * points[k].x);
            int y = (int)(factor * points[k-1].y) + points[k].y - (int)(factor * points[k].y);
            pom[k] = new Point(x,y);
        }
        pom[this.degree + 1] = new Point(points[this.degree].x, points[this.degree].y);
        points = pom;
    }
    
    public static void main(String[] args) {
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
