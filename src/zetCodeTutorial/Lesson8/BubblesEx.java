package zetCodeTutorial.Lesson8;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements ActionListener {

    private final Color colors[] = {
        Color.blue, Color.cyan, Color.green,
        Color.magenta, Color.orange, Color.pink,
        Color.red, Color.yellow, Color.lightGray, Color.white
    };

    private Ellipse2D.Float[] ellipses;
    private double esize[]; 
    private float estroke[]; //grubosc linii
    private double maxSize = 0;
    private final int NUMBER_OF_ELLIPSES = 20;
    private final int DELAY = 30;
    private final int INITIAL_DELAY = 150;    
    private Timer timer;

    public Surface() {

        initSurface();
        initEllipses();
        initTimer();
    }

    private void initSurface() {

        setBackground(Color.black);
        ellipses = new Ellipse2D.Float[NUMBER_OF_ELLIPSES];
        esize = new double[ellipses.length];
        estroke = new float[ellipses.length];
    }
    /*
     * The initSurface() method sets a black background for the panel. 
     * We create three arrays. 
     * An array for the ellipses (a circle is a special case of an ellipse), 
     * an array for the size of each of the ellipses, 
     * and an array for ellipses' strokes. 
     * Both size and stroke of the bubble grow during the animation.
     */

    private void initEllipses() {

        int w = 350;
        int h = 250;

        maxSize = w / 10;

        for (int i = 0; i < NUMBER_OF_ELLIPSES; i++) {

            ellipses[i] = new Ellipse2D.Float();
            posRandEllipses(i, maxSize * Math.random(), w, h);
        }
    }
    /* The ellipses array is filled with ellipse objects. 
     * The posRandEllipses() method positions randomly the ellipse objects on the window. 
     * The initial sizes of the ellipses are chosen also randomly.
     */

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.setInitialDelay(INITIAL_DELAY);
        timer.start();
    }
//    /A timer object is created and started. It is used to create the animation.


    private void posRandEllipses(int i, double size, int w, int h) {

        esize[i] = size; //poczatkowa wielkosc kazdej elipsy (sprawdzic, gdy podstawi sie jakas liczbe)
        estroke[i] = 1.0f; //grubosc linii
        double x = Math.random() * (w - (maxSize / 2));
        double y = Math.random() * (h - (maxSize / 2));
        ellipses[i].setFrame(x, y, size, size);
    }
    /* The posRandEllipses() method positions the ellipses randomly on the window. 
     * The esize and estroke arrays are filled with values. 
     * The setFrame() method sets the location and size of the framing rectangle of an ellipse.
     */

    private void doStep(int w, int h) {

        for (int i = 0; i < ellipses.length; i++) {

            estroke[i] += 0.025f;
            esize[i]++;

            if (esize[i] > maxSize) {

                posRandEllipses(i, 1, w, h);
            } else {

                ellipses[i].setFrame(ellipses[i].getX(), ellipses[i].getY(),
                        esize[i], esize[i]);
            }
        }
    }
    /* The animation consists of steps. 
     * In each step, we increase the stroke and size values of each ellipse. 
     * After the bubble reaches its maximum size, it is reset to the minimum size and randomly repositioned on the panel. 
     * Else it is displayed with the increased values.
     */
    
    private void drawEllipses(Graphics2D g2d) {    //The drawEllipses() method draws all the ellipses from the array on the panel.

        for (int i = 0; i < ellipses.length; i++) {

            g2d.setColor(colors[i % colors.length]);
            g2d.setStroke(new BasicStroke(estroke[i]));
            g2d.draw(ellipses[i]);
        }
    }


    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        doStep(size.width, size.height);
        drawEllipses(g2d);
        
        g2d.dispose();
    }
    /* In the doDrawing() method, we compute the size of the panel. 
     * If the window is resized, the bubbles are distributed randomly over the whole area of the window.
     */

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}

public class BubblesEx extends JFrame {

    public BubblesEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());
        
        setTitle("Bubbles");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                BubblesEx ex = new BubblesEx();
                ex.setVisible(true);
            }
        });
    }
}