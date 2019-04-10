package connectFour;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
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
	private Color speler1kleur;
	private Color speler2kleur;
	
	
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
	public Color getSpeler1Kleur() {
		return speler1kleur;
	}
	public Color getSpeler2Kleur() {
		return speler2kleur;
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
		    	 grid[rij][kolom].addActionListener(new ActionListenerController(this, this.game));
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
	
	//laad de kleuren voor op de afbeeldingen voor op de achtergrond van de buttons en sla deze op in variabelen in de klasse. 
	private void laadAfbeeldingen() {
			
			kiesKleurVakjes();
			
			afbSpeler1 = maakVakje(speler1kleur);
			afbSpeler2 = maakVakje(speler2kleur);
			afbLeeg = maakVakje(Color.WHITE);
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
	
	//vraag de speler om de kleur die hij wilt zijn, en sla dit op
	public void kiesKleurVakjes() {
        speler1kleur = JColorChooser.showDialog(null, "Speler1: Kies een kleur om mee te spelen", getBackground());
        do {
        	speler2kleur = JColorChooser.showDialog(null, "Speler1: Kies een kleur om mee te spelen", getBackground());
        }
        while(speler1kleur == speler2kleur);
	}
	

	//maak aan de hand van de gekozen kleuren de vakjes 
	private BufferedImage maakVakje(Color kleur) {
		BufferedImage rondAfb = new BufferedImage(BUTTONWIDTH, BUTTONHEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) rondAfb.createGraphics();
		g.setColor(kleur);
		g.fillOval(15, 15, 70, 70);
		return rondAfb;
	}
	
}
