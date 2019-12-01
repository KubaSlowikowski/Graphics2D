package zetCodeTutorial.Lesson9;

//http://zetcode.com/gfx/java2d/java2dimages/

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FlippedImageEx extends JFrame {
		
	class Surface extends JPanel {

	    private Image mshi;
	    private BufferedImage bufimg;
	    private final int SPACE = 10;

	    public Surface() {

	        loadImage();
	        createFlippedImage();
	        setSurfaceSize();
	    }

	    private void loadImage() {

	        mshi = new ImageIcon("res/images/mushrooms.jpg").getImage();
	    }

	    private void createFlippedImage() {
	        
	        bufimg = new BufferedImage(mshi.getWidth(null), mshi.getHeight(null), BufferedImage.TYPE_INT_RGB);
	        
	        Graphics gb = bufimg.getGraphics();
	        gb.drawImage(mshi, 0, 0, null);
	        gb.dispose();

	        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	        tx.translate(-mshi.getWidth(null), 0);
	        
	        //Flipping an image means scaling it and translating it. So we do an AffineTransform operation.

	        
	        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        bufimg = op.filter(bufimg, null);        
	        
	        /*
	         * This is one of the filtering operations available. 
	         * This could be also done by pixel manipulation. 
	         * But Java 2D provides high level classes that make it easier to manipulate images. 
	         * In our case, the AffineTransformOp class performs scaling and translation on the image pixels.
	         */
	    }
	    
	    private void setSurfaceSize() {
	                
	        int w = bufimg.getWidth();
	        int h = bufimg.getHeight();
	        
	        Dimension d = new Dimension(3 * SPACE + 2 * w, h + 2 * SPACE);
	        setPreferredSize(d);
	    }

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g;

	        g2d.drawImage(mshi, SPACE, SPACE, null);
	        g2d.drawImage(bufimg, null, 2*SPACE + bufimg.getWidth(), SPACE);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	
	
    public FlippedImageEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());
        pack();

        setTitle("Flipped image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlippedImageEx ex = new FlippedImageEx();
                ex.setVisible(true);
            }
        });
    }
}
