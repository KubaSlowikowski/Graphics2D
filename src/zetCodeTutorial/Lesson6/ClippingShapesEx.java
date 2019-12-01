package zetCodeTutorial.Lesson6;

//http://zetcode.com/gfx/java2d/clipping/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class ClippingShapesEx extends JFrame {

	class Surface extends JPanel implements ActionListener {
		
		private Timer timer;
		private double rotate = 1;
		private int pos_x = 8;
		private int pos_y = 8;
		private final double delta[] = {1, 1}; // Vx and Vy velocities
		
		private final int DELAY = 10;
		private final int RADIUS = 60;
		
		
		public Surface() {
		
		    initTimer();
		}
		
		private void initTimer() {
		
		    timer = new Timer(DELAY, this);
		    timer.start();
		}
		
		private void doDrawing(Graphics g) {
		
		    Graphics2D g2d = (Graphics2D) g;
		
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		            RenderingHints.VALUE_ANTIALIAS_ON);
		
		    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
		            RenderingHints.VALUE_RENDER_QUALITY);
		    //wygladzanie krawedzi kola i prostokata
		    
		    Shape oldClip = g2d.getClip(); 
//Since we did not create a copy of the Graphics2D object, we store the old clip for later use. In the end, we must reset the clip to the original one.
		
		    int w = getWidth();
		    int h = getHeight();
		
		    Rectangle rect = new Rectangle(0, 0, 200, 80);
		
		    AffineTransform tx = new AffineTransform();
		    tx.rotate(Math.toRadians(rotate), w / 2, h / 2); //obracanie prostokata 
		    tx.translate(w / 2 - 100, h / 2 - 40); //przeniesienie srodka ukladu wspolrzednych do srodka prostokata
		//The rectangle is being rotated. It is always positioned in the middle of the panel.

		    Ellipse2D circle = new Ellipse2D.Double(pos_x, pos_y, RADIUS, RADIUS);
		
		    GeneralPath path = new GeneralPath(); //PROSTOKAT
		    path.append(tx.createTransformedShape(rect), false);
		//Here we get the shape of the rotated rectangle.
		    
		    g2d.clip(circle);
		    g2d.clip(path);
		    //Here we restrict drawing to the intersection of the two shapes
		    //If they overlap, the interior of the resulting shape is filled with colour. 
		    //The clip() method combines the initial clip (the component's client area) with the given two shapes.
		    
		    g2d.setPaint(new Color(110, 110, 110));
		    g2d.fill(circle);
		
		    g2d.setClip(oldClip);
		    //With the setClip() method, we reset the clip area to the old clip before we draw the shapes. 
		    //Unlike the clip() method, the setClip() does not combine clipping areas. 
		    //It resets the clip to the new area. Therefore, this method should be exclusively used in restoring the old clip.
		    g2d.draw(circle);
		    g2d.draw(path);
		}
		
		@Override
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    doDrawing(g);	
		}
		
		public void step() { //moving circle
		
		    int w = getWidth();
		    int h = getHeight();
		    
		    rotate += 1;
		
		    if (pos_x < 0) {
		
		        delta[0] = 1;
		    } else if (pos_x > w - RADIUS) {
		
		        delta[0] = -1;
		    }
		
		    if (pos_y < 0) {
		
		        delta[1] = 1;
		    } else if (pos_y > h - RADIUS) {
		
		        delta[1] = -1;
		    }
		
		    pos_x += delta[0];
		    pos_y += delta[1];
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
		    step();
		    repaint();
		}
	}
	
	
	
    public ClippingShapesEx() {

        initUI();
    }

    private void initUI() {

        setTitle("Clipping shapes");

        add(new Surface());

        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ClippingShapesEx ex = new ClippingShapesEx();
                ex.setVisible(true);
            }
        });
    }
}