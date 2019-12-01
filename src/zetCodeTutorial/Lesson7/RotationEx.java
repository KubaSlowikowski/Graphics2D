package zetCodeTutorial.Lesson7;

//http://zetcode.com/gfx/java2d/transformations/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RotationEx extends JFrame {
	
	class Surface extends JPanel {

	    private void doDrawing(Graphics g) {
	        
	        Graphics2D g2d = (Graphics2D) g.create();
	        
	        g2d.setPaint(new Color(150, 150, 150));
	        g2d.fillRect(20, 20, 80, 50);
	        g2d.translate(180, -50);
	        g2d.rotate(Math.PI/4);
	        g2d.fillRect(80, 80, 80, 50);
	        
	        g2d.dispose();
	    }

	    @Override
	    public void paintComponent(Graphics g) {
	        
	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	
	
    public RotationEx() {
        
        initUI();
    }
    
    private void initUI() {
        
        setTitle("Rotation");

        add(new Surface());

        setSize(300, 200);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                RotationEx ex = new RotationEx();
                ex.setVisible(true);
            }
        });                    
    }
}