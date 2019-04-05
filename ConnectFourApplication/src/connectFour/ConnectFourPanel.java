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
	@Override
	public void update(Observable o, Object arg) {
		
		displayGame();
		
	}
	
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
	
	private void gewonnen() {
		view.setLabelGewonnen();
	}
	
	private void gelijkspel() {
		view.setLabelGelijkspel();
	}
	
	private void restart() {
		for (int rij = 0; rij<grid.length; rij++){
		     for (int kolom = 0; kolom<grid[rij].length; kolom++){
		    	 setButtonBackground(rij, kolom);
		     }
		}
	}
	
}
