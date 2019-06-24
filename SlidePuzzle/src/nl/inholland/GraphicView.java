package nl.inholland;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GraphicView extends JFrame implements Observer{
	
	private GameModel game;
	private PanelView panel;
		
	private JPanel topBar;
	private JButton resetButton;
	private JButton button2;
	private JPanel bottomBar;
	private JPanel bottomBarLeft;
	private JPanel bottomBarRight;
	private JLabel label1;
	private JLabel label2;

	public GraphicView(GameModel game) {
		this.game = game;
		
		panel = new PanelView(this.game, this);
		
		createWindow();
		createTopBar();
		createBottomBar();
		
		//update labels at launch
		updateLabels();
		
		game.addObserver(this);
	}
	
	private void createWindow() {
		//determine width and height (so that buttons are square)
		int width = panel.getButtonSize() * game.getMATRIX();
		int height = (panel.getButtonSize() * game.getMATRIX()) + 50;
		
		setSize(width, height);
		setLayout(new BorderLayout());

		//set location of window in middle of screen
		Toolkit tk = Toolkit.getDefaultToolkit();	
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		setLocation(xPos, yPos);

		setResizable(false);
		setTitle("Slide Puzzle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add panel to main window
		add(panel, BorderLayout.CENTER);

		setVisible(true);
	}
	
	//maak en vul de topbar met buttons
	private void createTopBar() {
		topBar = new JPanel();
		
		resetButton = new JButton();
		button2 = new JButton();
		
		//set button tekst
		resetButton.setText("reset");
		button2.setText("button2");
		
		//add buttons to top bar
		topBar.add(resetButton);
		topBar.add(button2);
		
		//add top bar to main window
		add(topBar, BorderLayout.PAGE_START);
	}
	
	private void createBottomBar() {
		bottomBar = new JPanel(new BorderLayout());
		bottomBarLeft = new JPanel(new BorderLayout());
		bottomBarRight = new JPanel(new BorderLayout());
		
		//divide bottomBar in two panels
		bottomBarLeft.setPreferredSize(new Dimension((panel.getButtonSize() * game.getMATRIX() / 2), 30));
		bottomBarRight.setPreferredSize(new Dimension((panel.getButtonSize() * game.getMATRIX() / 2), 30));

		
		label1 = new JLabel();
		label1.setText("label 1");
		label1.setHorizontalAlignment(JLabel.CENTER);
		
		label2 = new JLabel();
		label2.setText("label 2");
		label2.setHorizontalAlignment(JLabel.CENTER);
		
		//add label to panel
		bottomBar.add(bottomBarLeft, BorderLayout.LINE_START);
		bottomBar.add(bottomBarRight, BorderLayout.LINE_END);
		bottomBarLeft.add(label1, BorderLayout.CENTER);
		bottomBarRight.add(label2, BorderLayout.CENTER);
		
		//add panel to main window
		add(bottomBar, BorderLayout.PAGE_END);
	}
	
	public void showDialogWon() {
		String ObjButtons[] = {"Restart","Close"};
		int PromptResult = JOptionPane.showOptionDialog(null, "You Won! Do you want to restart?", "SlidePuzzle", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
				ObjButtons,ObjButtons[1]);
		if(PromptResult==0)
		{
			//yes: restart
			game.restartGame();

		}
		if(PromptResult==1) {
			//no: close game
			System.exit(0);
    			    	
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateLabels();
	}
	
	private void updateLabels() {
		//update labels
		label1.setText("correct Tiles: " + game.getCorrectTiles());
		label2.setText("Slides: " + game.getSlides());
	}
	

}
