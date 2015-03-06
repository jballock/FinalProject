package firstPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MainMenu extends JPanel implements ActionListener, KeyListener{
	public static int x = 26;
	public static Font f = new Font("Garamond", Font.BOLD , 24);
	public MainMenu(){
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(1000/30, this);
        timer.start();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if(x > 50){
        	x = 0;
        }
        else if(x > 25){
        	g2d.setColor(Color.RED);
        	g2d.setFont(f);
        	g2d.drawString("Press Enter to Continue!", 300, 250);
        	x++;
        }
        else{
        	x++;	
        } 
    }
    public void change(){
    	repaint();
    }
    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {		
        }
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			CharacterSelect panel1 = new CharacterSelect();
			MainClass.changePanel(panel1);
		}			
	}
	public void keyTyped(KeyEvent e) {
		
	}
	public void actionPerformed(ActionEvent arg0) {
		change();
		
	}
}
