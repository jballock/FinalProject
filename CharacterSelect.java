package firstPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CharacterSelect extends JPanel implements ActionListener, MouseListener{
	/*
	 * Known Problems:
	 * 	- Really slow averaging 10 FPS distorting flashing text
	 * 
	 */	
	public Image image;
	
	public boolean playerOneSelect = false;
	public boolean playerTwoSelect = false;
	public boolean playerThreeSelect = false;
	public boolean playerFourSelect = false;
	
	public boolean allSelect = false;
	
	public int selectedCharacter1 = -1;
	public int selectedCharacter2 = -1;
	public int selectedCharacter3 = -1;
	public int selectedCharacter4 = -1;
	
	public Map<Integer, Image> fileMap = new HashMap<>();
	
	public int timer = 0;
	
	public static Font f = new Font("Garamond", Font.BOLD , 24);
	
	public CharacterSelect(){
		makeImages();
        setBackground(Color.WHITE);
        setFocusable(true);
        addMouseListener(this);
        
        requestFocusInWindow();
        
        Timer timer = new Timer(1000/60, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int c = 0;
        if((selectedCharacter4 != -1) && (selectedCharacter3 != -1) && (selectedCharacter2 != -1) && (selectedCharacter1 != -1)){
        	allSelect = true;
        }
        for(int y = 0; y < 200; y += 100){
        	for(int x = 0; x < 700; x += 100){
        		c++;
        	    image = fileMap.get(c).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            	//g2d.drawRect(x+60, y+40, 100, 100);
            	g.drawImage(image, x+60, y+40, 100, 100, null);
            }
        }
        if(selectedCharacter1 != -1){               
     	    image = fileMap.get(selectedCharacter1).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
        	g.drawImage(image, 40, 290, 125, 150, null);
        }
        if (selectedCharacter2 != -1){               
      	    image = fileMap.get(selectedCharacter2).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
        	g.drawImage(image, 248, 290, 125, 150, null);
        }
		if (selectedCharacter3 != -1){              
	     	image = fileMap.get(selectedCharacter3).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
			g.drawImage(image, 456, 290, 125, 150, null);
		}
		if (selectedCharacter4 != -1){             
	     	image = fileMap.get(selectedCharacter4).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
			g.drawImage(image, 665, 290, 125, 150, null);
		}
		if (allSelect){              
	     	image = fileMap.get(0).getScaledInstance(850, 50, Image.SCALE_SMOOTH);
			g.drawImage(image, 0, 200, 850, 50, null);
			g2d.drawRect(0, 200, 850, 50);
			if(timer > 6){
	        	timer = 0;
			}
			else if (timer > 3){
				g2d.setColor(Color.RED);
	        	g2d.setFont(f);
	        	g2d.drawString("Click here to start the match!", 260, 240);
	        	timer++;
			}
			else{
				timer++;
			}
			//System.out.println(timer);
			
		}
		
		g2d.setColor(Color.BLACK);
        g2d.drawRect(40, 290, 125, 150);
        g2d.drawRect(248, 290, 125, 150);
        g2d.drawRect(456, 290, 125, 150);
        g2d.drawRect(665, 290, 125, 150);
    }
    public void makeImages(){
    	for(int i = 0; i < 14; i++){
			try {
				fileMap.put((i+1), ImageIO.read(new File("src\\firstPack\\GameStuff\\pic"+ (i+1) +".png")));
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		try {
			fileMap.put(0, ImageIO.read(new File("src\\firstPack\\GameStuff\\ready.png")));
		} catch (IOException e) {
			System.out.println(e);
		}
    }
    public void change(){
    	repaint();
    }
	public void actionPerformed(ActionEvent arg0) {
		change();	
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {		
	}
	public void mouseReleased(MouseEvent arg0) {	
		int checker = -1;
		int counter = 1;
		if(allSelect){
			if(arg0.getX() >= 0 && arg0.getX() <= 850 && arg0.getY() >= 200 && arg0.getY() <= 250){
				//System.out.println("Moving on!");
				Game panel1 = new Game();
				MainClass.changePanel(panel1);
	    	}
		}
		for(int y = 40; y < 200; y += 100){
        	for(int x = 60; x < 700; x += 100){
            	if(arg0.getX() >= x && arg0.getX() <= x+100 && arg0.getY() >= y && arg0.getY() <= y+100){
            		checker = counter;
            		break;
            	}
            	counter++;
            }
        	if(checker == counter+1){
        		break;
        	}
        }
		if(checker != -1){
			//System.out.println("Caught in Sector " + checker);
			if(playerOneSelect){
				//System.out.println("Character Set 1!");
				selectedCharacter1 = checker;
				playerOneSelect = false;
			}
			else if (playerTwoSelect){
				//System.out.println("Character Set 2!");
				selectedCharacter2 = checker;
				playerTwoSelect = false;
			}
			else if (playerThreeSelect){
				//System.out.println("Character Set 3!");
				selectedCharacter3 = checker;
				playerThreeSelect = false;
			}
			else if (playerFourSelect){
				//System.out.println("Character Set 4!");
				selectedCharacter4 = checker;
				playerFourSelect = false;
			}
    	}
		else if(arg0.getX() >= 40 && arg0.getX() <= 165 && arg0.getY() >= 290 && arg0.getY() <= 440){
			playerOneSelect = true;
			//System.out.println("Player one Select!");
    	}
		else if(arg0.getX() >= 248 && arg0.getX() <= 373 && arg0.getY() >= 290 && arg0.getY() <= 440){
			playerTwoSelect = true;
			//System.out.println("Player two Select!");
    	}
		else if(arg0.getX() >= 456 && arg0.getX() <= 581 && arg0.getY() >= 290 && arg0.getY() <= 440){
			playerThreeSelect = true;
			//System.out.println("Player three Select!");
    	}
		else if(arg0.getX() >= 665 && arg0.getX() <= 790 && arg0.getY() >= 290 && arg0.getY() <= 440){
			playerFourSelect = true;
			//System.out.println("Player four Select!");
    	}
	}
}