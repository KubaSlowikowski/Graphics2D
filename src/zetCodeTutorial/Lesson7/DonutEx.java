package zetCodeTutorial.Lesson7;

//http://zetcode.com/gfx/java2d/transformations/

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DonutEx extends JFrame {
	
	class Surface extends JPanel {

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g.create();

	        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);

	        rh.put(RenderingHints.KEY_RENDERING,
	                RenderingHints.VALUE_RENDER_QUALITY);

	        g2d.setRenderingHints(rh);

	        Dimension size = getSize();
	        double w = size.getWidth();
	        double h = size.getHeight();

	        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
	        g2d.setStroke(new BasicStroke(1));
	        g2d.setPaint(Color.gray);

	        for (double deg = 0; deg < 360; deg += 5) {
	            AffineTransform at = AffineTransform.getTranslateInstance(w / 2, h / 2);
	            at.rotate(Math.toRadians(deg));
	            g2d.draw(at.createTransformedShape(e));
	        }
	    }

	    @Override
	    public void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	
	
    public DonutEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Donut");
        setSize(370, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                DonutEx ex = new DonutEx();
                ex.setVisible(true);
            }
        });
    }
}