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
	private BufferedImage afbLeeg;
	private BufferedImage afbSpeler1;
	private BufferedImage afbSpeler2;
	
	
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
		
		Image speler1 = afbSpeler1.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
		Image speler2 = afbSpeler2.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
		Image leeg = afbLeeg.getScaledInstance(BUTTONWIDTH, BUTTONHEIGHT,  java.awt.Image.SCALE_SMOOTH);
		
		if(game.getGrid()[rij][kolom] == status.player1) {
			button.setIcon(new ImageIcon(speler1));
		}
		if(game.getGrid()[rij][kolom] == status.player2) {
			button.setIcon(new ImageIcon(speler2));
		}
		else {
			button.setIcon(new ImageIcon(leeg));
		}
		
	}
	
	private void laadAfbeeldingen() {
		try {
			afbLeeg = ImageIO.read(new FileInputStream("src/assets/cell_empty.png"));
			afbSpeler1 = ImageIO.read(new FileInputStream("src/assets/cell_player1.png"));
			afbSpeler2 = ImageIO.read(new FileInputStream("src/assets/cell_player2.png"));
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
