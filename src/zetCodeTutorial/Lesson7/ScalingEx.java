package zetCodeTutorial.Lesson7;

//http://zetcode.com/gfx/java2d/transformations/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ScalingEx extends JFrame {

	class Surface extends JPanel {

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g.create();

	        g2d.setColor(new Color(150, 150, 150));
	        g2d.fillRect(20, 20, 80, 50);

	        g2d.drawLine(-200, 0, 200, 0); g2d.drawLine(0, -200, 0, 200);
	        AffineTransform tx1 = new AffineTransform();
	        tx1.translate(200, 100);
	        tx1.scale(1, 1);
	        g2d.setColor(Color.red);
	        g2d.setTransform(tx1);
	        g2d.drawLine(-200, 0, 200, 0); g2d.drawLine(0, -200, 0, 200);

	        g2d.fillRect(20, 20, 80, 50);

	        AffineTransform tx2 = new AffineTransform();
	        tx2.translate(0, 100);
	        tx2.scale(2.5, 2.5);

	        g2d.setTransform(tx2);
	        g2d.setColor(Color.blue);
	        g2d.drawLine(-200, 0, 200, 0); g2d.drawLine(0, -200, 0, 200);
	        g2d.fillRect(20, 20, 80, 50);
	        
	        g2d.dispose();
	    }

	    @Override
	    public void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	
	
	public ScalingEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Scaling");
        setSize(500, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                ScalingEx ex = new ScalingEx();
                ex.setVisible(true);
            }
        });
    }
}