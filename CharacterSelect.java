package firstPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CharacterSelect extends JPanel implements ActionListener, MouseListener{
	/*
	 * Known Problems:
	 *  
	 */	
	public static Color one;
	public static Color two;
	public static Color three;
	public static Color four;
	
	public Image image;
	public Image background = MainClass.background;
	public Image oneSelect;
	public Image twoSelect;
	public Image threeSelect;
	public Image fourSelect;
	public Image ready = MainClass.ready;
	
	
	public boolean playerOneSelect = false;
	public boolean playerTwoSelect = false;
	public boolean playerThreeSelect = false;
	public boolean playerFourSelect = false;
	
	public boolean allSelect = false;
	
	public int selectedCharacter1 = -1;
	public int selectedCharacter2 = -1;
	public int selectedCharacter3 = -1;
	public int selectedCharacter4 = -1;
	
	public Map<Integer, BufferedImage> fileMap = MainClass.hash;
	
	public int timer = 0;
	
	public static Font f = new Font("Garamond", Font.BOLD , 24);
	public CharacterSelect(){ 
        setBackground(Color.WHITE);
        setFocusable(true);
        addMouseListener(this);

        Timer time = new Timer(1000/60, this);
        time.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if((selectedCharacter4 != -1) && (selectedCharacter3 != -1) && (selectedCharacter2 != -1) && (selectedCharacter1 != -1)){
        	allSelect = true;
        }
        g.drawImage(background, 60, 40, 700, 200, null);
        if(selectedCharacter1 != -1){               
        	g.drawImage(oneSelect, 40, 290, 125, 150, null);
        }
        if (selectedCharacter2 != -1){               
        	g.drawImage(twoSelect, 248, 290, 125, 150, null);
        }
		if (selectedCharacter3 != -1){              
			g.drawImage(threeSelect, 456, 290, 125, 150, null);
		}
		if (selectedCharacter4 != -1){             
			g.drawImage(fourSelect, 665, 290, 125, 150, null);
		}
		if (allSelect){              
			g.drawImage(ready, 0, 200, 850, 50, null);
			g2d.drawRect(0, 200, 850, 50);
			if(timer > 10){
	        	timer = 0;
			}
			else if (timer > 5){
				g2d.setColor(Color.RED);
	        	g2d.setFont(f);
	        	g2d.drawString("Click here to start the match!", 260, 240);
	        	timer++;
			}
			else{
				timer++;
			}			
		}
		g2d.setColor(Color.BLACK);
        g2d.drawRect(40, 290, 125, 150);
        g2d.drawRect(248, 290, 125, 150);
        g2d.drawRect(456, 290, 125, 150);
        g2d.drawRect(665, 290, 125, 150);
    }
    public static BufferedImage toBufferedImage(Image img)
    {
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
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
				setFocusable(false);
				removeMouseListener(this);
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
			if(playerOneSelect){
				selectedCharacter1 = checker;
				oneSelect = fileMap.get(checker).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
				playerOneSelect = false;
				one = new Color(toBufferedImage(oneSelect).getRGB(0, 0));
			}
			else if (playerTwoSelect){
				selectedCharacter2 = checker;
				twoSelect = fileMap.get(checker).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
				playerTwoSelect = false;
				two = new Color(toBufferedImage(twoSelect).getRGB(0, 0));
			}
			else if (playerThreeSelect){
				selectedCharacter3 = checker;
				threeSelect = fileMap.get(checker).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
				playerThreeSelect = false;
			}
			else if (playerFourSelect){
				selectedCharacter4 = checker;
				fourSelect = fileMap.get(checker).getScaledInstance(125, 150, Image.SCALE_SMOOTH);
				playerFourSelect = false;
			}
    	}
		else if(arg0.getX() >= 40 && arg0.getX() <= 165 && arg0.getY() >= 290 && arg0.getY() <= 440){
			playerOneSelect = true;
    	}
		else if(arg0.getX() >= 248 && arg0.getX() <= 373 && arg0.getY() >= 290 && arg0.getY() <= 440){
			playerTwoSelect = true;
    	}
		else if(arg0.getX() >= 456 && arg0.getX() <= 581 && arg0.getY() >= 290 && arg0.getY() <= 440){
			playerThreeSelect = true;
    	}
		else if(arg0.getX() >= 665 && arg0.getX() <= 790 && arg0.getY() >= 290 && arg0.getY() <= 440){
			playerFourSelect = true;
    	}
		
	}
}