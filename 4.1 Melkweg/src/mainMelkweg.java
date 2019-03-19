import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.Graphics;

public class mainMelkweg extends JFrame{
	
	MelkwegPanel panel;
	Graphics g;
	
	//construct
	public mainMelkweg() {
		
		panel = new MelkwegPanel();
		
		//generate window
		createWindow();
		
		//make and draw stars at random position
		panel.repaint();
		
	}

	public static void main(String[] args) {
		
		new mainMelkweg();
	}
	
	private void createWindow() {
		
		setSize(1200,800);

		//set location of window in middle of screen
		Toolkit tk = Toolkit.getDefaultToolkit();	
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		setLocation(xPos, yPos);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Melkweg");
		
		//set background color and add panel to frame
		panel.setBackground(Color.BLUE);
		add(panel);

		setVisible(true);
	}
}
