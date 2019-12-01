package zetCodeTutorial.Lesson11;

//http://zetcode.com/gfx/java2d/hitmove/

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ResizingRectangleEx extends JFrame {
		
	
	class Surface extends JPanel {

	    private Point2D[] points;
	    private final int SIZE = 8; //size of the small rectangles
	    private int pos;

	    public Surface() {

	        initUI();
	    }

	    private void initUI() {

	        addMouseListener(new ShapeTestAdapter());
	        addMouseMotionListener(new ShapeTestAdapter());
	        pos = -1;

	        points = new Point2D[2];
	        points[0] = new Point2D.Double(50, 50);
	        points[1] = new Point2D.Double(150, 100);
	    }
	    
	    private void doDrawing(Graphics g) {
	        
	        Graphics2D g2d = (Graphics2D) g;

	        for (Point2D point : points) {
	            double x = point.getX() - SIZE / 2;
	            double y = point.getY() - SIZE / 2;
	            g2d.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
	        }

	        Rectangle2D r = new Rectangle2D.Double();
	        r.setFrameFromDiagonal(points[0], points[1]);

	        g2d.draw(r);        
	    }

	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        doDrawing(g);
	    }

	    private class ShapeTestAdapter extends MouseAdapter {

	    	//In the mousePressed() method, we determine if we have clicked inside one of the two controlling points. 
	    	//If we hit one of them, the pos variable stores which of them it was.
	    	
	        @Override
	        public void mousePressed(MouseEvent event) {

	            Point p = event.getPoint();

	            for (int i = 0; i < points.length; i++) {

	                double x = points[i].getX() - SIZE / 2;
	                double y = points[i].getY() - SIZE / 2;

	                Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);

	                if (r.contains(p)) {

	                    pos = i;
	                    //return;
	                }
	            }
	        }

	        @Override
	        public void mouseReleased(MouseEvent event) {

	            pos = -1;
	        }

	        @Override
	        public void mouseDragged(MouseEvent event) {

	            if (pos == -1) {
	                return;
	            }

	            points[pos] = event.getPoint();
	            repaint();
	        }
	    }
	}

		
    public ResizingRectangleEx()  {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Surface());

        setTitle("Resize rectangle");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                  
    }
    
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ResizingRectangleEx ex = new ResizingRectangleEx();
                ex.setVisible(true);
            }
        });
    }
}
