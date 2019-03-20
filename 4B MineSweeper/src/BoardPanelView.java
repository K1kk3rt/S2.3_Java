import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class BoardPanelView extends JPanel implements Observer{
	
	//fields
	BoardGame game;
	
	//construct
	public BoardPanelView() {
		
		//create board game
		game = new BoardGame(this);
		
		//create panel for board and set layout, rows and columns
		GridLayout boardLayout = new GridLayout(game.getRows(), game.getColumns());
		setLayout(boardLayout);
		
		game.createGame();
		
		printGame();
		
		game.addObserver(this);
	}
	
	//implement Observer
	@Override
	public void update(Observable o, Object arg) {
		
		Zone zone;
		
		if (arg instanceof Zone) {
			zone = (Zone)arg;
			
			printZone(zone);
		}
	}
	
	public void printGame() {
		//loop through grid and define zone look
		for(Zone item : game.getGameGrid()) {
			printZone(item);
		}
	}
	
	private void printZone(Zone zone) {
		if (zone.getIsRevealed()) {
			if(!zone.getIsMarked()) {
				zone.setBackground(new Color(203, 204, 206));
			}
			if(zone.getIsMine()) {
				zone.setBackground(Color.BLACK);
			}
			if(zone.getNeighbours() > 0 && !zone.getIsMine() && !zone.getIsMarked()) {
				String count = String.valueOf(zone.getNeighbours());
				zone.setText(count);
			}
			if(zone.getIsMarked()) {
				zone.setBackground(Color.RED);
			}
		}
	}
}
