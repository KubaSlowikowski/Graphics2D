package zetCodeTutorial.Lesson6;

import java.awt.BasicStroke;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ClippingShapesTest extends JFrame{

	private static final int wys = 600;
	private static final int szer = 900;
	
	class Surface extends JPanel implements ActionListener {

		private Timer timer;
		private final int DELAY = 3; //frequency program is working
		private int pos_x = 8;//cirle1 position
		private int pos_y = 8;
		private int pos2_x = 100;//circle2 position
		private int pos2_y = 100;
		private int r2 = 120;//radius of cirle2
		private int r = 100;
		private final double delta[] = {1, 2}; // Vx and Vy velocities
		private final double delta2[] = {1, 2}; // Vx and Vy velocities
		private double rotate = 1; //how fast rect is rotating
		private short WIDTH = 3; // width of drawed lines
		private int i = 0; //colour iteration
	    private boolean colour = true; //is colour changing into blue
	    private float temp = 1.5f; //how fast is colour being changed
	    private final int rectHeight = 100;
	    private final int rectWidth = 500;
	    
	    
		public Surface() {
			initTimer();
		}
		
		private void initTimer() {
			Timer timer = new Timer(DELAY,this);
			timer.start();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			doDrawing(g);
		}
		
		private void doDrawing (Graphics g) {
			
			Graphics2D g2d = (Graphics2D) g;
			setBackground(Color.BLACK);
			
			 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			            RenderingHints.VALUE_ANTIALIAS_ON);
			
			    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
			            RenderingHints.VALUE_RENDER_QUALITY);
			    
			    g2d.setStroke(new BasicStroke(WIDTH));
			    
			    Shape staryObraz = g2d.getClip();
			    
			    int w = getWidth();
			    int h = getHeight();
			    
			    Rectangle rect = new Rectangle(0,0,rectWidth,rectHeight);
			    
			    AffineTransform tr = new AffineTransform();
			    tr.rotate(Math.toRadians(rotate),w/2,h/2);
			    tr.translate(w/2-rectWidth/2, h/2-rectHeight/2);
//			    GeneralPath path = new GeneralPath();
//			    path.append(tr.createTransformedShape(rect), false);
			    
			    Ellipse2D circle = new Ellipse2D.Double(pos_x,pos_y,r,r);
			    Ellipse2D circle2 = new Ellipse2D.Double(pos2_x,pos2_y,r2,r2);

			    
			    g2d.clip(tr.createTransformedShape(rect));
			    g2d.clip(circle);
			    g2d.setPaint(new Color ((255-i), 0, i));
			    g2d.fill(tr.createTransformedShape(rect));
			    
			    g2d.setClip(staryObraz);
			    
			    g2d.clip(tr.createTransformedShape(rect));
			    g2d.clip(circle2);
			    g2d.setPaint(new Color ((255-i), 0, i));
			    g2d.fill(tr.createTransformedShape(rect));
			    
			    g2d.setClip(staryObraz);
			    g2d.draw(tr.createTransformedShape(rect));
			    g2d.draw(circle);
			    g2d.draw(circle2);
		}
		
		private void step() {
			int w = getWidth();
		    int h = getHeight();
		
		    rotate += 1;
		
		    if (pos_x < 0) {
		
		        delta[0] = 1;
		    } else if (pos_x > w - r) {
		
		        delta[0] = -1;
		    }
		
		    if (pos_y < 0) {
		
		        delta[1] = 1;
		    } else if (pos_y > h - r) {
		
		        delta[1] = -1;
		    }
		
		    pos_x += delta[0];
		    pos_y += delta[1];
		}
	
		private void step2() {
			int w = getWidth();
		    int h = getHeight();
		
		    if (pos2_x < 0) {
		
		        delta2[0] = 1;
		    } else if (pos2_x > w - r2) {
		
		        delta2[0] = -1;
		    }
		
		    if (pos2_y < 0) {
		
		        delta2[1] = 1;
		    } else if (pos2_y > h - r2) {
		
		        delta2[1] = -1;
		    }
		
		    pos2_x += delta2[0];
		    pos2_y += delta2[1];
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			step();
			step2();
			colourChanger();
			repaint();
			
		}
		
		private void colourChanger() {
	    	 if(i+temp<=255 && colour)
	          	i+=temp;
	          else
	          	colour=false;
	          if(i-temp>=0 && !colour)
	          	i-=temp;
	          else
	          	colour=true;
		}
		
	}

	
	public ClippingShapesTest() {
		initUI();
	}
	
	private void initUI() {
		add(new Surface());
		
		setSize(szer,wys);
		setTitle("tytul");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ClippingShapesTest test = new ClippingShapesTest();
				test.setVisible(true);	
			}		
		});
	}
}
