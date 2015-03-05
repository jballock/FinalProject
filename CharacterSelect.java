package firstPack;

import java.awt.Color;
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

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CharacterSelect extends JPanel implements ActionListener, MouseListener{
	public Image image;
	public boolean playerOneSelect = false;
	public boolean playerTwoSelect = false;
	public boolean playerThreeSelect = false;
	public boolean playerFourSelect = false;
	public int selectedCharacter1 = -1;
	public int selectedCharacter2 = -1;
	public int selectedCharacter3 = -1;
	public int selectedCharacter4 = -1;
	public CharacterSelect(){
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
        for(int y = 0; y < 200; y += 100){
        	for(int x = 0; x < 700; x += 100){
        		c++;
        		try {                
        	       image = ImageIO.read(new File("src\\firstPack\\GameStuff\\pic"+ c +".png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        		} catch (IOException ex) {
        	    }
            	//g2d.drawRect(x+60, y+40, 100, 100);
            	g.drawImage(image, x+60, y+40, 100, 100, null);
            }
        }
        if(selectedCharacter1 != -1){
        	try {                
     	       image = ImageIO.read(new File("src\\firstPack\\GameStuff\\pic"+ selectedCharacter1 +".png")).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
     		} catch (IOException ex) {
     	    }
        	g.drawImage(image, 40, 290, 125, 150, null);
        }
        if (selectedCharacter2 != -1){
        	try {                
      	       image = ImageIO.read(new File("src\\firstPack\\GameStuff\\pic"+ selectedCharacter2 +".png")).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
      		} catch (IOException ex) {
      	    }
        	g.drawImage(image, 248, 290, 125, 150, null);
        }
		if (selectedCharacter3 != -1){
			try {                
	     	       image = ImageIO.read(new File("src\\firstPack\\GameStuff\\pic"+ selectedCharacter3 +".png")).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
	     		} catch (IOException ex) {
	     	    }
			g.drawImage(image, 456, 290, 125, 150, null);
		}
		if (selectedCharacter4 != -1){
			try {                
	     	       image = ImageIO.read(new File("src\\firstPack\\GameStuff\\pic"+ selectedCharacter4 +".png")).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
	     		} catch (IOException ex) {
	     	    }
			g.drawImage(image, 665, 290, 125, 150, null);
		}
        g2d.drawRect(40, 290, 125, 150);
        g2d.drawRect(248, 290, 125, 150);
        g2d.drawRect(456, 290, 125, 150);
        g2d.drawRect(665, 290, 125, 150);
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