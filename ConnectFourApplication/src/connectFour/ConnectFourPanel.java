package connectFour;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

import connectFour.GameModel.status;

public class ConnectFourPanel extends JPanel implements Observer{
	
	private GameModel game;
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
	public ConnectFourPanel(GameModel game) {
		
		//set layout
		setLayout(new GridLayout(game.getRijen(),game.getKolommen()));
		
		this.game = game;
		this.game.addObserver(this);
		
		grid = new JButton[game.getRijen()][game.getKolommen()];
		
		laadAfbeeldingen();
		initGame();
		
	}
	
	//implement interfaces
	@Override
	public void update(Observable o, Object arg) {
		
		displayGame();
		
	}
	
	private void initGame() {
		
		for (int rij = 0; rij<grid.length; rij++){
		     for (int kolom = 0; kolom<grid[rij].length; kolom++){
		    	 grid[rij][kolom] = new JButton();
		    	 setButtonBackground(grid[rij][kolom], rij, kolom);
		    	 grid[rij][kolom].addActionListener(new ActionListenerController(this, this.game));
		    	 add(grid[rij][kolom]);
		     }
		}
	}
	
	private void setButtonBackground(JButton button, int rij, int kolom) {
		
		button.setBorder(null);
		
		if(game.getGrid()[rij][kolom] == status.player1) {
			button.setIcon(new ImageIcon(afbSpeler1));
		}
		if(game.getGrid()[rij][kolom] == status.player2) {
			button.setIcon(new ImageIcon(afbSpeler2));
		}
		else {
			button.setIcon(new ImageIcon(afbLeeg));
		}
		
	}
	
	private void laadAfbeeldingen() {
		try {
			Image leeg = ImageIO.read(new FileInputStream("src/assets/cell_empty.png"));
			Image speler1 = ImageIO.read(new FileInputStream("src/assets/cell_player2.png"));
			Image speler2 = ImageIO.read(new FileInputStream("src/assets/cell_player1.png"));
			
			afbSpeler1 = speler1.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
			afbSpeler2 = speler2.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
			afbLeeg = leeg.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void displayGame() {
		for (int rij = 0; rij<grid.length; rij++){
		     for (int kolom = 0; kolom<grid[rij].length; kolom++){
		    	 setButtonBackground(grid[rij][kolom], rij, kolom);
		     }
		}
	}
	
}
