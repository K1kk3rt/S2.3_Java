import java.awt.Color;
import java.util.Random;

public class BoardGame {
	
	BoardPanelView View;
	Zone[] gameGrid;
	final int AmountOfBombs = 150;
	
	//construct
	public BoardGame(BoardPanelView view) {
		
		View = view;
		
		//generate game grid
		gameGrid = new Zone[view.rows * view.columns];
		createGame();
	}
	
	private void createGame() {
		
		//loop through grid and create instance of zone for every Zone item in grid
		int i = 0; 
		for(int y = 0; y < View.rows; y++) {
			for (int x = 0; x < View.columns; x++) {
				gameGrid[i] = new Zone(x,y);
				//gameGrid[i].addActionListener(new ZoneClickListener(View));
				gameGrid[i].addMouseListener(new ZoneClickListener(View));
				View.add(gameGrid[i]);
				i++;
			}
		}
		
		//pick available bomb spots, save in array
		i=0;
		boolean bombOptions[][] = new boolean[View.columns][View.rows];
		for(int y = 0; y < View.rows; y++) {
			for (int x = 0; x < View.columns; x++) {
				bombOptions[x][y] = true;
			}
		}
		
		//loop through bombs and define bomb placement
		i=0;
		while (i < AmountOfBombs) {
			Random rand = new Random();
			int x = rand.nextInt(View.columns);
			int y = rand.nextInt(View.rows);
			
			if(bombOptions[x][y]) {
				gameGrid[getGridIndex(x,y)].IsMine = true;
				bombOptions[x][y] = false;
			}
			
			i++;
		}

		//loop through grid and define neighbor count
		i=0; 
		for(int y = 0; y < View.rows; y++) {
			for (int x = 0; x < View.columns; x++) {
				gameGrid[getGridIndex(x,y)].Neighbours = countNeighbours(x,y);
			}
		}
	}
	
	private int countNeighbours(int x, int y) {
		int total = 0;
		
		//offset array
		int[][] neighbors = {
		    {-1, -1}, {-1, 0}, {-1, +1},
		    { 0, -1},          { 0, +1},
		    {+1, -1}, {+1, 0}, {+1, +1}};
			
		//use offset array to determine alive neighbors
		for (int[] offset : neighbors) {
			int xOff = x + offset[0];
			int yOff = y + offset[1];
			
			//make sure offset is not out of array bounds
			if(xOff >= 0 && xOff < View.columns && yOff >= 0 && yOff < View.rows) {
				if (gameGrid[getGridIndex(xOff, yOff)].IsMine) {
					total++;
			    }
			}
			
		}
		
		return total;
	}
	
	public int getGridIndex(int xPos, int yPos) {
		int i = 0; 
		for(int y = 0; y < View.rows; y++) {
			for (int x = 0; x < View.columns; x++) {
				if(gameGrid[i].X == xPos && gameGrid[i].Y == yPos) {
					return i;
				}
				i++;
			}
		}
		
		return -1;
	}
}
