package connectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import connectFour.GameModel.status;

public class ConnectFourGraphicView extends JFrame implements Observer{
	
	GameModel game;
	ConnectFourPanel connectFourPanel;
	private JPanel topBar;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JPanel bottomBar;
	private JLabel lblStatus;
	
	//construct
	public ConnectFourGraphicView(GameModel game){
		this.game = game;
		
		connectFourPanel = new ConnectFourPanel(this, this.game);
		
		createWindow();
		createTopBar();
		createBottomBar();
		
		game.addObserver(this);
		
	}
	
	//implement interfaces
	@Override
	public void update(Observable o, Object arg) {
		lblStatus.setText(game.getPlayer() + " is aan de buurt!");
		setBottomBarColor();
		
	}
	
	private void createWindow() {
		//determine width and height (so that buttons are square)
		int width = connectFourPanel.getButtonWidth() * game.getKolommen();
		int height = (connectFourPanel.getButtonHeight() * game.getRijen()) + 50;
		
		setSize(width, height);
		setLayout(new BorderLayout());

		//set location of window in middle of screen
		Toolkit tk = Toolkit.getDefaultToolkit();	
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		setLocation(xPos, yPos);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Connect Four");
		
		//add panel to main window
		add(connectFourPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	private void createTopBar() {
		topBar = new JPanel();
		
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		
		//set button tekst
		button1.setText("button1");
		button2.setText("button2");
		button3.setText("button3");
		
		//add buttons to top bar
		topBar.add(button1);
		topBar.add(button2);
		topBar.add(button3);
		
		//add top bar to main window
		add(topBar, BorderLayout.PAGE_START);
	}
	
	private void createBottomBar() {
		bottomBar = new JPanel();
		
		lblStatus = new JLabel();
		lblStatus.setText("player 1 is aan de buurt!");
		
		setBottomBarColor();
		
		//add label to panel
		bottomBar.add(lblStatus);
		
		//add panel to main window
		add(bottomBar, BorderLayout.PAGE_END);
	}
	
	public void setLabelGewonnen() {
		lblStatus.setText(game.getPlayer() + " heeft gewonnen!");
	}
	
	public void setLabelGelijkspel() {
		lblStatus.setText("Gelijkspel!");
	}
	
	private void setBottomBarColor() {
		//player1 #80D5FF
		//player2 #006597
		
		Color player1 = new Color(128,213,255);
		Color player2 = new Color(0,101,151);
		
		if(game.getPlayer() == status.player1) {
			bottomBar.setBackground(player2);
		}
		if(game.getPlayer() == status.player2) {
			bottomBar.setBackground(player1);
		}
		
	}
	
}
