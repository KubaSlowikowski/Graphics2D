package zetCodeTutorial.Lesson5;

//http://zetcode.com/gfx/java2d/composition/

import java.awt.AlphaComposite;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SunAndCloudEx extends JFrame {

	
	class Surface extends JPanel implements ActionListener {

	    private Image sun;
	    private Image cloud;
	    private Timer timer;
	    private float alpha = 1f;
	    
	    private final int DELAY = 100;

	    public Surface() {

	        loadImages();
	        initTimer();
	    }

	    private void loadImages() {

	        sun = new ImageIcon("res/images/sun.png").getImage();
	        cloud = new ImageIcon("res/images/cloud.png").getImage();
	    }

	    private void initTimer() {

	        timer = new Timer(DELAY, this);
	        timer.start();
	    }

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g.create();

	        BufferedImage buffImg = new BufferedImage(220, 140,
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics2D gbi = buffImg.createGraphics();

	        AlphaComposite ac = AlphaComposite.getInstance(
	                AlphaComposite.SRC_OVER, alpha);

	        gbi.drawImage(sun, 40, 30, null);
	        gbi.setComposite(ac);
	        //The images are rendered into a BufferedImage and are later copied to the screen. 
	        //The setComposite() specifies how new pixels are to be combined with the existing pixels on the graphics device during the rendering process.
	        
	        gbi.drawImage(cloud, 0, 0, null);

	        g2d.drawImage(buffImg, 20, 20, null);

	        gbi.dispose();
	        g2d.dispose();
	    }

	    @Override
	    protected void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }

	    private void step() {
	        
	        alpha -= 0.05;

	        if (alpha <= 0) {

	            alpha = 1.0f;
	            //timer.stop();
	        }
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {

	        step();
	        repaint();
	    }
	}
	
	
    public SunAndCloudEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Sun and cloud");
        setSize(300, 210);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                SunAndCloudEx ex = new SunAndCloudEx();
                ex.setVisible(true);
            }
        });
    }
}