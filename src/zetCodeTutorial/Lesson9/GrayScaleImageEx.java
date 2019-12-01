package zetCodeTutorial.Lesson9;

//http://zetcode.com/gfx/java2d/java2dimages/

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GrayScaleImageEx extends JFrame {
		
	class Surface extends JPanel {

	    private Image mshi;
	    private BufferedImage bufimg;
	    private Dimension d;

	    public Surface() {

	        loadImage();
	        determineAndSetSize();
	        createGrayImage();
	    }

	    private void determineAndSetSize() {

	        d = new Dimension();
	        d.width = mshi.getWidth(null);
	        d.height = mshi.getHeight(null);
	        setPreferredSize(d);
	    }
	    
	    private void createGrayImage() {
	        
	        bufimg = new BufferedImage(d.width, d.height, BufferedImage.TYPE_BYTE_GRAY);

	        Graphics2D g2d = bufimg.createGraphics();
	        g2d.drawImage(mshi, 0, 0, null);
	        g2d.dispose();        
	    }

	    private void loadImage() {

	        mshi = new ImageIcon("res/images/mushrooms.jpg").getImage();
	    }

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g;
	        g2d.drawImage(bufimg, null, 0, 0);
	    }

	    @Override
	    public void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	
	
    public GrayScaleImageEx() {
        
        initUI();
    }

    private void initUI() {

        Surface dpnl = new Surface();
        add(dpnl);

        pack();
        
        setTitle("GrayScale image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GrayScaleImageEx ex = new GrayScaleImageEx();
                ex.setVisible(true);
            }
        });
    }
}