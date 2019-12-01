package zetCodeTutorial.Lesson8;

//http://zetcode.com/gfx/java2d/effects/

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PuffEx extends JFrame {    
	
		class Surface extends JPanel implements ActionListener {
	
		    private Timer timer;
		    private int size = 1;
		    private float alpha = 1;
		    private final int DELAY = 15;
		    private final int INITIAL_DELAY = 200;
	
		    public Surface() {
		        
		        initTimer();
		    }
		    
		    private void initTimer() {
		        
		        timer = new Timer(DELAY, this);
		        timer.setInitialDelay(INITIAL_DELAY);
		        timer.start();               
		    }
		    
		    private void doDrawing(Graphics g) {
		        
		        Graphics2D g2d = (Graphics2D) g.create();
		        
		        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
		        	   RenderingHints.VALUE_ANTIALIAS_ON);
	
		        rh.put(RenderingHints.KEY_RENDERING,
		               RenderingHints.VALUE_RENDER_QUALITY);
	
		        g2d.setRenderingHints(rh);
	
		        Font font = new Font("Dialog", Font.PLAIN, size);
		        g2d.setFont(font);
	
		        FontMetrics fm = g2d.getFontMetrics();
		        
		        //The getFontMetrics() returns the FontMetrics class. 
		        //The class stores information about the rendering of a particular font on a particular screen.
		        
		        String s = "ZetCode";
		        
		        Dimension size = getSize();
	
		        int w = (int) size.getWidth();
		        int h = (int) size.getHeight();
	
		        int stringWidth = fm.stringWidth(s);
		        
		        AlphaComposite ac = AlphaComposite.getInstance(
		                AlphaComposite.SRC_OVER, alpha);
		        g2d.setComposite(ac);
	
		        g2d.drawString(s, (w - stringWidth) / 2, h / 2);        
		        
		        g2d.dispose();
		    }
	
		    @Override
		    public void paintComponent(Graphics g) {
		        
		        super.paintComponent(g);        
		        doDrawing(g);
		    }   
		    
		    private void step() {
		        
		        size += 1;
	
		        if (size > 40)
		            alpha -= 0.01;
	
		        if (alpha <= 0.01)
		            timer.stop();        
		    }
	
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		        step();
		        repaint();
		    }        
		}
	
	
    public PuffEx() {
        
        initUI();
    }
    
    private void initUI() {
        
        setTitle("Puff");

        add(new Surface());

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                PuffEx ex = new PuffEx();
                ex.setVisible(true);
            }
        });      
    }
}