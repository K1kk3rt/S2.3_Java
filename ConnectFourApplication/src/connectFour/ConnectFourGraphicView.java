package connectFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import connectFour.GameModel.status;

public class ConnectFourGraphicView extends JFrame implements Observer{
	
	private GameModel game;
	private ConnectFourPanel connectFourPanel;
	private FileController fileController;
	private TimerController timerController;
	private JPanel topBar;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JPanel bottomBar;
	private JLabel lblStatus;
	private JLabel lblRonde;
	private JLabel lblTijd;
	
	//construct
	//start de weergave voor het window, geef een gamemodel mee zodat de methoden en properties van die klasse bereikbaar zijn
	//maak een nieuw connectfour panel aan. hier wordt het spel zelf weergeven. geef een instantie van deze klasse en het gamemodel mee
	//maak een aantal onderdelen en stel instellingen in via methodes. geef labels een waarde via methodes
	//start de timer
	//voeg deze view toe als observer zodat deze ook geupdate wordt als een speler een zet doet.
	public ConnectFourGraphicView(GameModel game){
		this.game = game;
		
		connectFourPanel = new ConnectFourPanel(this, this.game);
		timerController = new TimerController(game, this);
		fileController = new FileController(game, timerController);
		
		createWindow();
		createTopBar();
		createBottomBar();
		setCloseOperation();
		showDialogAtOpen();
		
		setLabelTijd(timerController.getMinutes(),timerController.getSeconds());
		setLabelAantalFishes();
		
		//start timer
		Timer tick = new Timer(1000, timerController);
        tick.start();
		
		game.addObserver(this);
		
	}
	
	//implement interfaces
	//update de tekst van het label in de onderste bar, en de kleur aan de hand van de speler die aan de buurt is.
	//update het aantal fishes
	@Override
	public void update(Observable o, Object arg) {
		lblStatus.setText(game.getPlayer() + " is aan de buurt!");
		setBottomBarColor();
		setLabelAantalFishes();
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
		setTitle("Connect Four");
		
		//add panel to main window
		add(connectFourPanel, BorderLayout.CENTER);

		setVisible(true);
	}
	
	//maak een popup die komt als de applicatie gesloten wordt.
	//ja is opslaan en dan afsluiten
	//nee is afsluiten zonder op te slaan
	//cancel is niks doen
	private void setCloseOperation() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Ja","Nee", "Cancel"};
			    int PromptResult = JOptionPane.showOptionDialog(null, "Moet het spel worden opgeslagen?", "ConnectFour", 
			    		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			    		ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			    	//ja
			    	fileController.saveGameToFile();
			    	System.exit(0);
			    }
			    if(PromptResult==1) {
			    	//nee
			    	System.exit(0);          			    	
			    }
			    if(PromptResult==2) {
			    	//cancel
			    }
			  }
		});
	}
	
	//maak en vul de topbar met buttons
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
	
	//maak de bottombar en vul deze met labels
	//geef de bar ook een kleur
	private void createBottomBar() {
		bottomBar = new JPanel(new BorderLayout());
		
		lblStatus = new JLabel();
		lblStatus.setText("player 1 is aan de buurt!");
		lblStatus.setHorizontalAlignment(JLabel.CENTER);
		
		lblRonde = new JLabel();
		lblRonde.setText("0 fishes");
		
		lblTijd = new JLabel();
		lblTijd.setText("verstreken tijd: ");
		
		setBottomBarColor();
		
		//add label to panel
		bottomBar.add(lblStatus, BorderLayout.CENTER);
		bottomBar.add(lblRonde, BorderLayout.LINE_START);
		bottomBar.add(lblTijd, BorderLayout.LINE_END);
		
		//add panel to main window
		add(bottomBar, BorderLayout.PAGE_END);
	}
	
	//deze popup wordt weergeven bij het openen van de applicatie wanneer er een savegame is
	//ja: er wordt een opgeslagen spel geladen
	//nee: er wordt een nieuw spel geladen (standaard)
	private void showDialogAtOpen() {
		
		if(fileController.checkIfSaveGameExists()) {
			String ObjButtons[] = {"Ja","Nee"};
			int PromptResult = JOptionPane.showOptionDialog(null, "Er is een opgeslagen spel, moet deze hervat worden?", "ConnectFour", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
					ObjButtons,ObjButtons[1]);
			if(PromptResult==0)
			{
				//ja: laad het spel en start daarmee
				fileController.loadGameFromFile();

			}
			if(PromptResult==1) {
				//nee: laad nieuw spel 
        			    	
			}
		}
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
		
		Color player1 = connectFourPanel.getSpeler1Kleur();
		Color player2 = connectFourPanel.getSpeler2Kleur();
		
		if(game.getPlayer() == status.player1) {
			bottomBar.setBackground(player2);
		}
		if(game.getPlayer() == status.player2) {
			bottomBar.setBackground(player1);
		}
		
	}
	
	//geef aan de hand van de ronde het aantal fishes
	private void setLabelAantalFishes() {
		lblRonde.setText(game.getRonde() + " fishes");
	}
	
	//weergeef de tijd
	public void setLabelTijd(int minuten, int seconden) {
		lblTijd.setText("verstreken tijd: " + minuten + ":" + seconden);
	}
}
