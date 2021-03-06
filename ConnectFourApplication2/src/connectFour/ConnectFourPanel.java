package connectFour;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ConnectFourPanel extends JPanel implements Observer{
	
	private GameModel game;
	private ConnectFourGraphicView view;
	private JButton[][] grid;
	private final int BUTTONWIDTH = 100;
	private final int BUTTONHEIGHT = 100;
	private Image afbLeeg;
	private Image afbSpeler1;
	private Image afbSpeler2;
	
	
	//getters
	public int getButtonWidth() {
		return BUTTONWIDTH;
	}
	public int getButtonHeight() {
		return BUTTONHEIGHT;
	}
	public JButton[][] getButtonGrid(){
		return grid;
	}
	
	//construct
	//geef de graphic view en gamemodel game mee zodat deze methoden en properties bereikt kunnen worden
	//voeg het panel toe als observer, zodat deze geupdate wordt als een speler een zet doet.
	//maak een grid van jbuttons aan de hand van het aantal rijen en kolommen in gamemodel
	//laad de afbeeldingen zodat deze in de hele class gebruikt kunnen worden en maar 1 keer geladen hoeven worden
	//maar het spel klaar om gespeeld te worden
	public ConnectFourPanel(ConnectFourGraphicView view, GameModel game) {
		
		//set layout
		setLayout(new GridLayout(game.getRijen(),game.getKolommen()));
		
		this.game = game;
		this.view = view;
		this.game.addObserver(this);
		
		grid = new JButton[game.getRijen()][game.getKolommen()];
		
		laadAfbeeldingen();
		initGame();
		
	}
	
	//implement interfaces
	//weergeef het spel op het moment dat een speler een zet doet
	@Override
	public void update(Observable o, Object arg) {
		
		displayGame();
		
	}
	
	//maak de weergave van het spel klaar om te spelen
	//loop door de jbutton grid en maak een nieuwe jbutton aan. bepaal dan de achtergrond en zet een actionlistener voor het klikken.
	//voeg de button toe aan het grid
	private void initGame() {
		
		for (int rij = 0; rij<grid.length; rij++){
		     for (int kolom = 0; kolom<grid[rij].length; kolom++){
		    	 grid[rij][kolom] = new JButton();
		    	 setButtonBackground(rij, kolom);
		    	 grid[rij][kolom].addActionListener(new ActionListenerGameController(this, this.game));
		    	 add(grid[rij][kolom]);
		     }
		}
	}
	
	//bepaal de achtergrond van een button aan de hand van de status van die locatie in het grid
	//de parameters rij en kolom geven aan van welke button de achtergrond verandert moet worden
	private void setButtonBackground(int rij, int kolom) {
		
		grid[rij][kolom].setBorder(null);
		
		switch(game.getGrid()[rij][kolom]) {
		  case player1:
			  grid[rij][kolom].setIcon(new ImageIcon(afbSpeler1));
		    break;
		  case player2:
			  grid[rij][kolom].setIcon(new ImageIcon(afbSpeler2));
			break;
		  case isEmpty:
			  grid[rij][kolom].setIcon(new ImageIcon(afbLeeg));
			break;
		  default:
			  //grid[rij][kolom].setIcon(new ImageIcon(afbLeeg));
		}
	}
	
	//laad de afbeeldingen voor op de achtergrond van de buttons en sla deze op in variabelen in de klasse. 
	//vang errors op met de try catch
	private void laadAfbeeldingen() {
		try {
			Image leeg = ImageIO.read(getClass().getResource("/assets/cell_empty.png"));
			Image speler1 = ImageIO.read(getClass().getResource("/assets/cell_player2.png"));
			Image speler2 = ImageIO.read(getClass().getResource("/assets/cell_player1.png"));
			
			afbSpeler1 = speler1.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
			afbSpeler2 = speler2.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
			afbLeeg = leeg.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//weergeef het spel aan de hand van de status van de huidige rij en kolom
	//op deze manier hoeft er 1 jbutton verandert te worden, en niet allemaal.
	//zet de buttonbackground, en controleer aan de hand van bepaald variablen of er nog andere dingen moeten gebeuren.
	private void displayGame() {
		int rij = game.getCurrentRij();
		int kolom = game.getCurrentKolom();
		
		setButtonBackground(rij, kolom);
		
		if(game.getGewonnen()) {
			gewonnen();
		}
		if(game.getGelijkspel()) {
			gelijkspel();
		}
		if(game.getRestart()) {
			restart();
		}
	}
	
	//geef aan wie er gewonnen heeft
	private void gewonnen() {
		view.setLabelGewonnen();
	}
	
	//geef aan of het gelijkspel is
	private void gelijkspel() {
		view.setLabelGelijkspel();
	}
	
	
	//restart de weergave van het spel. zet de achtergrond van alle buttons weer op "leeg"
	private void restart() {
		for (int rij = 0; rij<grid.length; rij++){
		     for (int kolom = 0; kolom<grid[rij].length; kolom++){
		    	 setButtonBackground(rij, kolom);
		     }
		}
	}
	
}
