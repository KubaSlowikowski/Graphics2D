package zetCodeTutorial.Lesson4;

//http://zetcode.com/gfx/java2d/transparency/

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class FadeOutEx extends JFrame {

	class Surface extends JPanel implements ActionListener {

	    private Image img;
	    private Timer timer;
	    private float alpha = 1f;
	    
	    private final int DELAY = 40;
	    private final int INITIAL_DELAY = 500; //the time in milliseconds to wait after the timer is startedbefore firing the first event

	    public Surface() {

	        loadImage();
	        setSurfaceSize();
	        initTimer();
	    }

	    private void loadImage() {

	        img = new ImageIcon("res/images/mushrooms.jpg").getImage();
	    }

	    private void setSurfaceSize() {

	        int h = img.getHeight(this);
	        int w = img.getWidth(this);
	        setPreferredSize(new Dimension(w, h));
	    }

	    private void initTimer() {

	        timer = new Timer(DELAY, this);
	        timer.setInitialDelay(INITIAL_DELAY);
	        timer.start();
	    }

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g.create();

	        AlphaComposite acomp = AlphaComposite.getInstance(
	                AlphaComposite.SRC_OVER, alpha);
	        g2d.setComposite(acomp);
	        g2d.drawImage(img, 0, 0, null);

	        g2d.dispose();
	    }

	    @Override
	    public void paintComponent(Graphics g) {

	    	super.paintComponent(g);
	        doDrawing(g);
	    }

	    private void step() {
	        
	        alpha += -0.01f;

	        if (alpha <= 0) {

	            alpha = 0;
	            timer.stop();
	        }
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {

	        step();
	        repaint();
	    }
	}
	
	//KONIEC KLASY SURFACE//
	
    public FadeOutEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        pack(); //The preferred size with the combination of the pack() method will display the window just large enough to show the whole image.

        setTitle("Fade out");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                FadeOutEx ex = new FadeOutEx();
                ex.setVisible(true);
            }
        });
    }
}