package zetCodeTutorial.Lesson10;

//http://zetcode.com/gfx/java2d/textfonts/

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RotatedTextEx extends JFrame {
		
	
	class Surface extends JPanel {
	    
	    private void doDrawing(Graphics g) {
	        
	        Graphics2D g2d = (Graphics2D) g.create();

	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);

	        String s = "ZetCode, tutorials for programmers";

	        Font font = new Font("Courier", Font.PLAIN, 13);

	        g2d.translate(20, 20);

	        FontRenderContext frc = g2d.getFontRenderContext();

	        GlyphVector gv = font.createGlyphVector(frc, s);
	        int length = gv.getNumGlyphs();

	        for (int i = 0; i < length; i++) {
	            
	            Point2D p = gv.getGlyphPosition(i);
	            double theta = (double) i / (double) (length - 1) * Math.PI / 3;
	            AffineTransform at = AffineTransform.getTranslateInstance(p.getX(),
	                    p.getY());
	            at.rotate(theta);

	            Shape glyph = gv.getGlyphOutline(i);
	            Shape transformedGlyph = at.createTransformedShape(glyph);
	            g2d.fill(transformedGlyph);
	        }        
	        
	        g2d.dispose();
	    }    
	    
	    @Override
	    public void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }       
	}

	
	
    public RotatedTextEx() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Surface());
        
        setTitle("Rotated text");
        setSize(450, 300);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                RotatedTextEx ex = new RotatedTextEx();
                ex.setVisible(true);
            }
        });       
    }
}