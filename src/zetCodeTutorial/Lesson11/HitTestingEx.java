package zetCodeTutorial.Lesson11;

//http://zetcode.com/gfx/java2d/hitmove/

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


public class HitTestingEx extends JFrame {
		
	class Surface extends JPanel {

	    private Rectangle2D rect;
	    private Ellipse2D ellipse;
	    private float alpha_rectangle;
	    private float alpha_ellipse;
	    //These two variables control the transparency of the two geometrical objects.


	    public Surface() {
	        
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

	        g2d.setPaint(new Color(50, 50, 50));

	        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);

	        rh.put(RenderingHints.KEY_RENDERING,
	                RenderingHints.VALUE_RENDER_QUALITY);

	        g2d.setRenderingHints(rh);

	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha_rectangle));
	        //Inside the doDrawing() method, we set the transparency of the rectangle. The alpha_rectangle is computed inside a dedicated Thread.
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


	    class HitTestAdapter extends MouseAdapter implements Runnable {
	    	
	        private RectRunnable rectAnimator;
	        private Thread ellipseAnimator;

	        @Override
	        public void mousePressed(MouseEvent e) {
	            
	            int x = e.getX();
	            int y = e.getY();

	            if (rect.contains(x, y)) {

	                rectAnimator = new RectRunnable();
	            }

	            if (ellipse.contains(x, y)) {

	                ellipseAnimator = new Thread(this);
	                ellipseAnimator.start();
	            }
	        }

	        @Override
	        public void run() {

	            while (alpha_ellipse >= 0) {

	                repaint();
	                alpha_ellipse += -0.02f;

	                if (alpha_ellipse < 0) {

	                    alpha_ellipse = 0;
	                }

	                try {
	                    
	                    Thread.sleep(50);
	                } catch (InterruptedException ex) {
	                    
	                    Logger.getLogger(Surface.class.getName()).log(Level.SEVERE, 
	                        null, ex);
	                }
	            }
	        }
	    }
	    
	    class RectRunnable implements Runnable {

	        private Thread runner;

	        public RectRunnable() {
	            
	            initThread();
	        }
	        
	        private void initThread() {
	            
	            runner = new Thread(this);
	            runner.start();
	        }

	        @Override
	        public void run() {

	            while (alpha_rectangle >= 0) {
	                
	                repaint();
	                alpha_rectangle += -0.01f;

	                if (alpha_rectangle < 0) {
	                    alpha_rectangle = 0;
	                }

	                try {
	                    
	                    Thread.sleep(50);
	                } catch (InterruptedException ex) {
	                    
	                     Logger.getLogger(Surface.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	    }
	}
	
	
    public HitTestingEx() {
        
        add(new Surface());

        setTitle("Hit testing");
        setSize(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);           
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                HitTestingEx ex = new HitTestingEx();
                ex.setVisible(true);
            }
        });     
    }
}