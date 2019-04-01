import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class BoardController extends JFrame{
	
	JPanel mainPanel;
	BoardPanelView boardPanel;
	int width;
	int height;

	//construct
	public BoardController() {
		mainPanel = new JPanel();
		boardPanel = new BoardPanelView();
		
		width = boardPanel.game.getColumns()*45;
		height = boardPanel.game.getRows()*45;
		
		createWindow();
	}
	
	public static void main(String[] args) {
		new BoardController();
	}
	
	private void createWindow() {
		
		setSize(width, height + 200);

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
