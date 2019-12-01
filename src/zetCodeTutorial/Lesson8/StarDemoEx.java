package zetCodeTutorial.Lesson8;

//http://zetcode.com/gfx/java2d/effects/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StarDemoEx extends JFrame {

	class Surface extends JPanel implements ActionListener {
	
		private final int points[][] = {
		    {0, 85}, {75, 75}, {100, 10}, {125, 75},
		    {200, 85}, {150, 125}, {160, 190}, {100, 150},
		    {40, 190}, {50, 125}, {0, 85}
		};
		
		private Timer timer;
		private double angle = 0;
		private double scale = 1;
		private double delta = 0.01;
		
		private final int DELAY = 10;
		
		public Surface() {
		
		    initTimer();
		}
		
		private void initTimer() {
		    
		    timer = new Timer(DELAY, this);
		    timer.start();        
		}
		
		private void doDrawing(Graphics g) {
		    
		    int h = getHeight();
		    int w = getWidth();
		
		    Graphics2D g2d = (Graphics2D) g.create();
		
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		            RenderingHints.VALUE_ANTIALIAS_ON);
		
		    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
		            RenderingHints.VALUE_RENDER_QUALITY);
		
		    setBackground(Color.BLACK);
		    
		    g2d.translate(w / 2, h / 2);
		    GeneralPath star = new GeneralPath();
		    star.moveTo(points[0][0], points[0][1]);
		
		    for (int k = 1; k < points.length; k++) {
		        
		        star.lineTo(points[k][0], points[k][1]);
		    }
		
		    g2d.rotate(angle);
		    g2d.scale(scale, scale);
		    
		    g2d.setColor(Color.BLUE);
		    g2d.fill(star);        
		    
		    g2d.dispose();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
		
		    super.paintComponent(g);
		    doDrawing(g);
		}
		
		private void step() {
		    
		    if (scale < 0.01) {
		        
		        delta = -delta;
		    } else if (scale > 0.99) {
		        
		        delta = -delta;
		    }
		
		    scale += delta;
		    angle += 0.01;        
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
		    step();
		    repaint();
		}
	}

	
    public StarDemoEx() {

        initUI();
    }

    private void initUI() {
        
        add(new Surface());

        setTitle("Star");
        setSize(600, 400);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                StarDemoEx ex = new StarDemoEx();
                ex.setVisible(true);
            }
        });
    }
}