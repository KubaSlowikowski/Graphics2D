package zetCodeTutorial.Lesson8;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BubblesTest extends JFrame {
	
	class Surface extends JPanel implements ActionListener {

		    private final Color colors[] = {
		            Color.blue, Color.cyan, Color.green,
		            Color.magenta, Color.orange, Color.pink,
		            Color.red, Color.yellow, Color.lightGray, Color.white
		        };

	        private Rectangle2D.Float[] rectangles;
	        private double rectSize[]; 
	        private float rectStroke[]; //grubosc linii
	        private double maxSize = 0;
	        private final int NUMBER_OF_RECTANGLES = 20;
	        private final int DELAY = 25;
	        private final int INITIAL_DELAY = 150;    
	        private Timer timer;
	        private Image img;
	        
	        protected final static int width = 600;
	        protected final static int height = 400;
		
		
		Surface() {
			initSurface();
			initRectangles();
			initTimer();
		//	loadImage();
		}
		
		private void initSurface() {
			setBackground(Color.BLACK);
			rectangles = new Rectangle2D.Float[NUMBER_OF_RECTANGLES];
			rectSize = new double[rectangles.length];
			rectStroke = new float[rectangles.length];
		}
		
		private void initRectangles() {
			
			maxSize = width/10;
			
			for(int i = 0; i < NUMBER_OF_RECTANGLES; i++) {
				rectangles[i] = new Rectangle2D.Float();
				posRandRectangles(i, maxSize * Math.random(), width , height);
			}
		}
		
		private void initTimer() {
			timer = new Timer(DELAY,this);
			timer.setInitialDelay(INITIAL_DELAY);
			timer.start();
		}
		
		private void loadImage() {
	        img = new ImageIcon("res/images/wallpaper.jpg").getImage();
	    }
		
		private void posRandRectangles(int i, double size, int w, int h) {
			rectSize[i] = size;
			rectStroke[i] = 1.0f;
			double x = Math.random() * (w - (maxSize / 2));
			double y = Math.random() * (h - (maxSize / 2));
			rectangles[i].setFrame(x,y,size,size);
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			doDrawing(g);
		}
		
		private void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g.create();
			
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

		    rh.put(RenderingHints.KEY_RENDERING,
		            RenderingHints.VALUE_RENDER_QUALITY);
		
		    g2d.setRenderingHints(rh);
		    
		    Dimension size = getSize();
		    
		    g2d.drawImage(img,0,0,null);
		    
		    doStep(size.width, size.height);
		    drawEllipses(g2d);
		    g2d.dispose();
		}
		
		private void doStep(int w, int h) {
			
			for (int i = 0; i < rectangles.length; i++) {
				
				rectStroke[i] += 0.025f;
				rectSize[i]+=0.5;
				
				if(rectSize[i] > maxSize) {
					
					posRandRectangles(i, 1, w, h);
				} else {
					
					rectangles[i].setFrame(rectangles[i].getX(), rectangles[i].getY(),
							rectSize[i], rectSize[i]);
				}
			}
		}
		
		private void drawEllipses(Graphics2D g2d) {
			
			for (int i = 0; i < rectangles.length; i++) {
				
				g2d.setColor(colors[i%colors.length]);
				g2d.setStroke(new BasicStroke(rectStroke[i]));
				g2d.fill(rectangles[i]);
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
		
	}
	
	public BubblesTest() {
		initUI();
	}
	
	private void initUI() {
		
        add(new Surface());
        
        setTitle("Bubbles");
        setSize(Surface.width, Surface.height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main (String [] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BubblesTest b = new BubblesTest();
				b.setVisible(true);
			}
		});
		
	}
}