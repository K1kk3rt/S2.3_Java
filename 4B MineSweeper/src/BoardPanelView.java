import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BoardPanelView extends JPanel{
	
	//fields
	BoardGame game;
	final int columns = 40;
	final int rows = 30;
	
	//construct
	public BoardPanelView() {
		
		//create panel for board and set layout, rows and columns
		GridLayout boardLayout = new GridLayout(rows, columns);
		setLayout(boardLayout);
		
		//create board game and print
		game = new BoardGame(this);
	}
	
	public void printGame() {
		//loop through grid and define zone look
		for(Zone item : game.gameGrid) {
			if (item.IsRevealed) {
				item.setBackground(Color.GRAY);
				if(item.IsMine) {
					item.setBackground(Color.BLACK);
				}
				if(item.Neighbours > 0 && !item.IsMine) {
					String count = String.valueOf(item.Neighbours);
					item.setText(count);
				}
				if(item.IsMarked) {
					item.setBackground(Color.RED);
				}
			}
		}
	}
	
	public void revealZones(Zone zone) {
		//get clicked grid index position
		int i = game.getGridIndex(zone.X, zone.Y);
		
		//reveal zone
		game.gameGrid[i].IsRevealed = true;
		
		//reveal more zones if it doesn't have neighbor mines
		if(game.gameGrid[i].Neighbours == 0) {
			revealNeighbourZones(zone);
		}
		
		printGame();
	}
	
	public void revealNeighbourZones(Zone zone) {
		
		for (int xoff = -1; xoff <= 1; xoff++) {
			for (int yoff = -1; yoff <= 1; yoff++) {
				int x = zone.X + xoff;
				int y = zone.Y + yoff;
				int i = game.getGridIndex(x, y);
				
				if(x>-1 && x < columns && y > -1 && y < rows) {
					if(!game.gameGrid[i].IsMine && !game.gameGrid[i].IsRevealed) {
						revealZones(game.gameGrid[i]);
					}
				}
			}
		}
	}
	
	public void markZone(Zone zone) {
		int i = game.getGridIndex(zone.X, zone.Y);
		game.gameGrid[i].IsMarked = true;
		game.gameGrid[i].IsRevealed = true;
		
		printGame();
	}
	
	public void gameOver() {
		//loop through game grid and set isRevealed true
		for(Zone item : game.gameGrid) {
			item.IsRevealed = true;
		}
		
		printGame();
	}
}
