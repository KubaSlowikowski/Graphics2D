package zetCodeTutorial.Lesson9;

//http://zetcode.com/gfx/java2d/java2dimages/

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BlurredImageEx extends JFrame {
		
	class Surface extends JPanel {

	    private BufferedImage image;
	    private BufferedImage blurredImage;

	    public Surface() {

	        loadImage();
	        createBlurredImage();
	        setSurfaceSize();
	    }

	    private void loadImage() {
	        
	        try {
	            
	            image = ImageIO.read(new File("res/images/mushrooms.jpg"));
	        } catch (IOException ex) {
	            
	            Logger.getLogger(Surface.class.getName()).log(
	                    Level.WARNING, null, ex);
	        }
	    }
	    
	    private void createBlurredImage() {

	        float[] blurKernel = {
	            1 / 9f, 1 / 9f, 1 / 9f,
	            1 / 9f, 1 / 9f, 1 / 9f,
	            1 / 9f, 1 / 9f, 1 / 9f
	        };
	        //This matrix is called a kernel. The values are weights that are applied to the neighbouring values of the pixel being changed.

	        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
	        blurredImage = blur.filter(image, new BufferedImage(image.getWidth(),
	        		image.getHeight(), image.getType()));
	    }
	    
	    private void setSurfaceSize() {
	        
	        Dimension d = new Dimension();
	        d.width = image.getWidth(null);
	        d.height = image.getHeight(null);
	        setPreferredSize(d);        
	    }

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g;
	        g2d.drawImage(blurredImage, null, 0, 0);
	    }

	    @Override
	    public void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	

    public BlurredImageEx() {

        setTitle("Blurred image");
        add(new Surface());

        pack();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                BlurredImageEx ex = new BlurredImageEx();
                ex.setVisible(true);
            }
        });
    }
}
