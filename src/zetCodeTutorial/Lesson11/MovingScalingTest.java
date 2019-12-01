package zetCodeTutorial.Lesson11;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovingScalingTest extends JFrame {

		class Surface extends JPanel {
			
			private RectangleKuba rectangle;
			private EllipseKuba ellipse;
			
			Surface() {
				initUI();
			}
			
			private void initUI() {
				MovingAdapter movingAdapter = new MovingAdapter();
				
				addMouseMotionListener(movingAdapter);
				addMouseListener(movingAdapter);
				addMouseWheelListener(new ScaleHandler());
				
				rectangle = new RectangleKuba(50,50,50,50);
				ellipse = new EllipseKuba(150,70,80,80);
			}
			
			private void doDrawing(Graphics g) {
				
				Graphics2D g2d = (Graphics2D) g.create();
				
				setBackground(Color.BLACK);
				
		        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
		        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		        
		        g2d.setPaint(Color.MAGENTA);
		        g2d.fill(rectangle);

		        g2d.setPaint(Color.YELLOW);
		        g2d.fill(ellipse);
				
			}
			
			@Override
			protected void paintComponent (Graphics g) {
				super.paintComponent(g);
				doDrawing(g);
			}
			
			class RectangleKuba extends Rectangle2D.Float {
				
				public RectangleKuba(float x, float y, float width, float height) {
					setFrame(x,y,width,height);
				}
				
				public boolean isHit(float x, float y) {
					return getBounds2D().contains(x,y);
				}
				
				public void addX(float x) {
		            
		            this.x += x;
		        }

		        public void addY(float y) {
		            
		            this.y += y;
		        }

		        public void addWidth(float w) {
		            
		            this.width += w;
		        }

		        public void addHeight(float h) {
		            
		            this.height += h;
		        }
				
				
			}
			
			class EllipseKuba extends Ellipse2D.Float {
				
				public EllipseKuba (float x, float y, float width, float height) {
					setFrame(x,y,width,height);
				}
				
				public boolean isHit(float x, float y) {
					return getBounds2D().contains(x,y);
				}
				
				public void addX(float x) {
		            
		            this.x += x;
		        }

		        public void addY(float y) {
		            
		            this.y += y;
		        }

		        public void addWidth(float w) {
		            
		            this.width += w;
		        }

		        public void addHeight(float h) {
		            
		            this.height += h;
		        }
			}
			
			class MovingAdapter extends MouseAdapter {
				
				private int x;
				private int y;
				
				@Override
				public void mousePressed(MouseEvent e) {
					
					x = e.getX();
					y = e.getY();
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					
					int dx = e.getX() - x;
					int dy = e.getY() - y;
					
					if(rectangle.isHit(x, y)) {
						rectangle.addX(dx);
						rectangle.addY(dy);
						repaint();
					}
					
					if(ellipse.isHit(x, y)) {
						ellipse.addX(dx);
						ellipse.addY(dy);
						repaint();
					}
					
					x += dx;
					y += dy;
				}
			}
			
			class ScaleHandler implements MouseWheelListener {

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					
					doScale(e);
				}
				
				private void doScale(MouseWheelEvent e) {
					
					int x = e.getX();
					int y = e.getY();
					
					if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
						
						if(rectangle.isHit(x, y)) {
							
							float ammount = e.getWheelRotation() * 5f;
							rectangle.addWidth(ammount);
							rectangle.addHeight(ammount);
							repaint();
						}
						
						if(ellipse.isHit(x, y)) {
							
							float ammount = e.getWheelRotation() * 5f;
							ellipse.addWidth(ammount);
							ellipse.addHeight(ammount);
							repaint();
						}
					}
				}
			}
		}
	
	
	public MovingScalingTest() {
		initUI();
	}
	
	private void initUI() {
		add(new Surface());
		
		setTitle("Test");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				MovingScalingTest t = new MovingScalingTest();
				t.setVisible(true);
			} 
		});
	}
}
