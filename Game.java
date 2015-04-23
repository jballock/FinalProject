package firstPack;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener{
	public static final Color PLAYER1 = CharacterSelect.one;
	public static final Color PLAYER2 = CharacterSelect.two;
	
	public static boolean leftPressed = false;
	public static boolean rightPressed = false;
	
	public static int ballx = 100;
	public static int bally = 250;
	public static int vely = 0;
	public static boolean inAir = true;
	public static boolean leftBlock = false;
	public static boolean rightBlock = false;
	
	public static boolean leftPressed2 = false;
	public static boolean rightPressed2 = false;
	public static int ball2x = 700;
	public static int ball2y = 250;
	public static int vel2y = 0;
	public static boolean inAir2 = true;
	public static boolean shootleft = false;
	public static boolean shootright = false;
	
	public static boolean proj = false;
	public static int projx = 0;
	public static int projy = 0;
	
	public static int red = 0;
	public static int green = 0;
	
	public Game(){
        setBackground(Color.BLACK);
        
        addKeyBinding();
        
        requestFocusInWindow();
        setFocusable(true);
        
        Timer timer = new Timer(1000/60, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(inAir){
        	vely = vely + 2;
        }
        if(inAir2){
        	vel2y = vel2y + 2;
        }
        if(!inAir && (rightBlock || leftBlock)){
        	leftPressed = false;
        	rightPressed =false;
        }
        if(bally >= this.getHeight()){
        	inAir = false; 
        	bally = this.getHeight() -22;
        	vely = 0;
        }
        else{
        	bally = bally + vely;
        }
        if(ball2y >= this.getHeight()){
        	inAir2 = false; 
        	ball2y = this.getHeight() -22;
        	vel2y = 0;
        }
        else{
        	ball2y = ball2y + vel2y;
        }
        if(leftPressed){
        	if(ballx -7 > 0){
        		ballx = ballx -7;	
        	}
        	else{
        		ballx = -1;	
        	}
        }
        else if(rightPressed){
        	if(ballx +7 < this.getWidth() -50){
        		ballx = ballx +7;	
        	}
        	else{
        		ballx = this.getWidth() -50;
        	}
        }
        if(leftPressed2){
        	if(ball2x -7 > 0){
        		ball2x = ball2x -7;	
        	}
        	else{
        		ball2x = -1;	
        	}
        }
        else if(rightPressed2){
        	if(ball2x +7 < this.getWidth() -50){
        		ball2x = ball2x +7;	
        	}
        	else{
        		ball2x = this.getWidth() -50;
        	}
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(PLAYER1);
        g2d.fillArc(ballx, bally, 50, 45, 0, 180);
        if(leftBlock){
        	g2d.fillRect(ballx -4, bally +2, 3, 13);
        	if(ball2y > bally -4 && bally + 6 >= ball2y && ball2x + 51 > ballx && ball2x < ballx ){
        		reset();
        		green++;
        	}
        }
        else if(rightBlock){
        	g2d.fillRect(ballx +51, bally +2, 3, 13);
        	if(ball2y > bally -4 && bally + 6 >= ball2y  && ball2x > ballx && ball2x < ballx +51){
        		reset();
        		green++;
        	}
        }
        g2d.setColor(PLAYER2);
        g2d.fillArc(ball2x, ball2y, 50, 45, 0, 180);
        if(proj && shootleft){
        	projx = projx - 35;
        	if(projx < 0){
        		proj = false;
        		shootleft = false;
        	}
        	else if(!rightBlock && projy > bally && bally +16 >= projy && projx > ballx && projx < ballx+50){
        		reset();
        		red++;
        	}
        	else if(rightBlock && projy > bally && bally +16 >= projy && projx > ballx && projx < ballx+50){
        		proj = false;
        		shootleft = false;
        	}
        	else{
        		g2d.fillRect(projx, projy, 7, 3);
        	}	
        }
        else if(proj && shootright){
        	projx = projx + 35;
        	if(projx > this.getWidth()){
        		proj = false;
        		shootright = false;
        	}
        	else if(!leftBlock && projy > bally && bally +16 >= projy && projx+7 > ballx && projx < ballx+50){
        		reset();
        		red++;
        	}
        	else if(leftBlock && projy > bally && bally +16 >= projy && projx+7 > ballx && projx < ballx+50){
        		proj = false;
        		shootright = false;
        	}
        	else{
        		g2d.fillRect(projx, projy, 7, 3);
        
        	}
        }
        g2d.setColor(PLAYER1);
        g2d.drawString(Integer.toString(green), 100, 50);
        g2d.setColor(PLAYER2);
        g2d.drawString(Integer.toString(red), 700, 50);
    }
    public static void reset(){
		leftPressed = false;
		rightPressed = false;
		ballx = 100;
		bally = 250;
		vely = 0;
		inAir = true;
		leftBlock = false;
		rightBlock = false;
		
		leftPressed2 = false;
		rightPressed2 = false;
		ball2x = 700;
		ball2y = 250;
		vel2y = 0;
		inAir2 = true;
		shootleft = false;
		shootright = false;
		
		proj = false;
    }
    public void change(){
    	repaint();
    }
    void addKeyBinding() {
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left pressed");
        getActionMap().put("left pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	leftPressed = true;
            }
        });

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "left released");
        getActionMap().put("left released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	leftPressed = false;
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right pressed");
        getActionMap().put("right pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	rightPressed = true;
            }
        });

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "right released");
        getActionMap().put("right released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	rightPressed = false;
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up pressed");
        getActionMap().put("up pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if(!inAir && !rightBlock && !leftBlock){
                	vely = -30;
                	inAir = true;
                }
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0, false), "leftblock pressed");
        getActionMap().put("leftblock pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	 if(!rightBlock){
                 	leftBlock =  true;
                 }
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0, true), "leftblock released");
        getActionMap().put("leftblock released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	leftBlock = false;
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0, false), "rightblock pressed");
        getActionMap().put("rightblock pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	 if(!leftBlock){
                 	rightBlock =  true;
                 }
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0, true), "rightblock released");
        getActionMap().put("rightblock released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	rightBlock = false;
            }
        });
        
        
        
        
        
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "left2 pressed");
        getActionMap().put("left2 pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	leftPressed2 = true;
            }
        });

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "left2 released");
        getActionMap().put("left2 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	leftPressed2 = false;
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "right2 pressed");
        getActionMap().put("right2 pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	rightPressed2 = true;
            }
        });

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "right2 released");
        getActionMap().put("right2 released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	rightPressed2 = false;
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "up2 pressed");
        getActionMap().put("up2 pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if(!inAir2 && !rightBlock && !leftBlock){
                	vel2y = -30;
                	inAir2 = true;
                }
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "leftblock2 pressed");
        getActionMap().put("leftblock2 pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if(!proj){
                	proj =  true;
                	projx = ball2x;
                	projy = ball2y +6;
                	shootleft = true;
                }
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "rightblock2 pressed");
        getActionMap().put("rightblock2 pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if(!proj){
                 	proj =  true;
                 	projx = ball2x +50;
                 	projy = ball2y +6;
                 	shootright = true;
                 }
            }
        });        
        
    }
	public void actionPerformed(ActionEvent e) {
		change();
		
	}

}
