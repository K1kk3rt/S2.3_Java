package connectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import connectFour.GameModel.status;

public class ConnectFourGraphicView extends JFrame implements Observer{
	
	private GameModel game;
	private ConnectFourPanel connectFourPanel;
	private JPanel topBar;
	private JButton save;
	private JButton load;
	private JButton reset;
	private JPanel bottomBar;
	private JLabel lblStatus;
	
	//getters
	public JButton getSaveButton() {return save;}
	public JButton getLoadButton() {return load;}
	public JButton getResetButton() {return reset;}
	
	//construct
	//start de weergave voor het window, geef een gamemodel mee zodat de methoden en properties van die klasse bereikbaar zijn
	//maak een nieuw connectfour panel aan. hier wordt het spel zelf weergeven. geef een instantie van deze klasse en het gamemodel mee
	//maak het window zelf, en vervolgens de top en bottom bar
	//voeg deze view toe als observer zodat deze ook geupdate wordt als een speler een zet doet.
	public ConnectFourGraphicView(GameModel game){
		this.game = game;
		
		connectFourPanel = new ConnectFourPanel(this, this.game);
		
		createWindow();
		createTopBar();
		createBottomBar();
		
		game.addObserver(this);
		
	}
	
	//implement interfaces
	//update de tekst van het label in de onderste bar, en de kleur aan de hand van de speler die aan de buurt is.
	@Override
	public void update(Observable o, Object arg) {
		lblStatus.setText(game.getPlayer() + " is aan de buurt!");
		setBottomBarColor();
		
	}
	
	//maak het window zelf
	//zet de breedte en de hoogte, en de layout, en andere belangrijke eigenschappen van het window
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
	
	//maak en vul de topbar met buttons
	private void createTopBar() {
		topBar = new JPanel();
		
		save = new JButton();
		save.addActionListener(new ActionListenerButtonController(this, game));
		load = new JButton();
		load.addActionListener(new ActionListenerButtonController(this, game));
		reset = new JButton();
		reset.addActionListener(new ActionListenerButtonController(this, game));
		
		//set button tekst
		save.setText("save");
		load.setText("load");
		reset.setText("reset");
		
		//add buttons to top bar
		topBar.add(save);
		topBar.add(load);
		topBar.add(reset);
		
		//add top bar to main window
		add(topBar, BorderLayout.PAGE_START);
	}
	
	//maak de bottombar en vul deze met een label
	//geef de bar ook een kleur
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
	
	//geef aan als er een speler gewonnen heeft in het label van de bottom bar
	public void setLabelGewonnen() {
		lblStatus.setText(game.getPlayer() + " heeft gewonnen!");
	}
	
	//geef aan als er gelijkspel is in het label van de bottom bar
	public void setLabelGelijkspel() {
		lblStatus.setText("Gelijkspel!");
	}
	
	//bepaal de kleur van de onderste bar aan de hand van de speler die aan de buurt is
	//de kleuren zijn gebaseerd op de kleuren van de muntjes van de spelers.
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
