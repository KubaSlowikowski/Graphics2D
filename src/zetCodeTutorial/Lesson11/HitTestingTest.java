package zetCodeTutorial.Lesson11;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import zetCodeTutorial.Lesson11.HitTestingEx.Surface;

	public class HitTestingTest extends JFrame {
	
		class Surface extends JPanel {
			
			private Rectangle2D rect;
			private Ellipse2D ellipse;
			private float alpha_rectangle;
			private float alpha_ellipse;
			
			Surface() {
				initSurface();
			}
			
			private void initSurface() {
				addMouseListener(new HitTestAdapter());
				
				rect = new Rectangle2D.Float(20f, 20f, 80f, 50f);
				ellipse = new Ellipse2D.Float(120f, 30f, 60f, 60f);
				
				alpha_rectangle = 1f;
				alpha_ellipse = 1f;
			}
			
			private void doDrawing(Graphics g) {
				
				Graphics2D g2d = (Graphics2D) g.create();
				
				setBackground(Color.BLACK);
				g2d.setPaint(Color.ORANGE);
				
				RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			                RenderingHints.VALUE_ANTIALIAS_ON);

			    rh.put(RenderingHints.KEY_RENDERING,
			                RenderingHints.VALUE_RENDER_QUALITY);
			    
			    g2d.setRenderingHints(rh);
			    
			    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha_rectangle));
			    g2d.fill(rect);
			    
			    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha_ellipse));
			    g2d.fill(ellipse);
				
			    g2d.dispose();
			}
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				doDrawing(g);
			}
			
		    class HitTestAdapter extends MouseAdapter {
		    	
		    	private RectRunnable rectAnimator;
		    	private EllipseRunnable ellipseAnimator;
		    	
				@Override
				public void mousePressed(MouseEvent e) {
					
					int x = e.getX();
					int y = e.getY();
					
					if(rect.contains(x, y)) {
						rectAnimator = new RectRunnable();
					}
					
					if (ellipse.contains(x, y)) {
						ellipseAnimator = new EllipseRunnable();
					}
				}
		    }
		    
		    class RectRunnable implements Runnable {

		    	private Thread thread;
		    	
		    	public RectRunnable() {
		    		initThread();
				}
		    	
		    	private void initThread() {
		    		thread = new Thread(this);
		    		thread.start();
		    	}
		    	
				@Override
				public void run() {
					
					while( alpha_rectangle >= 0) {
						
						repaint();
						alpha_rectangle -= 0.01f;
						
						if (alpha_rectangle < 0) {
							alpha_rectangle = 0;
						}
						
						try {
							Thread.sleep(40);
						} catch (InterruptedException e) {
		                    Logger.getLogger(Surface.class.getName()).log(Level.SEVERE, null, e);
						}
					}
				}
		    }
		    
		    class EllipseRunnable implements Runnable {

		    	private Thread thread;
		    	
		    	public EllipseRunnable() {
		    		initThread();
		    	}
		    	
		    	private void initThread() {
		    		thread = new Thread(this);
		    		thread.start();	
		    	}
		    	
				@Override
				public void run() {
					
					while (alpha_ellipse >= 0) {
						
						repaint();
						alpha_ellipse -= 0.01f;
						
						if(alpha_ellipse < 0) {
							alpha_ellipse = 0;
						}
						
						try {
							Thread.sleep(40);
						} catch (InterruptedException e) {
		                     Logger.getLogger(Surface.class.getName()).log(Level.SEVERE, null, e);
						}
					}
				}
		    }
		}
	
	
	public HitTestingTest() {
		
		add(new Surface());
		
		setTitle("Test");
		setSize(250,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				HitTestingTest t = new HitTestingTest();
				t.setVisible(true);
			}
		});
	}
}
