package firstPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener{
	public boolean leftPressed = false;
	public boolean rightPressed = false;

	public double ballx = 250;
	public double bally = 250;
	
	public double rot = 0;
	
	public int color = 0;
	public int red = 0;
	public int green = 0;
	public int blue = 0;
	
	public static List<Point> points = new ArrayList<Point>();
	
	public Game(){
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(1000/60, this);
        timer.start();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        for(Point point:points){
        	g2d.fillOval(point.x, point.y, 10, 10);
        }
    }
    public void change(){
    	if(ballx+10 > this.getWidth() || ballx < 0){
    		points.clear();
    		ballx = 250;
    		bally = 250;
    	}
    	else if(bally+10 > this.getHeight() || bally < 0){
    		points.clear();
    		ballx = 250;
    		bally = 250;
    	}
    	/*else if(red != 0 && blue != 0 && green != 0){
    		points.clear();
    		ballx = 250;
    		bally = 250;
    	}*/
    	ballx =  (ballx + ( 3 *  Math.sin(Math.toRadians(rot))));
    	bally =  (bally + (3 * Math.cos(Math.toRadians(rot))));
    	
    	DecimalFormat form = new DecimalFormat("#.##");
    	ballx = Double.parseDouble(form.format(ballx));
    	bally = Double.parseDouble(form.format(bally));
    	
    	Point p = new Point();
    	p.setLocation(ballx, bally);
    	points.add(p);
    	if(leftPressed){
    		rot = rot + 3;
    	}
    	if(rightPressed){
    		rot = rot - 3;
    	}
    	repaint();
    }
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
		
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent e) {
			change();
		
	}

}
