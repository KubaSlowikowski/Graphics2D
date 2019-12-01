package zetCodeTutorial.Lesson3;

//http://zetcode.com/gfx/java2d/shapesandfills/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class ColoursEx extends JFrame {

	class Surface extends JPanel {
        
	    public void doDrawing(Graphics g) {
	    	
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(new Color(125, 167, 116));
	        g2d.fillRect(10, 10, 90, 60);

	        g2d.setColor(new Color(42, 179, 231));
	        g2d.fillRect(130, 10, 90, 60);

	        g2d.setColor(new Color(70, 67, 123));
	        g2d.fillRect(250, 10, 90, 60);

	        g2d.setColor(new Color(130, 100, 84));
	        g2d.fillRect(10, 100, 90, 60);

	        g2d.setColor(new Color(252, 211, 61));
	        g2d.fillRect(130, 100, 90, 60);

	        g2d.setColor(new Color(241, 98, 69));
	        g2d.fillRect(250, 100, 90, 60);

	        g2d.setColor(new Color(217, 146, 54));
	        g2d.fillRect(10, 190, 90, 60);

	        g2d.setColor(new Color(63, 121, 186));
	        g2d.fillRect(130, 190, 90, 60);

	        g2d.setColor(new Color(31, 21, 1));
	        g2d.fillRect(250, 190, 90, 60);
	    }

	    @Override
	    public void paintComponent(Graphics g) {
	                
	        super.paintComponent(g);
	        doDrawing(g);
	    }           
	}

	
    public ColoursEx() {

        initUI();
    }

    private void initUI() {
        
        add(new Surface());
        
        setTitle("Colours");
        setSize(360, 300);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ColoursEx ex = new ColoursEx();
                ex.setVisible(true);
            }
        });
    }
}