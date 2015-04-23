package firstPack;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainClass {
	public static Image background;
	public static Image ready;
	public static Map<Integer, BufferedImage> hash = new HashMap<>();
	private static JFrame frame = new JFrame("Fight!");
	public static void main(String[] args) {
		try {
			background = ImageIO.read(new File("src\\firstPack\\GameStuff\\total.png")).getScaledInstance(700, 200, Image.SCALE_SMOOTH);
			ready = ImageIO.read(new File("src\\firstPack\\GameStuff\\ready.png")).getScaledInstance(850, 50, Image.SCALE_SMOOTH);
		} catch (IOException e) {
		}
		for(int i = 0; i < 14; i++){
			try {
				hash.put((i+1), ImageIO.read(new File("src\\firstPack\\GameStuff\\pic"+ (i+1) +".png")));
			} catch (IOException e) {
				System.out.println(e);
			}
		}
        frame.setTitle("Fighting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        MainMenu panel1 = new MainMenu();
        frame.getContentPane().add(panel1);
        //frame.add(panel1, BorderLayout.CENTER);
        frame.setSize(850, 500);
        frame.setVisible(true); 
	}
	public static void pack(){
	}
	public static void changePanel(Component c){
		frame.getContentPane().removeAll();
		frame.getContentPane().invalidate();
		frame.getContentPane().add(c);
		frame.getContentPane().validate();
		frame.getContentPane().setVisible(true);
	}
}
