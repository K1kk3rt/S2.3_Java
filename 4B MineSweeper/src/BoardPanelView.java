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
		
		game.createGrid();
		
		game.addObserver(this);
	}
	
	//implement Observer
	@Override
	public void update(Observable o, Object arg) {
		
		ZoneModel zone;
		
		if (arg instanceof ZoneModel) {
			zone = (ZoneModel)arg;
			
			printZone(zone);
			
		}
	}
	
	public void printGame() {
		//loop through grid and define zone look
		for(ZoneModel item : game.getGameGrid()) {
			printZone(item);
		}
	}
	
	private void printZone(ZoneModel zone) {
		
		if (zone.IsRevealed) {
			if(!zone.IsMarked) {
				zone.setBackground(new Color(203, 204, 206));
			}
			if(zone.IsMine) {
				zone.setBackground(Color.BLACK);
			}
			if(zone.Neighbours > 0 && !zone.IsMine && !zone.IsMarked) { 
				String count = String.valueOf(zone.Neighbours);
				zone.setText(count);
			}
			if(zone.IsMarked) {
				zone.setBackground(Color.RED);
			}
		}
	}
}
