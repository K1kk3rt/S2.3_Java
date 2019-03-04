import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.*;

public class View extends JFrame{

	//construct
	public View() {
			
		createWindow();
		
		createContent();
	}
	
	private void createWindow() {
		
		this.setSize(400,400);
		
		//set location of window in middle of screen
		Toolkit tk = Toolkit.getDefaultToolkit();	
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		setLocation(xPos, yPos);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Alarm clock");
		
		setVisible(true);
	}
	
	private void createContent() {
		
		JPanel p = new JPanel();
		JLabel lTitle = new JLabel("I'm A clock");
		
		p.add(lTitle);
	}
}
