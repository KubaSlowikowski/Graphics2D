package zetCodeTutorial.Lesson2;

//http://zetcode.com/gfx/java2d/basicdrawing/

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class CapsEx extends JFrame {

	class Surface extends JPanel {

	    private void doDrawing(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g.create();
/*
	        RenderingHints rh = new RenderingHints( 					//nie wiem po co to jest
	                RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);

	        rh.put(RenderingHints.KEY_RENDERING,
	                RenderingHints.VALUE_RENDER_QUALITY);
	                
	        g2d.setRenderingHints(rh);
	        */
	        g2d.drawString("CAP_BUTT", 100, 23);
	        BasicStroke bs1 = new BasicStroke(8, BasicStroke.CAP_BUTT,
	                BasicStroke.JOIN_BEVEL);
	        g2d.setStroke(bs1);
	        g2d.drawLine(20, 30, 250, 30);

	        g2d.drawString("CAP_ROUND",100,73);
	        BasicStroke bs2 = new BasicStroke(8, BasicStroke.CAP_ROUND,
	                BasicStroke.JOIN_BEVEL);
	        g2d.setStroke(bs2);
	        g2d.drawLine(20, 80, 250, 80);

	        g2d.drawString("CAP_SQUARE", 100, 123);
	        BasicStroke bs3 = new BasicStroke(8, BasicStroke.CAP_SQUARE,
	                BasicStroke.JOIN_BEVEL);
	        g2d.setStroke(bs3);
	        g2d.drawLine(20, 130, 250, 130);

	        BasicStroke bs4 = new BasicStroke();
	        g2d.setStroke(bs4);

	        g2d.drawLine(20, 20, 20, 140);
	        g2d.drawLine(250, 20, 250, 140);
	        g2d.drawLine(254, 20, 254, 140);
	        
	        g2d.dispose();
	    }

	    @Override
	    public void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	
	
    public CapsEx() {
        initUI();
    }
    
    private void initUI() {
        
        add(new Surface());

        setTitle("Caps");
        setSize(280, 270);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                CapsEx ex = new CapsEx();
                ex.setVisible(true);
            }
        });
    }
}