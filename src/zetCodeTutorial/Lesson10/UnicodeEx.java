package zetCodeTutorial.Lesson10;

//http://zetcode.com/gfx/java2d/textfonts/

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UnicodeEx extends JFrame {
		
	class Surface extends JPanel {

	    String sent1 = "\u0424\u0451\u0434\u043e\u0440 \u041c\u0438\u0445" +
	"\u0430\u0439\u043b\u043e\u0432\u0438\u0447 \u0414\u043e\u0441\u0442" +
	"\u043e\u0435\u0432\u0441\u043a\u0438\u0439 \u0440\u043e\u0434\u0438" +
	"\u043b\u0441\u044f 30 \u043e\u043a\u0442\u044f\u0431\u0440\u044f " +
	"(11 \u043d\u043e\u044f\u0431\u0440\u044f) 1821 \u0433\u043e\u0434" +
	"\u0430 \u0432 \u041c\u043e\u0441\u043a\u0432\u0435. ";

	    String sent2 = "\u0411\u044b\u043b \u0432\u0442\u043e\u0440\u044b\u043c " +
	"\u0438\u0437 7 \u0434\u0435\u0442\u0435\u0439. \u041e\u0442\u0435\u0446, " +
	"\u041c\u0438\u0445\u0430\u0438\u043b \u0410\u043d\u0434\u0440\u0435\u0435" +
	"\u0432\u0438\u0447, \u0440\u0430\u0431\u043e\u0442\u0430\u043b \u0432 " +
	"\u0433\u043e\u0441\u043f\u0438\u0442\u0430\u043b\u0435 \u0434\u043b\u044f " +
	"\u0431\u0435\u0434\u043d\u044b\u0445."; 

	    String sent3 = "\u041c\u0430\u0442\u044c, \u041c\u0430\u0440\u0438\u044f " +
	"\u0424\u0451\u0434\u043e\u0440\u043e\u0432\u043d\u0430 " +
	"(\u0432 \u0434\u0435\u0432\u0438\u0447\u0435\u0441\u0442\u0432\u0435 " +
	"\u041d\u0435\u0447\u0430\u0435\u0432\u0430), \u043f\u0440\u043e\u0438\u0441" +
	"\u0445\u043e\u0434\u0438\u043b\u0430 \u0438\u0437 \u043a\u0443\u043f\u0435" +
	"\u0447\u0435\u0441\u043a\u043e\u0433\u043e \u0440\u043e\u0434\u0430.";

	    String sent4 = "\u041a\u043e\u0433\u0434\u0430 \u0414\u043e\u0441\u0442" +
	"\u043e\u0435\u0432\u0441\u043a\u043e\u043c\u0443 \u0431\u044b\u043b\u043e 15 " +
	"\u043b\u0435\u0442, \u0435\u0433\u043e \u043c\u0430\u0442\u044c " +
	"\u0443\u043c\u0435\u0440\u043b\u0430 \u043e\u0442 \u0447\u0430\u0445\u043e" +
	"\u0442\u043a\u0438, \u0438 \u043e\u0442\u0435\u0446 \u043e\u0442\u043f\u0440" +
	"\u0430\u0432\u0438\u043b";

	    String sent5 = "\u0441\u0442\u0430\u0440\u0448\u0438\u0445 \u0441\u044b" +
	"\u043d\u043e\u0432\u0435\u0439, \u0424\u0451\u0434\u043e\u0440\u0430 \u0438 " +
	"\u041c\u0438\u0445\u0430\u0438\u043b\u0430 (\u0432\u043f\u043e\u0441\u043b" +
	"\u0435\u0434\u0441\u0442\u0432\u0438\u0438 \u0442\u0430\u043a\u0436\u0435 " +
	"\u0441\u0442\u0430\u0432\u0448\u0435\u0433\u043e \u043f\u0438\u0441\u0430" +
	"\u0442\u0435\u043b\u0435\u043c),"; 

	    String sent6 = "\u0432 \u043f\u0430\u043d\u0441\u0438\u043e\u043d \u041a. " +
	"\u0424. \u041a\u043e\u0441\u0442\u043e\u043c\u0430\u0440\u043e\u0432\u0430 " +
	"\u0432 \u041f\u0435\u0442\u0435\u0440\u0431\u0443\u0440\u0433\u0435.";

	    private void doDrawing(Graphics g) {
	        
	        Graphics2D g2d = (Graphics2D) g;

	        RenderingHints rh =
	            new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
	            RenderingHints.VALUE_ANTIALIAS_ON);

	        rh.put(RenderingHints.KEY_RENDERING,
	               RenderingHints.VALUE_RENDER_QUALITY);

	        g2d.setRenderingHints(rh);

	        g2d.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 11));

	        g2d.drawString(sent1, 20, 30);
	        g2d.drawString(sent2, 20, 55);
	        g2d.drawString(sent3, 20, 80);
	        g2d.drawString(sent4, 20, 120);
	        g2d.drawString(sent5, 20, 145);
	        g2d.drawString(sent6, 20, 170);        
	    }
	    
	    @Override
	    public void paintComponent(Graphics g) {
	        
	        super.paintComponent(g);
	        doDrawing(g);
	    }
	}
	
	
    public UnicodeEx() {
        
        initUI();
    }
    
    private void initUI() {
        
        setTitle("Unicode");

        add(new Surface());

        setSize(550, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                UnicodeEx ex = new UnicodeEx();
                ex.setVisible(true);
            }
        });        
    }
}