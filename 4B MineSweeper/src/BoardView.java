import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class BoardView extends JFrame{
	
	JPanel mainPanel;
	BoardPanelView boardPanel;

	//construct
	public BoardView() {
		mainPanel = new JPanel();
		boardPanel = new BoardPanelView();
		
		createWindow();
	}
	
	public static void main(String[] args) {
		new BoardView();
	}
	
	private void createWindow() {
		
		setSize(1400,1200);

		//set location of window in middle of screen
		Toolkit tk = Toolkit.getDefaultToolkit();	
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		setLocation(xPos, yPos);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Minesweeper");
		
		//add panel to frame
		add(mainPanel);
		mainPanel.add(boardPanel);

		setVisible(true);
	}

}
