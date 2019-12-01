package zetCodeTutorial.Lesson6;

//http://zetcode.com/gfx/java2d/clipping/

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


class Surface extends JPanel implements ActionListener {

    private int pos_x = 8;
    private int pos_y = 8;
    private final int RADIUS = 90;
    private final int DELAY = 35;

    private Timer timer;
    private Image image;

    private final double delta[] = { 3, 3 }; //predkosc poczatkowa Vx i Vy

    public Surface() {
        
        loadImage();
        determineAndSetImageSize();
        initTimer();
    }
    
    private void loadImage() {
        
        image = new ImageIcon("res/images/mushrooms.jpg").getImage();
    }
    
    private void determineAndSetImageSize() {
        
        int h = image.getHeight(this);
        int w = image.getWidth(this);
        setPreferredSize(new Dimension(w, h));        
    }    

    private void initTimer() {   

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g.create(); //we're working on copy of g

        g2d.clip(new Ellipse2D.Double(pos_x, pos_y, RADIUS, RADIUS));
        g2d.drawImage(image, 0, 0, null); 
        
        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        moveCircle();
        repaint();
    }
    
    private void moveCircle() {

        int w = getWidth();
        int h = getHeight();

        if (pos_x < 0) {
            
            delta[0] = Math.random() % 4 + 5;
        } else if (pos_x > w - RADIUS) {
            
            delta[0] = -(Math.random() % 4 + 5);
        }

        if (pos_y < 0 ) {
            
            delta[1] = Math.random() % 4 + 5;
        } else if (pos_y > h - RADIUS) {
            
            delta[1] = -(Math.random() % 4 + 5);
        }

        pos_x += delta[0];
        pos_y += delta[1];
    }       
}

public class ClippingEx extends JFrame {
    
    public ClippingEx() {
        
        initUI();
    }
    
    private void initUI() {
        
        setTitle("Clipping");

        add(new Surface());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                ClippingEx cl = new ClippingEx();
                cl.setVisible(true);
            }
        });        
    }
}