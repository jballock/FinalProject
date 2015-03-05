package firstPack;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;

public class MainClass {

	private static JFrame frame = new JFrame("Fight!");
	public static void main(String[] args) {
        frame.setTitle("Fighting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        MainMenu panel1 = new MainMenu();
        frame.getContentPane().add(panel1);
        //frame.add(panel1, BorderLayout.CENTER);
        frame.setSize(850, 500);
        frame.setVisible(true);    

	}
	public static void changePanel(Component c){
		frame.getContentPane().removeAll();
		frame.getContentPane().invalidate();
		frame.getContentPane().add(c);
		frame.getContentPane().validate();
		frame.getContentPane().setVisible(true);    
		//System.out.println("Changed Panel");
	}

}
