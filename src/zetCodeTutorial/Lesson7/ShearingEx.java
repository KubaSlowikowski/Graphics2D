package zetCodeTutorial.Lesson7;

//http://zetcode.com/gfx/java2d/transformations/
//A shear is a transformation that moves an object perpendicular to a given axis, with greater value on one side of the axis than the other.
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ShearingEx extends JFrame {

	class Surface extends JPanel {

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g.create();

	        AffineTransform tx1 = new AffineTransform();
	        tx1.translate(50, 90);

	        g2d.setTransform(tx1);
	        g2d.setPaint(Color.green);
	        g2d.drawRect(0, 0, 160, 50);

	        AffineTransform tx2 = new AffineTransform();
	        tx2.translate(50, 90);
	        tx2.shear(0, -1);

	        g2d.setTransform(tx2);
	        g2d.setPaint(Color.blue);

	        g2d.draw(new Rectangle(0, 0, 80, 50));

	        AffineTransform tx3 = new AffineTransform();
	        tx3.translate(130, 10);
	        tx3.shear(0, 1);

	        g2d.setTransform(tx3);
	        g2d.setPaint(Color.red);
	        g2d.drawRect(0, 0, 80, 50);
	        
	        g2d.dispose();
	    }

	    @Override
	    public void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	
	
    public ShearingEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Shearing");
        setSize(330, 270);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                ShearingEx ex = new ShearingEx();
                ex.setVisible(true);
            }
        });
    }
}